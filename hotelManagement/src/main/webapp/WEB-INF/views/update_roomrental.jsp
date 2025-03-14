<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Blue Heaven Hotel Management System</title>
<link rel="icon" type="image/png" href="logo.png">

<!-- Bootstrap CSS -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Bootstrap Icons -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.11.0/font/bootstrap-icons.min.css"
	rel="stylesheet">
<style>
body {
	font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
	margin: 0;
	padding: 20px;
	background-color: #f5f5f5;
}

.container {
	max-width: 600px;
	margin: 0 auto;
	background-color: white;
	padding: 30px;
	border-radius: 8px;
	box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}

h1 {
	text-align: center;
	color: #333;
	margin-bottom: 30px;
}

.form-group {
	margin-bottom: 20px;
}

label {
	display: block;
	margin-bottom: 5px;
	font-weight: bold;
	color: #555;
}

input[type="text"], input[type="tel"], textarea {
	width: 100%;
	padding: 10px;
	border: 1px solid #ddd;
	border-radius: 4px;
	font-size: 16px;
}

textarea {
	height: 100px;
}

button {
	background-color: #4CAF50;
	color: white;
	border: none;
	padding: 12px 20px;
	cursor: pointer;
	font-size: 16px;
	border-radius: 4px;
	width: 100%;
	margin-top: 10px;
}

button:hover {
	background-color: #45a049;
}

.id-field {
	background-color: #f5f5f5;
}

.actions {
	display: flex;
	gap: 10px;
}

.actions button:last-child {
	background-color: #f44336;
}

.actions button:last-child:hover {
	background-color: #d32f2f;
}
</style>
</head>
<body>
	<div class="container">
    <h1>Cập Nhật Phòng</h1>
    <form id="roomRentalUpdateForm" action="../roomrental/update" method="post">
    <div class="form-group">
        <label for="rent_id">Số đặt phòng</label>
        <input type="text" value="${roomRental.rentId}" id="rent_id" name="rentId" class="id-field" readonly>
    </div>

    <div class="form-group">
        <label for="room_id">Số phòng</label>
        <input type="text" value="${roomRental.room.roomId}" id="room_id" name="roomId" readonly>
    </div>

    <div class="form-group">
        <label for="customer_id">Tên khách hàng</label>
        <input type="text" value="${roomRental.customer.customerId}" id="customer_id" name="customerId" >
    </div>

    <div class="form-group">
        <label for="check_in_date">Ngày nhận phòng</label>
        <input type="date" value="${roomRental.checkInDate}" id="check_in_date" name="checkInDate">
    </div>

    <div class="form-group">
        <label for="check_out_date">Ngày trả phòng</label>
        <input type="date" value="${roomRental.checkOutDate}" id="check_out_date" name="checkOutDate">
    </div>

    <div class="form-group">
        <label for="rental_status">Trạng thái</label>
        <select class="form-select" id="rental_status" name="rentalStatus">
            <option value="Confirmed" ${roomRental.rentalStatus == 'Confirmed' ? 'selected' : ''}>Confirmed</option>
            <option value="Pending" ${roomRental.rentalStatus == 'Pending' ? 'selected' : ''}>Pending</option>
            <option value="Cancelled" ${roomRental.rentalStatus == 'Cancelled' ? 'selected' : ''}>Cancelled</option>
        </select>
    </div>

    <div class="actions">
        <button type="submit">Cập Nhật</button>
    </div>
</form>

</div>

</body>
</html>