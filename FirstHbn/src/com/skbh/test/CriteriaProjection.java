package com.skbh.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.skbh.pojo.Event;

public class CriteriaProjection {

	public static void main(String[] args) {
		SessionFactory sessionfactory=new Configuration().configure().buildSessionFactory();
		Session session=sessionfactory.openSession();
		Transaction tx=session.beginTransaction();
		try {
			Event event =new Event();
		    /*event.setName("Private Event");
		    event.setDescription("General Motors Robotic Auto Event");
		    session.save(event);*/
			
			//event = (Event) session.get(Event.class, 6);
			event.setId(8);
			event.setName("#Auto Event");
			event.setDescription("Robo Motors Robotic Auto Event");
		    System.out.println(event.getName());
		    session.saveOrUpdate(event);
		    tx.commit();
		    session.close();
		    
		    event.setName("#after closing session Event");
		    
		    Session session1=sessionfactory.openSession();
			Transaction tx1=session1.beginTransaction();
		  Event event2 = (Event) session1.get(Event.class, 8);
		  event2.setName("testing");
		  
		  Event newEvent=(Event) session1.merge(event);
		  event.setDescription("Old");
		  event.setName("sdfsdf");
		  
		  tx1.commit();
		  session1.close();
		
		}
		catch(Exception e) {
			System.out.println(e);
		}finally{
			if(session.isOpen()) {
				//session.close();
			}
			sessionfactory.close();
		}
		
	}

}
