<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
        
        
        
        .custom-table {
    display: block;
    width: 100%;
    overflow-x: auto; /* Thêm thanh trượt ngang */
    white-space: nowrap; /* Ngăn dòng xuống */
}

.custom-table thead {
    position: sticky;
    top: 0;
    background-color: #007bff; /* Màu nền tiêu đề giống ảnh */
    color: white;
    z-index: 1; /* Đảm bảo thead nằm trên tbody khi cuộn */
}

.custom-table tbody {
    display: block;
    max-height: 400px; /* Giới hạn chiều cao để có thanh trượt dọc nếu cần */
    overflow-y: auto; /* Thêm thanh trượt dọc nếu vượt quá chiều cao */
}

.custom-table th,
.custom-table td {
    min-width: 120px; /* Đặt độ rộng tối thiểu cho các cột */
}

/* Đảm bảo các cột trong thead và tbody đồng bộ */
.custom-table thead tr,
.custom-table tbody tr {
    display: table;
    width: 100%;
    table-layout: fixed;
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
                    <a href="/logout" class="btn bg-white text-primary fw-bolder">Đăng xuất</a>
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
            <header class="mb-4">
                <div class="d-flex justify-content-between align-items-center">
                    <button id="sidebarToggle" class="btn btn-sm btn-outline-secondary">
                        <i class="bi bi-list"></i>
                    </button>
                    <div class="d-flex align-items-center">
                        <div class="search-wrapper me-3">
                            <i class="bi bi-search"></i>
                            <input type="text" class="form-control" placeholder="Tìm kiếm...">
                        </div>
                        <div class="dropdown">
                            <div class="profile-menu" data-bs-toggle="dropdown">
                                <i class="bi bi-bell position-relative">
                                    <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
                                        3
                                    </span>
                                </i>
                            </div>
                            <ul class="dropdown-menu dropdown-menu-end alert-section">
                                <li><h6 class="dropdown-header">Thông báo</h6></li>
                                <li><a class="dropdown-item" href="#">Khách mới đặt phòng 101</a></li>
                                <li><a class="dropdown-item" href="#">Bảo trì phòng 205 hoàn tất</a></li>
                                <li><a class="dropdown-item" href="#">Nhắc nhở: check-out phòng 304</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </header>
            
            <!-- Content -->
	<div class="tab-content">
        <!-- Rental Management Section -->
        <div class="tab-pane fade show active" id="rental-management">
            <h3 class="mb-4">Quản Lý Đặt Phòng</h3>

            <!-- Filter and Search Section -->
            <div class="card mb-4 p-3">
                <div class="row g-3">
                    <div class="col-md-3">
                        <select class="form-select" id="rentalStatusFilter">
                            <option value="">Trạng thái</option>
                            <option value="Đang chờ">Đang chờ</option>
                            <option value="Đã xác nhận">Đã xác nhận</option>
                            <option value="Đã hoàn thành">Đã hoàn thành</option>
                            <option value="Đã hủy">Đã hủy</option>
                        </select>
                    </div>
                    <div class="col-md-3">
                        <input type="date" class="form-control" id="checkInDateFilter" placeholder="Ngày nhận phòng">
                    </div>
                    <div class="col-md-3">
                        <input type="date" class="form-control" id="checkOutDateFilter" placeholder="Ngày trả phòng">
                    </div>
                    <div class="col-md-3">
                        <div class="search-wrapper">
                            <i class="bi bi-search"></i>
                            <input type="text" class="form-control" placeholder="Tìm đặt phòng..." id="searchRentalInput">
                        </div>
                    </div>
                </div>
            </div>

            <!-- Add New Rental Button -->
            <div class="mb-4">
                <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addRentalModal">Thêm đặt phòng mới</button>
            </div>

            <!-- Rental List -->
            <div class="room-list-wrapper">
        <table class="table table-striped table-hover custom-table">
            <thead>
                <tr>
                    <th>Số đặt phòng</th>
                    <th>Số phòng</th>
                    <th>Tên khách hàng</th>
                    <th>Ngày nhận phòng</th>
                    <th>Ngày trả phòng</th>
                    <th>Trạng thái</th>
                    <th>Hành động</th>
                </tr>
            </thead>
            <tbody id="rentalList">
                <c:forEach var="rental" items="${rentals}">
                    <tr>
                        <td>${rental.rentId}</td>
                        <td>${rental.room.roomId}</td>
                        <td>${rental.customer.customerId}</td>
                        <td>${rental.checkInDate}</td>
                        <td>${rental.checkOutDate}</td>
                        <td>
                            <span class="status-badge 
                                <c:choose>
                                    <c:when test="${rental.rentalStatus == 'Đang chờ'}">room-reserved</c:when>
                                    <c:when test="${rental.rentalStatus == 'Đã xác nhận'}">room-available</c:when>
                                    <c:when test="${rental.rentalStatus == 'Đã hoàn thành'}">room-occupied</c:when>
                                    <c:when test="${rental.rentalStatus == 'Đã hủy'}">room-maintenance</c:when>
                                </c:choose>">${rental.rentalStatus}</span>
                        </td>
                        <td>
                            <button class="btn btn-warning btn-sm btn-action" data-bs-toggle="modal" data-bs-target="#editRentalModal" onclick="loadRentalData('${rental.rentId}')">Sửa</button>
                            <button class="btn btn-danger btn-sm btn-action" onclick="cancelRental('${rental.rentId}')">Hủy</button>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

            <!-- Add Rental Modal -->
            <div class="modal fade" id="addRentalModal" tabindex="-1" aria-labelledby="addRentalModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="addRentalModalLabel">Thêm đặt phòng mới</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <form id="addRentalForm">
                                <div class="mb-3">
                                    <label for="bookingId" class="form-label">Số đặt phòng</label>
                                    <input type="text" class="form-control" id="bookingId" readonly>
                                </div>
                                <div class="mb-3">
                                    <label for="roomNumber" class="form-label">Số phòng</label>
                                    <select class="form-select" id="roomNumber" required>
                                        <c:forEach var="room" items="${availableRooms}">
                                            <option value="${room.roomNumber}">${room.roomNumber} (${room.category.categoryName})</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="mb-3">
                                    <label for="customerName" class="form-label">Tên khách hàng</label>
                                    <input type="text" class="form-control" id="customerName" required>
                                </div>
                                <div class="mb-3">
                                    <label for="checkInDate" class="form-label">Ngày nhận phòng</label>
                                    <input type="date" class="form-control" id="checkInDate" required>
                                </div>
                                <div class="mb-3">
                                    <label for="checkOutDate" class="form-label">Ngày trả phòng</label>
                                    <input type="date" class="form-control" id="checkOutDate" required>
                                </div>
                                <div class="mb-3">
                                    <label for="rentalStatus" class="form-label">Trạng thái</label>
                                    <select class="form-select" id="rentalStatus" required>
                                        <option value="Đang chờ">Đang chờ</option>
                                        <option value="Đã xác nhận">Đã xác nhận</option>
                                    </select>
                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                            <button type="button" class="btn btn-primary" onclick="addRental()">Thêm</button>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Edit Rental Modal -->
            <div class="modal fade" id="editRentalModal" tabindex="-1" aria-labelledby="editRentalModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="editRentalModalLabel">Sửa thông tin đặt phòng</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <form id="editRentalForm">
                                <div class="mb-3">
                                    <label for="editBookingId" class="form-label">Số đặt phòng</label>
                                    <input type="text" class="form-control" id="editBookingId" readonly>
                                </div>
                                <div class="mb-3">
                                    <label for="editRoomNumber" class="form-label">Số phòng</label>
                                    <select class="form-select" id="editRoomNumber" required>
                                        <c:forEach var="room" items="${availableRooms}">
                                            <option value="${room.roomNumber}">${room.roomNumber} (${room.category.categoryName})</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="mb-3">
                                    <label for="editCustomerName" class="form-label">Tên khách hàng</label>
                                    <input type="text" class="form-control" id="editCustomerName" required>
                                </div>
                                <div class="mb-3">
                                    <label for="editCheckInDate" class="form-label">Ngày nhận phòng</label>
                                    <input type="date" class="form-control" id="editCheckInDate" required>
                                </div>
                                <div class="mb-3">
                                    <label for="editCheckOutDate" class="form-label">Ngày trả phòng</label>
                                    <input type="date" class="form-control" id="editCheckOutDate" required>
                                </div>
                                <div class="mb-3">
                                    <label for="editRentalStatus" class="form-label">Trạng thái</label>
                                    <select class="form-select" id="editRentalStatus" required>
                                        <option value="Đang chờ">Đang chờ</option>
                                        <option value="Đã xác nhận">Đã xác nhận</option>
                                        <option value="Đã hoàn thành">Đã hoàn thành</option>
                                        <option value="Đã hủy">Đã hủy</option>
                                    </select>
                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                            <button type="button" class="btn btn-primary" onclick="updateRental()">Cập nhật</button>
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