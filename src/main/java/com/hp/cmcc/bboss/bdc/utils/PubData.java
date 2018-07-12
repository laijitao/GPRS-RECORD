package com.hp.cmcc.bboss.bdc.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class PubData {

	public enum TimeStrFmt {
		Fmt23, // yyyy-MM-dd HH:mm:ss.SSS
		Fmt19, // yyyy-MM-dd HH:mm:ss
		Fmt18, // yyyyMMddHHmmss.SSS
		Fmt17, // yyyyMMddHHmmssSSS
		Fmt16, // yyMMddHHmmss.SSS
		Fmt14, // yyyyMMddHHmmss
		Fmt12, // yyMMddHHmmss
		Fmt10, // yyyy-MM-dd
		Fmt8,  // yyyyMMdd
		Fmt6   // yyMMdd
	}
	
	public enum NoValidService{
		NOTIFY_INFO,
		NOTIFY_RESULT;
	}
	
	public static int[] ProvinceId = {
			000,100,200,210,220,
			230,240,250,270,280,
			290,311,351,371,431,
			451,471,531,551,571,
			591,731,771,791,851,
			871,891,898,931,951,
			971,991
	};

	
	public static final int DRCT_GPRS_FIELDS = 14;
	
	public static final int GPRS_NEXT_DAY = 2;
	
	public static final String NOTIFY_INFO = "NOTIFY_INFO";
	public static final String NOTIFY_RESULT = "NOTIFY_RESULT";

	public static final String TMFMT23 = "yyyy-MM-dd HH:mm:ss.SSS";
	public static final String TMFMT19 = "yyyy-MM-dd HH:mm:ss";
	public static final String TMFMT18 = "yyyyMMddHHmmss.SSS";
	public static final String TMFMT17 = "yyyyMMddHHmmssSSS";
	public static final String TMFMT16 = "yyMMddHHmmss.SSS";
	public static final String TMFMT14 = "yyyyMMddHHmmss";
	public static final String TMFMT12 = "yyMMddHHmmss";
	public static final String TMFMT10 = "yyyy-MM-dd";
	public static final String TMFMT8  = "yyyyMMdd";
	public static final String TMFMT6  = "yyMMdd";
	
	public static final Pattern ExpTMFMT23 = Pattern.compile("^[0-9]{4}[^0-9]([0-9]{2}[^0-9]){5}[0-9]{3}");
	public static final Pattern ExpTMFMT19 = Pattern.compile("^[0-9]{4}[^0-9]([0-9]{2}[^0-9]){4}[0-9]{2}");
	public static final Pattern ExpTMFMT18 = Pattern.compile("^[0-9]{14}[^0-9][0-9]{3}");
	public static final Pattern ExpTMFMT17 = Pattern.compile("^[0-9]{17}");
	public static final Pattern ExpTMFMT16 = Pattern.compile("^[0-9]{12}[^0-9][0-9]{3}");
	public static final Pattern ExpTMFMT14 = Pattern.compile("^[0-9]{14}");
	public static final Pattern ExpTMFMT12 = Pattern.compile("^[0-9]{12}");
	public static final Pattern ExpTMFMT10 = Pattern.compile("^[0-9]{4}[^0-9][0-9]{2}[^0-9][0-9]{2}");
	public static final Pattern ExpTMFMT8  = Pattern.compile("^[0-9]{8}");
	public static final Pattern ExpTMFMT6  = Pattern.compile("^[0-9]{6}");

	public static final DateTimeFormatter TMDF23 = DateTimeFormatter.ofPattern(TMFMT23);
	public static final DateTimeFormatter TMDF19 = DateTimeFormatter.ofPattern(TMFMT19);
	public static final DateTimeFormatter TMDF18 = DateTimeFormatter.ofPattern(TMFMT18);
	public static final DateTimeFormatter TMDF17 = DateTimeFormatter.ofPattern(TMFMT17);
	public static final DateTimeFormatter TMDF16 = DateTimeFormatter.ofPattern(TMFMT16);
	public static final DateTimeFormatter TMDF14 = DateTimeFormatter.ofPattern(TMFMT14);
	public static final DateTimeFormatter TMDF12 = DateTimeFormatter.ofPattern(TMFMT12);
	public static final DateTimeFormatter DTDF10 = DateTimeFormatter.ofPattern(TMFMT10);
	public static final DateTimeFormatter DTDF8  = DateTimeFormatter.ofPattern(TMFMT8);
	public static final DateTimeFormatter DTDF6  = DateTimeFormatter.ofPattern(TMFMT6);
	
	public static final ZoneId LocTZ = ZoneId.systemDefault();
	public static final long UNIX_EPOCH_MILLIS = 0;
	public static final LocalDateTime UNIX_EPOCH_DATE = LocalDateTime.ofEpochSecond(0, 0, ZoneOffset.UTC);
}
