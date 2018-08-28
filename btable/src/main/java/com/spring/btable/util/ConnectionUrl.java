package com.spring.btable.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class ConnectionUrl {
	
	public static void main(String[] args) throws Exception {
		sendMessage();
	}
	public static void sendMessage() throws Exception{
		URL url = new URL("http://localhost:2020/json");
		HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		connection.setDoInput(true);
		connection.setDoOutput(true);
		connection.setUseCaches(false);
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
		connection.connect();
		OutputStream os = connection.getOutputStream();
		com.alibaba.fastjson.JSONObject json = new com.alibaba.fastjson.JSONObject();
		json.put("name", "德玛西亚");
		os.write(json.toString().getBytes("UTF-8"));
		os.flush();
		os.close();
		InputStream inputStream = connection.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
		String result;
		StringBuffer sb = new StringBuffer("");
		while((result=br.readLine())!=null){
			result = new String(result.getBytes(),"UTF-8");
			sb.append(result);
		}
		System.out.println("返回报文："+sb);
		JSONObject res = (JSONObject)JSON.parse(sb.toString());
		System.out.println("res:"+res.getString("res"));
		br.close();
		connection.disconnect();
	}
}
