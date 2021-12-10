package com.alive.networks.util;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
public class TimeUtil {

	// 시간 계산 연산
	public static String getAddTime(String format, String strDate, int addType, int addValue) throws Exception{
		SimpleDateFormat dateForm = new SimpleDateFormat(format);
		Date date = dateForm.parse(strDate);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		//Calendar.DAY_OF_MONTH
		cal.add(addType, addValue);

		return dateForm.format(cal.getTime());
	}


	/*  @ getTime
	 *  Get Current Time
	 *  Request : format(yyyyMMdd HHmmss)
	 *  Result : 20141020
	 *  BY SHINEC
	 */
	public static String getTime(String format) throws Exception{
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.KOREA);
		return sdf.format(date);
	}

	/*	@ getFileLastTime
	 *  Get LastTime for File
	 *  BY SHINEC
	 */
	public static String getFileLastTime(String format, String filePath) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.KOREA);
		File file = new File(filePath);
		Date date = new Date(file.lastModified());
		return sdf.format(date);
	}

	/*
	 * getDateToSecond
	 * arg1_format : yyyyMMddhhmmss
	 * arg2_time : 20141210183042
	 * Result : 3249823894 second
	 * BY SHINEC
	 */
	public static long convertDateToSecond(String format, String time) throws Exception{
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		sdf.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
		date = sdf.parse(time);
		return date.getTime();
	}

	/*
	 * @ convertMonthDigit
	 * Month String Change to Digit
	 * Before : Jan
	 * After : 01
	 * BY SHINEC
	 */
	public static String convertMonthDigit(String month){
		if(month.equalsIgnoreCase("Jan")){
			return "01";
		}else if(month.equalsIgnoreCase("Feb")){
			return "02";
		}else if(month.equalsIgnoreCase("Mar")){
			return "03";
		}else if(month.equalsIgnoreCase("Apr")){
			return "04";
		}else if(month.equalsIgnoreCase("May")){
			return "05";
		}else if(month.equalsIgnoreCase("Jun")){
			return "06";
		}else if(month.equalsIgnoreCase("Jul")){
			return "07";
		}else if(month.equalsIgnoreCase("Aug")){
			return "08";
		}else if(month.equalsIgnoreCase("Sep")){
			return "09";
		}else if(month.equalsIgnoreCase("Oct")){
			return "10";
		}else if(month.equalsIgnoreCase("Nov")){
			return "11";
		}else if(month.equalsIgnoreCase("Dec")){
			return "12";
		}else {
			return "00";
		}
	}

	/* @ convertLinuxDateFormat
	 * Format Chage For Linux Date Type
	 * Before : 'Thu Nov 20 09:27:15 2014'
	 * Before : 'Thu Jan  1 21:04:36 2015'
	 * After : '20141120092715'
	 * By SHINEC
	 */
	public static String convertLinuxDateFormat(String format) throws Exception {
		String[] datePiece = format.split("\\s+");

		String year = datePiece[4];
		String month = convertMonthDigit(datePiece[1]);
		String day = String.format("%02d", Integer.parseInt(datePiece[2]));

		String[] hhmmss = datePiece[3].split(":");
		String hour = String.format("%02d", Integer.parseInt(hhmmss[0]));
		String min = String.format("%02d", Integer.parseInt(hhmmss[1]));
		String sec =  String.format("%02d", Integer.parseInt(hhmmss[2]));

		return (year + month + day + hour + min + sec);
	}

	// 밀리세컨드를 포멧 데이트로 변경
	public static String convertMileSecToDateFormat(String format, long mileSec) throws Exception{
		DateFormat df = new SimpleDateFormat(format);
		return df.format(mileSec);
	}

	// 파일 생성시간 밀리세컨드가져오기
	public static long getFileCreateTimeForMSec(File file) throws Exception{
		Path path = Paths.get(file.getCanonicalPath());
		BasicFileAttributes fileInfo = Files.readAttributes(path, BasicFileAttributes.class);
		return fileInfo.creationTime().toMillis();
	}

	// 파일 생성 시간 날짜포멧 가져오기
	public static String getFileCreateTimeForDate(File file, String wantReturnFormat) throws Exception{
		Path path = Paths.get(file.getCanonicalPath());
		BasicFileAttributes fileInfo = Files.readAttributes(path, BasicFileAttributes.class);
		return convertMileSecToDateFormat(wantReturnFormat, fileInfo.creationTime().toMillis());
	}

	public static Date getFormatTime(String time, String format) throws ParseException{
		SimpleDateFormat formater = new SimpleDateFormat(format);
		return formater.parse(time);
	}

	public static long getTime(String currentTime, String format) throws ParseException{
		SimpleDateFormat formater = new SimpleDateFormat(format);
		Date parseTime = formater.parse(currentTime);
		return parseTime.getTime();
	}

	public static long timeAdd(String tmp_time, long interval, String format) throws ParseException{
		long time = getTime(tmp_time, format);
		return time + (interval * 1000);
	}
}
