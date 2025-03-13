<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chi tiết khách hàng</title>
</head>
<body>
    		<p>${customer.customerId}</p>
        	<p>${customer.customerName}</p>
        	<p>${customer.customerPhone}</p>
        	<p>${customer.address}</p>

</body>
</html>