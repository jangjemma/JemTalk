<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
 	<title>signUp page</title>
    <link href="${pageContext.request.contextPath}/resources/css/styles.css" rel="stylesheet"/>
    <script src="https://kit.fontawesome.com/f7fe0761ae.js" crossorigin="anonymous"></script>
</head>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
	$(function(){
		$(document).on("change","#domain_list", function(){
			if($(this).val() == 'self')
			{
				$('#v_mail_domain').val("");
				$('#v_mail_domain').attr("readonly", false);
			}
			else
			{
				$('#v_mail_domain').val($(this).val());
				$('#v_mail_domain').attr("readonly", true);
			}
		});
	});
</script>
<body>
	<header class="welcome-header">
      <h1 class="welcome-header__title">Welcome to Disney</h1>
      <p class="welcome-header__text">
        Enter your ID and password to participate in the Disney party.
      </p>
    </header>

    <form action="/signUpAction" method="GET" class="signUp-form">
      <input
        name="user_name"
        class="signUp-form__input"
        type="text"
        placeholder="이름을 입력해주세요"
        required
      />
      <input
        name="user_id"
        class="signUp-form__input"
        type="text"
        placeholder="아이디를 입력해주세요"
        required
      />
      <input
        name="v_pwd"
        class="signUp-form__input"
        type="password"
        placeholder="비밀번호를 입력해주세요"
        required
      />
      
      <input
        name="v_pwd_check"
        class="signUp-form__input"
        type="password"
        placeholder="비밀번호를 한번더 입력해주세요"
        required
      />
      <div class="signUp-form__mail">
      <input
        name="v_mail_id"
        class="signUp-form__input"
        type="text"
        placeholder="메일"
        required
      />
      @
      <input
        name="v_mail_domain"
        id="v_mail_domain"
        class="signUp-form__input"
        type="text"
        required
      />
      <select name="email" class="signUp-form__input" id="domain_list">
						<option value="naver.com">naver.com</option>
						<option value="daum.net">daum.net</option>
						<option value="gmail.com">gmail.com</option>
						<option value="self" selected="selected">직접 입력</option>
	  </select>
	  </div>
      <input class="signUp__btn" type="submit" value="회원 가입" />
    </form>

    <div id="splash-screen">
      <i class="fas fa-comment"></i>
    </div>

    <div id="no-mobile">
      <span>Your screen is too big</span>
    </div>
</body>
</html>