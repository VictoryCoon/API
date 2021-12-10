package com.alive.networks.worker.network.url;

import com.alive.networks.constants.ResponseCode;
import com.alive.networks.db.dao.ApiDao;
import com.alive.networks.db.vo.ApiInfoVo;
import com.alive.networks.db.vo.ResponseVO;
import com.alive.networks.util.JsonUtil;
import com.alive.networks.worker.network.RequestEngine;
import io.netty.channel.ChannelHandlerContext;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InsertUserInfoUrl extends RequestEngine {

	private static Logger logger = LoggerFactory.getLogger(InsertUserInfoUrl.class);


	@Override
	public String workProcess(String jsonString, ChannelHandlerContext ctx) throws Exception {
		ResponseVO usrRes = new ResponseVO();

		ApiInfoVo vo = null;
		try {
			vo = JsonUtil.unmarshallingJson(jsonString, ApiInfoVo.class);
		} catch (Exception e) {
			logger.error("Request Json String Parsing ERROR\n", e);
			usrRes.setCode(ResponseCode.E003_CODE);
			usrRes.setDesc(ResponseCode.E003_DESC);
			return makeResponseString(usrRes);  // Fail 응답
		}

		if (vo == null) {
			//데이터 오류
			logger.error("Request Json String Parsing ERROR");
			usrRes.setCode(ResponseCode.EUI0_CODE);
			usrRes.setDesc(ResponseCode.EUI0_CODE+" : all()");
			return makeResponseString(usrRes);
		} else {
			ApiDao apiDao = new ApiDao();
			if(vo.getUserId() == null){
				usrRes.setCode(ResponseCode.EUI0_CODE);
				usrRes.setDesc(ResponseCode.EUI0_DESC+" : vo.getUserId()");
				return makeResponseString(usrRes);
			}

			if(vo.getUserId().length() > 32){
				usrRes.setCode(ResponseCode.EUI1_CODE);
				usrRes.setDesc(ResponseCode.EUI1_DESC+" : vo.getUserId()");
				return makeResponseString(usrRes);
			}

			if(vo.getUserName() == null){
				usrRes.setCode(ResponseCode.EUI0_CODE);
				usrRes.setDesc(ResponseCode.EUI0_DESC+" : vo.getUserName()");
				return makeResponseString(usrRes);
			}

			if(vo.getUserName().length() > 20){
				usrRes.setCode(ResponseCode.EUI1_CODE);
				usrRes.setDesc(ResponseCode.EUI1_DESC+" : vo.getUserName()");
				return makeResponseString(usrRes);
			}

			if(vo.getUserNick() == null){
				usrRes.setCode(ResponseCode.EUI0_CODE);
				usrRes.setDesc(ResponseCode.EUI0_DESC+" : vo.getUserPhone()");
				return makeResponseString(usrRes);
			}

			if(vo.getUserNick().length() > 20){
				usrRes.setCode(ResponseCode.EUI1_CODE);
				usrRes.setDesc(ResponseCode.EUI1_DESC+" : vo.getUserNick()");
				return makeResponseString(usrRes);
			}

			if(vo.getUserPhone() == null){
				usrRes.setCode(ResponseCode.EUI0_CODE);
				usrRes.setDesc(ResponseCode.EUI0_DESC+" : vo.getUserPhone()");
				return makeResponseString(usrRes);
			}

			if(vo.getUserPhone().length() > 11){
				usrRes.setCode(ResponseCode.EUI1_CODE);
				usrRes.setDesc(ResponseCode.EUI1_DESC+" : vo.getUserPhone()");
				return makeResponseString(usrRes);
			}

			if(vo.getUserBirth() == null){
				usrRes.setCode(ResponseCode.EUI0_CODE);
				usrRes.setDesc(ResponseCode.EUI0_DESC+" : vo.getUserBirth()");
				return makeResponseString(usrRes);
			}

			if(vo.getUserBirth().length() > 8){
				usrRes.setCode(ResponseCode.EUI1_CODE);
				usrRes.setDesc(ResponseCode.EUI1_DESC+" : vo.getUserBirth()");
				return makeResponseString(usrRes);
			}

			if(vo.getUserEmailId() == null){
				usrRes.setCode(ResponseCode.EUI0_CODE);
				usrRes.setDesc(ResponseCode.EUI0_DESC+" : vo.getUserEmailId()");
				return makeResponseString(usrRes);
			}

			if(vo.getUserEmailId().length() > 64){
				usrRes.setCode(ResponseCode.EUI1_CODE);
				usrRes.setDesc(ResponseCode.EUI1_DESC+" : vo.getUserEmailId()");
				return makeResponseString(usrRes);
			}

			if(vo.getUserEmailPf() == null){
				usrRes.setCode(ResponseCode.EUI0_CODE);
				usrRes.setDesc(ResponseCode.EUI0_DESC+" : vo.getUserEmailId()");
				return makeResponseString(usrRes);
			}

			if(vo.getUserEmailPf().length() > 32){
				usrRes.setCode(ResponseCode.EUI1_CODE);
				usrRes.setDesc(ResponseCode.EUI1_DESC+" : vo.getUserEmailPf()");
				return makeResponseString(usrRes);
			}

			if(vo.getUserGender() == null){
				usrRes.setCode(ResponseCode.EUI0_CODE);
				usrRes.setDesc(ResponseCode.EUI0_DESC+" : vo.getUserGender()");
				return makeResponseString(usrRes);
			}

			if(vo.getUserGender().length() > 1){
				usrRes.setCode(ResponseCode.EUI1_CODE);
				usrRes.setDesc(ResponseCode.EUI1_DESC+" : vo.getUserGender()");
				return makeResponseString(usrRes);
			}

			ApiInfoVo existChecker = apiDao.selectUserExistCheck(vo);

			if(existChecker.getCheckParam().equals("F")){
				usrRes.setCode(ResponseCode.EUI2_CODE);
				usrRes.setDesc(ResponseCode.EUI2_DESC
						+"\n existChecker.getUserId()    : "+existChecker.getUserId()
						+"\n existChecker.getUserNick()  : "+existChecker.getUserNick()
						+"\n existChecker.getUserName()  : "+existChecker.getUserName()
						+"\n existChecker.getUserBirth() : "+existChecker.getUserBirth()
						+"\n existChecker.getUserPhone() : "+existChecker.getUserPhone()
				);
				return makeResponseString(usrRes);  // Fail 응답
			}

			//3. DB 작업 요청
			if (apiDao.insertUserInfo(vo) == true) {
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
