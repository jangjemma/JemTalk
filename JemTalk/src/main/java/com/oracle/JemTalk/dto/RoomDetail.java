package com.oracle.JemTalk.dto;

import java.util.List;

import lombok.Data;

@Data
public class RoomDetail {
	private Long room_num;
	private String room_name;
	private Long last_log;
	private Long user_num;
	private List<ChatUser> chatMembers;
	private List<ChatMessage> LastTwentyMessages;
}
