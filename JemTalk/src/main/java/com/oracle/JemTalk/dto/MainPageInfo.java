package com.oracle.JemTalk.dto;

import java.util.List;

import com.oracle.JemTalk.dto.ChatRoom;
import com.oracle.JemTalk.dto.ChatUser;

import lombok.Data;

@Data
public class MainPageInfo {
	private Long user_num;
	private List<ChatRoom> chatRooms;
	private List<ChatUser> chatFriends;
	private int totCnt;
	
	public MainPageInfo(Long user_num) {
		this.user_num = user_num;
	}
	
	public MainPageInfo(List<ChatRoom> chatRooms, List<ChatUser> chatFriends) {
		this.chatRooms = chatRooms;
		this.chatFriends = chatFriends;
	}
}
