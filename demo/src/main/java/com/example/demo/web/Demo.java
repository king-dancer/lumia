package com.example.demo.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Demo {
	public void test(){
		
	}
//	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public void demo(HttpServletRequest request,HttpServletResponse response) throws Exception{
		FileItemFactory df = new DiskFileItemFactory();
		
		List<FileItem> fileItems = new ArrayList<FileItem>();
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if(isMultipart){
			ServletFileUpload upload = new ServletFileUpload(df);
			fileItems = upload.parseRequest(request);
			Iterator<FileItem> iterator = fileItems.iterator();
			System.out.println(iterator.hasNext());
			while (iterator.hasNext()) {
				FileItem fileItem = (FileItem) iterator.next();
				InputStream inputStream = fileItem.getInputStream();
				FileOutputStream fos = new FileOutputStream("/app");
				byte[] buffer = new byte[1024];
				while (inputStream.read(buffer) > 0) {
					fos.write(buffer, 0, buffer.length);
				}
				fos.flush();
				fos.close();
			}
		}
	}
	@PostMapping("/upload")
	public void processUpload(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		try {
            
            //1 工厂
            FileItemFactory fileItemFactory = new DiskFileItemFactory();
            
            //2 核心类
            ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);
            
            //3 解析request  ,List存放 FileItem （表单元素的封装对象，一个<input>对应一个对象）
            List<FileItem> list = servletFileUpload.parseRequest(request);
            
            //4 遍历集合获得数据
            for (FileItem fileItem : list) {
                if(fileItem.isFormField()){
                    // 5 是否为表单字段（普通表单元素）
                    //5.1.表单字段名称
                    String fieldName = fileItem.getFieldName();
                    System.out.println(fieldName);
                    //5.2.表单字段值
                    String fieldValue = fileItem.getString();    //中文会出现乱码
                    System.out.println(fieldValue);
                } else {
                    //6 上传字段（上传表单元素）
                    //6.1.表单字段名称  fileItem.getFieldName();
                    //6.2.上传文件名
                    String fileName = fileItem.getName();
                    // * 兼容浏览器， IE ： C:\Users\xxx\Desktop\abc.txt  ; 其他浏览器 ： abc.txt
                    fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
                    System.out.println(fileName);    //上传的文件名会中文乱码，
                    //6.3.上传内容
                    InputStream is = fileItem.getInputStream();    //获得输入流，
                    String parentDir = "/app";
                    File file = new File(parentDir,fileName);
                    if(! file.getParentFile().exists()){  //父目录不存在
                        file.getParentFile().mkdirs();        //mkdirs():创建文件夹，如果上级目录没有的话，也一并创建出来。
                    }
                    FileOutputStream out = new FileOutputStream(file);    
                    byte[] buf = new byte[1024];
                    int len = -1;
                    while( (len = is.read(buf)) != -1){
                        out.write(buf, 0, len);
                    }
                    
                    //关闭
                    out.close();
                    is.close();
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            
            throw new RuntimeException(e);
            
        }
    }
}
