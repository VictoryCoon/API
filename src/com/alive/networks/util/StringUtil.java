package com.alive.networks.util;

import java.io.UnsupportedEncodingException;

public class StringUtil {

	public static String lPadding(String s, int size, char paddingChar) {
		int gap = size - s.length();
		if (gap <= 0) {
			return s;
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < gap; i++) {
			sb.append(paddingChar);
		}

		sb.append(s);
		return sb.toString();
	}

	public static String lpad(String data, int count, String addString) {
		String tmpData = (data == null) ? "" : data;
		if (tmpData.length() >= count) {
			return tmpData;
		} else {
			return lpad(addString + tmpData, count, addString);
		}
	}

	public static String cutFirstStrInByte(String str, int endIndex) {
		StringBuffer sbStr = new StringBuffer(endIndex);
		int iTotal = 0;
		for (char c : str.toCharArray()) {
			try {
				iTotal += String.valueOf(c).getBytes("UTF-8").length;
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			if (iTotal > endIndex) {
				break;
			}
			sbStr.append(c);
		}
		return sbStr.toString();
	}
}



