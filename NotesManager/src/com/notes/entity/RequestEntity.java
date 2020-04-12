package com.notes.entity;

public class RequestEntity {
	private String sessionID;
	private String username;
	private NoteItem noteItem;
	public String getSessionID() {
		return sessionID;
	}
	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public NoteItem getNoteItem() {
		return noteItem;
	}
	public void setNoteItem(NoteItem noteItem) {
		this.noteItem = noteItem;
	}
	
}
