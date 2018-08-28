package com.webserver.http;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class HttpResponse {
	private int a = 1344;
	private Socket socket;
	private OutputStream os;
	private File file;
	
	private int statusCode = 200;
	private String statusReason = "OK";
	
	private Map<String,String>header = new HashMap<String,String>();
	public HttpResponse(Socket socket) {
		this.socket = socket;
		try {
			this.os = socket.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void flush() {
		sendLine();
		sendHeader();
		sendContent();
	}
	//行
	private void sendLine() {
		System.out.println("响应行开始");
		try {
			String line = "HTTP/1.1"+statusCode+statusReason;
			os.write(line.getBytes("ISO8859-1"));
			os.write(13);
			os.write(10);
			System.out.println("响应行结束");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//头
	private void sendHeader() {
		System.out.println("响应头开始");
		try {
			Set<Entry<String,String>>set = header.entrySet();
			for(Entry<String,String> entry:set) {
				String key = entry.getKey();
				String value = entry.getValue();
				String line = key+": "+value;
				os.write(line.getBytes("ISO8859-1"));
				os.write(13);
				os.write(10);
			}
			os.write(13);
			os.write(10);
			System.out.println("响应头结束");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//文
	private void sendContent() {
		System.out.println("响应文开始");
		try {
			FileInputStream fis = new FileInputStream(file);
			byte[]data = new byte[1024*10];
			int d = -1;
			while((d = fis.read(data))!=-1) {
				os.write(data, 0, d);
			}
			System.out.println("响应文结束");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
		this.header.put("Content-Length", file.length()+"");
		
		String fileName = file.getName();
		int index = fileName.lastIndexOf(".")+1;
		String Type = fileName.substring(index);
		this.header.put("Content-Type", HttpContext.getStatusType(Type));
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
		this.statusReason = HttpContext.getStatusCode(statusCode);
	}
	public String getStatusReason() {
		return statusReason;
	}
	public void setStatusReason(String statusReason) {
		this.statusReason = statusReason;
	}
	
	
	
}
