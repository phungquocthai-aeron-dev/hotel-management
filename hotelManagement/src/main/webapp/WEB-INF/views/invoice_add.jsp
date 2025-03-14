<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Thêm Hóa Đơn - Blue Heaven</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
<style>
body {
	background-color: #f8f9fa;
}

.card {
	border-radius: 10px;
	box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
}

.form-label {
	font-weight: bold;
}

.btn-custom {
	background-color: #1e3a8a;
	color: white;
}

.btn-custom:hover {
	background-color: #163372;
}
</style>
</head>
<body>
	<div class="container mt-5">
		<div class="row justify-content-center">
			<div class="col-md-8">
				<div class="card p-4">
					<h2 class="text-center mb-4">
						<i class="fas fa-file-invoice"></i> Hóa Đơn
					</h2>

					<form:form action="../invoice/save" method="post"
						modelAttribute="invoice">
						
						<div class="mb-3">
							<label class="form-label">Ngày lập</label>
							<form:input type="date" path="invoiceDate" class="form-control"
								required="true" />
						</div>
						<div class="mb-3">
							<label class="form-label">Mã nhân viên</label>
							<select name="staffId" class="form-control"
								required="true">
								<c:forEach var="staff" items="${staffs}">
									<form:option value="${staff.staffId}" label="${staff.staffName}" />
								</c:forEach>
							</select>
						</div>
						<div class="mb-3">
							<label class="form-label">Mã dịch vụ</label>
							<form:select path="useService.usId" class="form-control"
								required="true">
								<c:forEach var="service" items="${useServiceList}">
									<form:option value="${service.usId}"
										label="${service.usId}" />
								</c:forEach>
							</form:select>
						</div>
						<div class="text-center">
							<button type="submit" class="btn btn-custom">
								<i class="fas fa-save"></i> Lưu Hóa Đơn
							</button>
							<a href="../invoice" class="btn btn-secondary"><i
								class="fas fa-arrow-left"></i> Quay lại</a>
						</div>
					</form:form>

				</div>
			</div>
		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
