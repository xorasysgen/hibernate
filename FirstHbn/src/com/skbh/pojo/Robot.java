package com.skbh.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class  Robot  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer robotId;
	private String robotName;
	private Double robotPrice;
	
	public Integer getRobotId() {
		return robotId;
	}
	public void setRobotId(Integer robotId) {
		this.robotId = robotId;
	}
	public String getRobotName() {
		return robotName;
	}
	public void setRobotName(String robotName) {
		this.robotName = robotName;
	}
	public Double getRobotPrice() {
		return robotPrice;
	}
	public void setRobotPrice(Double robotPrice) {
		this.robotPrice = robotPrice;
	}
	
	public Robot( String robotName, Double robotPrice) {
		this.robotName = robotName;
		this.robotPrice = robotPrice;
	}
	
	public Robot() {}
	

}
