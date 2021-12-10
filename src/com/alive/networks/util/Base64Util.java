package com.alive.networks.util;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;




public class Base64Util {
	public static String base64Encoding(String data){
		String encodedData = null;
		try {
			byte[] dataBytes = data.getBytes("UTF-8");
			encodedData = new String(Base64.encodeBase64(dataBytes));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return encodedData;
	}
	public static String base64Decoding(String data) {
		String decodedData = null;
		decodedData = new String(Base64.decodeBase64(data.getBytes()));
		return decodedData;
	}
}
