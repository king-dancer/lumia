package com.spring.btable.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class IoUtils {
	public static void main(String[] args){
		readFile();
	}
	public static void readFile(){
		BufferedReader br = null;
		try{
			br = new BufferedReader(new FileReader(new File("D:\\wls\\123.txt")));
			String str;
			StringBuffer sb = new StringBuffer();
			while((str=br.readLine())!=null){
				System.out.println(str);
				sb.append(str);
			}
			System.out.println(sb);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(br!=null){
				try{
					br.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}
	/**
	 * 
	 * @param filePath  目标文件
	 * @param savePath	要拷贝文件
	 */
	public void copyFile(String filePath,String savePath){
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			fis = new FileInputStream(new File("D:\\wls\\123.txt"));//filePath
			fos = new FileOutputStream(new File("D:\\wls\\456.txt"));//savePath
			byte[] b = new byte[5];
			int len;
			while((len=fis.read(b))!=-1){
				fos.write(b, 0, len);
			}
			fos.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(fos!=null){
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(fis!=null){
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
