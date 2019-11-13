package com.skbh.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.skbh.pojo.Robot;

public class GetObjects {

	public static void main(String[] args) throws InterruptedException {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		try {
		Robot robot1 = (Robot) session.load(Robot.class, 4);
		System.out.println(robot1.getClass().getName());
		//Thread.currentThread().sleep(30000);
		robot1.setRobotName("One kuka4");
		session.beginTransaction();
		session.update(robot1);
		session.getTransaction().commit();
		session.close();
		System.out.println("sdfsdfsd" + robot1.getRobotName());
		System.out.println(robot1.getRobotPrice());
	}
	catch(HibernateException he) {
		System.out.println(he.getMessage());
		
	}finally {
		if(session.isOpen()) {
			session.close();
		}
		sessionFactory.close();
		
	}
	}

	/*
	 * robot1.setRobotId(2); robot1.setRobotName("invincible Bot");
	 * robot1.setRobotPrice(545433d); robot2=(Robot) session.merge(robot1);
	 * robot2.setRobotPrice(9999d); robot1.setRobotName("AFTER");
	 */
}
