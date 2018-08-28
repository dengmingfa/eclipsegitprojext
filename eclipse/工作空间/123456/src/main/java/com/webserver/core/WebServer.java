package com.webserver.core;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {
	private ServerSocket server;
	public WebServer() {
		System.out.println("启动服务器");
		try {
			server = new ServerSocket(12345);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void start() {
		System.out.println("等待连接");
		try {
			Socket socket = server.accept();
			System.out.println("一个用户连接");
			
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
