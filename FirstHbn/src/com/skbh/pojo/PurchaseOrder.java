package com.skbh.pojo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.SelectBeforeUpdate;
import org.hibernate.envers.Audited;

@Entity
@SelectBeforeUpdate
@Audited
public class PurchaseOrder {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Integer id;
	
	String OrderName;
	// mapped by is similar to inverse="true" inverse means relationship owner //fetch=FetchType.LAZY
    @OneToMany(cascade=CascadeType.ALL,mappedBy="purchaseOrder",orphanRemoval=true,fetch=FetchType.LAZY)
    List<Item> item;
        
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public List<Item> getItem() {
		return item;
	}
	public void setItem(List<Item> item) {
		this.item = item;
	}
	public String getOrderName() {
		return OrderName;
	}
	public void setOrderName	(String orderName) {
		OrderName = orderName;
	}
	
	
    
}
