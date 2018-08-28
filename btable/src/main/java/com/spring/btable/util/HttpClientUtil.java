package com.spring.btable.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.http.MediaType;

import com.alibaba.fastjson.JSONObject;

public class HttpClientUtil {
	public static void main(String[] args) {
		new HttpClientUtil().doPost();
	}
	public String doPost(){
		try{
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("name", "张三");
			HttpClient httpClient = HttpClients.createDefault();
			HttpPost post = new HttpPost("http://localhost:2020/httpClientJson");
			post.setHeader("Content-Type", "application/json; charset=UTF-8");
			post.setHeader("Accept", MediaType.APPLICATION_JSON.toString());
			post.setEntity(new StringEntity(jsonObject.toString(), "UTF-8"));
			HttpResponse response = httpClient.execute(post);
			HttpEntity entity = response.getEntity();
			System.out.println(EntityUtils.toString(entity, "UTF-8"));
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
}
