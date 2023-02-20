package com.oracle.JemTalk.dto;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {
	 // 메시지 타입 : 입장, 채팅
    public enum MessageType {
        CREATE, ENTER, TALK, LEAVE, INVITE
    }
    private MessageType type; // 메시지 타입
    private Long room_num; // 방번호
    private String room_name; // 방이름
    private Long log_num; // 메세지 번호
    private Long user_num; // 메세지 보낸사람
    private String user_name;//보낸사람 이름
    private String msg_content; // 메세지 내용
    private Long msg_type; // 메세지 타입
    private String attach_save_name; //파일 저장 이름
    private LocalDateTime send_date; // 보낸날짜
    private Long not_read_cnt; //안읽은 수
    private List<Long> user_nums;
    
    public ChatMessage(MessageType type, Long room_num,Long log_num, Long user_num) {
    	this.type = type;
    	this.room_num = room_num;
    	this.log_num = log_num;
    	this.user_num = user_num;
    }
}
