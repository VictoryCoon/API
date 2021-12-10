package com.alive.networks.worker.network.url;

import com.alive.networks.util.JsonUtil;
import com.alive.networks.worker.network.RequestEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alive.networks.constants.ResponseCode;
import com.alive.networks.db.dao.ApiDao;
import com.alive.networks.db.vo.ApiInfoVo;
import com.alive.networks.db.vo.ResponseVO;

import io.netty.channel.ChannelHandlerContext;

public class InsertBoardInfoUrl extends RequestEngine {

	private static Logger logger = LoggerFactory.getLogger(InsertBoardInfoUrl.class);

	@Override
	public String workProcess(String jsonString, ChannelHandlerContext ctx) throws Exception {
		ResponseVO usrRes = new ResponseVO();
		ApiInfoVo vo = null;
		ApiDao dao = new ApiDao();
		//1.jsonString 파싱
		try {
			vo = JsonUtil.unmarshallingJson(jsonString, ApiInfoVo.class);
			logger.info("UnmarshallingJson Result : "+vo);
		} catch(Exception e) {
			logger.error("Request Json String Parsing ERROR",e);
			//데이터 오류
			usrRes.setCode(ResponseCode.E003_CODE);
			usrRes.setDesc(ResponseCode.E003_DESC);
			return makeResponseString(usrRes);  // Fail 응답
		}

		//2.필수파라메터 체크
		if ( vo == null ){
			//데이터 오류
			logger.error("Request Json String Parsing ERROR");
			usrRes.setCode(ResponseCode.E003_CODE);
			usrRes.setDesc(ResponseCode.E003_DESC);
			return makeResponseString(usrRes);  // Fail 응답
		} else {
			if(vo.getBoardTitle() == null){
				usrRes.setCode(ResponseCode.EUI0_CODE);
				usrRes.setDesc(ResponseCode.EUI0_DESC+" : vo.getBoardTitle()");
				return makeResponseString(usrRes);
			}

			if(vo.getBoardTitle().length() > 255){
				usrRes.setCode(ResponseCode.EUI1_CODE);
				usrRes.setDesc(ResponseCode.EUI1_DESC+" : vo.getBoardTitle()");
				return makeResponseString(usrRes);
			}

			if(vo.getBoardContent() == null){
				usrRes.setCode(ResponseCode.EUI0_CODE);
				usrRes.setDesc(ResponseCode.EUI0_DESC+" : vo.getBoardContent()");
				return makeResponseString(usrRes);
			}

			if(vo.getBoardContent().length() > 4000){
				usrRes.setCode(ResponseCode.EUI1_CODE);
				usrRes.setDesc(ResponseCode.EUI1_DESC+" : vo.getBoardContent()");
				return makeResponseString(usrRes);
			}

			ApiDao apiDao = new ApiDao();
			//3. DB 작업 요청
			if (apiDao.insertBoardInfo(vo)) {
				usrRes.setCode(ResponseCode.OK);
				usrRes.setDesc(ResponseCode.OK_DESC);
			} else {
				//DB 등록 실패
				usrRes.setCode(ResponseCode.IOFE_CODE);
				usrRes.setDesc(ResponseCode.IOFE_DESC);
			}
			return makeResponseString(usrRes);  // Success 응답
		}
	}
}
