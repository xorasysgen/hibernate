package com.skbh.test;


import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.impl.crypto.MacProvider;

import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.skbh.pojo.KeyByteArray;
import com.skbh.pojo.Robot;

public class HibernatFileTesting {

	public static void main(String[] args) {
		SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		/*SecretKey  key = MacProvider.generateKey(SignatureAlgorithm.HS512);
		String compactJws=tokenGenerator(key);
		System.out.println(compactJws);
		System.out.println("getFormat # " + key.getFormat());
		System.out.println("getAlgorithm # " + key.getAlgorithm());
		System.out.println("getEncoded # " + key.getEncoded());
	    byte[] data = key.getEncoded();
	    KeyByteArray keyToSave=new KeyByteArray();
	    keyToSave.setKeyData(data);
	    keyToSave.setCompactJws(compactJws);
	    Integer id1=(Integer) session.save(keyToSave);*/
	    KeyByteArray retrieveddata=session.get(KeyByteArray.class, 4);
	    byte[] data1=retrieveddata.getKeyData();
	    SecretKey key2 = new SecretKeySpec(data1, 0, data1.length, "HmacSHA512");
	   /* System.out.println(key.equals(key2));*/
	    System.out.println("Decoded getFormat #" + key2.getFormat());
		System.out.println(" Decoded getAlgorithm #" + key2.getAlgorithm());
		System.out.println("Decoded getEncoded #" + key2.getEncoded());
		
		parseJWT(retrieveddata.getCompactJws(),key2);
		
		
		tx.commit();
		session.close();
		
	}
	
	private static String tokenGenerator(SecretKey key){
		/*Key key = MacProvider.generateKey(SignatureAlgorithm.HS512);*/
		 long nowMillis = System.currentTimeMillis();
		    Date now = new Date(nowMillis);
		    long ttlMillis=36000000;
	
		JwtBuilder builder = Jwts.builder().setId("8968")
                .setIssuedAt(now)
                .setSubject("subject-sso-net")
                .setIssuer("issuer-name")
                .signWith(SignatureAlgorithm.HS512, key);
		if (ttlMillis >= 0) {
		    long expMillis = nowMillis + ttlMillis;
		        Date exp = new Date(expMillis);
		        builder.setExpiration(exp);
		    }
		    	String compactJws =builder.compact();
		System.out.println("compactJws: "  + compactJws);
		return compactJws;
		
	}
	
	private static void parseJWT(String jwt,Key key) {
	  	try {
			Jws jwtClaims =Jwts.parser().setSigningKey(key).parseClaimsJws(jwt);
		 System.out.println(jwtClaims.getBody().toString());
		} catch (SignatureException e) {
			 System.out.println(e.getMessage());
			 System.out.println(e.getSuppressed());
		    //don't trust the JWT!
		}
		 
	}

}
