<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
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

.search-wrapper {
	position: relative;
}

.search-wrapper i {
	position: absolute;
	top: 50%;
	left: 10px;
	transform: translateY(-50%);
	color: #6c757d;
}

.search-wrapper input {
	padding-left: 35px;
}
/* Đảm bảo ô tìm kiếm nằm giữa và chiếm 1/3 chiều rộng */
.row.justify-content-center {
	display: flex;
	justify-content: center;
}

.col-md-4 {
	max-width: 33.333%; /* Chiếm 1/3 chiều rộng */
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
	max-height: 500px;
	/* Giới hạn chiều cao để có thanh trượt dọc nếu cần */
	overflow-y: auto; /* Thêm thanh trượt dọc nếu vượt quá chiều cao */
}

.custom-table th, .custom-table td {
	min-width: 120px; /* Đặt độ rộng tối thiểu cho các cột */
}

/* Đảm bảo các cột trong thead và tbody đồng bộ */
.custom-table thead tr, .custom-table tbody tr {
	display: table;
	width: 100%;
	table-layout: fixed;
}
</style>
</head>
<body>
	<div class="wrapper">
		<!-- Sidebar -->
		 <nav id="sidebar" class="sidebar overflow-auto" style="max-width: 250px; height: 100vh;">
			<div class="p-3">
				<div class="d-flex align-items-center mb-4 mt-2">
					<div class="bg-white p-2 rounded me-2">
						<img src="logo.png" alt="Blue Heaven Logo" class="hotel-logo">
					</div>
					<h4 class="mb-0">Blue Heaven</h4>
				</div>

				<div class="user-info d-flex align-items-center mb-2">
					<img style="width: 40px; height: 40px; border-radius: 50%;"
						src="https://static.vecteezy.com/system/resources/thumbnails/012/210/707/small_2x/worker-employee-businessman-avatar-profile-icon-vector.jpg"
						alt="User" class="rounded-circle me-2">
					<div>
						<h6 class="mb-0">${staff.staffName }</h6>
                        <small>${staff.role }</small>
					</div>
				</div>
				<div class="mb-4">
					<form action="logout" method="post">
				        <button type="submit" class="btn bg-white text-primary fw-bolder">
				            Đăng xuất
				        </button>
				    </form>
				</div>

				<ul class="nav flex-column">
					<li class="nav-item"><a href="home" class="nav-link">
							<i class="bi bi-speedometer2"></i> Tổng quan
					</a></li>
					<li class="nav-item"><a href="room" class="nav-link"> <i
							class="bi bi-house-door"></i> Quản lý phòng
					</a></li>
					<li class="nav-item"><a href="roomrental" class="nav-link"> <i
							class="bi bi-calendar-check"></i> Đặt phòng
					</a></li>
					<li class="nav-item">
                        <a href="us" class="nav-link">
                            <i class="bi bi-clipboard2-check"></i> Đăng ký dịch vụ
                        </a>
                    </li>
					<li class="nav-item"><a href="customer" class="nav-link">
							<i class="bi bi-people"></i> Khách hàng
					</a></li>
					<li class="nav-item"><a href="service" class="nav-link active"> <i
							class="bi bi-basket"></i> Dịch vụ
					</a></li>
					<li class="nav-item"><a href="staff" class="nav-link"> <i
							class="bi bi-person-badge"></i> Nhân viên
					</a></li>
					<li class="nav-item"><a href="invoice" class="nav-link"> <i
							class="bi bi-receipt"></i> Hóa đơn
					</a></li>
					<li class="nav-item"><a href="maintenance" class="nav-link">
							<i class="bi bi-tools"></i> Bảo trì
					</a></li>
					<li class="nav-item"><a href="promotion" class="nav-link">
							<i class="bi bi-tag"></i> Khuyến mãi
					</a></li>
					<li class="nav-item"><a href="statistics" class="nav-link">
							<i class="bi bi-bar-chart"></i> Báo cáo
					</a></li>
				</ul>
			</div>
		</nav>

		<!-- Content -->
		<div class="content p-4">

			<!-- Content -->
			<div class="tab-content">
				<!-- Service Management Section -->
				<div class="tab-pane fade show active" id="service-management">
					<h3 class="mb-4 text-center">Quản Lý Dịch Vụ</h3>

					<!-- Filter and Search Section (Moved to Top) -->
					<div class="card mb-4 p-3">
						<div class="row g-3 justify-content-center">
							<div class="col-12">
								<!-- Chiếm toàn bộ chiều ngang -->
								<div class="d-flex align-items-center">
										<form id="search" action="service/search" method="get" class="d-flex w-100">
											<input type="text" class="form-control me-2"
												placeholder="Mã dịch vụ..." name="serviceId"> <input
												type="text" class="form-control me-2"
												placeholder="Tên dịch vụ..." name="serviceName"> <input
												type="number" step="0.01" class="form-control me-2"
												placeholder="Giá dịch vụ..." name="servicePrice">
											<button type="submit" class="btn btn-primary">Tìm
												kiếm</button>
										</form>
									<a class="btn btn-primary ms-2" href=""> <i
								class="fas fa-list"></i> Xem tất cả
							</a>
									<c:if test="${not empty services}">
										<form action="service/export" method="post">
											<c:forEach var="service" items="${services }"
												varStatus="status">
												<input type="hidden" name="servicesExport"
													value="${service.serviceId}" />
											</c:forEach>
											<button class="btn btn-success ms-2">Xuất Excel</button>
										</form>
									</c:if>
								</div>
							</div>
						</div>
					</div>
					<!-- Main Content with Two Columns -->
					<div class="row">
						<!-- Left Column: Service List -->
						<div class="col-md-6">
							<div class="service-list-wrapper">
								<table class="table table-striped table-hover custom-table">
									<thead>
										<tr>
											<th>Mã dịch vụ</th>
											<th>Tên dịch vụ</th>
											<th>Giá</th>

											<th>Hành động</th>
										</tr>
									</thead>
									<tbody id="serviceList">
										<c:forEach var="service" items="${services}">
											<tr>
												<td>${service.serviceId}</td>
												<td>${service.serviceName}</td>
												<td>${service.servicePrice}</td>

												<td>
													<button class="btn btn-warning btn-sm btn-action"
														onclick="window.location.href='service/details?id=${service.serviceId}'">
														Sửa</button>
													<form action="service/delete" method="post"
														onsubmit="return confirm('Bạn có chắc chắn muốn xóa không?');">
														<input type="hidden" name="id"
															value="${service.serviceId}">
														<button type="submit"
															class="btn btn-danger btn-sm btn-action">Xóa</button>
													</form>

												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
						<div class="col-md-6">
							<div class="card p-3">
								<h4 class="mb-4">Thêm Dịch Vụ</h4>
								<form action="service/save" method="post">
									<label for="serviceId">Mã Dịch Vụ:</label> <input type="text"
										id="serviceId" name="serviceId" required><br> <br>
									<label for="serviceName">Tên Dịch Vụ:</label> <input
										type="text" id="serviceName" name="serviceName" required><br>
									<br> <label for="servicePrice">Giá:</label> <input
										type="number" id="servicePrice" name="servicePrice"
										step="0.01" required><br> <br>

									<button class="btn btn-primary text-white" type="submit">Lưu Dịch Vụ</button>
								</form>


							</div>
							<c:if test="${not empty error}">
								<div class="alert alert-danger">${error}</div>
							</c:if>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>


    <script type="text/javascript">
                                document.addEventListener('DOMContentLoaded', function() {
                                    // Chọn tất cả các phần tử có thể nhập liệu (input, textarea, select)
const inputElements = document.querySelectorAll('#search input[type="text"], #search textarea, #search select');
                                    
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
                                    document.querySelector('#search').addEventListener('submit', function(e) {
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