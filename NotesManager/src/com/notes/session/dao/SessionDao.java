package com.notes.session.dao;

import java.sql.SQLException;

public interface SessionDao {
	public boolean deleteSession(String session_id) throws SQLException, ClassNotFoundException;
	public String createSession(String username,String passwordHash) throws SQLException, ClassNotFoundException;
	public boolean extendSession(String session_id) throws SQLException, ClassNotFoundException;
	public boolean validateSession(String username, String session_id) throws SQLException, ClassNotFoundException;
}
