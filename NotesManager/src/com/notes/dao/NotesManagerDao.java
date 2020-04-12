package com.notes.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.notes.entity.NoteItem;
import com.notes.entity.ResponseEntity;

public interface NotesManagerDao {
	public ResponseEntity addNote(NoteItem item) throws ClassNotFoundException, SQLException;
	
	public ResponseEntity deleteNote(NoteItem item) throws ClassNotFoundException, SQLException;
	
	public ResponseEntity editNote(NoteItem item) throws ClassNotFoundException, SQLException;
	
	public ArrayList<NoteItem> getByAuthor(NoteItem item) throws ClassNotFoundException, SQLException;
	
	
}
