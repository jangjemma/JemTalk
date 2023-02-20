package com.oracle.JemTalk.dto;

import lombok.Data;

@Data
public class ChatUser {
	private Long user_num;
	private String user_name;
	private String user_id;
	private String password;
	private String user_mail;
	private String role;
	private String connect_room;
	
	public ChatUser(Long user_num, String user_name) {
		this.user_num = user_num;
		this.user_name = user_name;
	}
}
