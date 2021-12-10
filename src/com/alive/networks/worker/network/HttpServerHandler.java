package com.alive.networks.worker.network;
import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_LENGTH;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alive.networks.worker.network.url.*;
import io.netty.handler.timeout.ReadTimeoutException;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alive.networks.constants.Constant;
import com.alive.networks.constants.ResponseCode;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.LastHttpContent;
import io.netty.handler.codec.http.QueryStringDecoder;


public class HttpServerHandler extends ChannelInboundHandlerAdapter {

	private Logger logger = LoggerFactory.getLogger(HttpServerHandler.class);
	private HttpRequest http = null;
	private HttpContent content = null;
    private StringBuffer fullContent;
    private String method ="";
    private RequestEngine reqEngine = null;
    boolean isKeep = false;
    private HashMap<String, String> reqParam = new HashMap<String, String>();
    
	@Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		boolean isKeep = false;
		
		if(msg instanceof HttpRequest){
			http = (HttpRequest)msg;
			if(!http.getDecoderResult().isSuccess()){
				logger.error("Invalid Wrong Packet Failed");
				sendResponse(ctx, defaultResponseMake(ResponseCode.BAD_REQUEST, ResponseCode.BAD_REQUEST_DESC), false);
				return;
			}

			method = http.getMethod().toString();
			logger.info("Request Full Uri : [" + http.getUri() + "] , Method : [" + method +"]");
			//Url Check
			if ( http.getUri().contains(Constant.INSERT_USER_INFO_URL)){
				if ( method.equalsIgnoreCase("POST")){
					reqEngine	= new InsertUserInfoUrl();
				} else {
					logger.error("Http method is wrong - POST");
					sendResponse(ctx, defaultResponseMake(ResponseCode.E002_CODE, ResponseCode.E002_CODE), false);
					return;
				}
			} else if ( http.getUri().contains(Constant.INSERT_BOARD_INFO_URL)){
				if ( method.equalsIgnoreCase("POST")){
					reqEngine	= new InsertBoardInfoUrl();
				} else {
					logger.error("Http method is wrong - POST");
					sendResponse(ctx, defaultResponseMake(ResponseCode.E002_CODE, ResponseCode.E002_DESC), false);
					return;
				}
			} else if (http.getUri().contains(Constant.SELECT_BOARD_LIST_URL)) {
				if ( method.equalsIgnoreCase("GET")){
					reqEngine = new SelectBoardListUrl();
				} else {
					logger.error("Http method is wrong - GET");
					sendResponse(ctx, defaultResponseMake(ResponseCode.E002_CODE, ResponseCode.E002_DESC), false);
					return;
				}
			} else if (http.getUri().contains(Constant.SELECT_BOARD_REPLY_LIST_URL)) {
				if ( method.equalsIgnoreCase("GET")){
					reqEngine = new SelectReplyListUrl();
				} else {
					logger.error("Http method is wrong - GET");
					sendResponse(ctx, defaultResponseMake(ResponseCode.E002_CODE, ResponseCode.E002_DESC), false);
					return;
				}
			} else if (http.getUri().contains(Constant.SELECT_BOARD_RE_REPLY_LIST_URL)) {
				if ( method.equalsIgnoreCase("GET")){
					reqEngine = new SelectReReplyListUrl();
				} else {
					logger.error("Http method is wrong - GET");
					sendResponse(ctx, defaultResponseMake(ResponseCode.E002_CODE, ResponseCode.E002_DESC), false);
					return;
				}
			} else if (http.getUri().contains(Constant.SELECT_BOARD_DETAIL_VIEW_URL)) {
				if ( method.equalsIgnoreCase("GET")){
					reqEngine = new SelectBoardDetailViewUrl();
				} else {
					logger.error("Http method is wrong - GET");
					sendResponse(ctx, defaultResponseMake(ResponseCode.E002_CODE, ResponseCode.E002_DESC), false);
					return;
				}
			} else  {
				sendResponse(ctx, defaultResponseMake(ResponseCode.E001_CODE, ResponseCode.E001_DESC), false);
				return;
			}

			QueryStringDecoder qsd = new QueryStringDecoder(http.getUri());
			if(qsd != null) {
				Map<String, List<String>> uriMap = qsd.parameters();				
				for(String key : uriMap.keySet()){
					for(String value : uriMap.get(key)){
						reqParam.put(key, value);
						logger.info(key + " : " + value);
					}
				}
			}
			
			HttpHeaders head = http.headers();
			if(logger.isDebugEnabled()){
				logger.debug("======== Header Start==========");
				for (Map.Entry<String, String> h : head.entries()) {
    					logger.trace("Header: {} = {}", h.getKey(), h.getValue());
    			}
				logger.debug("======== Header End==========");
			}
			if(head.get("Connection") != null){
				isKeep = (head.get("Connection").equalsIgnoreCase("keep-alive"));

			}
			fullContent = new StringBuffer();
		}
		
