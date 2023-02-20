package com.oracle.JemTalk.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class ChatRoom {
	private Long room_num;
	private String room_name;
	
	private String last_log;
	private LocalDate send_date;
	private String msg_cnt;
	

	public ChatRoom(Long room_num, String room_name, String last_log, LocalDate send_date, String msg_cnt) {
		this.room_num = room_num;
		this.room_name = room_name;
		this.last_log = last_log;
		this.send_date = send_date;
		this.msg_cnt = msg_cnt;
	}
}
