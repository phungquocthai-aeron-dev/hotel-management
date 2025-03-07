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
                        <a href="/home" class="nav-link active" data-bs-toggle="pill">
                            <i class="bi bi-speedometer2"></i> Tổng quan
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="/room" class="nav-link" data-bs-toggle="pill">
                            <i class="bi bi-house-door"></i> Quản lý phòng
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="#rental" class="nav-link" data-bs-toggle="pill">
                            <i class="bi bi-calendar-check"></i> Đặt phòng
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="/customer" class="nav-link" data-bs-toggle="pill">
                            <i class="bi bi-people"></i> Khách hàng
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="/service" class="nav-link" data-bs-toggle="pill">
                            <i class="bi bi-basket"></i> Dịch vụ
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="/staff" class="nav-link" data-bs-toggle="pill">
                            <i class="bi bi-person-badge"></i> Nhân viên
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="/invoice" class="nav-link" data-bs-toggle="pill">
                            <i class="bi bi-receipt"></i> Hóa đơn
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="/maintenance" class="nav-link" data-bs-toggle="pill">
                            <i class="bi bi-tools"></i> Bảo trì
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="/promotion" class="nav-link" data-bs-toggle="pill">
                            <i class="bi bi-tag"></i> Khuyến mãi
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="/statistics" class="nav-link" data-bs-toggle="pill">
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
                                            <h6 class="card-title text-muted">Tổng số phòng</h6>
                                            <h2 class="mb-0">120</h2>
                                        </div>
                                        <div class="bg-light p-3 rounded-circle">
                                            <i class="bi bi-house-door fs-3 text-primary"></i>
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
                                            <h2 class="mb-0">85</h2>
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
                                            <h6 class="card-title text-muted">Doanh thu hôm nay</h6>
                                            <h2 class="mb-0">28.5M</h2>
                                        </div>
                                        <div class="bg-light p-3 rounded-circle">
                                            <i class="bi bi-currency-dollar fs-3 text-warning"></i>
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
                                            <h6 class="card-title text-muted">Đặt phòng mới</h6>
                                            <h2 class="mb-0">12</h2>
                                        </div>
                                        <div class="bg-light p-3 rounded-circle">
                                            <i class="bi bi-calendar-plus fs-3 text-danger"></i>
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
                                    <h5 class="mb-0">Thống kê doanh thu</h5>
                                    <div class="btn-group">
                                        <button class="btn btn-sm btn-outline-secondary active">Ngày</button>
                                        <button class="btn btn-sm btn-outline-secondary">Tuần</button>
                                        <button class="btn btn-sm btn-outline-secondary">Tháng</button>
                                    </div>
                                </div>
                                <div class="card-body">
                                    <img src="/api/placeholder/700/300" alt="Revenue Chart" class="img-fluid">
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="card mb-4">
                                <div class="card-header bg-white">
                                    <h5 class="mb-0">Tình trạng phòng</h5>
                                </div>
                                <div class="card-body">
                                    <img src="/api/placeholder/300/200" alt="Room Status Chart" class="img-fluid">
                                    <div class="mt-3">
                                        <div class="d-flex justify-content-between align-items-center mb-2">
                                            <span>Có sẵn</span>
                                            <span class="badge room-available px-3">35</span>
                                        </div>
                                        <div class="d-flex justify-content-between align-items-center mb-2">
                                            <span>Đã đặt</span>
                                            <span class="badge room-occupied px-3">65</span>
                                        </div>
                                        <div class="d-flex justify-content-between align-items-center mb-2">
                                            <span>Bảo trì</span>
                                            <span class="badge room-maintenance px-3">12</span>
                                        </div>
                                        <div class="d-flex justify-content-between align-items-center">
                                            <span>Đặt trước</span>
                                            <span class="badge room-reserved px-3">8</span>
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
                                    <a href="#" class="btn btn-sm btn-outline-primary">Xem tất cả</a>
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
                                                <tr>
                                                    <td>#RNT001</td>
                                                    <td>Nguyễn Văn A</td>
                                                    <td>101</td>
                                                    <td>14:00</td>
                                                    <td><button class="btn btn-sm btn-outline-success">Check-in</button></td>
                                                </tr>
                                                <tr>
                                                    <td>#RNT002</td>
                                                    <td>Trần Thị B</td>
                                                    <td>205</td>
                                                    <td>15:30</td>
                                                    <td><button class="btn btn-sm btn-outline-success">Check-in</button></td>
                                                </tr>
                                                <tr>
                                                    <td>#RNT003</td>
                                                    <td>Lê Văn C</td>
                                                    <td>304</td>
                                                    <td>16:00</td>
                                                    <td><button class="btn btn-sm btn-outline-success">Check-in</button></td>
                                                </tr>
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
                                    <a href="#" class="btn btn-sm btn-outline-primary">Xem tất cả</a>
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
                                                <tr>
                                                    <td>#RNT004</td>
                                                    <td>Phạm Thị D</td>
                                                    <td>102</td>
                                                    <td>12:00</td>
                                                    <td><button class="btn btn-sm btn-outline-warning">Check-out</button></td>
                                                </tr>
                                                <tr>
                                                    <td>#RNT005</td>
                                                    <td>Hoàng Văn E</td>
                                                    <td>207</td>
                                                    <td>12:00</td>
                                                    <td><button class="btn btn-sm btn-outline-warning">Check-out</button></td>
                                                </tr>
                                                <tr>
                                                    <td>#RNT006</td>
                                                    <td>Vũ Thị F</td>
                                                    <td>306</td>
                                                    <td>13:00</td>
                                                    <td><button class="btn btn-sm btn-outline-warning">Check-out</button></td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Rooms Tab -->
                <div class="tab-pane pb-5 fade show active" id="rooms">
                    <div class="d-flex justify-content-between align-items-center mb-4">
                        <h3>Quản lý phòng</h3>
                        <button class="btn btn-primary">
                            <i class="bi bi-plus-circle me-1"></i> Thêm phòng mới
                        </button>
                    </div>
                    
                    <div class="card mb-4">
                        <div class="card-body">
                            <div class="row">
                                <div class="col-md-3 mb-3">
                                    <select class="form-select">
                                        <option selected>Tất cả loại phòng</option>
                                        <option>Phòng đơn</option>
                                        <option>Phòng đôi</option>
                                        <option>Phòng VIP</option>
                                        <option>Phòng gia đình</option>
                                    </select>
                                </div>
                                <div class="col-md-3 mb-3">
                                    <select class="form-select">
                                        <option selected>Tất cả trạng thái</option>
                                        <option>Có sẵn</option>
                                        <option>Đã đặt</option>
                                        <option>Bảo trì</option>
                                        <option>Đang dọn dẹp</option>
                                    </select>
                                </div>
                                <div class="col-md-3 mb-3">
                                    <select class="form-select">
                                        <option selected>Số phòng</option>
                                        <option>1</option>
                                        <option>2</option>
                                        <option>3</option>
                                        <option>4</option>
                                    </select>
                                </div>
                                <div class="col-md-3 mb-3">
                                    <div class="input-group">
                                        <input type="text" class="form-control" placeholder="Tìm phòng...">
                                        <button class="btn btn-outline-secondary">
                                            <i class="bi bi-search"></i>
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <div class="row">
                        <div class="col-md-3 mb-3">
                            <div class="card">
                                <div class="card-header room-available d-flex justify-content-between align-items-center py-2">
                                    <span class="fw-bold">101</span>
                                    <span class="status-badge bg-white text-success">Trống</span>
                                </div>
                                <div class="card-body">
                                    <div class="d-flex justify-content-between mb-2">
                                        <small class="text-muted">Loại phòng:</small>
                                        <span>Phòng đơn</span>
                                    </div>
                                    <div class="d-flex justify-content-between mb-2">
                                        <small class="text-muted">Giá:</small>
                                        <span>850.000 VND</span>
                                    </div>
                                    <div class="d-flex justify-content-between mb-3">
                                        <small class="text-muted">Tầng:</small>
                                        <span>1</span>
                                    </div>
                                    <div class="d-grid gap-2">
                                        <button class="btn btn-sm btn-primary">Đặt phòng</button>
                                        <button class="btn btn-sm btn-outline-secondary">Chi tiết</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-3 mb-3">
                            <div class="card">
                                <div class="card-header room-occupied d-flex justify-content-between align-items-center py-2">
                                    <span class="fw-bold">102</span>
                                    <span class="status-badge bg-white text-danger">Đã đặt</span>
                                </div>
                                <div class="card-body">
                                    <div class="d-flex justify-content-between mb-2">
                                        <small class="text-muted">Loại phòng:</small>
                                        <span>Phòng đôi</span>
                                    </div>
                                    <div class="d-flex justify-content-between mb-2">
                                        <small class="text-muted">Giá:</small>
                                        <span>1.200.000 VND</span>
                                    </div>
                                    <div class="d-flex justify-content-between mb-3">
                                        <small class="text-muted">Tầng:</small>
                                        <span>1</span>
                                    </div>
                                    <div class="d-grid gap-2">
                                        <button class="btn btn-sm btn-warning">Check-out</button>
                                        <button class="btn btn-sm btn-outline-secondary">Chi tiết</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-3 mb-3">
                            <div class="card">
                                <div class="card-header room-maintenance d-flex justify-content-between align-items-center py-2">
                                    <span class="fw-bold">103</span>
                                    <span class="status-badge bg-white text-warning">Bảo trì</span>
                                </div>
                                <div class="card-body">
                                    <div class="d-flex justify-content-between mb-2">
                                        <small class="text-muted">Loại phòng:</small>
                                        <span>Phòng đơn</span>
                                    </div>
                                    <div class="d-flex justify-content-between mb-2">
                                        <small class="text-muted">Giá:</small>
                                        <span>850.000 VND</span>
                                    </div>
                                    <div class="d-flex justify-content-between mb-3">
                                        <small class="text-muted">Tầng:</small>
                                        <span>1</span>
                                    </div>
                                    <div class="d-grid gap-2">
                                        <button class="btn btn-sm btn-success">Hoàn thành</button>
                                        <button class="btn btn-sm btn-outline-secondary">Chi tiết</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-3 mb-3">
                            <div class="card">
                                <div class="card-header room-reserved d-flex justify-content-between align-items-center py-2">
                                    <span class="fw-bold">104</span>
                                    <span class="status-badge bg-white text-primary">Đặt trước</span>
                                </div>
                                <div class="card-body">
                                    <div class="d-flex justify-content-between mb-2">
                                        <small class="text-muted">Loại phòng:</small>
                                        <span>Phòng VIP</span>
                                    </div>
                                    <div class="d-flex justify-content-between mb-2">
                                        <small class="text-muted">Giá:</small>
                                        <span>2.500.000 VND</span>
                                    </div>
                                    <div class="d-flex justify-content-between mb-3">
                                        <small class="text-muted">Tầng:</small>
                                        <span>1</span>
                                    </div>
                                    <div class="d-grid gap-2">
                                        <button class="btn btn-sm btn-success">Check-in</button>
                                        <button class="btn btn-sm btn-outline-secondary">Chi tiết</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- Các phòng khác -->
                    </div>
                </div>
                
                <!-- Other Tabs -->
                <div class="tab-pane pb-5 fade show active" id="reservations">
                    <h3 class="mb-4">Quản lý đặt phòng</h3>
                    <!-- Reservation Management Content -->
                </div>
                
                <div class="tab-pane pb-5 fade show active" id="customers">
                    <h3 class="mb-4">Quản lý khách hàng</h3>
                    <!-- Customers Management Content -->
                </div>
                
                <div class="tab-pane pb-5 fade show active" id="services">
                    <h3 class="mb-4">Quản lý dịch vụ</h3>
                    <!-- Services Management Content -->
                </div>
                
                <div class="tab-pane pb-5 fade show active" id="staff">
                    <h3 class="mb-4">Quản lý nhân viên</h3>
                    <!-- Staff Management Content -->
                </div>
                
                <div class="tab-pane pb-5 fade show active" id="invoices">
                    <h3 class="mb-4">Quản lý hóa đơn</h3>
                    <!-- Invoices Management Content -->
                </div>
                
                <div class="tab-pane pb-5 fade show active" id="maintenance">
                    <h3 class="mb-4">Quản lý bảo trì</h3>
                    <!-- Maintenance Management Content -->
                </div>
                
                <div class="tab-pane pb-5 fade show active" id="promotions">
                    <h3 class="mb-4">Quản lý khuyến mãi</h3>
                    <!-- Promotions Management Content -->
                </div>
                
                <div class="tab-pane pb-5 fade show active" id="reports">
                    <h3 class="mb-4">Báo cáo thống kê</h3>
                    <!-- Reports Content -->
                </div>
            </div>
        </div>
    </div>
</body>
</html>