		//Post Content Body 처리 - formbody
		if(msg instanceof HttpContent){
			if ( method.equalsIgnoreCase("POST") ) {
				content = (HttpContent)msg;
				if(content != null) { 
					if(content.getDecoderResult().isFailure()){
						logger.error("Http Bad Request"); 
						sendResponse(ctx, defaultResponseMake(ResponseCode.BAD_REQUEST, ResponseCode.BAD_REQUEST_DESC), false);
						return; // End
					}
				}
				
				ByteBuf buf = content.content();
				if ( logger.isDebugEnabled() ) {
					logger.debug(buf.toString(Charset.forName("utf-8")));
				}
				fullContent.append(buf.toString(Charset.forName("utf-8")));
			}
		}

		if(msg instanceof LastHttpContent){
			String body=fullContent.toString();
			//Post Content Body 처리 - formbody
			if ( method.equalsIgnoreCase("POST") ) {
				if (body.isEmpty()){
					logger.error("HTTP Post Body content is Null");
					sendResponse(ctx, defaultResponseMake(ResponseCode.E003_CODE, ResponseCode.E003_DESC), false);
					return;

				}
				
				if ( logger.isDebugEnabled() ) {
					logger.debug(body);
				}
			}
			
			//업무처리
			if ( reqEngine != null){
				reqEngine.setReqParam(reqParam);
				String responseStr = reqEngine.workProcess(body,ctx);
				sendResponse(ctx, responseStr, isKeep);
			} else {
				logger.error("Http URL is NotMatch"); 
				sendResponse(ctx, defaultResponseMake(ResponseCode.E999_CODE, ResponseCode.E999_DESC), isKeep);
			}
			
		}
    }
	
	private String defaultResponseMake(String responseCode, String responseDesc){
		return responseCode+String.format("%-20s",responseDesc);
	}
	
	private void sendResponse(ChannelHandlerContext ctx, String responseStr, boolean isKeep) {
		//FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.copiedBuffer(responseStr.getBytes(Charset.forName("euc-kr"))/*CharsetUtil.UTF_8*/));
		FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.copiedBuffer(responseStr.getBytes(CharsetUtil.UTF_8)));
		response.headers().set("Date", new java.util.Date(System.currentTimeMillis()).toString());
		response.headers().set(CONTENT_LENGTH, responseStr.getBytes(Charset.forName("euc-kr")).length);

		if(logger.isDebugEnabled()){
	        logger.debug("======== Response Header==========");
			for(String name : response.headers().names()){
				logger.debug(name + " : " + response.headers().get(name));
			}
		}

		ctx.write(response);
		ctx.flush();

		if(responseStr.length()>50) {
			logger.info("Response Body : [" + responseStr.substring(0,24) +"totalCount:"+responseStr.substring(24,29)+
                    ", resultCount:"+responseStr.substring(29,34) + ", personal information...]");
		}else{
			logger.info("Response Body : [" + response.content()/*.toString(CharsetUtil.UTF_8)*/ + "]");
		}
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
		try{
			if(cause instanceof IOException){
				if(logger.isDebugEnabled()){
					logger.debug("Client Connection Session Closed - " + ctx.channel());
				}
			}if(cause instanceof ReadTimeoutException){
				if(logger.isDebugEnabled()){
					logger.debug("Client Connection Session read timeout - " + ctx.channel());
				}
			}else{
				logger.error("Netty Error Cause", cause);
			}
			if(ctx != null){
				sendResponse(ctx, "Response 500 Error", false);
				ctx.close();
			}
		}catch(Exception e){
			logger.error("", e);
		}
	}
}

