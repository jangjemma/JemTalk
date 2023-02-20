package com.oracle.JemTalk.dao;

import com.oracle.JemTalk.dto.ChatUser;

public interface UserDao {

	ChatUser login(String username);

	int signUpAction(ChatUser chatUser);

}
