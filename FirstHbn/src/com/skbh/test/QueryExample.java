package com.skbh.test;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.skbh.pojo.Robot;

public class QueryExample {

	public static void main(String[] args) {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query=session.createQuery("from Robot where robotId=1");
		Robot robot=(Robot) query.uniqueResult();
		System.out.println(robot.getRobotId());
		robot.setRobotName("Robot228 India");
		
		session.getTransaction().commit();
		System.out.println(robot.getClass());
		session.close();
	}


}
