package com.spring.util;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
	public static <T> Object jsonToObj(String jsonStr,Class<T> obj){
		T t = null;
		try{
			ObjectMapper objectMapper = new ObjectMapper();
			t = objectMapper.readValue(jsonStr, obj);
		}catch(Exception e){
			e.printStackTrace();
		}
		return t;
	}
	
//	public static void main(String[] args) {
//		JSONObject json = new JSONObject();
//		json.put("id", 4);
//		json.put("username", "zhangsan");
//		json.put("password", new BCryptPasswordEncoder().encode("zs123456"));
//		JSONArray roles = new JSONArray();
//		JSONObject role = new JSONObject();
//		role.put("id", 1);
//		role.put("name", "ROLE_ADMIN");
//		roles.add(role);
//		json.put("roles", roles);
//		SysUser sysUser = (SysUser)JsonUtil.jsonToObj(json.toJSONString(), SysUser.class);
//		System.out.println(sysUser.toString());
//	}
}
