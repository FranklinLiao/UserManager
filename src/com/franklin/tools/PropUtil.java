package com.franklin.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Properties;



public class PropUtil {
	private static String configFile = "/config/dbconfig.properties";
	private static String infoFile = "/config/info.properties";
	private static String url = "";
	private static String user = "";
	private static String password = "";
	
	static {
		readConfig();
	}
	public static void readConfig() {
		InputStream is = PropUtil.class.getResourceAsStream(configFile);
		Properties prop = new Properties();
		try {
			prop.load(is);
			url = prop.getProperty("url");
			user = prop.getProperty("user");
			password = prop.getProperty("password");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String readInfo(String infoName) {
		InputStream is = PropUtil.class.getResourceAsStream(infoFile);
		//BufferedReader bf = new BufferedReader(new InputStreamReader(is));  
		Properties prop = new Properties();
		String infoString = "";
		try {
			prop.load(is);
			infoString = prop.getProperty(infoName); 
			infoString=new String(infoString.getBytes("ISO-8859-1"), "utf-8");//这一句是重点
		} catch (IOException e) {
			//TODO Auto-generated catch block
			e.printStackTrace();
		}
		return infoString;
	}
	public static String getUrl() {
		return url;
	}

	public static String getUser() {
		return user;
	}

	public static String getPassword() {
		return password;
	}
	
	
}
