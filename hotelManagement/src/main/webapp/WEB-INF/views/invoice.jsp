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
					<li class="nav-item"><a href="invoice" class="nav-link active"> <i
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
		<div class="content  p-4">
			<div class="tab-content">
				<c:if test="${not empty message}">
					<div id="message-container" class="alert alert-success">
						<span>${message}</span>
					</div>
				</c:if>
				<h2 class="text-center mb-4">
					<i class="fas fa-file-invoice"></i> Quản lý Hóa đơn
				</h2>

				<!-- Thanh tìm kiếm -->
				<!-- Thanh tìm kiếm -->
				<form action="invoice/search" method="GET"
					class="row bg-white p-3 rounded-3 mb-4">
					<!-- Mã hóa đơn -->
					<div class="col-md-3 mb-3">
						<input type="text" class="form-control" placeholder="Mã hóa đơn"
							name="invoiceId">
					</div>

					<!-- Nhân viên -->
					<div class="col-md-3 mb-3">
						<input type="text" class="form-control" placeholder="Nhân viên"
							name="staffId">
					</div>

					<!-- Tìm kiếm theo khoảng Tổng Tiền -->
					<div class="col-md-3 mb-3">
						<select class="form-control" name="totalAmountRange">
							<option value="">Chọn khoảng tổng tiền</option>
							<option value="under500">Dưới 500.000</option>
							<option value="500to2000">Từ 500.000 đến 2.000.000</option>
							<option value="above2000">Trên 2.000.000</option>
						</select>
					</div>

					<!-- Ngày sử dụng dịch vụ -->
					<div class="col-md-3 mb-3">
						<input type="date" class="form-control" name="serviceDate">
					</div>

					<!-- Nút tìm kiếm -->
					<div class="col-md-12 d-flex justify-content-center  mb-3">
						<button type="submit"
							class="btn btn-outline-secondary btn-primary text-white mx-auto">
							<i class="bi bi-search"></i> Tìm kiếm
						</button>
					</div>
				</form>

				<div class="container row">
					<div class="col-6 d-flex justify-content-start mt-3 mb-3">
						<div class="text-center mt-3">
							<a class="btn btn-outline-success" href="invoice/add"> <i
								class="fas fa-plus"></i> Thêm Hóa Đơn
							</a>
						</div>
					</div>

					<div class="col-6  d-flex justify-content-end mt-3 mb-3">
						<!-- Nút chức năng -->
						<div class="text-center mt-3">
							<c:if test="${not empty invoices}">
								<form action="invoice/export" method="post"
									style="display: inline;">
									<c:forEach var="invoice" items="${invoices}">
										<input type="hidden" name="invoicesExport"
											value="${invoice.invoiceId}" />
									</c:forEach>
									<button type="submit" class="btn btn-success">
										<i class="fas fa-file-excel"></i> Xuất Excel
									</button>
								</form>
							</c:if>

							<!-- Nút Thêm Hóa Đơn -->


							<!-- Nút Xem Tất Cả (đổi thành màu primary) -->
							<a class="btn btn-primary" href="invoice"> <i
								class="fas fa-list"></i> Xem tất cả
							</a>
						</div>
					</div>
				</div>



				<!-- Bảng dữ liệu -->
				<div class="table-responsive" style="max-height: 500px; overflow-y: auto; border: 1px solid #ddd;">
					<table class="table table-bordered table-hover text-center">
						<thead class="text-center" style="position: sticky; top: 0; background: white; z-index: 1; text-align: center;">
							<tr>
								<th>STT</th>
								<th>Mã hóa đơn</th>
								<th>Ngày lập</th>
								<th>Tổng tiền</th>
								<th>Mã nhân viên</th>
								<th>Mã dịch vụ</th>

								<th>Thao tác</th>
							</tr>
						</thead>
						<tbody id="invoiceTable">
							<c:forEach var="invoice" items="${invoices }" varStatus="status">
								<tr>
									<td>${status.count}</td>
									<td>${invoice.invoiceId}</td>

									<td>${invoice.invoiceDate}</td>
									<td>${invoice.totalAmount}</td>
									<td>${invoice.staff.getStaffId()}</td>
									<td>${invoice.useService.getUsId()}</td>


									<td class="action-btns d-flex justify-content-center">

										<form action="invoice/delete" method="post"
											onsubmit="return confirm('Bạn có chắc chắn muốn xóa ${invoice.invoiceId} không?');">
											<input type="hidden" name="id" value="${invoice.invoiceId}">
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
		<!-- Bootstrap & JS -->
		<script
			src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

		<script type="text/javascript">
		document.addEventListener('DOMContentLoaded', function () {
		    const form = document.querySelector('form');
		    const inputs = document.querySelectorAll('input, select');

		    inputs.forEach(input => {
		        input.addEventListener('input', function () {
		            if (this.value.trim() !== '') {
		                inputs.forEach(other => {
		                    if (other !== this) {
		                        other.value = '';
		                    }
		                });
		            }
		        });

		        if (input.type === 'date') {
		            input.addEventListener('change', function () {
		                if (this.value !== '') {
		                    inputs.forEach(other => {
		                        if (other !== this) {
		                            other.value = '';
		                        }
		                    });
		                }
		            });
		        }
		    });

		    form.addEventListener('submit', function (e) {
		        let hasValue = false;

		        inputs.forEach(input => {
		            if (input.value.trim() !== '') {
		                hasValue = true;
		            }
		        });

		        if (!hasValue) {
		            e.preventDefault();
		            alert('Vui lòng nhập ít nhất một giá trị để tìm kiếm');
		        }
		    });
		});


    window.onload = function() {
        let messageContainer = document.getElementById("message-container");
        if (messageContainer) {
            setTimeout(function() {
                messageContainer.style.display = "none";
            }, 3000); // Ẩn sau 5 giây
        }
    };
		</script>
</body>

</html>