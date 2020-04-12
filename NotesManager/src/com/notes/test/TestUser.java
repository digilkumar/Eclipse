package com.notes.test;

import java.sql.SQLException;

import com.notes.dao.NotesManagerDao;
import com.notes.dao.impl.NotesManagerDaoImpl;
import com.notes.entity.NoteItem;
import com.notes.session.dao.SessionDao;
import com.notes.session.dao.impl.SessionDaoImpl;

public class TestUser {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
//		UserDao u = new UserDaoImpl();
//		System.out.println(u.validateUser("digilkumar","test1"));
//		
		SessionDao s = new SessionDaoImpl();
		
		NotesManagerDao n = new NotesManagerDaoImpl();
		
		//System.out.println(s.createSession("digilkumar", "test"));
		//System.out.println(s.deleteSession("d69bcf2c-8c57-4e94-8404-64d5acbd27d1"));
		NoteItem n1 =new NoteItem();
		n1.setAuthor("digilkumar");
		n1.setUsername("digilkumar");
		n1.setSessionId("e9991522-77b3-40a8-affa-1090d3902bbe");
		System.out.println(n.getByAuthor(n1));
		
	}

}
