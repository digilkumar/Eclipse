package com.notes.users.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

import com.notes.users.dao.UserDao;
import com.notes.util.DbUtil;

public class UserDaoImpl implements UserDao{

	@Override
	public boolean validateUser(String username, String password) throws ClassNotFoundException, SQLException {
		
		String encodedPass = new String(Base64.getEncoder().encode(password.getBytes()));
		int count=0;
		
		Connection conn = DbUtil.getConnection();
		
		PreparedStatement ps = conn.prepareStatement("select count(*) from users where username=? and password_str=?");
		ps.setString(1, username);
		ps.setString(2, encodedPass);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			count = rs.getInt(1);
		}
		DbUtil.closeUtil(conn, ps);
		
		if(count==1) {
			return true;
		}
		else
			return false;
		
		
		
	}

	
	
}
