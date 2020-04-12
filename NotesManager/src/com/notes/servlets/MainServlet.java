package com.notes.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.notes.session.dao.SessionDao;
import com.notes.session.dao.impl.SessionDaoImpl;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/main")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("inside main");
		
		Map<String,String> hm = new HashMap<String,String>();
		
		
		Cookie[] cookies = request.getCookies();
		
		
		if(cookies!=null) {
		for (Cookie aCookie : cookies) {
			if(aCookie.getName()!=null && aCookie.getValue()!=null)
		   hm.put(aCookie.getName(), aCookie.getValue());
		}
		SessionDao s = new SessionDaoImpl();
		try {
			if(hm.containsKey("username") && hm.containsKey("session_id")) {
			if(s.validateSession(hm.get("username"), hm.get("session_id"))){
				System.out.println("inside validate home");
				RequestDispatcher rd = request.getRequestDispatcher("/home1.html");
				rd.forward(request, response);
			}else {
				RequestDispatcher rd = request.getRequestDispatcher("/login");
				rd.forward(request, response);
			}
			
			}
			else {
				RequestDispatcher rd = request.getRequestDispatcher("/login");
				rd.forward(request, response);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}}else {
			
				RequestDispatcher rd = request.getRequestDispatcher("/login");
				rd.forward(request, response);
		}
	}
	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
