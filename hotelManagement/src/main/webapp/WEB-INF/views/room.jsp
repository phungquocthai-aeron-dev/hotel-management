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

<!-- Bootstrap Bundle với Popper -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>

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

.room-list-wrapper {
	width: 100%;
	overflow-x: auto; /* Cho phép cuộn ngang */
	padding-bottom: 10px; /* Khoảng cách dưới để tránh che thanh cuộn */
}

.room-list-wrapper {
	max-height: 600px; /* Chiều cao tối đa cho 2 hàng */
	overflow-y: auto; /* Kích hoạt cuộn dọc khi vượt quá chiều cao */
	overflow-x: hidden; /* Ẩn cuộn ngang */
	padding-bottom: 10px; /* Khoảng cách dưới để tránh che thanh cuộn */
}

/* Tùy chỉnh thanh cuộn */
.room-list-wrapper::-webkit-scrollbar {
	width: 8px; /* Chiều rộng thanh cuộn */
}

.room-list-wrapper::-webkit-scrollbar-thumb {
	background-color: var(--primary); /* Màu thanh cuộn */
	border-radius: 10px;
}

.room-list-wrapper::-webkit-scrollbar-track {
	background: #f1f1f1; /* Màu nền thanh cuộn */
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
					<li class="nav-item"><a href="room" class="nav-link active"> <i
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
					<li class="nav-item"><a href="service" class="nav-link"> <i
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
		

			<div class="tab-content">
				<!-- Room Management Section -->
				<div class="tab-pane fade show active" id="room-management">
					<h3 class="mb-4">Quản Lý Phòng</h3>

					<!-- Filter and Search Section -->
					<div class="card mb-4 p-3">
						<div class="row g-3 align-items-center justify-content-between">
							<!-- Form tìm kiếm -->
							<div class="col-auto">
								<form id="search" action="room/search" method="get"
									class="d-flex flex-wrap align-items-center">
									<div class="search-wrapper me-3">
										<i class="bi bi-search"></i> <input type="text"
											class="form-control" placeholder="Nhập mã phòng..."
											name="roomId">
									</div>
									<div class="search-wrapper me-3">
										<i class="bi bi-search"></i> <input type="text"
											class="form-control" placeholder="Nhập số phòng..."
											name="roomNumber">
									</div>
									<div class="search-wrapper me-3">
										<select class="form-control" name="categoryName">
											<option value="">Chọn loại phòng</option>
											<c:forEach var="category" items="${categories }" varStatus="status">
												<option value="${category.categoryName }">${category.categoryName }</option>
											</c:forEach>
										</select>
									</div>
									<button type="submit" class="btn btn-primary">Tìm kiếm</button>
								</form>
							<a class="btn btn-primary mt-3" href=""> <i
								class="fas fa-list"></i> Xem tất cả
							</a>
							</div>

							<!-- Nút Xuất Excel căn phải -->
							<c:if test="${not empty rooms}">
									<form action="room/export" method="post">
									<c:forEach var="room" items="${rooms }" varStatus="status">
										<input type="hidden" name="roomIds" value="${room.roomId}" />
									</c:forEach>
									<button class="btn btn-success">Xuất Excel</button>
								</form>
						</div>
						</c:if>
						
					</div>
				</div>



				<!-- Add New Room Button -->
				<div class="mb-4">
					<button class="btn btn-primary" data-bs-toggle="modal"
						data-bs-target="#addRoomModal">Thêm phòng mới</button>
				</div>


				<!-- Room List -->

				<div class="room-list-wrapper">
					<div class="row">
						<c:forEach var="room" items="${rooms}">
							<div class="col-md-3 mb-4">
								<div
									class="card ${room.status == 'Trống' ? 'room-available' : 
                                  room.status == 'Đã đặt' ? 'room-occupied' : 
                                  room.status == 'Bảo trì' ? 'room-maintenance' : 
                                  'room-reserved'}">
									<div class="card-body">
<span class="fs-6 badge ${room.status == 'Available' ? 'text-success' : 
                    room.status == 'Occupied' ? 'text-danger' : 
                    room.status == 'Maintenance' ? 'text-warning' : 'bg-secondary'}">
  <i class="bi bi-tag"></i> ${room.status}
</span>
										<p class="mt-2">
											<strong>Mã phòng:</strong> ${room.roomId}
										</p>
										<p class="mt-2">
											<strong>Số phòng:</strong> ${room.roomNumber}
										</p>

										<p>
											<strong>Loại phòng:</strong> ${room.category != null ? room.category.categoryName : 'N/A'}
										</p>
										<p class="mt-2">
											<strong>Giá phòng:</strong> ${room.category.roomPrice}
										</p>

										<button class="btn btn-warning w-100 mb-2"
											onclick="window.location.href='room/details?id=${room.roomId}'">
											Sửa</button>
										<form action="room/delete" method="post"
											onsubmit="return confirm('Bạn có chắc chắn muốn xóa không?');">
											<input type="hidden" name="id" value="${room.roomId}">
											<button type="submit"
												class="btn btn-danger btn-sm btn-action w-100 mb-2">Xóa</button>
										</form>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>


				<!-- Add Room Modal -->
				<div class="modal fade" id="addRoomModal" tabindex="-1"
					aria-labelledby="addRoomModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="addRoomModalLabel">Thêm phòng
									mới</h5>
								<button type="button" class="btn-close" data-bs-dismiss="modal"
									aria-label="Close"></button>
							</div>
							<div class="modal-body p-3">
								<form action="room/save" method="post">


									<div class="mb-3">
										<label for="roomNumber" class="form-label">Số phòng</label> <input
											type="number" class="form-control" id="roomNumber"
											name="roomNumber" required>
									</div>

									<div class="mb-3">
										<label for="status" class="form-label">Trạng thái</label> <select
											class="form-select" id="status" name="status" required>
											<option value="Available">Available</option>
											<option value="Occupied">Occupied</option>
											<option value="Maintenance">Maintenance</option>
										</select>
									</div>

									<div class="mb-3">
										<label for="categoryId" class="form-label">Mã danh mục</label>
										<input type="text" class="form-control" id="categoryId"
											name="categoryId" required>
									</div>
									<c:if test="${not empty error}">
										<div class="alert alert-danger">${error}</div>
									</c:if>
									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-bs-dismiss="modal">Đóng</button>
										<button type="submit" class="btn btn-primary">Lưu
											phòng</button>
									</div>
								</form>

							</div>
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