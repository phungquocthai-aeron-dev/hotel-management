<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
</head>
<body>
	<h1><c:out value="${title}" /></h1>
    <p>Công nghệ: <c:out value="${techology}" /></p>
    <p><c:out value="${message}" /></p>
    <a href="<c:out value="${link}" />"><c:out value="${linkTitle}" /></a>
</body>
</html>