package com.bear.dao;

import java.sql.SQLException;

import com.bear.entity.BearEntity;

public interface BearDao {
	boolean addBear(BearEntity bear) throws ClassNotFoundException, SQLException;
	boolean deleteBear(int agn);
	boolean updateBear(int agn, BearEntity bear);
}
