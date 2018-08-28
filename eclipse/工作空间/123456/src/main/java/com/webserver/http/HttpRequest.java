package com.webserver.http;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
//�������������ȡ�û�����������
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
	//��
	private void parseLine() {
		System.out.println("�п�ʼ");
		try {
			String line = readLine();
			System.out.println("�����У�"+line);
			
			String[]arr=line.split("\\s");
			method = arr[0];
			url = arr[1];
			protocol = arr[2];
			System.out.println("����ʽ�� "+method);
			System.out.println("������Դ·���� "+url);
			System.out.println("Э��汾�� "+protocol);
			System.out.println("�н���");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//ͷ
	private void parseHeader() {
		System.out.println("ͷ��ʼ");
		try {
			while(true) {
				String line = readLine();
				if("".equals(line)) {
					break;
				}
				System.out.println("��Ϣͷ�� "+line);
				String[]arr=line.split(": ");
				header.put(arr[0], arr[1]);
			}
			System.out.println("��Ϣͷ��ֵ�ԣ�"+header);
			System.out.println("ͷ����");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//��
	private void parseContent() {
		System.out.println("�Ŀ�ʼ");
		System.out.println("�Ľ���");
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
