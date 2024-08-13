<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="kr">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>차박차박</title>
<link rel="stylesheet" href="/resources/Kim/css/index.css">
<!--   <link rel="stylesheet" href="/resources/lee/css/detail.css"> -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>												
<script src="/resources/Kim/js/index.js" defer></script>
    
<script>

</script>
</head>
<body>
    <!-- 광고 -->
    <div class="ad-Container ad-left">
        <img class="ad-img-left" src="/resources/Kim/image/ad1.png" alt="">
    </div>
    <div class="ad-Container ad-rigth">
        <img class="ad-img-right" src="/resources/Kim/image/ad2.png" alt="">
    </div>

 

    <!-- header -->
    <header class="header-Container">
        <div class="navbar-Logo" style="display: flex">
            <a href="/"><img alt="" src="/resources/Kim/image/logo2.png"></a>
            <span class="font-size-10 bold-500 mb-10"> 차박을 좋아하는 사람들..</span>
        </div>
        <div class="login-Container">
            <input type="text" class="search">
            
        </div>
    </header>

    <!-- nav -->
    <nav class="navbar mt-10">
        <ul class="nav-list">
            <li class="nav-item">
                <a href="#" class="nav-link">공지사항</a>
                <ul class="sub-nav-list">
                    <li class="sub-nav-item"><a href="/Jang/board/notice/list" class="sub-nav-link">공지사항</a></li>
                    <li class="sub-nav-item"><a href="/lee/board/list/join" class="sub-nav-link">가입인사</a></li>
                    <li class="sub-nav-item"><a href="/lee/board/list/check" class="sub-nav-link">출석체크</a></li>
                </ul>
            </li>
            <li class="nav-item">
                <a href="#" class="nav-link">커뮤니티</a>
                <ul class="sub-nav-list">
                    <li class="sub-nav-item"><a href="/lee/board/list/free" class="sub-nav-link">자유게시판</a></li>
                    <li class="sub-nav-item"><a href="/Kim/board/info" class="sub-nav-link">정보공유</a></li>
                    <li class="sub-nav-item"><a href="/lee/board/list/review" class="sub-nav-link">리뷰</a></li>
                    <li class="sub-nav-item"><a href="/lee/board/list/qna" class="sub-nav-link">질문게시판</a></li>
                </ul>
            </li>
            <li class="nav-item">
                <a href="#" class="nav-link">모임</a>
                <ul class="sub-nav-list">
                    <li class="sub-nav-item"><a href="/lee/board/list/meet" class="sub-nav-link">벙캠/동행</a></li>
                    <li class="sub-nav-item"><a href="/lee/board/list/meetReview" class="sub-nav-link">모임후기</a></li>
                </ul>
            </li>
            <li class="nav-item">
                <a href="#" class="nav-link">중고장터</a>
                <ul class="sub-nav-list">
                    <li class="sub-nav-item"><a href="/lee/board/list/buy" class="sub-nav-link">삽니다</a></li>
                    <li class="sub-nav-item"><a href="/lee/board/list/sell" class="sub-nav-link">팝니다</a></li>
                </ul>
            </li>
            <li class="nav-item"><a href="/metaverse" class="nav-link">메타버스</a></li>
            <li class="nav-item"><a href="/lee/board/list/report" class="nav-link">신고하기</a></li>
        </ul>
    </nav>
<!--     <div class="login-profile-container"> -->
<!--     	<div class="login-profile-box"> -->
<!--     		<div class="login-profile"> -->
<%--     			<c:choose> --%>
<%-- 	    			<c:when test="${login != null}"> --%>
<!-- 					    <a href="#"><img class="img-mail" alt="" src="/resources/lee/image/mail.png"></a> -->
<%-- 					    <a href="/user/profile" class="bold-800 ml-10">${login.u_nickname}</a> --%>
<!-- 						<span class="reply-count ml-20">레벨</span> -->
<%-- 				   		<span class="ml-5">${login.u_level}</span> --%>
<%-- 		    		</c:when> --%>
<%-- 		    		<c:otherwise> --%>
<!-- 		    			<span class="reply-count ml-20">차박차박</span> -->
<%-- 		    		</c:otherwise> --%>
<%-- 	    		</c:choose> --%>
<!-- 			</div> -->
<!-- 			<div class="ml-10"> -->
<!-- 				로그인/로그아웃 버튼 로그인 시 display 처리 -->
<%-- 				<c:choose> --%>
<%-- 	            	<c:when test="${login == null}"> --%>
<!-- 			            <a class="btn2 btn-yellow" href="/user/login">로그인</a> -->
<%-- 	            	</c:when> --%>
<%-- 	            	<c:otherwise> --%>
<!-- 			            <a class="btn2 btn-yellow mb-10"  id="btnLogout" href="/user/logout">로그아웃</a> -->
<%-- 			            <a class="btn2 btn-yellow" href="/user/profile/${login.u_id}">설정</a> --%>
<%-- 	            	</c:otherwise> --%>
<%--             	</c:choose> --%>
            	
<!-- 			</div> -->
<!--     	</div> -->
          	
    </div>
<section class="section-Container">
<%--  ${login} --%>