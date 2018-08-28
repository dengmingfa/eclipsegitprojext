package com.webserver.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import com.webserver.http.HttpRequest;
import com.webserver.http.HttpResponse;

public class Client implements Runnable{
	private Socket socket;
	public Client(Socket socket) {
		this.socket = socket;
	}
	public void run() {
		/*
		 * ����
		 * ����
		 * ��Ӧ
		 */
		try {
			HttpRequest request = new HttpRequest(socket);
			HttpResponse response = new HttpResponse(socket);
			
			String url = request.getUrl();
			File file = new File("webapps"+url);
			if(file.exists()) {
				System.out.println("��Դ���ҵ�");
				response.setFile(file);
			}else {
				System.out.println("��Դ�Ҳ���Ŷ");
			}
			response.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
