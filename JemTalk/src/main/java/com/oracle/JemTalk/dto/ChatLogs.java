package com.oracle.JemTalk.dto;

import java.util.List;

import lombok.Data;

@Data
public class ChatLogs {
	private List<ChatMessage> chatMessages;
}
