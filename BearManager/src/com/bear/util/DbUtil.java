package com.bear.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DbUtil {
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
	
		Class.forName("org.h2.Driver");
		Connection conn = DriverManager.getConnection("jdbc:h2:~/test","sa","");
		return conn;
		
	}
	
	public static void closeUtil(Connection conn, PreparedStatement ps) throws SQLException {
		conn.close();
		ps.close();
	}
}
