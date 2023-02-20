package com.oracle.JemTalk.service;

import org.springframework.stereotype.Service;

import com.oracle.JemTalk.dao.UserDao;
import com.oracle.JemTalk.dto.ChatUser;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
	
	private final UserDao userDao;
	
	@Override
	public int signUpAction(ChatUser chatUser) {
		int result = userDao.signUpAction(chatUser);
		return result;
	}

}
