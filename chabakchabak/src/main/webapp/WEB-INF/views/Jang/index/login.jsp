<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>회원가입</title>
<link rel="stylesheet" href="/resources/Jang/css/index.css">
</head>

<body>
	<!-- Modal-start -->
	<div id="loginModal" class="modal">
		<div class="modal-content">
			<!-- <span class="close-modal" id="close-modal">&times;</span> -->
			<h2>로그인</h2>
			<form action="/Jang/user/login" method="post" class="form-modal">
				<label for="username">아이디:</label>
				<input id="modal-u_id" type="text" name="userId" placeholder=" 아이디">
				<label for="password">비밀번호:</label>
				<input id="modal-upw" type="password" name="userPw" placeholder=" 비밀번호">
				<input name="currentUrl" type="hidden" value="${currentUrl}">
				<div class="idCheckContainer">
					<input type="checkbox"> <span>자동 로그인</span>
				</div>
				<button id="modal-btnLogin" type="submit" class="btn">Login</button>
			</form>
			<div class="modal-links-container">
				<a href="/Jang/user/join">회원가입</a> <a href="#">아이디 찾기</a> <a href="#">비밀번호
					찾기</a>
			</div>
		</div>
	</div>

</body>
</html>