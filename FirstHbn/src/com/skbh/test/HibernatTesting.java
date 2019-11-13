package com.skbh.test;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.skbh.pojo.Robot;

public class HibernatTesting {

	public static void main(String[] args) {
		SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		try {
		//Robot robot1=session.get(Robot.class, 1);
		Robot robot=new Robot();
		robot.setRobotName("china Roobo");
		robot.setRobotPrice(9000d);
		session.save(robot);
		System.out.println(robot);
		//session.clear();
		//robot.setRobotPrice(256966.0);
		//session.update(robot);
		//session.evict(robot);// REMOVE FROM SESSION CACHE
/*		robot.setRobotName("Mr Cisco");
		robot.setRobotPrice(0d);
*/		//Robot robot1=(Robot) session.merge(robot);
		System.out.println("Robot name : " + robot.getRobotName());
		System.out.println("robot price : " + robot.getRobotPrice());
	
		tx.commit();
		}
		catch(HibernateException he) {
			he.getStackTrace();
			
		}finally {
			session.close();
			sessionFactory.close();
			
		}
		
		
	}

}
