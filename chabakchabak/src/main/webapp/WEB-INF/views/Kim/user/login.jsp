<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="kr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>차박차박 - 로그인</title>
    <link rel="stylesheet" href="/resources/Kim/css/indexksy.css">
</head>
<body class="bg-gray-100 min-h-screen flex items-center justify-center">
    <div class="login-container">
        <img src="/resources/Kim/image/logo.png" alt="차박차박 로고" class="mb-6 mx-auto block" style="width: 150px;">
        
        <form action="/login" method="post">
            <div class="mb-4">
                <input type="text" name="userid" placeholder="아이디" class="input-box">
            </div>
            <div class="mb-6">
                <input type="password" name="userpw" placeholder="비밀번호" class="input-box">
            </div>
            <button type="submit" class="login-btn">로그인</button>

            <div class="flex items-center justify-between mt-4">
                <label class="flex items-center text-sm text-gray-700">
                    <input type="checkbox" name="rememberMe" class="mr-2"> 아이디 기억
                </label>
                <a href="/findIdPwd" class="text-sm text-gray-700">아이디/비밀번호 찾기</a>
            </div>
        </form>

        <a href="/signup" class="mt-6 w-full login-btn">회원가입</a>
    </div>
</body>
</html>
