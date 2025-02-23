<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Demo</title>
</head>
<body>
	<h1><c:out value="${title}" /></h1>
    <p>Công nghệ: <c:out value="${techology}" /></p>
    <p><c:out value="${message}" /></p>
    <a href="<c:out value="${link}" />"><c:out value="${linkTitle}" /></a>
    
   		<p>Danh sách thông tin cụ thể từng khách hàng:</p>
    	<c:forEach var="customer" items="${customers }" varStatus="status">
    	    <p>STT: ${status.count}</p>
        	<p>${customer.customerName}</p>
        	<a href="/customer?id=${customer.customerId}">Xem chi tiết</a>
        	
        	<form action="customer/delete" method="post" onsubmit="return confirm('Bạn có chắc chắn muốn xóa không?');">
    			<input type="hidden" name="id" value="${customer.customerId}">
    			<button type="submit" style="color: red">Xóa khách hàng</button>
			</form>
    	</c:forEach>
	
	<c:if test="${empty customers}">
    	<p>Danh sách khách hàng không tồn tại hoặc trống.</p>
	</c:if>
	
	<h2>Thêm khách hàng mới</h2>

    <form action="saveCustomer" method="post">
        <label for="customerName">Tên Khách Hàng:</label>
        <input type="text" id="customerName" name="customerName" required><br><br>

        <label for="customerPhone">Số Điện Thoại:</label>
        <input type="text" id="customerPhone" name="customerPhone" required><br><br>

        <label for="address">Địa Chỉ:</label>
        <input type="text" id="address" name="address" required><br><br>

        <button type="submit">Lưu khách hàng</button>
    </form>
    
</body>
</html>