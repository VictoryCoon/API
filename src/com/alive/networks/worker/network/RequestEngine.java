package com.alive.networks.worker.network;

import com.alive.networks.util.JsonUtil;
import io.netty.channel.ChannelHandlerContext;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alive.networks.db.vo.ResponseVO;

abstract public class RequestEngine {

	abstract protected String workProcess(String jsonString, ChannelHandlerContext ctx) throws Exception;
	private static Logger logger = LoggerFactory.getLogger(RequestEngine.class);
	
	protected HashMap<String, String> reqParam = null;

	public HashMap<String, String> getReqParam() {
		return reqParam;
	}
	public void setReqParam(HashMap<String, String> reqParam) {
		this.reqParam = reqParam;
	}
	
	
	protected String makeResponseString(ResponseVO usrRes){
		String result = "{}";
		
		try {
			//result = "["+usrRes.getCode()+"]"+String.format("%-20s",usrRes.getDesc());//JsonUtil.marshallingJson(usrRes);
			result = JsonUtil.marshallingJson(usrRes);
		} catch(Exception e){
			logger.error("makeResponseString Json Error", e);
		}
		return result;
	}
}
