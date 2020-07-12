<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% 
		//request
		//response
		//application
		//config
		//page
		//session
		//out
		request.setCharacterEncoding("utf-8");
		String userName = null;
		if (request.getMethod().equals("POST"))
			userName = request.getParameter("userName");
		
		userName = (userName==null)?"":userName.trim();
	
	%>
	<h1>
		<% 
			if (userName.isEmpty())
				out.print("Привет!");
			else
				out.print(String.format("Привет, %s!", userName));
		%>
	</h1>
	<form method="POST">
		<label>Ваше имя: </label>
		<input type="text" name="userName" value="<%=userName%>">
		<input type="submit" value="привет">
	</form>
</body>
</html>