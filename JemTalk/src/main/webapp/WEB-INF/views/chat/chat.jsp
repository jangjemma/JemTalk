<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
   <title>Kakao Talk Chat Room</title>
   <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
   <meta http-equiv="X-UA-Compatible" content="IE-edge">
   <meta name="description" content="Kakao Talk Clone Chat Page">
   <meta name="robotos" content="noindex, nofollow">
   <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/chat-room.css">
   <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/general.css">
   <link rel="preconnect" href="https://fonts.gstatic.com">
   <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap">
</head>
<script src="https://kit.fontawesome.com/f7fe0761ae.js" crossorigin="anonymous"></script>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>

<script type="text/javascript">

	$(function(){
		
		window.scrollTo(0, document.body.scrollHeight);
		$(window).on('beforeunload', function(){
		    $.ajax({
		        type: "POST",
		        url: "/user/chatRoomLeave",
		        data: { room_num : '${RoomDetail.room_num }' },
		        success: function(response) {
		            console.log("User left the chat room!");
		        },
		        error: function(xhr, status, error) {
		            console.log("AJAX error: " + status + " - " + error);
		        }
		    });
		});
		
		$(document).on("click","#chatSend", function(){
			sendMsg();
		});
		
		$(document).on("keyup","#chatContent", function(key){
			if(key.keyCode==13) {
				sendMsg();
	        }
		});
		
		$(document).on("DOMSubtreeModified",".chat_log_balloon", function(){
			if($(this).html() == '0'){
				$(this).hide();
			}else{
				$(this).show();
			}
		});
	});
	function Exit()
	{
	    if(self.screenTop > 9000)
	    {
	    	//사이트 나가기
	    	$.ajax({
				 type: "POST",
				 url: "/user/chatRoomLeave",
				 async: false
			});
	    }
	    else
	    {
	        if(document.readyState == "complete")
	        {
	            alert('새로고침');  // 새로고침
	        }
	        else if(document.readyState == "loading")
	        {
	                alert('이동');  // 다른 사이트로 이동
	        }
	    }
	}
	

	
	function sendMsg(){
		var message ={
					type       : "TALK", 
					room_num  : "${RoomDetail.room_num }",
					msg_content   : $('#chatContent').val(),
					user_num : '${chatUser.user_num }',
					user_name : '${chatUser.user_name }',
					msg_type : 1,
					attach_save_name : $('#chatContent').val()
			}
			opener.ws.send("/pub/chat/message", {}, JSON.stringify(message));
		$('#chatContent').val("");
	}
	
	function someoneEnter(log_num){
		alert("여긴 오나?log_num->" + log_num);
		var first_log = $(".main-chat div:first").attr("id").split('_');
		var first_num = parseInt(first_log[1]);
		var log_num_int = parseInt(log_num);
		alert("첫번째 채팅->" + first_log[1]);
		if(log_num_int <= first_num){
			$(".main-chat div").each(function(){
				var cnt = parseInt($(this).find(".chat_log_balloon").html())-1;
				console.log("테스트  cnt" + cnt);
				$(this).find(".chat_log_balloon").html(cnt);
			});
		}else{
			$("#chat_" + log_num).nextAll().each(function(){
				var cnt = parseInt($(this).find(".chat_log_balloon").html())-1;
				console.log("테스트  cnt" + cnt);
				$(this).find(".chat_log_balloon").html(cnt);
			});
		}
		
	}
	
	function receiveMsg(msg){
		var str ="";
		console.log(msg.not_read_cnt);
		if(msg.user_num == '${chatUser.user_num}'){
			str += "<div class='me-chat' id='chat_" + msg.log_num +"'>";
			str += "<div class='me-chat-col'>";
			str += "<span class='balloon'>" + msg.msg_content +"</span>";
			str += "</div>";
			if(msg.not_read_cnt == '0'){
				str += "<span class='chat_log_balloon' style='display:none;'>" + msg.not_read_cnt + "</span>"
			}else{
				str += "<span class='chat_log_balloon'>" + msg.not_read_cnt + "</span>"
			}
			str += "<time datetime='" + msg.send_date + "'>" + msg.send_date +"</time>";
			str += "</div>";
		}else{
			str += "<div class='friend-chat' id='chat_" + msg.log_num +"'>";
            str += "<div class='friend-chat-col'>";
            str += "<span class='profile-name'>" + msg.user_name +"</span>";
            str += "<span class='balloon'>" + msg.msg_content + "</span>";
            str += "</div>";
            if(msg.not_read_cnt == '0'){
				str += "<span class='chat_log_balloon' style='display:none;'>" + msg.not_read_cnt + "</span>"
			}else{
				str += "<span class='chat_log_balloon'>" + msg.not_read_cnt + "</span>"
			}
            str += "<time datetime='" + msg.send_date + "'>" + msg.send_date +"</time>";
            str += "</div>";
		}
		//chatWindow[msg.room_num].document.querySelector(".main-chat").insertAdjacentHTML('beforeend',str);
		$(".main-chat").append(str);
		window.scrollTo(0, document.body.scrollHeight);
	}
	
