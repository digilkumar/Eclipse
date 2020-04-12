package com.notes.servlets;

import java.io.IOException;
import java.sql.SQLException;

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
 * Servlet implementation class ValidateServlet
 */
@WebServlet("/validate")
public class ValidateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/login");
		rd.include(request, response);
	}

	private void dispatchInclude(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/login");
		rd.include(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		SessionDao s = new SessionDaoImpl();
		String sessionId = "";
		try {
			System.out.println(username + " : " +password);
			sessionId = s.createSession(username, password);
			System.out.println(sessionId);
			if(sessionId.equalsIgnoreCase("failure")) {
				response.getWriter().println("Incorrect Credentials");	
				dispatchInclude(request, response);
			}else {
				System.out.println(1);
				Cookie userCookie = new Cookie("username",username);
				Cookie sessionCookie = new Cookie("session_id",sessionId);
				userCookie.setPath("/");
				sessionCookie.setPath("/");
				System.out.println(2);
				response.addCookie(userCookie);
				response.addCookie(sessionCookie);
				System.out.println(3);
			
				response.sendRedirect("/nm/main");
				
				//RequestDispatcher rd = request.getRequestDispatcher("/main");
//				System.out.println(4);
				//rd.forward(request, response);

			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
