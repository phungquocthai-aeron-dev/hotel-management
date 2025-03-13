<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Blue Heaven Hotel - Statistics</title>
    <link rel="icon" type="image/png" href="logo.png">

    <!-- Bootstrap CSS -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap Icons -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.11.0/font/bootstrap-icons.min.css" rel="stylesheet">
    <!-- Chart.js -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.9.1/chart.min.js"></script>
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
            margin-bottom: 20px;
        }
        
        .card:hover {
            transform: translateY(-5px);
        }
        
        .stat-card {
            border-left: 5px solid var(--primary);
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
        
        .chart-container {
            position: relative;
            height: 300px;
            width: 100%;
        }
        
        .nav-tabs .nav-link {
            color: var(--secondary);
        }
        
        .nav-tabs .nav-link.active {
            color: var(--primary);
            font-weight: bold;
            background-color: white;
        }
        
        .period-selector {
            display: flex;
            justify-content: center;
            margin-bottom: 20px;
        }
        
        .period-selector .btn-group {
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }
        
        .period-selector .btn {
            min-width: 80px;
        }
        
        .data-card {
            background-color: white;
            border-radius: 10px;
            padding: 20px;
            margin-bottom: 20px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.05);
        }
        
        .summary-value {
            font-size: 24px;
            font-weight: bold;
            color: var(--primary);
        }
        
        .summary-label {
            font-size: 14px;
            color: var(--secondary);
        }
        
        .stat-badge {
            display: inline-block;
            padding: 3px 8px;
            border-radius: 15px;
            font-size: 12px;
            font-weight: bold;
        }
        
        .trend-up {
            background-color: #dcfce7;
            color: #166534;
        }
        
        .trend-down {
            background-color: #fee2e2;
            color: #b91c1c;
        }
        
        .table th {
            background-color: var(--primary);
            color: white;
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
                        <a href="home" class="nav-link" data-bs-toggle="pill">
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
                        <a href="statistics" class="nav-link active" data-bs-toggle="pill">
                            <i class="bi bi-bar-chart"></i> Báo cáo
                        </a>
                    </li>
                </ul>
            </div>
        </nav>
        
        <!-- Content -->
        <div class="content p-4">
            <div class="container-fluid">
                <h2 class="mb-4 text-center">Báo cáo và Thống kê</h2>
                <div class="d-flex" style="justify-content: flex-end">
                	<c:if test="${not empty dataRevenues and not empty dataExpenses and not empty dataProfits}">
                    <form action="statistic/export" method="post">
                    	<% for (int i = 0; i < 12; i++) { %>
    					<input hidden value="${dataRevenues.get(i) }" name="revenues[]">
    					<input hidden value="${dataExpenses.get(i) }" name="expenses[]">
    					<input hidden value="${dataProfits.get(i) }" name="profits[]">
						<% } %>
                        <button class="btn btn-success">
                            Xuất Excel
                        </button>
                    </form>
                    </c:if>
                    <div class="input-group d-flex ms-2" style="width: 12%;">
                        <form action="statistics" method="get" class="d-flex">
                            <input name="year" type="number" class="form-control" min="2000" placeholder="Năm...">
                            <button class="btn btn-outline-secondary btn-primary text-white">
                                <i class="bi bi-search"></i>
                            </button>
                        </form>
                    </div>
                </div>
                
                <!-- Tab Content -->
                <div class="tab-content" id="reportTabsContent">
   	<canvas id="lineChart"></canvas>
    <p class="text-center mt-3">Biểu đồ đường thể hiện doanh thu, chi trả, lợi nhuận năm ${year }</p>
    <script>
        // Nhận dữ liệu từ Controller và chuyển thành mảng JS
        const dataRevenues = ${dataRevenues};
        const dataExpenses = ${dataExpenses};
        const dataProfits = ${dataProfits};

        // Xử lý dữ liệu thành mảng số JavaScript
        const revenues = dataRevenues.map(Number);
        const expenses = dataExpenses.map(Number);
        const profits = dataProfits.map(Number);

        const ctx = document.getElementById('lineChart').getContext('2d');
        const data = {
            labels: ['Tháng 1', 'Tháng 2', 'Tháng 3', 'Tháng 4', 'Tháng 5', 'Tháng 6', 'Tháng 7', 'Tháng 8', 'Tháng 9', 'Tháng 10', 'Tháng 11', 'Tháng 12'],
            datasets: [
                {
                    label: 'Doanh thu',
                    data: revenues,
                    borderColor: 'red',
                    fill: false
                },
                {
                    label: 'Chi trả',
                    data: expenses,
                    borderColor: 'blue',
                    fill: false
                },
                {
                    label: 'Lợi nhuận',
                    data: profits,
                    borderColor: 'green',
                    fill: false
                }
            ]
        };

        new Chart(ctx, {
            type: 'line',
            data: data,
            options: {
                responsive: true,
                scales: {
                    y: {
                        beginAtZero: true,
                        ticks: {
                            stepSize: 100000
                        }
                    }
                }
            }
        });
    </script>
                </div>
            </div>
        </div>
    </div>
</body>
</html>