package com.san.mpos.common.util;

import java.util.Map;

public class MposStringUtil {
	@SuppressWarnings("rawtypes")
	public static String getString(Map map,String key){
		return map.get(key).toString();
	}
	@SuppressWarnings("rawtypes")
	public static int getInt(Map map,String key){
		return Integer.parseInt(map.get(key).toString());
	}
	@SuppressWarnings("rawtypes")
	public static boolean getBoolean(Map map,String key){
		return Boolean.parseBoolean(map.get(key).toString());
	}
}
