
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
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
				    <form action="/logout" method="post">
				        <button type="submit" class="btn bg-white text-primary fw-bolder">
				            Đăng xuất
				        </button>
				    </form>
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
                        <a href="rental" class="nav-link" data-bs-toggle="pill">
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
                <!-- Dashboard Tab -->
                <div class="tab-pane pb-5 fade show active" id="dashboard">
                    <h3 class="mb-4">Tổng quan</h3>
                    
                    <div class="row mb-4">
                        <div class="col-md-3">
                            <div class="card stat-card mb-3">
                                <div class="card-body">
                                    <div class="d-flex justify-content-between">
                                        <div>
                                            <h6 class="card-title text-muted">Phòng chờ nhận</h6>
                                            <h2 class="mb-0">${givenRooms.size() }</h2>
                                        </div>
                                        <div class="bg-light p-3 rounded-circle">
                                            <i class="bi bi-clipboard-check fs-3 text-primary"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="card stat-card mb-3">
                                <div class="card-body">
                                    <div class="d-flex justify-content-between">
                                        <div>
                                            <h6 class="card-title text-muted">Đang sử dụng</h6>
                                            <h2 class="mb-0">${occupiedRooms.size() }</h2>
                                        </div>
                                        <div class="bg-light p-3 rounded-circle">
                                            <i class="bi bi-person-fill fs-3 text-success"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="card stat-card mb-3">
                                <div class="card-body">
                                    <div class="d-flex justify-content-between">
                                        <div>
                                            <h6 class="card-title text-muted">Phòng chờ trả</h6>
                                            <h2 class="mb-0">${checkOutRooms.size() }</h2>
                                        </div>
                                        <div class="bg-light p-3 rounded-circle">
                                        	<i class="bi bi-bell fs-3 text-danger"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="card stat-card mb-3">
                                <div class="card-body">
                                    <div class="d-flex justify-content-between">
                                        <div>
                                            <h6 class="card-title text-muted">Doanh thu hôm nay</h6>
                                            <h2 class="mb-0">${ revenue }M</h2>
                                        </div>
                                        <div class="bg-light p-3 rounded-circle">
                                            <i class="bi bi-currency-dollar fs-3 text-warning"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <div class="row mb-4">
                        <div class="col-md-8">
                            <div class="card">
                                <div class="card-header bg-white d-flex justify-content-between align-items-center">
                                    <h5 class="mb-0">Feedback</h5>
                                </div>
                                <div class="card-body">
                                    <div class="table-responsive" style="max-height: 250px; overflow-y: auto; border: 1px solid #ddd;">
    									<table class="table table-hover">
        								<thead class="text-center" style="position: sticky; top: 0; background: white; z-index: 1; text-align: center;">
            							<tr>
                							<th>Mã phản hồi</th>
                							<th>Nội dung</th>
                							<th>Thời gian</th>
                							<th>Mã sử dụng dịch vụ</th>
            							    <th>Mã khách hàng</th>
   									      </tr>
     									   </thead>
        									<tbody>
            								<c:forEach var="feedback" items="${feedbacks }" varStatus="status">
                							<tr>
                    							<td>${feedback.feedbackId }</td>
                    							<td>${feedback.content }</td>
                   								<td>
                  							      <fmt:formatDate value="${feedback.feedbackTime }" pattern="dd/MM/yyyy HH:mm" />
                    							</td>
                    							<td>${feedback.useService.getUsId() }</td>
             							    	<td>${feedback.customer.getCustomerId() }</td>
                							</tr>
 								           </c:forEach>
    									    </tbody>
    									</table>
									</div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="card mb-4">
                                <div class="card-header bg-white">
                                    <h5 class="mb-0">Tình trạng phòng</h5>
                                </div>
                                <div class="card-body">
                                    <div class="mt-3">
                                        <div class="d-flex justify-content-between align-items-center mb-2">
                                            <span>Có sẵn</span>
                                            <span class="badge room-available px-3">${totalAvailable }</span>
                                        </div>
                                        <div class="d-flex justify-content-between align-items-center mb-2">
                                            <span>Đã đặt</span>
                                            <span class="badge room-occupied px-3">${totalOccupied }</span>
                                        </div>
                                        <div class="d-flex justify-content-between align-items-center mb-2">
                                            <span>Bảo trì</span>
                                            <span class="badge room-maintenance px-3">${totalMaintenance }</span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <div class="row">
                        <div class="col-md-6">
                            <div class="card">
                                <div class="card-header bg-white d-flex justify-content-between align-items-center">
                                    <h5 class="mb-0">Check-in hôm nay</h5>
                                </div>
                                <div class="card-body">
                                    <div class="table-responsive">
                                        <table class="table table-hover">
                                            <thead>
                                                <tr>
                                                    <th>Mã đặt phòng</th>
                                                    <th>Khách hàng</th>
                                                    <th>Phòng</th>
                                                    <th>Thời gian</th>
                                                    <th></th>
                                                </tr>
                                            </thead>
                                            
                                            <tbody>
                                            <c:forEach var="givenRoom" items="${givenRooms }" varStatus="status">
                                                <tr>
                                                    <td>${givenRoom.rentId }</td>
                                                    <td>${givenRoom.customer.getCustomerName() }</td>
                                                    <td>${givenRoom.room.getRoomId() }</td>
                                                    <td>
                                                    	<fmt:formatDate value="${givenRoom.rentalDate }" pattern="dd/MM/yyyy HH:mm" />
                                                    </td>
                                                    <td>
                                                    	
                                                    	<form action="checkin" method="post">
                                                    		<input type="text" hidden name="rentId" value="${givenRoom.rentId }">
                                                    		<button class="btn btn-sm btn-outline-success">Check-in</button>
                                                    	</form>
                                                    </td>
                                                </tr>
                                             </c:forEach>
                                             
                                 
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="card">
                                <div class="card-header bg-white d-flex justify-content-between align-items-center">
                                    <h5 class="mb-0">Check-out hôm nay</h5>
                                </div>
                                <div class="card-body">
                                    <div class="table-responsive">
                                         <table class="table table-hover">
                                            <thead>
                                                <tr>
                                                    <th>Mã đặt phòng</th>
                                                    <th>Khách hàng</th>
                                                    <th>Phòng</th>
                                                    <th>Thời gian</th>
                                                    <th></th>
                                                </tr>
                                            </thead>
                                            
                                            <tbody>
                                            <c:forEach var="checkOutRoom" items="${checkOutRooms }" varStatus="status">
                                                <tr>
                                                    <td>${checkOutRoom.rentId }</td>
                                                    <td>${checkOutRoom.customer.getCustomerName() }</td>
                                                    <td>${checkOutRoom.room.getRoomId() }</td>
                                                    <td>
                                                    	<fmt:formatDate value="${checkOutRoom.rentalDate }" pattern="dd/MM/yyyy HH:mm"/>
                                                    </td>
                                                    <td>
                                                    	<form action="checkout" method="post">
                                                    		<input type="text" hidden name="rentId" value=">${checkOutRoom.rentId }">
                                                    		<input type="text" hidden name="roomId" value="${checkOutRoom.room.getRoomId() }">
                                                    		<input type="text" hidden name="status" value="Available">
                                                    		<button class="btn btn-sm btn-outline-success">Check-out</button>
                                                    	</form>
                                                    </td>
                                                </tr>
                                             </c:forEach>

                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
</body>
</html>
