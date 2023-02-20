package com.oracle.JemTalk.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.oracle.JemTalk.dto.ChatMessage;
import com.oracle.JemTalk.dto.ChatRoom;
import com.oracle.JemTalk.dto.ChatUser;
import com.oracle.JemTalk.dto.LeaveRoom;
import com.oracle.JemTalk.dto.MainPageInfo;
import com.oracle.JemTalk.dto.RoomDetail;
import com.oracle.JemTalk.dto.SearchUser;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ChatDaoImpl implements ChatDao {
	
	private final SqlSession session;
	
	

	@Override
	public void getMainPageInfo(MainPageInfo mainPageinfo) {
		log.info("getMainPageInfo start...");
		try {
			session.selectOne("getMainInfo", mainPageinfo);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		log.info("getMainPageInfo end...");
		return;
	}



	@Override
	public void EnterRoom(RoomDetail roomDetail) {
		log.info("EnterRoom start...");
		try {
			session.selectOne("EnterRoom", roomDetail);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		log.info("EnterRoom end...");
		return;
	}



	@Override
	public int saveMessage(ChatMessage chatMessage) {
		log.info("saveMessage start...");
		int result = 0;
		try {
			result = session.insert("saveMessage", chatMessage);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		log.info("saveMessage end...");
		return result;
	}



	@Override
	public int saveRoomInfo(ChatMessage chatMessage) {
		log.info("saveMessage start...");
		int result = 0;
		try {
			result = session.insert("saveRoomInfo", chatMessage);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		log.info("saveMessage end...");
		return result;
	}



	@Override
	public List<ChatUser> findUserList(SearchUser searchUser) {
		log.info("findUserList start...");
		List<ChatUser> chatUsers = null;
		try {
			chatUsers = session.selectList("findUserList", searchUser);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		log.info("findUserList end...");
		return chatUsers;
	}



	@Override
	public void createRoomResult(RoomDetail roomDetail) {
		log.info("createRoomResult start...");
		try {
			session.selectOne("createRoomResult", roomDetail);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		log.info("createRoomResult end...");
		return;
	}



	@Override
	public void chatRoomLeave(LeaveRoom leaveRoom) {
		log.info("chatRoomLeave start...");
		try {
			int result = session.update("chatRoomLeave", leaveRoom);
			System.out.println("퇴장 결과 ->" + result);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		log.info("chatRoomLeave end...");
		return;
	}



	@Override
	public List<ChatUser> findFriendList(SearchUser searchUser) {
		log.info("findUserList start...");
		List<ChatUser> chatUsers = null;
		try {
			chatUsers = session.selectList("findFriendList", searchUser);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		log.info("findUserList end...");
		return chatUsers;
	}

}