</script>
<body>
	<div id="chat-body">
            <!-- 설정바(최소화, 닫기 버튼 등) -->
            <div class="setting_bar">
                <!-- <i class="icon-window-minimize" alt="최소화버튼" title="최소화"></i>
                <i class="icon-window-maximize" alt="최대화버튼" title="최대화"></i>
                <i class="icon-cancel" alt="닫기버튼" title="닫기"></i> -->
            </div>
            <!-- 알림, 메뉴 기능 -->
            <div class="main-menu">
                <!-- <i class="icon-bell" title="알림"></i>
                <i class="icon-ellipsis" title="메뉴"></i> -->
            </div>
            <!-- 프로필 사진, 프로필명 -->
            <header>
                <img class="profile-img" src='${pageContext.request.contextPath}/resources/pic/default.png' alt="쀼프로필사진">
                <div class="profile-col">
                    <span class="profile-name">${RoomDetail.room_name }</span>
                    <div class="sub-menu">
                        <!-- <i class="icon-box" title="채팅방 서랍"></i>
                        <i class="icon-search" title="검색"></i> -->
                    </div>
                </div>
            </header>
            <main>
                <!-- 고정된 공지사항 영역 -->
                <div class="notice-bar">
                   <!--  <i class="icon-bullhorn"></i> -->
                    <span>멘트를 고정해놓는 곳입니다.</span>
                   <!--  <i class="icon-down-open-big"></i> -->
                </div>
                <!-- 채팅 내용 시작 -->
                <div class="chat-content">
                    <!-- 메시지 시작 날짜 -->
                    <div class="date-line">
                        <time datetime="2021-03-29">2021년 3월 29일 월요일</time>
                    </div>
                    <!-- 채팅 내용 -->
                    <div class="main-chat">
                    	<c:forEach var="Chats" items="${LastTwentyMessages }">
                    		<c:choose>
                    			<c:when test="${Chats.user_num eq chatUser.user_num}">
                    				<div class="me-chat" id="chat_${Chats.log_num }">
			                            <div class="me-chat-col">
			                            	<span class="balloon">${Chats.msg_content }</span>
			                            </div>
			                            <c:choose>
				                            <c:when test="${Chats.not_read_cnt eq '0'}">
							    				<span class="chat_log_balloon" style='display:none;'>${Chats.not_read_cnt }</span>
							    			</c:when>
							    			<c:otherwise>
							    				 <span class="chat_log_balloon">${Chats.not_read_cnt }</span>
							    			</c:otherwise>
						    			</c:choose>
			                            <time datetime="${Chats.send_date }">${Chats.send_date }</time>
			                        </div>
                    			</c:when>
                    			<c:otherwise>
                    				<div class="friend-chat" id="chat_${Chats.log_num }">
			                            <!-- <img class="profile-img" src="./pic/default.png" alt="쀼프로필사진"> -->
			                            <div class="friend-chat-col">
			                                <span class="profile-name">${Chats.user_name}</span>
			                                <span class="balloon">${Chats.msg_content }</span>
			                            </div>
			                            <c:choose>
				                            <c:when test="${Chats.not_read_cnt eq '0'}">
							    				<span class="chat_log_balloon" style='display:none;'>${Chats.not_read_cnt }</span>
							    			</c:when>
							    			<c:otherwise>
							    				 <span class="chat_log_balloon">${Chats.not_read_cnt }</span>
							    			</c:otherwise>
						    			</c:choose>
			                            <time datetime="${Chats.send_date }">${Chats.send_date }</time>
                       				</div>
                    			</c:otherwise>
                    		</c:choose>		
                    	</c:forEach>
                    </div>
                </div>
                <!-- 채팅 입력창 -->
                <div class="insert-content">
                    <div id="chatform">
                        <textarea name="chat-insert" id="chatContent" required></textarea>
                        <button class="chat-submit" id="chatSend">전송</button>
                    </div>
                    <!-- 채팅 입력 관련 기능(파일 첨부, 캡쳐 등) -->
                    <div class="insert-menu">
                        <i class="icon-smile"></i>
                        <i class="icon-attach"></i>
                        <i class="icon-phone"></i>
                        <i class="icon-calendar-empty"></i>
                        <i class="icon-camera"></i>
                    </div>
                </div>
            </main>
        </div>
</body>
</html>