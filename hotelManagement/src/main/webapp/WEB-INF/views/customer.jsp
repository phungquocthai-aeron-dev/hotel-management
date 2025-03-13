<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="com.quantridulieu.hotelManagement.entities.Customer" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Blue Heaven Hotel Management System</title>
    <link rel="icon" type="image/png" href="logo.png">

    <!-- Bootstrap CSS -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap Icons -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.11.0/font/bootstrap-icons.min.css" rel="stylesheet">
    <style>
        :root {
            --primary: #1e3a8a;
            --secondary: #475569;
            --accent: #f59e0b;
            --light: #f8fafc;
            --dark: #0f172a;
        }
        
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f0f4f8;
        }
        
        .sidebar {
            background-color: var(--primary);
            color: white;
            height: 100vh;
            position: fixed;
            transition: all 0.3s;
        }
        
        .content {
            margin-left: 250px;
            transition: all 0.3s;
        }
        
        .sidebar-collapsed .sidebar {
            margin-left: -250px;
        }
        
        .sidebar-collapsed .content {
            margin-left: 0;
        }
        
        .nav-link {
            color: rgba(255, 255, 255, 0.8);
            border-radius: 5px;
            margin: 5px 0;
        }
        
        .nav-link:hover, .nav-link.active {
            background-color: rgba(255, 255, 255, 0.1);
            color: white;
        }
        
        .nav-link i {
            margin-right: 10px;
        }
        
        .hotel-logo {
            height: 50px;
        }
        
        .card {
            border: none;
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            transition: transform 0.3s;
        }
        
        .card:hover {
            transform: translateY(-5px);
        }
        
        .stat-card {
            border-left: 5px solid var(--primary);
        }
        
        .room-available {
            background-color: #dcfce7;
            color: #166534;
        }
        
        .room-occupied {
            background-color: #fee2e2;
            color: #b91c1c;
        }
        
        .room-maintenance {
            background-color: #fef3c7;
            color: #92400e;
        }
        
        .room-reserved {
            background-color: #dbeafe;
            color: #1e40af;
        }
        
        .status-badge {
            padding: 5px 10px;
            border-radius: 20px;
            font-size: 12px;
            font-weight: bold;
        }
        
        .btn-primary {
            background-color: var(--primary);
            border-color: var(--primary);
        }
        
        .btn-primary:hover {
            background-color: #1e40af;
            border-color: #1e40af;
        }
        
        .profile-menu {
            cursor: pointer;
        }
        
        .table th {
            background-color: var(--primary);
            color: white;
        }
        
        .search-wrapper {
            position: relative;
        }
        
        .search-wrapper i {
            position: absolute;
            top: 10px;
            left: 10px;
            color: #6b7280;
        }
        
        .search-wrapper input {
            padding-left: 35px;
        }
        
        .alert-section {
            max-height: 300px;
            overflow-y: auto;
        }
        
        a {
        	text-decoration: none;
        }
    </style>
