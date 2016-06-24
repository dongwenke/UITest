package com.dzf.test.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtil {
	/*
	 * 禁止实例化
	 */
	private DateUtil() {
	}

	public static String getCurrentMonth() {
		return new SimpleDateFormat("yyyy-MM").format(new Date());
	}

	public static String getCurrentDay() {
		return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	}

	public static String getCurrentSecond() {
		return new SimpleDateFormat("yyMMdd-hhms").format(new Date());
	}

	public static String getCurrentDate(String formate) {
		return new SimpleDateFormat(formate).format(new Date());
	}

	public static List<String> dateToStrList(String beginDate, String endDate) throws ParseException {
		List<String> strDateList = new ArrayList<String>();

		Calendar begin = Calendar.getInstance();
		Calendar end = Calendar.getInstance();

		begin.setTime(new SimpleDateFormat("yyyy-MM").parse(beginDate));
		end.setTime(new SimpleDateFormat("yyyy-MM").parse(endDate));

		int sum = (end.get(Calendar.YEAR) - begin.get(Calendar.YEAR)) * 12
				+ (end.get(Calendar.MONTH) - begin.get(Calendar.MONTH));

		strDateList.add(beginDate);

		Calendar tmp = begin;
		for (int i = 0; i < sum; i++) {
			tmp.add(Calendar.MONTH, 1);

			String strDate = new SimpleDateFormat("yyyy-MM").format(tmp.getTime());

			strDateList.add(strDate);
		}

		System.out.println(strDateList);

		return strDateList;
	}

}
