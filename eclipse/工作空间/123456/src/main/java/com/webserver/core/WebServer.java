package com.webserver.core;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {
	private ServerSocket server;
	public WebServer() {
		System.out.println("����������");
		try {
			server = new ServerSocket(12345);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void start() {
		System.out.println("�ȴ�����");
		try {
			Socket socket = server.accept();
			System.out.println("һ���û�����");
			
			new Thread(new Client(socket)).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		WebServer server = new WebServer();
		server.start();
	}
}
