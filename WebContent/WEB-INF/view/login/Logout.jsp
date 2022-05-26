<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그아웃</title>
</head>
<body>
<script type="text/javascript">
 if (confirm("로그아웃 하시겠습니까?")) {
	location.href="./LogOutView.lo";
}else {
	history.back();
}
</script>

</body>
</html>