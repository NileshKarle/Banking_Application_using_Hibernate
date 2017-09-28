package com.bridgelabz.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.bridgelabz.HibernateUtil.HibernateUtil;
@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("name");
		session.invalidate();
		HibernateUtil hb=new HibernateUtil();
		SessionFactory factory =hb.GetSessionFactory();
		Session session1 = factory.openSession();
		session1.close();
		response.sendRedirect("Welcome");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("name");
		session.invalidate();
		HibernateUtil hb=new HibernateUtil();
		SessionFactory factory =hb.GetSessionFactory();
		Session session1 = factory.openSession();
		session1.close();
		response.sendRedirect("Welcome");
	}

}
