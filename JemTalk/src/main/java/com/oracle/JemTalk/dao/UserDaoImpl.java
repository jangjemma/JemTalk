package com.oracle.JemTalk.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.oracle.JemTalk.dto.ChatUser;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao {
	private final SqlSession session;

	@Override
	public ChatUser login(String username) {
		log.info("loginAction start...");
		ChatUser chatUser = null;
		try {
			chatUser = session.selectOne("loginAction",    username);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		log.info("findRoomByEmpno end...");
		return chatUser;
	}

	@Override
	public int signUpAction(ChatUser chatUser) {
		log.info("findRoomByEmpno start...");
		int result = 0;
		try {
			result = session.insert("signUpAction", chatUser);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		log.info("findRoomByEmpno end...");
		return result;
	}
	
	
}
