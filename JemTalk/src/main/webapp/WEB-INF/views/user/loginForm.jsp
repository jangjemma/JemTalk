<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />
    <title>Kakao Talk Clone</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="Kakao Talk Clone Login View">
    <meta name="keywords" content="KakaoTalk, Clone, Login">
    <meta name="robots" content="noindex,nofollow">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/general.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/login.css">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">
</head>
<script src="https://kit.fontawesome.com/f7fe0761ae.js" crossorigin="anonymous"></script>
<body class="login_body">
    <div class="max_screen">
            <p>화면을 500px 이하로 줄여주세요.</p>
        </div>
        <div class="setting_bar">
            
        </div>
        <div id="login_page">
            <header class="logo_header">
                <img src="${pageContext.request.contextPath}/resources/pic/logo.png" alt="카카오톡로고" class="logo">
            </header>
            <section class="login_form" id="login_form">
                <form name="loginform" action="/login" method="post">
                    <input type="text" class="id-pw" name="username" alt="ID입력" placeholder="계정" required>
                    <input type="password" class="id-pw" name="password" alt="비밀번호입력" placeholder="비밀번호" required>
                    <input type="submit" class="loginbutton" value="로그인" alt="로그인버튼">
<!--                     <label for="auto-login"><input type="checkbox" name="auto_login" id="auto-login" value="auto_yes" alt="자동로그인">자동로그인</label> -->
                </form>
            </section>
        </div>
        <div class="forget">
            <section class="forget_password">
                <a href="/signUp">회원 가입</a>
                <a href="/findIdPwd">아이디 비밀번호 찾기</a>
            </section>
     	</div>
</body>
</html>