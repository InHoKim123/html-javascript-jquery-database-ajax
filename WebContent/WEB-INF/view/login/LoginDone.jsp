<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:choose>
 <c:when test="${sessionScope.seman eq 1}">
  <script type="text/javascript">
   alert("관리자님 반갑습니다!");
   location.href="./Home.lo";
  </script>
 </c:when>
 <c:otherwise>
  <script type="text/javascript">
   alert("${sessionScope.senic}님 반갑습니다!");
   location.href="./Home.lo";
  </script>
 </c:otherwise>
</c:choose>

</body>
</html>