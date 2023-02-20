package com.oracle.JemTalk.dao;

import java.util.List;

import com.oracle.JemTalk.dto.ChatMessage;
import com.oracle.JemTalk.dto.ChatRoom;
import com.oracle.JemTalk.dto.ChatUser;
import com.oracle.JemTalk.dto.LeaveRoom;
import com.oracle.JemTalk.dto.MainPageInfo;
import com.oracle.JemTalk.dto.RoomDetail;
import com.oracle.JemTalk.dto.SearchUser;

public interface ChatDao {


	void getMainPageInfo(MainPageInfo mainPageinfo);

	void EnterRoom(RoomDetail roomDetail);

	int saveMessage(ChatMessage chatMessage);

	int saveRoomInfo(ChatMessage chatMessage);

	List<ChatUser> findUserList(SearchUser searchUser);

	void createRoomResult(RoomDetail roomDetail);

	void chatRoomLeave(LeaveRoom leaveRoom);

	List<ChatUser> findFriendList(SearchUser searchUser);

}
