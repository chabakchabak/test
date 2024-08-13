<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/Kim/include/header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/tailwindcss/2.2.19/tailwind.min.css">
<link rel="stylesheet" href="/resources/Kim/css/indexksy.css">
<body class="bg-white text-black min-h-screen p-4">
	<main class="grid grid-cols-3 gap-4 mt-4">
		<section class="col-span-2 space-y-4">
			<div class="bg-gray-100 p-4">
				<h2 class="text-3xl font-bold">정보공유게시판 - 정보공유게시판입니다.</h2>
                <div class="mt-2">
                    <c:forEach items="${latestAnnounce}" var="announce">
                        <div class="flex-container">
                            <span class="text-yellow-500 flex-item">공지</span> 
                            <a href="/Kim/board/read?boardno=${announce.boardNo}" class="flex-item">
                                <span>${announce.title}</span>
                            </a> 
                            <span class="text-gray-500 flex-item">${announce.views}</span> 
                            <span class="text-gray-500 flex-item">
                                <fmt:formatDate value="${announce.regdate}" pattern="MM-dd" />
                            </span>
                        </div>
                    </c:forEach>
                </div>
			</div>
            <div class="bg-gray-100 p-1">
                <h2 class="text-lg font-bold">오늘의 통합 BEST</h2>
                <div class="grid grid-cols-2 gap-1">
                    <c:forEach items="${todayBestList}" var="boardVo" varStatus="num">
                        <div class="flex items-center justify-between p-2 bg-gray-300 best-item">
                        	<span>${num.index + 1}</span> 
                            <span class="text-gray-500">${boardVo.boardName}</span>
                            <a href="/Kim/board/read?boardNo=${boardVo.boardNo}"><span class="flex-1 ml-2">${boardVo.title}</span></a>
                            <span class="text-gray-500">${boardVo.views}</span>
                        </div>
                    </c:forEach>
                </div>
            </div>
<div class="bg-gray-100 p-4">
    <div class="flex justify-center">
        <a href="/Kim/board/info" class="mr-4 bg-yellow-600 text-black px-3 py-1 rounded">전체글</a>
        <form id="categoryForm" action="/Kim/board/info" method="get" class="flex">
            <input type="hidden" name="type" value="${criteria.type}">
            <input type="hidden" name="keyword" value="${criteria.keyword}">
            <input type="hidden" name="pageNum" value="1">
            <input type="hidden" name="amount" value="${pageMaker.cri.amount}">
            <input type="hidden" name="sort" value="${criteria.sort}">
            <button class="mr-4 ${criteria.category == '음식' ? 'bg-yellow-500' : 'bg-gray-300'} text-black px-3 py-1 rounded" name="category" value="음식">음식</button>
            <button class="mr-4 ${criteria.category == '자동차' ? 'bg-yellow-500' : 'bg-gray-300'} text-black px-3 py-1 rounded" name="category" value="자동차">자동차</button>
            <button class="mr-4 ${criteria.category == '장소' ? 'bg-yellow-500' : 'bg-gray-300'} text-black px-3 py-1 rounded" name="category" value="장소">장소</button>
            <button class="mr-4 ${criteria.category == '핫딜' ? 'bg-yellow-500' : 'bg-gray-300'} text-black px-3 py-1 rounded" name="category" value="핫딜">핫딜</button>
            <button class="mr-4 ${criteria.category == '행사' ? 'bg-yellow-500' : 'bg-gray-300'} text-black px-3 py-1 rounded" name="category" value="행사">행사</button>
        </form>
    </div>
</div>
<div>title에 [음식], [자동차], [장소], [핫딜], [행사] 로 동작 하며 글작성 수정시 자동 삽입</div>




			<div class="bg-gray-100 p-4">
				<table class="mt-4 w-full bg-gray-100 text-black">
					<thead>
						<tr>
							<th class="text-xl">추천수</th>
							<th class="text-xl">제목</th>
							<th class="text-xl">닉네임</th>
							<th class="text-xl">조회수</th>
							<th class="text-xl">날짜</th>
						</tr>
					</thead>
                    <tbody>
                        <tr>
                            <td colspan="6"><hr class="table-hr"></td>
                        </tr>
                        <c:forEach items="${list}" var="boardVo">
                        
                            <tr class="spacer">
                                <td class="text-center">${boardVo.likes}</td>
                                <td class="text-center"><a class="a_bno" href="/Kim/board/read?boardNo=${boardVo.boardNo}">${boardVo.title}</a>
    							<span class="badge badge-secondary"> [${boardVo.replycount}] </span></td>
                                <td class="text-center">${boardVo.nickName}</td>
                                <td class="text-center">${boardVo.views}</td>
                                <td class="text-center"><fmt:formatDate value="${boardVo.regdate}" pattern="yyyy-MM-dd" /></td>
                            </tr>
                        </c:forEach>
                    </tbody>
				</table>
			</div>

			<!-- 페이징과 정렬 기능 추가 -->
			<div class="flex items-center justify-between p-1 bg-gray-100">
				<div class="flex items-center space-x-2">
					<form id="sortForm" action="/Kim/board/info" method="get">
						<input type="hidden" name="type" value="${criteria.type}">
						<input type="hidden" name="keyword" value="${criteria.keyword}">
						<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}"> 
						<input type="hidden" name="amount" value="${pageMaker.cri.amount}"> 
						<input type="hidden" name="category" value="${criteria.category}">
						<select name="sort" class="bg-gray-300 p-1 rounded" onchange="document.getElementById('sortForm').submit();">
							<option value="recent" ${criteria.sort == 'recent' ? 'selected' : ''}>최신 순</option>
							<option value="likes" ${criteria.sort == 'likes' ? 'selected' : ''}>추천순</option>
							<option value="views" ${criteria.sort == 'views' ? 'selected' : ''}>조회수 순</option>
						</select>
					</form>
				</div>
