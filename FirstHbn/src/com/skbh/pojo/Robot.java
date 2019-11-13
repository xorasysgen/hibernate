package com.skbh.pojo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;

@Entity
@Audited
public  class Robot  {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer robotId;
	private String robotName;
	private Double robotPrice;
	
	@Version
	@Type(type = "dbtimestamp")
	private Date version;
	
	
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


	public Robot() {}

	@Override
	public String toString() {
		return "Robot [robotId=" + robotId + ", robotName=" + robotName + ", robotPrice=" + robotPrice + "]";
	}
	

}
