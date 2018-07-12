package com.hp.cmcc.bboss.bdc.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils  {
	private static Logger L = LoggerFactory.getLogger(JsonUtils.class);
	
	ObjectMapper mapper = new ObjectMapper();
	public Object JsonToObj(String json,Class<?> c){
//		L.debug("{} prase to object start!",json);
		Object obj = null;
		try {
			obj = mapper.readValue(json, c);
		} catch (Exception e) {
			L.error("{} json parse exception!",json);
			e.printStackTrace();
		}
//		L.debug("{} prase success!",json);
		return obj;
	}
	
	public String objToJson(Object obj){
		if(Tools.IsEmpty(obj)){
			L.error("obj is null!");
			return "";
		}
		String json = "";
		try {
			json = mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			L.error("object to json fail!");
		}
		return json;
	}
	
}
