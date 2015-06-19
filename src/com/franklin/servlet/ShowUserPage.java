package com.franklin.servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.franklin.dao.User;
import com.franklin.db.DbUtil;

public class ShowUserPage extends HttpServlet {

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
		int pageSize = 10;
		int pageNow = Integer.parseInt(req.getParameter("pageNow"));
		int pageCnt = new DbUtil().getUserPageCnt(pageSize);
		List<User> users = new DbUtil().getUserByPage(pageNow, pageSize);
		Iterator<User> iter = users.iterator();
		PrintWriter pw = resp.getWriter();
		pw.write("<html>");
		pw.write("<head>");
		pw.write("</head>");
		pw.write("<body>");
		pw.write("<a href='Login'>退出系统</a>");
		pw.write("&nbsp;&nbsp;");
		pw.write("<a href='Main'>返回主界面</a>");
		pw.write("<p>");
		pw.write("<font color='#FF0000'>");
		pw.write("展示用户信息");
		pw.write("</font>");
		pw.write("</p>");
		pw.write("<table>");
		pw.write("<tr>");
		pw.write("<td>用户名</td>");
		pw.write("</tr>");
		while(iter.hasNext()) {
			String username = iter.next().getUsername();
			pw.write("<tr>");
			pw.write("<td>"+username+"</td>");
			pw.write("</tr>");
		}
		pw.write("</table>");
		if(pageNow>1) {
			pw.write("<a href='ShowUserPage?pageNow="+(pageNow-1)+"'>上一页</a>");
		}
		pw.write("当前"+pageNow+"/"+pageCnt+"页");
		if(pageNow<pageCnt) {
			pw.write("<a href='ShowUserPage?pageNow="+(pageNow+1)+"'>下一页</a>");
		}
		pw.write("</body>");
		pw.write("</html>");
		if(pw!=null) {
			pw.close();
			pw = null;
		}
	}
	
}
