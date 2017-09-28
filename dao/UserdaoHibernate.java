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

import com.bridgelabz.HibernateUtil.HibernateUtil;
import com.bridgelabz.model.UserDetailsMapping;

public class UserdaoHibernate {

	public void CollectUserData(UserDetailsMapping obj) {

		HibernateUtil hb = new HibernateUtil();
		SessionFactory factory=hb.GetSessionFactory();
		Session session = factory.openSession() ;
		Transaction transaction = session.beginTransaction();
		session.persist(obj);
		transaction.commit();
		session.close();
	}

	public String verify(String email, String password) {
		String name = null;
		HibernateUtil hb = new HibernateUtil();
		SessionFactory factory=hb.GetSessionFactory();
		Session session = factory.openSession() ;
		/*
		 * Criteria criteria = session.createCriteria(UserDetailsMapping.class);
		 */
		Criteria criteria = session.createCriteria(UserDetailsMapping.class);
		Criterion emailVerify = Restrictions.ilike("email", email);
		Criterion passwordVerify = Restrictions.ilike("password", password);

		LogicalExpression andExp = Restrictions.and(emailVerify, passwordVerify);
		criteria.add(andExp);

		List result = criteria.list();
		for (Iterator iterator = result.iterator(); iterator.hasNext();) {
			UserDetailsMapping user = (UserDetailsMapping) iterator.next();
			System.out.println("user name is " + user.getName());
			name = user.getName();
		}
		session.close();
		return name;
	}

	public int id(String userName) {
		int UserId = 0;
		HibernateUtil hb = new HibernateUtil();
		SessionFactory factory=hb.GetSessionFactory();
		Session session = factory.openSession() ;
		Criteria criteria = session.createCriteria(UserDetailsMapping.class);
		criteria.add(Restrictions.ilike("name", userName));
		List result = criteria.list();
		for (Iterator iterator = result.iterator(); iterator.hasNext();) {
			UserDetailsMapping user = (UserDetailsMapping) iterator.next();
			UserId = user.getId();
		}
		session.close();
		return UserId;
	}
	public UserDetailsMapping userObject(String userName) {
		UserDetailsMapping user=null;
		HibernateUtil hb = new HibernateUtil();
		SessionFactory factory=hb.GetSessionFactory();
		Session session = factory.openSession() ;
		Criteria criteria = session.createCriteria(UserDetailsMapping.class);
		criteria.add(Restrictions.ilike("name", userName));
		List result = criteria.list();
		for (Iterator iterator = result.iterator(); iterator.hasNext();) {
			user = (UserDetailsMapping) iterator.next();
		}
		session.close();
		return user;
	}
}
