package com.franklin.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.franklin.tools.PropUtil;



public class DbCon {
	private static DbCon instance = null;
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static DbCon getInstance() {
		if(instance==null) {
			instance = new DbCon();
		} 
		return instance;
	}
	
	public Connection getConnection() {
		String url = PropUtil.getUrl();
		String user = PropUtil.getUser();
		String password = PropUtil.getPassword();
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	public void closeConnection(Connection conn) {
		if(conn!=null) {
			try {
				conn.close();
				conn = null;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
