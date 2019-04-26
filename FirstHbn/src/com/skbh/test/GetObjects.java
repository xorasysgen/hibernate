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
		Robot robot = new Robot();
		//Robot robot1 = (Robot) session.get(Robot.class, 1);
	
		robot.setRobotName("Robot India");
		robot.setRobotPrice(256986.0);
		session.save(robot);
		session.getTransaction().commit();
		session.close();
		System.out.println("Robot name : " + robot.getRobotName());
		System.out.println("robot price : " + robot.getRobotPrice());
		System.out.println("Robot class name: " + robot.getClass());
		sessionFactory.close();
	}

	/*
	 * robot1.setRobotId(2); robot1.setRobotName("invincible Bot");
	 * robot1.setRobotPrice(545433d); robot2=(Robot) session.merge(robot1);
	 * robot2.setRobotPrice(9999d); robot1.setRobotName("AFTER");
	 */
}
