package com.notes.test;

import java.sql.SQLException;

import com.notes.dao.NotesManagerDao;
import com.notes.dao.impl.NotesManagerDaoImpl;
import com.notes.entity.UserItem;

public class Test {
	

//	public static void main(String[] args) throws ClassNotFoundException, SQLException {
//		Class.forName("org.h2.Driver");
//		Connection conn = DriverManager.getConnection("jdbc:h2:./WebContent/assets/test", "sa", "");
//		
//		PreparedStatement ps = conn.prepareStatement("select * from users");
//		ResultSet rs = ps.executeQuery();
//		while(rs.next()) {
//			System.out.println(rs.getString(2));
//		}
//	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
//		UserDao u = new UserDaoImpl();
//		System.out.println(u.validateUser("digilkumar", "test1"));
		UserItem userItem = new UserItem();
		userItem.setPassword("sumesh");
		userItem.setUsername("digilkumar1");
		
		NotesManagerDao nm = new NotesManagerDaoImpl();
		System.out.println(nm.createUser(userItem));
	}
	
	
}
