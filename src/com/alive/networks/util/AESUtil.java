//package com.alive.networks.util;
//
//import java.util.Base64;
//import java.util.Base64.Encoder;
//import java.util.Base64.Decoder;
//
//import javax.crypto.Cipher;
//import javax.crypto.spec.IvParameterSpec;
//import javax.crypto.spec.SecretKeySpec;
//
//public class AESUtil {
//
//	public static final String KEY_PASS = "FLK_PUSH";
//
//	public static String Decrypt(String text, String key) throws Exception{
//		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
//		byte[] keyBytes= new byte[16];
//		byte[] b= key.getBytes("UTF-8");
//		int len= b.length;
//		if (len > keyBytes.length) len = keyBytes.length;
//		System.arraycopy(b, 0, keyBytes, 0, len);
//		SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
//		IvParameterSpec ivSpec = new IvParameterSpec(keyBytes);
//		cipher.init(Cipher.DECRYPT_MODE,keySpec,ivSpec);
//
//		Decoder decoder = Base64.getDecoder();
//		byte [] results = cipher.doFinal(decoder.decode(text));
//		return new String(results,"UTF-8");
//	}
//
//	public static String Encrypt(String text, String key) throws Exception{
//		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
//		byte[] keyBytes= new byte[16];
//		byte[] b= key.getBytes("UTF-8");
//		int len= b.length;
//		if (len > keyBytes.length) len = keyBytes.length;
//		System.arraycopy(b, 0, keyBytes, 0, len);
//		SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
//		IvParameterSpec ivSpec = new IvParameterSpec(keyBytes);
//		cipher.init(Cipher.ENCRYPT_MODE,keySpec,ivSpec);
//
//		byte[] results = cipher.doFinal(text.getBytes("UTF-8"));
//		Encoder encoder = Base64.getEncoder();
//		return encoder.encodeToString(results);
//	}
//}