<div class="flex justify-center">
    <nav>
        <ul class="flex list-none space-x-1">
            <c:if test="${pageMaker.prev}">
                <li><a
                    href="?pageNum=${pageMaker.startPage - 1}&amount=${pageMaker.cri.amount}&type=${criteria.type}&keyword=${criteria.keyword}&sort=${criteria.sort}&category=${criteria.category}"
                    class="bg-gray-300 text-black px-3 py-1 rounded">이전</a></li>
            </c:if>
            <c:forEach var="i" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
                <c:choose>
                    <c:when test="${pageMaker.cri.pageNum == i}">
                        <span class="bg-yellow-500 text-black px-3 py-1 rounded">${i}</span>
                    </c:when>
                    <c:otherwise>
                        <a href="?pageNum=${i}&amount=${pageMaker.cri.amount}&type=${criteria.type}&keyword=${criteria.keyword}&sort=${criteria.sort}&category=${criteria.category}"
                            class="bg-gray-300 text-black px-3 py-1 rounded">${i}</a>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:if test="${pageMaker.next}">
                <li><a
                    href="?pageNum=${pageMaker.endPage + 1}&amount=${pageMaker.cri.amount}&type=${criteria.type}&keyword=${criteria.keyword}&sort=${criteria.sort}&category=${criteria.category}"
                    class="bg-gray-300 text-black px-3 py-1 rounded">다음</a></li>
            </c:if>
        </ul>
    </nav>
</div>
<a href="/Kim/board/write" class="bg-yellow-500 text-black p-1 rounded">글쓰기</a>
			</div>

			<div class="flex items-center justify-center p-1 bg-gray-100">
				<div class="flex items-center space-x-1">
					<form action="/Kim/board/info" method="get">
						<select name="type" class="bg-gray-300 p-1 rounded">
							<option value="T" ${criteria.type == 'T' ? 'selected' : ''}>제목</option>
							<option value="C" ${criteria.type == 'C' ? 'selected' : ''}>내용</option>
							<option value="W" ${criteria.type == 'W' ? 'selected' : ''}>작성자</option>
							<option value="TC" ${criteria.type == 'TC' ? 'selected' : ''}>제목+내용</option>
							<option value="TW" ${criteria.type == 'TW' ? 'selected' : ''}>제목+작성자</option>
							<option value="CW" ${criteria.type == 'CW' ? 'selected' : ''}>내용+작성자</option>
							<option value="TCW" ${criteria.type == 'TCW' ? 'selected' : ''}>제목+내용+작성자</option>
						</select> <input type="text" name="keyword" placeholder="검색어를 입력하세요."
							value="${criteria.keyword}" class="bg-gray-200 p-1 rounded" /> <input
							type="hidden" name="category" value="${criteria.category}">
						<button type="submit" class="bg-yellow-500 text-black p-1 rounded">검색</button>
					</form>
				</div>
			</div>
			<!-- 페이징과 정렬 기능 추가 끝 -->
		</section>
		<aside class="space-y-4">
			<div class="bg-gray-100 p-4">
				<div class="flex items-center justify-between p-2 bg-gray-300 rounded">
					<div class="ml-6 flex items-center">
							<div class="login-container">
    							<a href="/Kim/user/login" class="login-btn">로그인</a>
    						<div class="login-links">
        						<a href="/findIdPwd" class="link-left">아이디/비밀번호 찾기</a>
        						<a href="/signUp" class="link-right">회원가입</a>
    </div>
</div>
<!-- 						<img src="/resources/Kim/image/1111.png" alt="Profile Image" class="w-16 h-16 rounded-full"> -->
<!-- 						<div class="ml-8"> -->
<!-- 							<h3 class="text-xl font-bold">고양이의생일</h3> -->
<!-- 							<p class="text-sm text-gray-500">정회원</p> -->
<!-- 							<p class="text-sm text-gray-500">등급 : 우주시민 (4,291xp)</p> -->
<!-- 						</div> -->
					</div>
					<!-- <button class="bg-yellow-500 text-black px-4 py-2 rounded">로그아웃</button> -->
				</div>
				<div class="mt-4 grid grid-cols-3 gap-2 text-center">
<!-- 					<a href="#" class="bg-gray-300 p-2 rounded">기능1</a>  -->
<!-- 					<a href="#"	class="bg-gray-300 p-2 rounded">기능2</a>  -->
<!-- 					<a href="#" class="bg-gray-300 p-2 rounded">기능3 </a> -->
					<!-- <a href="#" class="bg-gray-300 p-2 rounded">스크랩</a>  -->
					<!-- <a href="#" class="bg-gray-300 p-2 rounded">공지</a> -->
				</div>
			</div>

			<div class="bg-gray-100 p-4 rounded">
			    <h2 class="text-lg font-bold">주간 정보게시판 BEST</h2>
			    <ul class="mt-2 space-y-2">
			        <c:forEach items="${weeklyBestList}" var="boardVo" varStatus="num">
			            <div class="flex items-center justify-between p-2 bg-gray-300 rounded">
			                <span>${num.index + 1}</span> 
			                <a class="a_bno" href="/Kim/board/read?boardNo=${boardVo.boardNo}">
			                    <span class="flex-1 ml-2">${boardVo.title}</span>
			                </a>
			                <span class="text-gray-500">${boardVo.views}</span>
			            </div>
			        </c:forEach>
			    </ul>
			</div> 
		</aside>
	</main>
</body>
</html>
<%@ include file="/WEB-INF/views/Kim/include/footer.jsp"%>
