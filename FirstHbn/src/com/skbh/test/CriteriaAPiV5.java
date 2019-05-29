package com.skbh.test;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.skbh.pojo.Department;
import com.skbh.pojo.Employee;

public class CriteriaAPiV5 {

	
	private static void functionTest() {
		
		SessionFactory sessionFactory= new Configuration().configure().buildSessionFactory();
		Session session=sessionFactory.openSession();
		
		try{
		CriteriaBuilder builder = session.getCriteriaBuilder();

        // Count number of employees
        CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
        Root<Employee> root = criteriaQuery.from(Employee.class);
        criteriaQuery.select(builder.count(root));
        Query<Long> query = session.createQuery(criteriaQuery);
        long count = query.getSingleResult();
        System.out.println("Count = " + count);

        // Get max salary
        CriteriaQuery<Integer> criteriaQuery2 = builder.createQuery(Integer.class);
        Root<Employee> root2 = criteriaQuery2.from(Employee.class);
        criteriaQuery2.select(builder.max(root2.get("salary")));
        Query<Integer> query2 = session.createQuery(criteriaQuery2);
        int maxSalary = query2.getSingleResult();
        System.out.println("Max Salary = " + maxSalary);

        // Get Average Salary
        CriteriaQuery<Double> criteriaQuery3 = builder.createQuery(Double.class);
        Root<Employee> root3 = criteriaQuery3.from(Employee.class);
        criteriaQuery3.select(builder.avg(root3.get("salary")));
        Query<Double> query3 = session.createQuery(criteriaQuery3);
        double avgSalary = query3.getSingleResult();
        System.out.println("Average Salary = " + avgSalary);

        // Count distinct employees
        CriteriaQuery<Long> criteriaQuery4 = builder.createQuery(Long.class);
        Root<Employee> root4 = criteriaQuery4.from(Employee.class);
        criteriaQuery4.select(builder.countDistinct(root4));
        Query<Long> query4 = session.createQuery(criteriaQuery4);
        long distinct = query4.getSingleResult();
        System.out.println("Distinct count = " + distinct);
        
	}
	catch(HibernateException he) {
		System.out.println(he);
	}
	finally{
		session.close();
		sessionFactory.close();
	}
	}
	
	
	private static void getfewData() {
		
		SessionFactory sessionFactory= new Configuration().configure().buildSessionFactory();
		Session session=sessionFactory.openSession();
		try{
			CriteriaBuilder builder = session.getCriteriaBuilder();
	         CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
	         Root<Employee> root = query.from(Employee.class);
	         query.multiselect(root.get("name"),root.get("designation"));
	         Query<Object[]> q=session.createQuery(query);
	         List<Object[]> list=q.getResultList();
	         for (Object[] objects : list) {
	            System.out.println("Name : "+objects[0]);
	            System.out.println("Designation : "+objects[1]);
	         }
		}
			catch(HibernateException he) {
				System.out.println(he);
			}
			finally{
				session.close();
				sessionFactory.close();
			}
	}
	
	
	private static void getEmployee() {
		SessionFactory sessionFactory= new Configuration().configure().buildSessionFactory();
		Session session=sessionFactory.openSession();
		
		try{
			CriteriaBuilder builder = session.getCriteriaBuilder();
	         CriteriaQuery<Employee> query = builder.createQuery(Employee.class);
	         Root<Employee> root = query.from(Employee.class);
	         query.select(root);
	         Query<Employee> q=session.createQuery(query);
	         List<Employee> employees=q.getResultList();
	         for (Employee employee : employees) {
	            System.out.println(employee.getName());
	         }
			}
			catch(HibernateException he) {
				System.out.println(he);
			}
			finally{
				session.close();
				sessionFactory.close();
			}
			
	} 
	
	
	private static void saveDepartment() {
		SessionFactory sessionFactory= new Configuration().configure().buildSessionFactory();
		Session session=sessionFactory.openSession();
		Department d=new Department();
		Employee e=new Employee();
		e.setName("susanta Kumar pradhan");e.setDesignation("Sr consultant");e.setSalary(60000);e.setDepartment(d);
		
		Employee e1=new Employee();
		e1.setName("kandarp sudan kumar");e1.setDesignation("SR sale excutive");e1.setSalary(72000);e1.setDepartment(d);
		
		Employee e2=new Employee();
		e2.setName("Dinesh singh");e2.setDesignation("Sale head");e2.setSalary(196000);e2.setDepartment(d);
		d.setName("Managment Department");
		
		d.getEmployees().add(e);
		d.getEmployees().add(e1);
		d.getEmployees().add(e2);
		try{
		session.save(d);
		}
		catch(HibernateException he) {
			System.out.println(e);
		}
		finally{
			session.close();
			sessionFactory.close();
		}
		
		
	}
	
	
	public static void main(String[] args) {
		//CriteriaAPiV5.saveDepartment();
		//CriteriaAPiV5.getEmployee();
		//CriteriaAPiV5.getfewData();
		//CriteriaAPiV5.functionTest();
		

	}

	

}
