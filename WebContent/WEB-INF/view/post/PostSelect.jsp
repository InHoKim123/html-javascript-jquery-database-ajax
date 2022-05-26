<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet" href="./css/styles.css">
<link href="./css/postSelect.css" rel="stylesheet">
<link href="./css/bootstrap.css" rel="stylesheet">
<script src="./js/jquery-3.5.1.min.js" type="text/javascript"></script>
<script src="./js/bootstrap.min.js" type="text/javascript"></script>
<title>전체 글</title>
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
         					 <a href="./Login.lo" class="nav__link">
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
	
	<form action="#" method="get" enctype="application/x-www-form-urlencoded">
		<div class="user-selectpost">
			<fieldset>
				<span><font class="select_post">게시판</font></span>
				<div class="writepost">
					<button type="button" class="writepostbtn"
						onclick="location.href='./PostInsert.po'">새 글 쓰기</button>
				</div>
				<div>
					<span> <span class="search_submit"> <select
							name="postkind" class="postkind">
								<option value="전체검색" selected="selected">전체검색
								<option value="작성자검색">작성자검색
								<option value="제목검색 ">제목검색
						</select>
					</span> <span> <input type="text" class="postwriter"
							id="postwriter" name="postwriter"
							placeholder="postwriter/varchar2(50BYTE)"> <input
							type="submit" value="검색" class="postwritersub">
					</span>
					</span>
				</div>
				<br>
				<br>			
				
				<c:forEach var="arrayList" items="${arrayList}">
				 <div class="postlist" onclick="location.href='./PostSelectDetail.po?postnum=${arrayList.postnum}'">
					<div class="postshape">
						<div class="postnum">
							<hr>
							<span class="postnumber">#${arrayList.postnum}</span>
							<span class="postkindcheck">${arrayList.postkind}</span>
						</div>
						<div class="posttitle">
						  <c:choose>
						   <c:when test="${arrayList.postwriter eq '관리자' }">
						    <span class="posttitlenotice">${arrayList.posttitle}</span> 
						   </c:when>
						   <c:otherwise>
						    <span class="posttitleset">${arrayList.posttitle}</span> 	
						   </c:otherwise>
						  </c:choose>					  				  
						   <span class="detail">
							<span class="viewspic"> 
							  <span><img class="viewpic" alt="조회수" src="./img/view.png"></span> 
							  <span class="views">${arrayList.views}</span>
							</span> 
							<span class="writerpic"> 
							  <img class="profilepic" alt="프로필사진" src="./img/dog.jpg">
							</span> 
							<span class="writeoption"> 
							   <span class="writernic">${arrayList.postwriter}</span>
							   <span class="writetime">${arrayList.postwriteday}</span>
							</span>
						   </span>
						</div>
					</div>
				</div>
				</c:forEach>

				<br>
				<br>
			</fieldset>
		</div>
	</form>


  </div>
	<footer>
		<p class="footercla">Copyright KimInHo © 2022 JavaProject</p>
	</footer>
	<!-- IONICONS -->
	<script src="https://unpkg.com/ionicons@5.2.3/dist/ionicons.js"></script>
	<!-- JS -->
	<script src="./js/main.js"></script>
</body>
</html>