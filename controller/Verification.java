package com.bridgelabz.controller;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bridgelabz.dao.UserdaoHibernate;
@WebServlet("/Verification")
public class Verification extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		UserdaoHibernate obj = new UserdaoHibernate();
		String name=obj.verify(email, password);
		if (name!="" || name!=null) {
			HttpSession session1=request.getSession();
			session1.setAttribute("name",name);
			response.sendRedirect("Welcome");
		} else {
			response.sendRedirect("login");
		}
	}

}
