package com.bridgelabz.dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.bridgelabz.HibernateUtil.HibernateUtil;

import com.bridgelabz.model.BankingDetailsMapping;
import com.bridgelabz.model.UserDetailsMapping;

/**
 * @author Nilesh 
 * 
 * @Description This is a Dao class. Which deals with all the person data.
 *
 */
public class BankdaoHibernate {

	/**
	 * @param object
	 * 
	 * object is a object of BankingDetailsMapping.
	 * 
	 * @Description This method add's the people data in the bank table in the database.
	 * 
	 */
	public void CollectBankData(BankingDetailsMapping object) {
		HibernateUtil hb = new HibernateUtil();
		SessionFactory factory=hb.GetSessionFactory();
		Session session = factory.openSession() ;
		Transaction transaction = session.beginTransaction();
		session.persist(object);
		transaction.commit();
		session.close();
	}

	/**
	 * @param city 
	 * @param name
	 * @return JSONARRAY
	 * @throws JSONException
	 * @Description returns all the data from the database that matches the city and the user_Id. 
	 */
	public JSONArray CollectBankData(String city, String name) throws JSONException{
		JSONArray array=new JSONArray();
		HibernateUtil hb=new HibernateUtil();
		SessionFactory factory=hb.GetSessionFactory();
		Session session = factory.openSession() ;
		Criteria criteria = session.createCriteria(UserDetailsMapping.class);
		criteria.add(Restrictions.ilike("name", name));
		List result = criteria.list();
		 UserDetailsMapping user2 = null ;
		 for (Iterator iterator =result.iterator();iterator.hasNext();){
			 UserDetailsMapping user=(UserDetailsMapping) iterator.next();
			 user2=user;
		 }
		
		 Criteria criteria2 = session.createCriteria(BankingDetailsMapping.class);
		 Criterion verifyCity = Restrictions.like("city",city);
			Criterion verifyUserId = Restrictions.eq("userMapping",user2);

			LogicalExpression andExp = Restrictions.and(verifyCity, verifyUserId);
			criteria2.add( andExp );
			result = criteria2.list();
			 for (Iterator iterator =result.iterator();iterator.hasNext();){
				 BankingDetailsMapping user=(BankingDetailsMapping) iterator.next();
				 JSONObject object = new JSONObject();
				object.put("id", user.getId());
				object.put("name", user.getName());
				object.put("bankName", user.getBankName());
				object.put("accountNumber", user.getAccountNumber());
				object.put("city", user.getCity());
				array.put(object);
			 }
			 session.close();
			 return array;		
	}

	/**
	 * @param id
	 * 
	 * @Description it deletes the row from the database that contains the specific id.
	 */
	public void RemoveRow(int id) {
		HibernateUtil hb = new HibernateUtil();
		SessionFactory factory=hb.GetSessionFactory();
		Session session = factory.openSession() ;
		Transaction transaction = session.beginTransaction();
		BankingDetailsMapping user = new BankingDetailsMapping();
		user.setId(id);
		session.delete(user);
		transaction.commit();
		session.close();
	}

	public JSONObject RowData(int id) throws JSONException {
		JSONObject object = new JSONObject();
		HibernateUtil hb=new HibernateUtil();
		SessionFactory factory=hb.GetSessionFactory();
		Session session = factory.openSession() ;
		/*Criteria criteria = session.createCriteria(UserDetailsMapping.class);*/
		Criteria criteria = session.createCriteria(BankingDetailsMapping.class);
		criteria.add(Restrictions.eq("id", id));
		List result = criteria.list();
		 for (Iterator iterator =result.iterator();iterator.hasNext();){
			 BankingDetailsMapping user=(BankingDetailsMapping) iterator.next();
			
			object.put("id", user.getId());
			object.put("name", user.getName());
			object.put("bankName", user.getBankName());
			object.put("accountNumber", user.getAccountNumber());
			object.put("city", user.getCity());
		 }
		 session.close();
		 return object;
	}

	public void editRow(int id, String name, String bankName, int accountNumber, String city) {
		
		HibernateUtil hb = new HibernateUtil();
		SessionFactory factory=hb.GetSessionFactory();
		Session session = factory.openSession() ;
		Transaction transaction = session.beginTransaction();
		BankingDetailsMapping user=(BankingDetailsMapping)session.get(BankingDetailsMapping.class, id);
		user.setName(name);
		user.setAccountNumber(accountNumber);
		user.setBankName(bankName);
		user.setCity(city);
		transaction.commit();
		session.update(user);
		session.close();
	}
}
