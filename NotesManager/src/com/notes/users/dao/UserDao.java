package com.notes.users.dao;

import java.sql.SQLException;

public interface UserDao {
	boolean validateUser(String username, String password) throws ClassNotFoundException, SQLException;
}

