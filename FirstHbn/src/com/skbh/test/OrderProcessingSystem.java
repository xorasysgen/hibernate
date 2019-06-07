package com.skbh.test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditQuery;
import org.hibernate.query.Query;

import com.skbh.pojo.Item;
import com.skbh.pojo.PurchaseOrder;


public class OrderProcessingSystem {

	public static void main(String[] args) {
		//new OrderProcessingSystem().getNPlus1ListOfOrderByCriteria(); //  N + 1 problem
		//new OrderProcessingSystem().getListOfOrder();// by join fetch
		new OrderProcessingSystem().getListOfOrderByCriteria();// by criteria api 
		//new OrderProcessingSystem().savePurchageOrder();
	/*try {
			new OrderProcessingSystem().OrderUpdate(5);
		} catch (InterruptedException exceptionObject) {
			// TODO Auto-generated catch block
			exceptionObject.printStackTrace();
		}*/
		//new OrderProcessingSystem().QueryOrder(5);
		
		new OrderProcessingSystem().AuditedReader();
		
	}
	
	private void getNPlus1ListOfOrderByCriteria() {
		SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
		Session session=sessionFactory.openSession();
		List<PurchaseOrder> list=session.createQuery("from PurchaseOrder").list();
		for (PurchaseOrder purchaseOrder : list) {
			System.out.println("Order Name=" + purchaseOrder.getOrderName() + " size" + purchaseOrder.getItem().size());
			List<Item> item=purchaseOrder.getItem();
			for (Item item2 : item) {
				System.out.println("Name#" + item2.getItemName() +  " Qnt# "+item2.getQuantity());
			}
			System.out.println("__________________________________________________");
		}
		session.close();
		sessionFactory.close();
		
	}

	private void OrderUpdate(Integer orderId) throws InterruptedException{
		SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
		Session session=sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		PurchaseOrder p=session.get(PurchaseOrder.class, orderId);
		System.out.println("before old update");
		p.setOrderName("audited update");
		session.update(p);
		tx.commit();
		System.out.println("after old update");
		session.close();
		session=sessionFactory.openSession();
		PurchaseOrder newOrder=session.get(PurchaseOrder.class, orderId);
		p.setOrderName(newOrder.getOrderName() + " # Final Order update Audited");
		//newOrder.setOrderName("Home Order");
		tx = session.beginTransaction();
		PurchaseOrder managedOrder=(PurchaseOrder) session.merge(p);
		Thread.currentThread().sleep(5000);
		//managedOrder.setOrderName("Final Order Name exe");
		tx.commit();
		session.close();
		sessionFactory.close();
	}
	
	
	private void QueryOrder(Integer orderId){
		SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
		Session session=sessionFactory.openSession();
		Query q=session.createQuery("from PurchaseOrder where id=:id");
		q.setParameter("id", orderId);
		PurchaseOrder o=(PurchaseOrder) q.uniqueResult();
		List<Item> i=o.getItem();
		for (Item item : i) {
			System.out.println("ItemName" + item.getItemName());
			System.out.println("Quatity" + item.getQuantity());
			System.out.println("_________________________________");
		}
		session.close();
		sessionFactory.close();
	}
	
	
	private Integer savePurchageOrder() {
		SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
		Session session=sessionFactory.openSession();
		Item i=new Item();
		List<Item> items= new ArrayList<Item>();
		PurchaseOrder order=new PurchaseOrder();
		i.setItemName("Pen");
		i.setQuantity(5);
		i.setPurchaseOrder(order);
		order.setItem(items);
		items.add(i);
		order.setOrderName("Office Order");
		Item i2=new Item();
		i2.setItemName("Water bottle");
		i2.setQuantity(2);
		i2.setPurchaseOrder(order);
		items.add(i2);
		Item i3=new Item();
		i3.setItemName("Water Glass");
		i3.setQuantity(10);
		i3.setPurchaseOrder(order);
		items.add(i3);
		/*List<Item> items2= new ArrayList<>();
		PurchaseOrder order2=new PurchaseOrder();
		Item i2=new Item();
		items2.add(i2);
		order2.setOrderName("second Order");
		i2.setItemName("Dove Bigy");
		i2.setQuantity(4);
		i2.setPurchaseOrder(order2);
		order2.setItem(items2);*/
		Transaction tx=session.beginTransaction();
		Integer x=(Integer) session.save(order);
		//Integer x2=(Integer) session.save(order2);
		tx.commit();
		session.close();
		sessionFactory.close();
		return x;
	}

	private void getListOfOrder() {
		SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
		Session session=sessionFactory.openSession();
		List<PurchaseOrder> list=session.createQuery("select distinct  po from PurchaseOrder po left join fetch po.item i").list();
		for (PurchaseOrder purchaseOrder : list) {
			System.out.println("Order Name=" + purchaseOrder.getOrderName() + " size" + purchaseOrder.getItem().size());
			List<Item> item=purchaseOrder.getItem();
			for (Item item2 : item) {
				System.out.println("Name#" + item2.getItemName() +  " Qnt# "+item2.getQuantity());
			}
			System.out.println("__________________________________________________");
		}
		session.close();
		sessionFactory.close();
	}
	
	private void getListOfOrderByCriteria() {
		SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
		Session session=sessionFactory.openSession();
		
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<PurchaseOrder> criteriaQuery=  builder.createQuery(PurchaseOrder.class);
		
		Root<PurchaseOrder> root=criteriaQuery.from(PurchaseOrder.class);
		root.fetch("item", JoinType.LEFT);
		
		criteriaQuery.select(root).distinct(true);
		
		Query<PurchaseOrder> p=session.createQuery(criteriaQuery);
		List<PurchaseOrder> list=p.getResultList();
		
		
		for (PurchaseOrder purchaseOrder : list) {
			System.out.println("Order Name=" + purchaseOrder.getOrderName() + " size" + purchaseOrder.getItem().size());
			List<Item> item=purchaseOrder.getItem();
			for (Item item2 : item) {
				System.out.println("Name#" + item2.getItemName() +  " Qnt# "+item2.getQuantity());
			}
			System.out.println("__________________________________________________");
		}
		session.close();
		sessionFactory.close();
	}
	
	private void AuditedReader() {
		SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
		Session session=sessionFactory.openSession();
		AuditReader reader = AuditReaderFactory.get(session);
		AuditQuery query = reader.createQuery()
				  .forEntitiesAtRevision(PurchaseOrder.class,13);
		List<PurchaseOrder> p=query.getResultList();
		for (PurchaseOrder purchaseOrder : p) {
			System.out.println(purchaseOrder.getOrderName());
		}
		Serializable s;
		sessionFactory.close();
	}
}
