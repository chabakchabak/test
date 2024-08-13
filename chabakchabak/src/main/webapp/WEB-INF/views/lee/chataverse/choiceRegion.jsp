<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/lee/chataverse/header.jsp" %>
<link rel="stylesheet" href="/resources/lee/css/chataverse/region.css">
<script type="text/javascript">
$(function(){
	$("title").text("Choice Region");
	$("#regionContainer").on("click", ".region-item", function(){
		console.log("지역 버튼 클릭");
		let that = $(this);
		let region = that.attr("data-region");
		console.log("region : " + region);
		
		let url = "/lee/chataverse/play/" + region;
		window.location.href = url;
	});
});
</script>

<div class="regionContainer" id="regionContainer">

	<div class="map-item">
		<img alt="" src="/resources/lee/image/region/mapgreen.png" class="map">
	<!-------------------- 맵 ---------------------->
	
		<!-- 부산 -->
		<div class="region-item busan" data-region="busan">
			<img alt="pin" src="/resources/lee/image/region/pin.png" class="pin-item pin-top">
			<div class="region-box">
				<div class="img-box">
					<img alt="" src="/resources/lee/image/region/test1.jpg" class="region-img">
					<img alt="" src="/resources/lee/image/region/test2.jpg" class="region-img">
				</div>
				<div class="explain-box">
					<p>부산</p>
					<div class="inline-flex">
						<span>4.3</span> 
						<img alt="star" src="/resources/lee/image/region/star.png" class="star ml-5"> 
						<span>(</span><span>31</span><span>)</span>
						<span class="ml-5">해변</span>
					</div>
					<p>2/10</p>
				</div>
			</div>
		</div>
		
		<!-- 동해 -->
		<div class="region-item donghae">
			<img alt="pin" src="/resources/lee/image/region/pin.png" class="pin-item pin-top">
			<div class="region-box">
				<div class="img-box">
					<img alt="" src="/resources/lee/image/region/test1.jpg" class="region-img">
					<img alt="" src="/resources/lee/image/region/test2.jpg" class="region-img">
				</div>
				<div class="explain-box">
					<p>동해</p>
					<div class="inline-flex">
						<span>4.4</span> 
						<img alt="star" src="/resources/lee/image/region/star.png" class="star ml-5"> 
						<span>(</span><span>42</span><span>)</span>
						<span class="ml-5">바다</span>
					</div>
					<p>6/10</p>
				</div>
			</div>
		</div>
		
		<!-- 제주 -->
		<div class="region-item jeju">
			<img alt="pin" src="/resources/lee/image/region/pin.png" class="pin-item pin-bottom">
			<div class="region-box">
				<div class="img-box">
					<img alt="" src="/resources/lee/image/region/test1.jpg" class="region-img">
					<img alt="" src="/resources/lee/image/region/test2.jpg" class="region-img">
				</div>
				<div class="explain-box">
					<p>제주 협재</p>
					<div class="inline-flex">
						<span>4.7</span> 
						<img alt="star" src="/resources/lee/image/region/star.png" class="star ml-5"> 
						<span>(</span><span>69</span><span>)</span>
						<span class="ml-5">해변</span>
					</div>
					<p>8/10</p>
				</div>
			</div>
		</div>

	<!-------------------- 맵 -------------->
	</div>
</div>

<%@ include file="/WEB-INF/views/lee/chataverse/footer.jsp" %>