package com.skbh.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.skbh.pojo.Event;

public class EventProcessor {

	public static void main(String[] args) {
		SessionFactory sessionfactory=new Configuration().configure().buildSessionFactory();
		Session session=sessionfactory.openSession();
		Transaction tx=session.beginTransaction();
		try {
			Event event =new Event();
			event.setVersion(1);
		    event.setName("UAE Summit Event");
		    event.setDescription("Next IT Event");
		    session.save(event);
		  
		  tx.commit();
		  session.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}finally{
			if(session.isOpen()) {
				session.close();
			}
			sessionfactory.close();
		}
		
	}

}
