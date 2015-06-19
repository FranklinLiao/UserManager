package com.franklin.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class MyHttpSessionListener implements HttpSessionListener {
	private int onlineUserCnt = 0;
	private int visitorUserCnt = 0;
	public void sessionCreated(HttpSessionEvent event) {
		// TODO Auto-generated method stub
		//OnlineU
		Object onlineObject = event.getSession().getServletContext().getAttribute("OnlineUserCnt");
		if(onlineObject!=null) {
			onlineUserCnt = (Integer) onlineObject;
		}
		onlineUserCnt++;
		event.getSession().getServletContext().setAttribute("OnlineUserCnt", onlineUserCnt);
		//VisitorUserCnt
		Object visitorObject = event.getSession().getServletContext().getAttribute("VisitorUserCnt");
		if(visitorObject!=null) {
			visitorUserCnt = (Integer) visitorObject;
		}
		visitorUserCnt++;
		event.getSession().getServletContext().setAttribute("VisitorUserCnt", visitorUserCnt);
	
	}

	public void sessionDestroyed(HttpSessionEvent event) {
		// TODO Auto-generated method stub
		Object object = event.getSession().getServletContext().getAttribute("OnlineUserCnt");
		if(object!=null) {
			onlineUserCnt = (Integer) object;
		}
		onlineUserCnt--;
		event.getSession().getServletContext().setAttribute("OnlineUserCnt", onlineUserCnt);
	}

}
