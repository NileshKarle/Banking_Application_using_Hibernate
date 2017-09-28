package com.bridgelabz.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bridgelabz.dao.BankdaoHibernate;
import com.bridgelabz.dao.UserdaoHibernate;
import com.bridgelabz.model.BankingDetailsMapping;
import com.bridgelabz.model.UserDetailsMapping;

@WebServlet("/UpdateBank")
public class UpdateBank extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String bankName = request.getParameter("bankName");
		int accountNumber = Integer.parseInt(request.getParameter("accountNumber"));
		String city = request.getParameter("city");
		String personId = request.getParameter("id");
		if (personId.equals("0")) {
			HttpSession session1 = request.getSession();
			String userName = (String) session1.getAttribute("name");
			UserdaoHibernate user = new UserdaoHibernate();
			int user_id = user.id(userName);
			UserDetailsMapping userMapping=new UserDetailsMapping();
			userMapping=user.userObject(userName);
			BankingDetailsMapping bank = new BankingDetailsMapping();
			bank.setName(name);
			bank.setBankName(bankName);
			bank.setAccountNumber(accountNumber);
			bank.setCity(city);
			/*bank.setUser_id(user_id);*/
			bank.setUser(userMapping);
			  BankdaoHibernate obj = new BankdaoHibernate(); 
			  obj.CollectBankData(bank);
			  
			response.sendRedirect("homepage");
		} else {
			BankdaoHibernate bankObject = new BankdaoHibernate();
			int pId = Integer.parseInt(personId);
			bankObject.editRow(pId, name, bankName, accountNumber, city);
		}
	}

}
