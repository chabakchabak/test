<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/views/Jang/include/header.jsp" %>

<!doctype html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <title>공지사항 작성</title>
	<link rel="stylesheet" href="/resources/Jang/css/editorStyle.css">
    <link rel="stylesheet" href="https://cdn.ckeditor.com/ckeditor5/42.0.2/ckeditor5.css">
	<link rel="stylesheet" href="/resources/Jang/css/noticePost.css">
</head>
<body>
<script type="text/javascript">
$(function(){
	console.log(`${loginSessionDto}`);
	console.log(`${loginSessionDto.nickname}`);
	
	$("#btnRegister").click(function(){
		$("#frmPost").submit();
	});
	
// 	$("#frmPost").submit(function(e){
// 		consoel.log(e);
// 		console.log($("#editor").html());
// 		return false;
// 	});
	
	
});

</script>
    <div class="container">
        <h2>공지사항 작성</h2>
        <form id="frmPost" action="/Jang/board/notice/post" method="post" >
            <div class="form-group">
                <label class="form-label" for="title">제목</label>
                <input type="text" id="title" name="title" class="form-control">
            </div>
            <div class="form-group">
                <label class="form-label" for="content">내용</label>
                <textarea id="editor" name="content" class="form-control"></textarea>
            </div>
            <div class="form-group">
                <label class="form-label" for="wrtier">작성자</label>
                <input type="text" id="nickname" name="nickname" class="form-control" value="${loginSessionDto.nickname}" readonly>
            </div>
            <div class="actions">
                <button id="btnRegister" type="button" class="btn btn-primary">등록</button>
                <a href="/Jang/board/notice/list" class="btn btn-secondary">취소</a>
            </div>
        </form>
    </div>
    <script type="importmap">
		{
			"imports": {
				"ckeditor5": "https://cdn.ckeditor.com/ckeditor5/42.0.2/ckeditor5.js",
				"ckeditor5/": "https://cdn.ckeditor.com/ckeditor5/42.0.2/"
			}
		}
		</script>
		<script type="module" src="/resources/Jang/js/editorModule.js"></script>
</body>
</html>

<%@ include file="/WEB-INF/views/Jang/include/footer.jsp" %>
