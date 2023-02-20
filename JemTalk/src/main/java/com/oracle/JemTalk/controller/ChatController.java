package com.oracle.JemTalk.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oracle.JemTalk.dto.ChatRoom;
import com.oracle.JemTalk.dto.ChatUser;
import com.oracle.JemTalk.dto.LeaveRoom;
import com.oracle.JemTalk.dto.MainPageInfo;
import com.oracle.JemTalk.dto.RoomDetail;
import com.oracle.JemTalk.dto.SearchUser;
import com.oracle.JemTalk.service.ChatService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/user")
public class ChatController {
	
	private final ChatService chatService;
	
	@GetMapping("/mainPage")
	public String roomList(HttpSession session, Model model) {
		log.info("chatController roomList start...");
		ChatUser chatUser = (ChatUser) session.getAttribute("chatUser");
		MainPageInfo mainPageinfo = new MainPageInfo(chatUser.getUser_num());
		chatService.getMainPageInfo(mainPageinfo);
		int totCnt = 0;
		for(ChatRoom chatRoom : mainPageinfo.getChatRooms()) {
			 totCnt += Integer.parseInt(chatRoom.getMsg_cnt());
		}
		mainPageinfo.setTotCnt(totCnt);
		log.info("find Result->" + mainPageinfo);
		model.addAttribute("mainPageinfo", mainPageinfo);
		return "/chat/main";
	}
	
	@GetMapping("/EnterRoom")
	public String EnterRoom(HttpSession session, RoomDetail roomDetail, Model model) {
		log.info("EnterRoom start...");
		ChatUser chatUser = (ChatUser) session.getAttribute("chatUser");
		roomDetail.setUser_num(chatUser.getUser_num());
		chatService.EnterRoom(roomDetail);
		log.info("EnterRoom check result->" + roomDetail);
		model.addAttribute("RoomDetail", roomDetail);
		model.addAttribute("LastTwentyMessages",roomDetail.getLastTwentyMessages());
		return "/chat/chat";
	}
	
	@GetMapping("/createRoomResult")
	public String createRoomResult(HttpSession session, RoomDetail roomDetail, Model model) {
		log.info("createRoomResult start...");
		ChatUser chatUser = (ChatUser) session.getAttribute("chatUser");
		roomDetail.setUser_num(chatUser.getUser_num());
		chatService.createRoomResult(roomDetail);
		log.info("check result->" + roomDetail);
		model.addAttribute("RoomDetail", roomDetail);
		model.addAttribute("LastTwentyMessages",roomDetail.getLastTwentyMessages());
		return "/chat/chat";
	}
	
	@PostMapping("/findUserList")
	@ResponseBody
	public List<ChatUser> findUserList(SearchUser searchUser, HttpSession session){
		ChatUser chatUser = (ChatUser) session.getAttribute("chatUser");
		searchUser.setUser_num(chatUser.getUser_num());
		log.info("findUserList start...");
		List<ChatUser> chatUsers = chatService.findUserList(searchUser);
		log.info("check result->" + chatUsers);
		return chatUsers;
	}
	
	@PostMapping("/findFriendList")
	@ResponseBody
	public List<ChatUser> findFriendList(SearchUser searchUser, HttpSession session){
		ChatUser chatUser = (ChatUser) session.getAttribute("chatUser");
		searchUser.setUser_num(chatUser.getUser_num());
		log.info("findFriendList start...");
		List<ChatUser> chatUsers = chatService.findFriendList(searchUser);
		log.info("check result->" + chatUsers);
		return chatUsers;
	}
	
	@PostMapping("/chatRoomLeave")
	public void chatRoomLeave(LeaveRoom leaveRoom, HttpSession session) {
		log.info("someone exit");
		ChatUser chatUser = (ChatUser) session.getAttribute("chatUser");
		leaveRoom.setUser_num(chatUser.getUser_num());
		chatService.chatRoomLeave(leaveRoom);
	}
	
}
