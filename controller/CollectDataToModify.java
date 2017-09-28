package com.bridgelabz.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.bridgelabz.dao.BankdaoHibernate;
@WebServlet("/CollectDataToModify")
public class CollectDataToModify extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 PrintWriter out =new PrintWriter(response.getWriter());
		
		int personId =Integer.parseInt(request.getParameter("id"));
		BankdaoHibernate bank=new BankdaoHibernate();
		JSONObject RowValue=null;
		try {
			RowValue = bank.RowData(personId);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		System.out.println("details "+RowValue);
			out.println(RowValue.toString());
			out.close();
	}
}


