package com.tgrape.json;

import com.tgrape.obj.QuotesAll;

import net.sf.json.JSONObject;

public class JsonUtil {

	public static String object2Json(Object object){
		JSONObject jo = JSONObject.fromObject(object);
		return jo.toString();		
	}

	public static String object2Json(QuotesAll qa) {
		JSONObject jo = JSONObject.fromObject(qa);
		return jo.toString();	
	}
}
