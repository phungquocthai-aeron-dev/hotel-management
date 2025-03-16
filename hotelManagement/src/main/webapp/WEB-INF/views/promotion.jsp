<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Blue Heaven Hotel Management System</title>
<link rel="icon" type="image/png" href="logo.png">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
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

.content {
	background: white;
	padding: 30px;
	border-radius: 10px;
	box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
}

.table th {
	background-color: #007bff;
	color: white;
	text-align: center;
}

.table td, .table th {
	vertical-align: middle;
}

.search-bar input, .search-bar select {
	max-width: 200px;
}

.action-btns button {
	margin-right: 5px;
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
					<li class="nav-item"><a href="promotion" class="nav-link active">
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
				<c:if test="${not empty message}">
					<div id="message-container" class="alert alert-success">
						<span>${message}</span>
					</div>
				</c:if>
				<h2 class="text-center mb-4">
					<i class="fas fa-tag"></i> Quản lý Khuyến mãi
				</h2>

				<!-- Thanh tìm kiếm -->
				<form action="promotion/search" method="GET"
					class="row bg-white p-3 rounded-3 mb-4">
					<!-- Mã khuyến mãi -->
					<div class="col-md-3 mb-3">
						<input type="text" class="form-control"
							placeholder="Mã khuyến mãi" name="promotionId">
					</div>

					<!-- Tên khuyến mãi -->
					<div class="col-md-3 mb-3">
						<input type="text" class="form-control"
							placeholder="Tên khuyến mãi" name="promotionName">
					</div>

					<!-- Giá trị khuyến mãi -->
					<div class="col-md-3 mb-3">
						<input type="text" class="form-control"
							placeholder="Giá trị khuyến mãi" name="discountRange">
					</div>

					<!-- Ngày bắt đầu khuyến mãi -->
					<div class="col-md-3 mb-3">
						<input type="date" class="form-control" placeholder="Ngày bắt đầu"
							name="startDate">
					</div>

					<!-- Nút tìm kiếm -->
					<div class="col-md-12 d-flex justify-content-center mb-3">
						<button type="submit"
							class="btn btn-outline-secondary btn-primary text-white mx-auto">
							<i class="bi bi-search"></i> Tìm kiếm
						</button>
					</div>
				</form>


				<div class="container row">
					<div class="col-6 d-flex justify-content-start mt-3 mb-3">
						<div class="text-center mt-3">
							<a class="btn btn-outline-success" href="promotion/add"> <i
								class="fas fa-plus"></i> Thêm Khuyến mãi
							</a>
						</div>
					</div>

					<div class="col-6 d-flex justify-content-end mt-3 mb-3">
						<!-- Nút chức năng -->
						<div class="text-center mt-3">
							<c:if test="${not empty promotions}">
								<form action="promotion/export" method="post"
									style="display: inline;">
									<c:forEach var="promotion" items="${promotions}">
										<input type="hidden" name="promotionsExport"
											value="${promotion.promotionId}" />
									</c:forEach>
									<button type="submit" class="btn btn-success">
										<i class="fas fa-file-excel"></i> Xuất Excel
									</button>
								</form>
							</c:if>

							<!-- Nút Xem Tất Cả -->
							<a class="btn btn-primary" href="promotion"> <i
								class="fas fa-list"></i> Xem tất cả
							</a>
						</div>
					</div>
				</div>

				<!-- Bảng dữ liệu -->
				<div class="table-responsive">
					<table class="table table-bordered table-hover text-center">
						<thead>
							<tr>
								<th>STT</th>
								<th>Mã khuyến mãi</th>
								<th>Tên khuyến mãi</th>
								<th>Mô tả</th>
								<th>Giá trị</th>
								<th>Ngày bắt đầu</th>
								<th>Ngày kết thúc</th>
								<th>Thao tác</th>
							</tr>
						</thead>
						<tbody id="promotionTable">
							<c:forEach var="promotion" items="${promotions}"
								varStatus="status">
								<tr>
									<td>${status.count}</td>
									<td>${promotion.promotionId}</td>
									<td>${promotion.promotionName}</td>
									<td>${promotion.promotionDescription}</td>
									<td>${promotion.promotionValue}</td>
									<td>${promotion.promotionStart}</td>
									<td>${promotion.promotionEnd}</td>
									<td class="action-btns d-flex justify-content-center">

										<form action="promotion/add" method="post">
											<input type="hidden" name="promotionId"
												value="${promotion.promotionId}">
											<button class="btn btn-warning btn-sm">
												<i class="fas fa-edit"></i> sửa
											</button>
										</form>


										<form action="promotion/delete" method="post"
											onsubmit="return confirm('Bạn có chắc chắn muốn xóa ${promotion.promotionId} không?');">
											<input type="hidden" name="id"
												value="${promotion.promotionId}">
											<button class="btn btn-danger btn-sm">
												<i class="fas fa-trash"></i> Xóa
											</button>
										</form>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>

		<div class="modal fade" id="editPromotionModal" tabindex="-1"
			aria-labelledby="editPromotionModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="editPromotionModalLabel">Thêm/Chỉnh
							sửa Khuyến Mãi</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
				</div>
			</div>
		</div>
		<!-- Bootstrap & JS -->
		<script
			src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

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
	</div>
</body>

</html>