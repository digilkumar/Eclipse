package com.assignment.util;

import java.util.Scanner;

public class KeyboardUtil {
	private KeyboardUtil() {
		
	}
	
	@SuppressWarnings("resource")
	public static String getString(String detail) {
		System.out.println(detail + " : ");
		Scanner scan = new Scanner(System.in);
		return scan.nextLine();
	}
	
	@SuppressWarnings("resource")
	public static String getUpdateString(String detail, String data) {
		System.out.println(detail + " ("+data+") :");
		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine();
		if(input.trim().length()==0) {
			return data;
		}
		else return input;
		
	}
}
