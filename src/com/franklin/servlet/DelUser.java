package com.franklin.servlet;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.franklin.dao.User;
import com.franklin.db.DbUtil;

public class DelUser extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("text/html;charset=UTF-8");
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("text/html;charset=UTF-8");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		if(username==null||password==null) {
			req.getRequestDispatcher("/Err?errcode=del&info=delnull").forward(req, resp);
			//return;
		}
		username = username.trim();
		password = password.trim();
		//用户名、密码为""
		if(username.equals("")||password.equals("")) {
			req.getRequestDispatcher("/Err?errcode=del&info=delnull").forward(req, resp);
			return;
		}
		User user = new User(username,password);
		//判断该用户是否已经存在
		boolean existFlag = new DbUtil().checkExistUser(user);
		if(!existFlag) {
			req.getRequestDispatcher("/Err?errcode=del&info=delnotexist").forward(req, resp);
			return;
		} 
		//判断是否删除成功
		boolean addFlag = new DbUtil().delUser(user);
		if(addFlag) {
			req.getRequestDispatcher("/Ok?code=del").forward(req, resp);
			return;
		} else {
			req.getRequestDispatcher("/Err?errcode=del&info=delErr").forward(req, resp);
			return;
		}
	}

}
