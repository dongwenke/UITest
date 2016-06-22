package com.dzf.test.util;

public class StringUtil {

	public static boolean notNullAndEmpty(String s) {
		return null != s && !"".equals(s);
	}

	public static String[] stringToStringArray(String s) {
		
		if(s == null){
			return new String[0];
		}
		
		String[] array = new String[s.length()];
		
		for (int i = 0; i < s.length(); i++) {
			array[i] = s.substring(i, i + 1);
		}
		return array;
	}
}
