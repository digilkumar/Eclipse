package com.bear.entity;

import java.sql.SQLException;

import com.bear.dao.BearDao;
import com.bear.dao.impl.BearDaoImpl;

public class Test {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		BearDao beardao = new BearDaoImpl();
		BearEntity bear = new BearEntity();
		bear.setAgn(3);
		bear.setName("Ida");
		bear.setType("Brown");
		beardao.addBear(bear);
		
	}
}  
