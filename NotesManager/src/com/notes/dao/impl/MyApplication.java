package com.notes.dao.impl;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("resources")
public class MyApplication extends ResourceConfig {
	public MyApplication() {
		packages("com.notes.dao.impl.NotesManagerDaoImpl");
	}
}
