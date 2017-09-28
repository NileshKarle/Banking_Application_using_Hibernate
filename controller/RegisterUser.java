package com.bridgelabz.controller;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bridgelabz.dao.UserdaoHibernate;
import com.bridgelabz.model.UserDetailsMapping;
@WebServlet("/RegisterUser")
public class RegisterUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		String name=request.getParameter("name");
		String mbNumber = request.getParameter("contact");
		long contact=Long.parseLong(mbNumber);
		if(!Pattern.matches("[a-zA-Z]+@[a-zA-Z]+\\.com$",email))
		{
			HttpSession session=request.getSession();  
	        session.setAttribute("error","something went wrong try again");
	        response.sendRedirect("signIn");
		}
		
		else{

			UserDetailsMapping user=new UserDetailsMapping();
			user.setName(name);
			user.setContact(contact);
			user.setEmail(email);
			user.setPassword(password);
			UserdaoHibernate object =new UserdaoHibernate();
			object.CollectUserData(user);
		    response.sendRedirect("login");
		}
	}

}

