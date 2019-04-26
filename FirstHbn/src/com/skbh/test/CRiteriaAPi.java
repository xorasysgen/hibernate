package com.skbh.test;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import com.skbh.pojo.Robot;

public class CRiteriaAPi {

	public static void main(String[] args) {
		SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Robot> list=session.createCriteria(Robot.class)
				.add(Restrictions.ilike("robotName", "%test %"))
				.add(Restrictions.ge("robotPrice", 5686.00))
				.add(Restrictions.between("robotId", new Integer(7), new Integer(18)))
				.list();
		System.out.println(list.size());
		for (Robot robot : list) {
			System.out.println("Robot ID# " + robot.getRobotId());
			System.out.println("Robot Name# " + robot.getRobotName());
			System.out.println("Robot Price# " + robot.getRobotPrice());
			
		}
		
		tx.commit();
	}

}
