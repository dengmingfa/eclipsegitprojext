package com.webserver.http;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
//请求对象，用来读取用户的请求内容
public class HttpRequest {
	private Socket socket;
	private InputStream is;
	
	private String method;
	private String url;
	private String protocol;
	
	private Map<String,String>header = new HashMap<String,String>();
	public HttpRequest(Socket socket) {
		this.socket = socket;
		try {
			this.is = socket.getInputStream();
			
			parseLine();
			parseHeader();
			parseContent();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//行
	private void parseLine() {
		System.out.println("行开始");
		try {
			String line = readLine();
			System.out.println("请求行："+line);
			
			String[]arr=line.split("\\s");
			method = arr[0];
			url = arr[1];
			protocol = arr[2];
			System.out.println("请求方式： "+method);
			System.out.println("请求资源路径： "+url);
			System.out.println("协议版本： "+protocol);
			System.out.println("行结束");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//头
	private void parseHeader() {
		System.out.println("头开始");
		try {
			while(true) {
				String line = readLine();
				if("".equals(line)) {
					break;
				}
				System.out.println("消息头： "+line);
				String[]arr=line.split(": ");
				header.put(arr[0], arr[1]);
			}
			System.out.println("消息头键值对："+header);
			System.out.println("头结束");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//文
	private void parseContent() {
		System.out.println("文开始");
		System.out.println("文结束");
	}
	private String readLine() throws IOException {
		StringBuilder builder = new StringBuilder();
		int d = -1;
		char c1 = 'a',c2 = 'a';
		while((d = is.read())!=-1) {
			c2 = (char)d;
			if(c1==13&&c2==10) {
				break;
			}
			builder.append(c2);
			c1 = c2;
		}
		return builder.toString().trim();
	}
	public String getMethod() {
		return method;
	}
	public String getUrl() {
		return url;
	}
	public String getProtocol() {
		return protocol;
	}
	
}
