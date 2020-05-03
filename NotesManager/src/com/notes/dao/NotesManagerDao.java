package com.notes.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.notes.entity.NoteItem;
import com.notes.entity.ResponseCreateUser;
import com.notes.entity.ResponseEntity;
import com.notes.entity.UserItem;

public interface NotesManagerDao {
	public ResponseEntity addNote(NoteItem item) throws ClassNotFoundException, SQLException;
	
	public ResponseEntity deleteNote(NoteItem item) throws ClassNotFoundException, SQLException;
	
	public ResponseEntity editNote(NoteItem item) throws ClassNotFoundException, SQLException;
	
	public ArrayList<NoteItem> getByAuthor(NoteItem item) throws ClassNotFoundException, SQLException;
	
	public ResponseCreateUser createUser(UserItem userItem) throws ClassNotFoundException, SQLException;
	
}
