<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/user_save.action" method="post">
		username:<input type="text" name="username"><br>
		password:<input type="password" name="password"><br>
		<input type="submit" value="保存"><br>
	</form>
	<a href="${pageContext.request.contextPath}/user_findAll.action">查找全部</a><br>
	<a href="${pageContext.request.contextPath}/user_findById.action">查找单个</a>
</body>
</html>