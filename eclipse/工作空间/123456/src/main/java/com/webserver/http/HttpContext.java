package com.webserver.http;

import java.util.HashMap;
import java.util.Map;

public class HttpContext {
	public static Map<Integer,String>statusCode=new HashMap<Integer,String>();
	public static Map<String,String>mimeMapping=new HashMap<String,String>();
	
	static {
		initStatusCode();
		initMimeMapping();
	}
	
	private static void initStatusCode() {
		statusCode.put(200, "OK");
		statusCode.put(201, "Created");
		statusCode.put(202, "Accepted");
		statusCode.put(204, "No Content");
		statusCode.put(301, "Moved Permanently");
		statusCode.put(302, "Moved Temporarily");
		statusCode.put(304, "Not Modified");
		statusCode.put(400, "Bad Request");
		statusCode.put(401, "Unauthorized");
		statusCode.put(403, "Forbidden");
		statusCode.put(404, "Not Found");
		statusCode.put(500, "Internal Server Error");
		statusCode.put(501, "Not Implemented");
		statusCode.put(502, "Bad Gateway");
		statusCode.put(503, "Service Unavailable");
	}
	
	private static void initMimeMapping() {
		mimeMapping.put("html", "text/html");
		mimeMapping.put("css", "text/css");
		mimeMapping.put("js", "application/javascript");
		mimeMapping.put("png", "image/png");
		mimeMapping.put("jpg", "image/jpeg");
		mimeMapping.put("gif", "image/gif");	
	}
	public static String getStatusCode(int Code) {
		return statusCode.get(Code);
	}
	public static String getStatusType(String Type) {
		return mimeMapping.get(Type);
	}
}
