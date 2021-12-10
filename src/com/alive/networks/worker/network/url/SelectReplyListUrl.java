package com.alive.networks.worker.network.url;

import com.alive.networks.constants.ResponseCode;
import com.alive.networks.db.dao.ApiDao;
import com.alive.networks.db.vo.ApiInfoVo;
import com.alive.networks.db.vo.ResponseVO;
import com.alive.networks.util.JsonUtil;
import com.alive.networks.worker.network.RequestEngine;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SelectReplyListUrl extends RequestEngine {

	private static Logger logger = LoggerFactory.getLogger(SelectReplyListUrl.class);


	@Override
	public String workProcess(String jsonString, ChannelHandlerContext ctx) throws Exception {
		ResponseVO usrRes = new ResponseVO();
		Map<String,Object> resMap = new HashMap<String,Object>();
		ApiInfoVo vo = new ApiInfoVo();

		try {
			vo.setBoardSid(reqParam.get("boardSid").toString());
		} catch (Exception e) {
			logger.error("Request Json String Parsing ERROR", e);
			//데이터 오류
			usrRes.setCode(ResponseCode.E003_CODE);
			usrRes.setDesc(ResponseCode.E003_DESC);
			return makeResponseString(usrRes);  // Fail 응답
		}

		if (vo == null) {
			logger.error("Request Json String Parsing ERROR");
			usrRes.setCode(ResponseCode.E003_CODE);
			usrRes.setDesc(ResponseCode.E003_DESC);
			return makeResponseString(usrRes);  // Fail 응답
		} else {
			ApiDao apiDao = new ApiDao();
			if(vo.getBoardSid() == null){
				usrRes.setCode(ResponseCode.EUI0_CODE);
				usrRes.setDesc(ResponseCode.EUI0_DESC+" : vo.getBoardSid()");
				return makeResponseString(usrRes);
			}
			if(vo.getBoardSid().length() > 24){
				usrRes.setCode(ResponseCode.EUI1_CODE);
				usrRes.setDesc(ResponseCode.EUI1_DESC+" : vo.getBoardSid()");
				return makeResponseString(usrRes);
			}

			List<ApiInfoVo> boardReplyList = apiDao.selectBoardReplyList(vo);

			if (boardReplyList != null) {
				resMap.put("code",ResponseCode.OK);
				resMap.put("desc",ResponseCode.OK_DESC);
				resMap.put("boardReplyList", boardReplyList);
			} else {
				usrRes.setCode(ResponseCode.EMTY_CODE);
				usrRes.setDesc(ResponseCode.EMTY_DESC);
			}
			String result = "{}";
			try {
				result = JsonUtil.marshallingJson(resMap);
				logger.debug("ResultString : "+result);
			} catch(Exception e){
				logger.error("makeResponseString Json Error", e);
			}

			return result;  // Success 응답
		}
	}
}
