package com.skbh.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.skbh.pojo.Robot;

public class GetObjects {

	public static void main(String[] args) {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Robot robot1 = (Robot) session.load(Robot.class, 20);
		System.out.println(robot1.getClass().getName());
		session.getTransaction().commit();
		session.close();
		System.out.println(robot1.getRobotName());
		sessionFactory.close();
		
	}

	/*
	 * robot1.setRobotId(2); robot1.setRobotName("invincible Bot");
	 * robot1.setRobotPrice(545433d); robot2=(Robot) session.merge(robot1);
	 * robot2.setRobotPrice(9999d); robot1.setRobotName("AFTER");
	 */
}
