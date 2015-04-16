package com.san.mpos.common.util;

import org.json.simple.JSONObject;

public class MposJsonFactory {
	private static MposJsonFactory instance;
	private MposJsonFactory(){}
	public static String RESULT_CODE = "resultCode";
	public static String RESULT_MSG = "resultMsg";
	
	public static MposJsonFactory getInstance(){
		if(instance==null) instance =new MposJsonFactory();
		return instance;
	}
	
	public JSONObject getOK(){
		return getResultJson("200", "OK");
	}
	public JSONObject getOK(String resultMsg){
		return getResultJson("200", resultMsg);
	}
	public JSONObject getNG(){
		return getResultJson("400", "NOT GOOD");
	}
	public JSONObject getNG(String resultMsg){
		return getResultJson("400", resultMsg);
	}
	
	public JSONObject getResultJson(int resultCode, String resultMsg){
		return getResultJson(String.valueOf(resultCode), resultMsg);
	}
	@SuppressWarnings("unchecked")
	public JSONObject getResultJson(String resultCode, String resultMsg){
		JSONObject jo = new JSONObject();
		jo.put(RESULT_CODE,resultCode);
		jo.put(RESULT_MSG,resultMsg);
		return jo;
	}
}
