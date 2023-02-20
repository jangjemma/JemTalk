package com.oracle.JemTalk.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.oracle.JemTalk.dto.ChatMessage;
import com.oracle.JemTalk.dto.ChatMessage.MessageType;
import com.oracle.JemTalk.dto.ChatRoom;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class KafkaConsumer {
	private final SimpMessageSendingOperations messagingTemplate;
	
	
	@KafkaListener(topicPattern = "jemtalk.topic.*",groupId = "jemtalk-group")
	public void consume(ConsumerRecord<String, ChatMessage> record) {
		System.out.println("receive topic : " + record.topic());
		System.out.println("receive toString : " + record.toString());
		System.out.println("receive value : " + record.value());
		System.out.println("receive partition : " + record.partition());
		System.out.println("receive offset : " + record.offset());
		ChatMessage chatMessage = record.value();
		System.out.println("chatMessage : " + chatMessage);
		if(chatMessage.getType() == MessageType.CREATE || chatMessage.getType() == MessageType.INVITE) {
			for(Long invite : chatMessage.getUser_nums()) {
				messagingTemplate.convertAndSend("/sub/user/" + invite, chatMessage);
			}
		}
		System.out.println(chatMessage.getRoom_num());
		messagingTemplate.convertAndSend("/sub/chat/room/" + chatMessage.getRoom_num(), chatMessage);
		
	}
	
//	@KafkaListener(topics = "jemtalk-create-room",groupId = "jemtalk-group")
//	public void create(ConsumerRecord<String, ChatRoom> record) {
//		System.out.println("receive topic : " + record.topic());
//		System.out.println("receive toString : " + record.toString());
//		System.out.println("receive value : " + record.value());
//		System.out.println("receive partition : " + record.partition());
//		ChatRoom chatRoom = record.value();
//		chatRoom.setRoom_num(record.offset());
//		System.out.println("chatRoom->" + chatRoom);
//	}
}
