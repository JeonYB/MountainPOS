package com.san.mpos.common.config;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.apache.commons.configuration.Configuration;

public class MposConfigUtils {
	public static boolean getBoolean(String name) {
		return getConfiguration().getBoolean(name);
	}
	
	public static boolean getBoolean(String name, boolean defaultValue) {
		return getConfiguration().getBoolean(name, defaultValue);
	}
	
	public static byte getByte(String name) {
		return getConfiguration().getByte(name);
	}
	
	public static byte getByte(String name, byte defaultValue) {
		return getConfiguration().getByte(name, defaultValue);
	}
	
	public static short getShort(String name) {
		return getConfiguration().getShort(name);
	}
	
	public static short getShort(String name, short defaultValue) {
		return getConfiguration().getShort(name, defaultValue);
	}
	
	public static int getInt(String name) {
		return getConfiguration().getInt(name);
	}
	
	public static int getInt(String name, int defaultValue) {
		return getConfiguration().getInt(name, defaultValue);
	}
	
	public static long getLong(String name) {
		return getConfiguration().getLong(name);
	}
	
	public static long getLong(String name, long defaultValue) {
		return getConfiguration().getLong(name, defaultValue);
	}
	
	public static float getFloat(String name) {
		return getConfiguration().getFloat(name);
	}
	
	public static float getFloat(String name, float defaultValue) {
		return getConfiguration().getFloat(name, defaultValue);
	}
	
	public static double getDouble(String name) {
		return getConfiguration().getDouble(name);
	}
	
	public static double getDouble(String name, double defaultValue) {
		return getConfiguration().getDouble(name, defaultValue);
	}
	
	public static BigInteger getBigInteger(String name) {
		return getConfiguration().getBigInteger(name);
	}
	
	public static BigInteger getBigInteger(String name, BigInteger defaultValue) {
		return getConfiguration().getBigInteger(name, defaultValue);
	}
	
	public static BigDecimal getBigDecimal(String name) {
		return getConfiguration().getBigDecimal(name);
	}
	
	public static BigDecimal getBigDecimal(String name, BigDecimal defaultValue) {
		return getConfiguration().getBigDecimal(name, defaultValue);
	}
	
	public static String getString(String name) {
		return getConfiguration().getString(name);
	}
	
	public static String getString(String name, String defaultValue) {
		return getConfiguration().getString(name, defaultValue);
	}
	
	private static Configuration getConfiguration() {
		return MposConfigurer.getInstance().getConfiguration();
	}
}
