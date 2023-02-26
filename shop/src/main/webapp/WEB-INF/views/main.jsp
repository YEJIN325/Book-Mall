<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome BookMall</title>
<link rel="stylesheet" href="resources/css/main.css">
<link rel="stylesheet" type="text/css" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"/>
<link rel="stylesheet" type="text/css" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick-theme.css"/>
<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous"></script>
<script type="text/javascript" src="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
</head>
<body>
<div class="wrapper">
	<div class="wrap">
		<div class="top_gnb_area">
			<ul class="list">
				<c:if test="${member == null }">
					<li>
						<a href="/member/login">로그인</a>
					</li>
					<li>
						<a href="/member/join">회원가입</a>
					</li>
				</c:if>
				<c:if test="${member != null }">
					<c:if test="${member.adminCk == 1 }">
						<li><a href="/admin/main">관리자 페이지</a>
					</c:if>
					<li>
						<a id="gnb_logout_button">로그아웃</a>
					</li>
					<li>
						마이페이지
					</li>
					<li>
						<a href="/cart/${member.memberId}">장바구니</a>
					</li>
				</c:if>
				<li>
					고객센터
				</li>
			</ul>
		</div>
		<div class="top_area">
			<div class="logo_area">
				<a href="/main"><img src="resources/img/Logo.png"></a>
			</div>
			<div class="search_area">
				<div class="search_wrap">
					<form id="searchForm" action="/search" method="get">
						<div class="search_input">
							<select name="type">
								<option value="T">책 제목</option>
								<option value="A">작가</option>
							</select>
							<input type="text" name="keyword">
							<button class='btn search_btn'>검 색</button>
						</div>
					</form>
				</div>
			</div>
			<div class="login_area">
				<c:if test="${member == null }">
					<div class="login_button"><a href="/member/login">로그인</a></div>
					<span><a href="/member/join">회원가입</a></span>
				</c:if>
				<c:if test="${member != null }">
					<div class="login_success_area">
						<span>회원 : ${member.memberName}</span>
						<span>충전금액 : <fmt:formatNumber value="${member.money}" pattern="\#,###.##" /></span>
						<span>포인트 : <fmt:formatNumber value="${member.point}" pattern="#,###" /></span>
						<a href="/member/logout">로그아웃</a>
					</div>
				</c:if>
			</div>
			<div class="clearfix"></div>
		</div>
		<div class="navi_bar_area">
			<div class="dropdown">
				<button class="dropbtn">국내
				<i class="fa fa-caret-down"></i>
				</button>
				<div class="dropdown-content">
					<c:forEach items="${cate1}" var="cate">
						<a href="/search?type=C&cateCode=${cate.cateCode}">${cate.cateName}</a>
					</c:forEach>
				</div>
			</div>
			<div class="dropdown">
				<button class="dropbtn">국외
				<i class="fa fa-caret-down"></i>
				</button>
				<div class="dropdown-content">
					<c:forEach items="${cate2}" var="cate">
						<a href="/search?type=C&cateCode=${cate.cateCode}">${cate.cateName}</a>
					</c:forEach>
				</div>
			</div>
		</div>
		<div class="content_area">
			<div class="slide_div_wrap">
				<div class="slide_div">
					<div>
						<a>
							<img src="../resources/img/slide_01.jpeg">
						</a>
					</div>
					<div>
						<a>
							<img src="../resources/img/slide_02.jpeg">
						</a>
					</div>			
				</div>	
			</div>
		</div>
		
		<div class="ls_wrap">
			<div class="ls_div_subject">
				평점순 상품
			</div>
			<div class="ls_div">
				<c:forEach items="${ls}" var="ls">
					<a href="/goodsDetail/${ls.bookId}">
						<div class="ls_div_content_wrap">
							<div class="ls_div_content">
								<div class="image_wrap" data-bookid="${ls.imageList[0].bookId}" data-path="${ls.imageList[0].uploadPath}" data-uuid="${ls.imageList[0].uuid}" data-filename="${ls.imageList[0].fileName}">
									<img>
								</div>				
								<div class="ls_category">
									${ls.cateName}
								</div>
								<div class="ls_rating">
									${ls.ratingAvg}
								</div>
								<div class="ls_bookName">
									${ls.bookName}
								</div>							
							</div>
						</div>
					</a>					
				</c:forEach>					
			</div>
		</div>
		
		<!-- footer 영역 -->
		<div class="footer_nav">
			<div class="footer_nav_container">
				<ul>
					<li>회사소개</li>
					<span class="line">|</span>
					<li>이용약관</li>
					<span class="line">|</span>
					<li>고객센터</li>
					<span class="line">|</span>
					<li>광고문의</li>
					<span class="line">|</span>
					<li>채용정보</li>
					<span class="line">|</span>
				</ul>
			</div>
		</div>
		
		<div class="footer">
			<div class="footer_container">
				<div class="footer_left">
					<img src="resources/img/Logo.png">
				</div>
				<div class="footer_right">
					(주)BookMall    대표이사 : seo
					<br>
					사업자등록번호 : 111-22-33333
					<br>
					대표전화 : 0000-0000(발신자 부담전화)
					<br>
					<br>
					COPYRIGHT(C) <strong>bookmall.com</strong>    ALL RIGHTS RESERVED.
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
</div>
<script>
	$("#gnb_logout_button").click(function(){
		// alert("버튼 작동");
		$.ajax({
			type: "POST",
			url: "/member/logout",
			success: function(data){
				document.location.reload();
			}
		});
	});
	
	$(document).ready(function(){
		// 슬라이드
		$(".slide_div").slick(
			{
				dots: true,
				autoplay: true,
				autoplaySpeed: 3000
			}		
		);
		
		$(".ls_div").slick({
			slidesToShow: 4,
			slidesToScroll: 4,
			prevArrow : "<button type='button' class='ls_div_content_prev'>이전</button>",
			nextArrow : "<button type='button' class='ls_div_content_next'>다음</button>"
		});
		
		// 이미지 삽입
		$(".image_wrap").each(function(i, obj){
			
			const bobj = $(obj);
			
			if (bobj.data("bookid")){
				const uploadPath = bobj.data("path");
				const uuid = bobj.data("uuid");
				const fileName = bobj.data("filename");
				
				const fileCallPath = encodeURIComponent(uploadPath + "/s_" + uuid + "_" + fileName);
				
				$(this).find("img").attr('src', '/display?fileName=' + fileCallPath);
			} else {
				$(this).find("img").attr('src', '/resources/img/goodsNoImage.png');
			}
		});
	});
</script>
</body>
</html>