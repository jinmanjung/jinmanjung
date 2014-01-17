package com.androidside.Messenger;

public class ChatUserItem {
	private String name;
	private String time;
	private String msg;
	private int type;
	public ChatUserItem(String msg, int type) {
		this.msg = msg;
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getType() {
		return type;
	}
}
