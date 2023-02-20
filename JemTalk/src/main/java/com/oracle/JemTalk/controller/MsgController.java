package com.oracle.JemTalk.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.servlet.http.HttpSession;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import com.oracle.JemTalk.dto.ChatMessage;
import com.oracle.JemTalk.dto.ChatMessage.MessageType;
import com.oracle.JemTalk.dto.ChatRoom;
import com.oracle.JemTalk.dto.ChatUser;
import com.oracle.JemTalk.service.ChatService;
import com.oracle.JemTalk.service.KafkaProducer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MsgController {
	
	private final KafkaProducer kafkaProducer;
	private final ChatService chatService;
	@MessageMapping("/chat/message")
	public void SendMessage(ChatMessage chatMessage) {
		chatMessage.setSend_date(LocalDateTime.now());
		log.info("chatMessage 송신->" + chatMessage);
		if(chatMessage.getType() == MessageType.CREATE) {
			chatMessage.setNot_read_cnt((long) (chatMessage.getUser_nums().size()-1));
			chatService.saveChatRoom(chatMessage);
		}else {
			chatService.saveMessage(chatMessage);
		}
		log.info("SendMessage start..");
		kafkaProducer.sendMessage(chatMessage);
	}
}
