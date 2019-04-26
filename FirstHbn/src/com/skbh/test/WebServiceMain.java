package com.skbh.test;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


import com.skbh.pojo.WebserviceResourceAccessLog;

public class WebServiceMain {

	public static void main(String[] args) {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query=session.createQuery("from WebserviceResourceAccessLog where resourceAccessTime> '2017-07-02' and  resourceAccessTime<='2017-07-03' and methodName='POST' and responseStatusCode=200");
		List<WebserviceResourceAccessLog> weblist=query.list();
		System.out.println(weblist.size());
		session.getTransaction().commit();
		session.close();

	}

}
