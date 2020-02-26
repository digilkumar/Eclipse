package com.bear.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.bear.dao.BearDao;
import com.bear.entity.BearEntity;
import com.bear.util.DbUtil;

public class BearDaoImpl implements BearDao{

	@Override
	public boolean addBear(BearEntity bear) throws ClassNotFoundException, SQLException {
		
		Connection conn = DbUtil.getConnection();
		try(PreparedStatement ps = conn.prepareStatement("insert into beartable values(?,?,?)")){
			ps.setInt(1, bear.getAgn());
			ps.setString(2, bear.getName());
			ps.setString(3, bear.getType());
			ps.executeUpdate();
			DbUtil.closeUtil(conn);
			DbUtil.closeUtil(ps);
		}
		return true;
	}

	@Override
	public boolean deleteBear(int agn) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateBear(int agn, BearEntity bear) {
		// TODO Auto-generated method stub
		return false;
	}

}
