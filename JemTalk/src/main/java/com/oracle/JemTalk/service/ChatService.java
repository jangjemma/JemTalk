package com.oracle.JemTalk.service;

import java.util.List;

import com.oracle.JemTalk.dto.ChatMessage;
import com.oracle.JemTalk.dto.ChatRoom;
import com.oracle.JemTalk.dto.ChatUser;
import com.oracle.JemTalk.dto.LeaveRoom;
import com.oracle.JemTalk.dto.MainPageInfo;
import com.oracle.JemTalk.dto.RoomDetail;
import com.oracle.JemTalk.dto.SearchUser;

public interface ChatService {

	void getMainPageInfo(MainPageInfo mainPageinfo);

	void EnterRoom(RoomDetail roomDetail);

	void saveChatRoom(ChatMessage chatMessage);

	List<ChatUser> findUserList(SearchUser searchUser);

	void createRoomResult(RoomDetail roomDetail);

	void saveMessage(ChatMessage chatMessage);

	void chatRoomLeave(LeaveRoom leaveRoom);

	List<ChatUser> findFriendList(SearchUser searchUser);


}
