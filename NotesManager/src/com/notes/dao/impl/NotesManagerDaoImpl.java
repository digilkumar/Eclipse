package com.notes.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.notes.dao.NotesManagerDao;
import com.notes.entity.NoteItem;
import com.notes.entity.ResponseCreateUser;
import com.notes.entity.ResponseEntity;
import com.notes.entity.UserItem;
import com.notes.session.dao.SessionDao;
import com.notes.session.dao.impl.SessionDaoImpl;
import com.notes.util.DbUtil;

@Path("/notes")
public class NotesManagerDaoImpl implements NotesManagerDao {

	
	@Override
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity addNote(NoteItem item) throws SQLException, ClassNotFoundException {
		ResponseEntity re = new ResponseEntity();
		SessionDao s = new SessionDaoImpl();
		
		if(s.validateSession(item.getUsername(),item.getSessionId())) {
			
			if(item.getNote().isEmpty()||item.getNote()==null||item.getAuthor().isEmpty()||item.getNote().length()>499) {
			re.setStatus("failure");
			re.setErrorFlag("Y");
			re.setErrorCode("Empty note/author");
			return re;
		}
		else {
			Connection conn = DbUtil.getConnection();
			PreparedStatement ps = conn.prepareStatement("insert into notes (note,author,timestamp) values (?,?,current_timestamp)",Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, item.getNote());
			ps.setString(2, item.getAuthor());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			while(rs.next()) {
				re.setAgn(rs.getInt(1));
			}
			DbUtil.closeUtil(conn,ps,rs);
			
			re.setStatus("success");
			return re;
		}
		}else {
			re.setStatus("failure");
			re.setErrorFlag("Y");
			re.setErrorCode("Invalid session");
			return re;
		}
		
	}
	
	@Override
	@POST
	@Path("/getByAuthor")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<NoteItem> getByAuthor(NoteItem item) throws ClassNotFoundException, SQLException {
		ResponseEntity re = new ResponseEntity();
		ArrayList<NoteItem> ar = new ArrayList<NoteItem>();
		NoteItem n1;
		SessionDao s = new SessionDaoImpl();
		if(s.validateSession(item.getUsername(),item.getSessionId())) {
		
		Connection conn = DbUtil.getConnection();
		PreparedStatement ps = conn.prepareStatement("select * from notes where author=?");
		ps.setString(1, item.getAuthor());
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			n1 = new NoteItem();
			n1.setAuthor(rs.getString("author"));
			n1.setNote(rs.getString("note"));
			n1.setNoteAgn(rs.getInt("note_agn"));
			n1.setTimeStamp(rs.getTimestamp("timestamp"));
			ar.add(n1);
			System.out.println(n1.getTimeStamp());
			
			String formattedDate = new SimpleDateFormat("yyyyMMddHHmm").format(n1.getTimeStamp());
			System.out.println(formattedDate);
		}
		
		
		DbUtil.closeUtil(conn,ps);
		re.setStatus("success");
		return ar;
		
		}else {
			re.setStatus("failure");
			re.setErrorFlag("Y");
			re.setErrorCode("Invalid session");
			return ar;
		}
	}
	

	@Override
	@POST
	@Path("/delete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity deleteNote(NoteItem item) throws ClassNotFoundException, SQLException {
		ResponseEntity re = new ResponseEntity();
		SessionDao s = new SessionDaoImpl();
		if(s.validateSession(item.getUsername(),item.getSessionId())) {
		
		Connection conn = DbUtil.getConnection();
		PreparedStatement ps = conn.prepareStatement("delete from notes where note_agn = ?");
		ps.setInt(1, item.getNoteAgn());
		ps.executeUpdate();
		DbUtil.closeUtil(conn,ps);
		re.setStatus("success");
		return re;
		
		}else {
			re.setStatus("failure");
			re.setErrorFlag("Y");
			re.setErrorCode("Invalid session");
			return re;
		}
	}

	
	@Override
	@POST
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity editNote(NoteItem item) throws ClassNotFoundException, SQLException {
		ResponseEntity re = new ResponseEntity();
		SessionDao s = new SessionDaoImpl();
		if(s.validateSession(item.getUsername(),item.getSessionId())) {
		
		Connection conn = DbUtil.getConnection();
		PreparedStatement ps = conn.prepareStatement("update notes set note=? where note_agn=? and author=?");
		System.out.println(item.getNote()+item.getAuthor()+item.getNoteAgn());
		ps.setString(1, item.getNote());
		ps.setInt(2, item.getNoteAgn());
		ps.setString(3, item.getAuthor());
		ps.executeUpdate();
		DbUtil.closeUtil(conn, ps);
		re.setStatus("success");
		return re;
		}else {
			re.setStatus("failure");
			re.setErrorFlag("Y");
			re.setErrorCode("Invalid session");
			return re;
		}
	}

	@Override
	@POST
	@Path("/createuser")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseCreateUser createUser(UserItem userItem) throws ClassNotFoundException, SQLException {
		ResponseCreateUser r = new ResponseCreateUser();
		String username = userItem.getUsername();
		String password = userItem.getPassword();
		String encodedPass = new String(Base64.getEncoder().encode(password.getBytes()));
		int count=0;
		Connection conn1 = DbUtil.getConnection();
		PreparedStatement ps1 = conn1.prepareStatement("select count(*) from users where username=?");
		
		ps1.setString(1, username);
		ResultSet rs1 = ps1.executeQuery();
		while(rs1.next()) {
			count = rs1.getInt(1);
		}
		if(count==1) {
			r.setUserStatus("exists");
			return r;
		}else {
			Connection conn2 = DbUtil.getConnection();
			PreparedStatement ps2 = conn2.prepareStatement("insert into users (username,password_str,created_date) values (?,?,current_timestamp)");
			ps2.setString(1, username);
			ps2.setString(2, encodedPass);
			
			ps2.executeUpdate();
			r.setUserStatus("created");
			return r;
		}
		
		
	}
	
	
	
}
