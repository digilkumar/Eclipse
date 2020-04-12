package com.notes.entity;

import java.sql.Timestamp;

public class NoteItem {
	private int noteAgn;
	private String note;
	private String author;
	private Timestamp timeStamp;
	private String sessionId;
	private String username;
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getNoteAgn() {
		return noteAgn;
	}
	public void setNoteAgn(int noteAgn) {
		this.noteAgn = noteAgn;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Timestamp getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	
	

}
