package com.school.common.facade;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository(value = "hiberSessionFactory")
public class HiberSessionFactory 
{
	@Autowired
	private SessionFactory sessionFactory;

	private static HiberSessionFactory hiberSessionFactory;
	
	
	private HiberSessionFactory() {
	}

	public static HiberSessionFactory getInstance()
	{
		if (hiberSessionFactory == null) {
			hiberSessionFactory = new HiberSessionFactory();
		}
		return hiberSessionFactory;
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
}
