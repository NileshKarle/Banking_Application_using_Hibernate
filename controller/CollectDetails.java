package com.bridgelabz.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;

import com.bridgelabz.dao.BankdaoHibernate;

@WebServlet("/CollectDetails")
public class CollectDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
			String city = request.getParameter("city");
			BankdaoHibernate bank = new BankdaoHibernate();
			HttpSession session = request.getSession();
			String name = (String) session.getAttribute("name");
			JSONArray bankData = new JSONArray();
			try {
				bankData = bank.CollectBankData(city, name);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			PrintWriter out = new PrintWriter(response.getWriter());
			out.println(bankData.toString());
			out.close();
	}
}
