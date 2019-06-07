package com.skbh.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class KeyByteArray {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private byte[] keyData;
	private String compactJws; 

	public byte[] getKeyData() {
		return keyData;
	}

	public void setKeyData(byte[] keyData) {
		this.keyData = keyData;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCompactJws() {
		return compactJws;
	}

	public void setCompactJws(String compactJws) {
		this.compactJws = compactJws;
	}
	
	
}
