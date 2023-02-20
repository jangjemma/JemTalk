package com.oracle.JemTalk.service;



import java.time.LocalDate;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import javax.annotation.PostConstruct;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.oracle.JemTalk.dao.ChatDao;
import com.oracle.JemTalk.dto.ChatMessage;
import com.oracle.JemTalk.dto.ChatRoom;
import com.oracle.JemTalk.dto.ChatMessage.MessageType;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaProducer {

	private final KafkaTemplate<String, ChatMessage> kafkaTemplate;
	private AdminClient adminClient;
	private final ChatDao chatDao;
	
	@PostConstruct
	private void init() throws InterruptedException, ExecutionException {
		Properties props = new Properties();
		props.setProperty("bootstrap.servers", "localhost:9092");
		adminClient = AdminClient.create(props);
	}
	
	public void sendMessage(ChatMessage chatMessage) {
		log.info("kafkaProducer start...");
		
		try {
			if(chatMessage.getType() == MessageType.CREATE) {
				//NewTopic newTopic = new NewTopic("jemtalk.topic." + chatMessage.getRoom_num(), 1, (short) 1);
				//adminClient.createTopics(Collections.singleton(newTopic)).all().get();
				System.out.println("생성은 공사중");
			}
			
			
			//ListenableFuture<SendResult<String, ChatMessage>> future = kafkaTemplate.send("jemtalk.topic." + chatMessage.getRoom_num(), chatMessage);
			ListenableFuture<SendResult<String, ChatMessage>> future = kafkaTemplate.send("jemtalk.topic.talk", chatMessage);
			future.addCallback(new ListenableFutureCallback<SendResult<String, ChatMessage>>() {
				
				@Override
				public void onSuccess(SendResult<String, ChatMessage> result) {
//					chatMessage.setLog_num(result.getRecordMetadata().offset() + 1);
//					chatMessage.setSend_date(LocalDate.now());
					//chatMessage.setNot_read_cnt((long) (chatMessage.getUser_nums().size()-1));
					log.info("Send Success : " + chatMessage + " with offset: " + (result.getRecordMetadata().offset() +1));
					//chatDao.saveMessage(chatMessage);
				}

				@Override
				public void onFailure(Throwable e) {
					log.error("Send Fail1 : " + e.getMessage());
				}

				
			});
		} catch (Exception e) {
			log.error("Send Fail2 : " + e.getMessage());
		}
	}
	
//	public void CreateRoom(ChatRoom chatRoom) {
//		log.info("kafkaProducer start...");
//		try {
//			NewTopic newTopic = new NewTopic("jemtalk-topic-"+chatRoom.getRoom_num(), 1, (short) 1);
//			adminClient.createTopics(Collections.singleton(newTopic)).all().get();
//			
//			ListenableFuture<SendResult<String, ChatMessage>> future = kafkaTemplate.send("jemtalk-topic-"+chatRoom.getRoom_num(), chatRoom);
//			future.addCallback(new ListenableFutureCallback<SendResult<String, ChatMessage>>() {
//
//				@Override
//				public void onSuccess(SendResult<String, ChatMessage> result) {
//					log.info("Send Success : " + chatRoom + " with offset: " + result.getRecordMetadata().offset());
//					chatRoom.setRoom_num(result.getRecordMetadata().offset());
//					chatDao.saveRoomInfo(chatRoom);
//				}
//
//				@Override
//				public void onFailure(Throwable e) {
//					log.error("Send Fail : " + e.getMessage());
//				}
//
//				
//			});
//		} catch (Exception e) {
//			log.error("Send Fail : " + e.getMessage());
//		}
//	}
}
