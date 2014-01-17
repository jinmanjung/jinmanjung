package com.androidside.Messenger;

import java.io.File;
import java.io.Serializable;

public class MsgHeader implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int SEND_STRING = 10;
	public static final int RECV_STRING = 20;
	public static final int SEND_STRING_RESULT = 30;
	public static final int RECV_STRING_RESULT = 40;
	public static final int SEND_FILE = 50;
	public static final int RECV_FILE = 60;
	public static final int SEND_FILE_RESULT = 70;
	public static final int RECV_FILE_RESULT = 80;

	public static final int RESULT_OK = 100;
	public static final int RESULT_ERROR = 110;
	
	public int type;
	public String name;
	public long size;
	public int result;
	
	MsgHeader() {
		type = 0;
		name = null;
		size = 0;
		result = 0;
	}
	
	public void makeHeader(String str) {
		type = SEND_STRING;
		name = null;
		size = str.length();
		result = 0;
	}

	public void makeHeader(File f) {
		type = SEND_FILE;
		name = f.getName();
		size = f.length();
		result = 0;
	}
}
