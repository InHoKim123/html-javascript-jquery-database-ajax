<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet" href="./css/styles.css">
<link href="./css/bootstrap.css" rel="stylesheet">
<link href="./css/slide.css" rel="stylesheet">
<link rel="stylesheet" href="./css/scroll_reset.css">
<link rel="stylesheet" href="./css/scroll_common.css">

<script src="./js/jquery-3.5.1.min.js" type="text/javascript"></script>
<script src="./js/bootstrap.min.js" type="text/javascript"></script>
<script src="./js/slide.js" type="text/javascript"></script>
<script src="./js/scroll_script.js" type="text/javascript"></script>

<title>JavaProject</title>
</head>
<body id="body-pd">
 <div id="wrap">
 <div class="l-navbar" id="navbar">
		<nav class="nav">
			<div>
			    
				<div class="nav__brand">
					<ion-icon name="menu-outline" class="nav__toggle" id="nav-toggle"></ion-icon>
					<a href="./Home.lo" class="nav__logo">OKKY</a>
				</div>
				<div class="nav__list">
					<a href="./Home.lo" class="nav__link active">
					 <ion-icon name="home-outline" class="nav__icon"></ion-icon>
					 <span class="nav_name">홈</span>
					</a> 


    					<c:if test="${sessionScope.seman eq 1}">  
       						<a href="./MemberSelect.me" class="nav__link">
					 		 <ion-icon name="people-outline" class="nav__icon"></ion-icon>
					 		 <span class="nav_name">회원검색</span>
							</a> 
						</c:if>
				
					<c:choose>  
    					<c:when test="${!empty sessionScope.seid}">  
       						<a href="./PostSelect.po" class="nav__link">
					 		 <ion-icon name="reader-outline" class="nav__icon"></ion-icon> 
					 		 <span class="nav_name">게시판</span>
							</a> 
    					</c:when>  
    				 <c:otherwise>  
         					 <a href="./DoLoginFirst.lo" class="nav__link">
							  <ion-icon name="reader-outline" class="nav__icon"></ion-icon> 
							  <span class="nav_name">게시판</span>
							 </a> 
    				 </c:otherwise>  
					</c:choose>  
					<form action="./MemberSelectDetail.me" method="get">
					<c:if test="${!empty sessionScope.seid}">
						<a href="./MemberSelectDetail.me?id=${sessionScope.seid }" class="nav__link">					 
						 <ion-icon name="person-outline" class="nav__icon"></ion-icon>
						 <span class="nav_name"> 내정보</span>
						</a>
					</c:if>
					</form>
					<c:choose>  
    					<c:when test="${!empty sessionScope.seid}">  
       						<a href="./LogOut.lo" class="nav__link">
							 <ion-icon name="log-out-outline" class="nav__icon"></ion-icon>
				 			 <span class="nav_name">로그아웃</span>
							</a>
    					</c:when>  
    				 <c:otherwise>  
         					 <a href="./Login.lo" class="nav__link">
							  <ion-icon name="log-out-outline" class="nav__icon"></ion-icon>
							  <span class="nav_name">로그인</span>
							 </a> 
    				 </c:otherwise>  
					</c:choose>  
					
						
				</div>
				
			</div>
		</nav>
	</div>
	
	<div>
	 <hr>
	</div>
  <form action="./Home.lo" method="get" enctype="application/x-www-form-urlencoded">
   <div class="banner">
	 <div class="slide-carousel" id="one">
        <div class="arrow-left">&lsaquo;</div>
        <div class="arrow-right">&rsaquo;</div>

        <div class="slides">
          <div class="slide" id="slide1"><span class="title"></span></div>
          <div class="slide" id="slide2"><span class="title"></span></div>
          <div class="slide" id="slide3"><span class="title"></span></div>
          <div class="slide" id="slide4"><span class="title"></span></div>
          <div class="slide" id="slide5"><span class="title"></span></div>
        </div>
        <div class="dots"></div>
      </div>      
   </div>
   </form>
   <form action="./Home.lo" method="post" enctype="application/x-www-form-urlencoded">
   <div> 
   <span class="fade_in u-fade-type-left js-scroll-trigger" onclick="location.href = '#'">
    <span class="custom-font">베스트 글</span>
   </span>
   <span class="fade_in u-fade-type-left js-scroll-trigger" onclick="location.href = '#'">
    <span class="custom-font">베스트 글</span>
   </span>
   </div>
   </form>
   
   
   
 </div>	
 
 <footer> <p class="footercla">Copyright KimInHo © 2022 JavaProject </p> </footer>
	<!-- IONICONS -->
	<script src="https://unpkg.com/ionicons@5.2.3/dist/ionicons.js"></script>
	<!-- JS -->
	<script src="./js/main.js"></script>
	
	
</body>
</html>