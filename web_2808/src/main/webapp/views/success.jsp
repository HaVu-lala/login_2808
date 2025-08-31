<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng nhập thành công</title>
</head>
<body>
	<h2 style="color: green;">Đăng nhập thành công!</h2>
	<p>
		Xin chào,
		<%=session.getAttribute("username")%>
		🎉
	</p>

	<a href="home.jsp">Về trang chủ</a>

</body>
</html>