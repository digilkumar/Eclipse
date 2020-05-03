package com.notes.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbUtil {

	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("org.h2.Driver");
		Connection conn = DriverManager.getConnection("jdbc:h2:./assets/test", "sa", "");
	
		return conn;
	}
	
	public static void closeUtil(Connection conn,PreparedStatement ps,ResultSet rs) throws SQLException {
		conn.close();
		ps.close();
		rs.close();
	}
	
	public static void closeUtil(Connection conn,PreparedStatement ps) throws SQLException {
		conn.close();
		ps.close();
		
	}
	
	
}
