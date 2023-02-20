package com.oracle.JemTalk.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oracle.JemTalk.dao.ChatDao;
import com.oracle.JemTalk.dto.ChatMessage;
import com.oracle.JemTalk.dto.ChatRoom;
import com.oracle.JemTalk.dto.ChatUser;
import com.oracle.JemTalk.dto.LeaveRoom;
import com.oracle.JemTalk.dto.MainPageInfo;
import com.oracle.JemTalk.dto.RoomDetail;
import com.oracle.JemTalk.dto.SearchUser;
import com.oracle.JemTalk.dto.ChatMessage.MessageType;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatServiceImpl implements ChatService {
	private final ChatDao chatDao;
	private final KafkaProducer kafkaProducer;
	

	@Override
	public void getMainPageInfo(MainPageInfo mainPageinfo) {
		chatDao.getMainPageInfo(mainPageinfo);
		return;
	}



	@Override
	public void EnterRoom(RoomDetail roomDetail) {
		try {
			chatDao.EnterRoom(roomDetail);
			ChatMessage chatMessage = new ChatMessage(MessageType.ENTER, roomDetail.getRoom_num(), roomDetail.getLast_log(), roomDetail.getUser_num());
			kafkaProducer.sendMessage(chatMessage);
			System.out.println(roomDetail);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return;
	}



	@Override
	public void saveChatRoom(ChatMessage chatMessage) {
		chatDao.saveRoomInfo(chatMessage);
	}



	@Override
	public List<ChatUser> findUserList(SearchUser searchUser) {
		List<ChatUser> chatUsers = chatDao.findUserList(searchUser);
		return chatUsers;
	}



	@Override
	public void createRoomResult(RoomDetail roomDetail) {

		chatDao.createRoomResult(roomDetail);
		return;
	}



	@Override
	public void saveMessage(ChatMessage chatMessage) {
		chatDao.saveMessage(chatMessage);
		return;
	}



	@Override
	public void chatRoomLeave(LeaveRoom leaveRoom) {
		chatDao.chatRoomLeave(leaveRoom);
		return;
	}



	@Override
	public List<ChatUser> findFriendList(SearchUser searchUser) {
		List<ChatUser> chatUsers = chatDao.findFriendList(searchUser);
		return chatUsers;
	}



	
}