</head>
<body>

    <div class="wrapper">
        <!-- Sidebar -->
        <nav id="sidebar" class="sidebar" style="max-width: 250px;">
            <div class="p-3">
                <div class="d-flex align-items-center mb-4 mt-2">
                    <div class="bg-white p-2 rounded me-2">
                        <img src="logo.png" alt="Blue Heaven Logo" class="hotel-logo">
                    </div>
                    <h4 class="mb-0">Blue Heaven</h4>
                </div>
                
                <div class="user-info d-flex align-items-center mb-2">
                    <img 
                    style="width: 40px; height: 40px; border-radius: 50%;"
                    src="https://static.vecteezy.com/system/resources/thumbnails/012/210/707/small_2x/worker-employee-businessman-avatar-profile-icon-vector.jpg" alt="User" class="rounded-circle me-2">
                    <div>
                        <h6 class="mb-0">Nguyễn Văn A</h6>
                        <small>Quản lý</small>
                    </div>
                </div>
                <div class="mb-4">
                    <a href="logout" class="btn bg-white text-primary fw-bolder">Đăng xuất</a>
                </div>
                
                <ul class="nav flex-column">
                   <li class="nav-item">
                        <a href="home" class="nav-link active" data-bs-toggle="pill">
                            <i class="bi bi-speedometer2"></i> Tổng quan
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="room" class="nav-link" data-bs-toggle="pill">
                            <i class="bi bi-house-door"></i> Quản lý phòng
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="roomrental" class="nav-link" data-bs-toggle="pill">
                            <i class="bi bi-calendar-check"></i> Đặt phòng
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="customer" class="nav-link" data-bs-toggle="pill">
                            <i class="bi bi-people"></i> Khách hàng
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="service" class="nav-link" data-bs-toggle="pill">
                            <i class="bi bi-basket"></i> Dịch vụ
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="staff" class="nav-link" data-bs-toggle="pill">
                            <i class="bi bi-person-badge"></i> Nhân viên
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="invoice" class="nav-link" data-bs-toggle="pill">
                            <i class="bi bi-receipt"></i> Hóa đơn
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="maintenance" class="nav-link" data-bs-toggle="pill">
                            <i class="bi bi-tools"></i> Bảo trì
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="promotion" class="nav-link" data-bs-toggle="pill">
                            <i class="bi bi-tag"></i> Khuyến mãi
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="statistics" class="nav-link" data-bs-toggle="pill">
                            <i class="bi bi-bar-chart"></i> Báo cáo
                        </a>
                    </li>
                </ul>
            </div>
        </nav>
        
        <!-- Content -->
        <div class="content p-4">
            
            <div class="tab-content">

                <!-- Rooms Tab -->
                <div class="tab-pane pb-5 fade show active" id="customers">
                    <h3 class="text-center mb-5">Quản lý khách hàng</h3>
                        
                    <form action="customer/search" method="get">
                            <div class="row bg-white p-3 rounded-3">
                                
                                <div class="col-md-3 mb-3">
                                    <input type="text" class="form-control" placeholder="Mã người dùng" name="customerId">
                                </div>
                                <div class="col-md-3 mb-3">
                                    <input type="text" class="form-control" placeholder="Tên người dùng" name="customerName">
                                </div>
                                <div class="col-md-3 mb-3">
                                    <input type="text" class="form-control" placeholder="Địa chỉ" name="address">
                                </div>
                                <div class="col-md-3 mb-3">
                                    <input type="text" class="form-control" placeholder="Số điện thoại" name="customerPhone">
                                </div>
                                <div class="col-md-12 mb-12">
                                    <div class="input-group">
                                        <button type="submit" class="btn btn-outline-secondary btn-primary text-white mx-auto">
                                            <i class="bi bi-search"></i>
                                            Tìm khách hàng
                                        </button>
                                    </div>
                                </div>
                               
                            </div>
          				</form>        
                         
                     </div>
                </div>
                <!-- Invoice Table -->
                <div class="table-responsive">
                
                	
                	<div class="float-end mb-3">
                		
                		<div class="d-flex">
                		
                			<c:if test="${not empty customers}">
                			<form action="customer/export" method="post">
                				<c:forEach var="customer" items="${customers }" varStatus="status">
                        			<input type="hidden" name="customersExport" value="${customer.customerId}" />
                				</c:forEach>
                				<button class="btn btn-success me-1">
                               		Xuất Excel
                            	</button>
                          	</form>
                          	</c:if>
							<a href="">
                    			<button class="btn btn-primary ms-1">
                                	Xem tất cả
                            	</button>
							</a>
                		</div>
                    </div>
                	 <table class="table table-hover table-bordered">
                        <thead>
                            <tr class="text-center">
                                <th>STT</th>
                                <th>Mã khách hàng</th>
                                <th>Tên khách hàng</th>
                                <th>Địa chỉ</th>
                                <th>Số điện thoại</th>
                        <%-- <c:if test="${staff.role == 'ADMIN'}"> --%>
                                <th>Thao tác</th>
                         <%--       </c:if> --%>
                            </tr>
                        </thead>
                        <tbody>
                        	<c:forEach var="customer" items="${customers }" varStatus="status">
                        	<tr>
                        		<%-- Dữ liệu gửi về server sẽ là 1 danh sách các id người dùng --%>
                        		<input type="hidden" name="customersExport" value="${customer.customerId}" />

                                
                                <td class="text-center">${status.count}</td>
                                <td>${customer.customerId}</td>
                                <td>${customer.customerName}</td>
                                <td>${customer.address}</td>
                                <td>${customer.customerPhone}</td>
                        <%--        <c:if test="${staff.role == 'ADMIN'}"> --%> 
                                <td class="text-center">
                                 <div class="mx-auto d-flex" style="justify-content: center;">
                                 	<a class="d-block" href="customer/details?id=${customer.customerId}" style="text-decoration: none;">
                                 		<button class="btn btn-warning me-1">Sửa</button>
                                 	</a>
                                 	<form action="customer/delete" method="post" onsubmit="return confirm('Bạn có chắc chắn muốn xóa không?');">
    									<input type="hidden" name="id" value="${customer.customerId}">
    									<button type=submit class="btn btn-danger">Xóa</button>
									</form>
                                 </div>
                                </td>
				<%-- 				</c:if>	--%> 
                            </tr>
                        	</c:forEach>
            
                        </tbody>
                    </table>
                                   
                </div>
            </div>
        </div>
        
         <script type="text/javascript">
                                document.addEventListener('DOMContentLoaded', function() {
                                    // Chọn tất cả các phần tử có thể nhập liệu (input, textarea, select)
                                    const inputElements = document.querySelectorAll('input[type="text"], textarea, select');
                                    
                                    // Thêm sự kiện cho mỗi phần tử
                                    inputElements.forEach(element => {
                                        // Xác định sự kiện phù hợp cho từng loại phần tử
                                        let eventType = 'input'; // Mặc định cho input và textarea
                                        
                                        if (element.tagName.toLowerCase() === 'select') {
                                            eventType = 'change'; // Sử dụng sự kiện change cho select
                                        }
                                        
                                        // Thêm người nghe sự kiện
                                        element.addEventListener(eventType, function() {
                                            // Kiểm tra xem phần tử này có giá trị hay không
                                            let hasValue = false;
                                            
                                            if (element.tagName.toLowerCase() === 'select') {
                                                // Với select, kiểm tra xem có option được chọn không phải là option mặc định
                                                hasValue = element.value !== '' && element.selectedIndex !== 0;
                                            } else {
                                                // Với input và textarea, kiểm tra có text không
                                                hasValue = element.value.trim() !== '';
                                            }
                                            
                                            // Nếu phần tử này có giá trị, xóa giá trị của tất cả phần tử khác
                                            if (hasValue) {
                                                inputElements.forEach(otherElement => {
                                                    if (otherElement !== element) {
                                                        if (otherElement.tagName.toLowerCase() === 'select') {
                                                            otherElement.selectedIndex = 0; // Reset select về option đầu tiên
                                                        } else {
                                                            otherElement.value = ''; // Xóa giá trị của input và textarea
                                                        }
                                                    }
                                                });
                                            }
                                        });
                                    });
                                    
                                    // Thêm xác nhận khi submit form để đảm bảo có ít nhất một trường được nhập
                                    document.querySelector('form').addEventListener('submit', function(e) {
                                        // Kiểm tra xem có ít nhất một trường có giá trị
                                        let hasValue = false;
                                        
                                        inputElements.forEach(element => {
                                            if (
                                                (element.tagName.toLowerCase() === 'select' && element.value !== '' && element.selectedIndex !== 0) ||
                                                (element.tagName.toLowerCase() !== 'select' && element.value.trim() !== '')
                                            ) {
                                                hasValue = true;
                                            }
                                        });
                                        
                                        // Nếu không có trường nào có giá trị, ngăn form submit
                                        if (!hasValue) {
                                            e.preventDefault();
                                            alert('Vui lòng nhập ít nhất một giá trị để tìm kiếm');
                                        }
                                    });
                                });
                                </script>
</body>
</html>