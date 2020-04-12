package com.notes.session.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import com.notes.session.dao.SessionDao;
import com.notes.users.dao.UserDao;
import com.notes.users.dao.impl.UserDaoImpl;
import com.notes.util.DbUtil;

public class SessionDaoImpl implements SessionDao {
	
	

	@Override
	public boolean deleteSession(String session_id) throws SQLException, ClassNotFoundException {
		Connection conn = DbUtil.getConnection();
		PreparedStatement ps = conn.prepareStatement("delete from sessions where session_id=?");
		ps.setString(1, session_id);
		ps.executeUpdate();
		DbUtil.closeUtil(conn, ps);
		return true;
	}

	@Override
	public String createSession(String username, String password) throws SQLException, ClassNotFoundException {
		UserDao u = new UserDaoImpl();
		if(u.validateUser(username,password)) {
		Connection conn = DbUtil.getConnection();
		PreparedStatement ps = conn.prepareStatement("insert into sessions(session_id,username,created_date,expiry_date) values (?,?,current_timestamp,timestampadd(minute,15,current_timestamp))");
		String session_id = UUID.randomUUID().toString();
		ps.setString(1, session_id);
		ps.setString(2, username);
		ps.executeUpdate();
		DbUtil.closeUtil(conn, ps);
		return session_id;
		}else {
		
		return "failure";
		}
	}

	@Override
	public boolean extendSession(String session_id) throws ClassNotFoundException, SQLException {
		Connection conn = DbUtil.getConnection();
		PreparedStatement ps = conn.prepareStatement("update sessions set expiry_date=timestampadd(minute,15,current_timestamp)");
		ps.executeUpdate();
		DbUtil.closeUtil(conn, ps);
		return true;
	}

	@Override
	public boolean validateSession(String username, String session_id) throws ClassNotFoundException, SQLException {
		Connection conn = DbUtil.getConnection();
		PreparedStatement ps = conn.prepareStatement("SELECT count(*) FROM SESSIONS where username=? and session_id=? and expiry_date>current_timestamp");
		
		ps.setString(1, username);
		ps.setString(2, session_id);
		ResultSet rs = ps.executeQuery();
		int count = 0;
		while(rs.next()) {
			count = rs.getInt(1);
				
		}
		DbUtil.closeUtil(conn, ps, rs);
	
		if(count>=1) {
			return true;
		}
		else 
			return false;
	}

}
