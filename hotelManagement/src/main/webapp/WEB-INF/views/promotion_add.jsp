<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Thêm Khuyến Mãi - Blue Heaven</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
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
						<i class="fas fa-tags"></i> Khuyến Mãi
					</h2>

					<form:form action="../promotion/save" method="post"
						modelAttribute="promotion">

						<div class="mb-3 d-none">
							<label class="form-label">Mã Khuyến Mãi</label>
							<c:if test="${not empty promotion.promotionId}">
								<form:input path="promotionId" class="form-control"
									readonly="true" />
							</c:if>
						</div>


						<div class="mb-3">
							<label class="form-label">Tên Khuyến Mãi</label>
							<form:input path="promotionName" class="form-control"
								required="true" />
						</div>
						<div class="mb-3">
							<label class="form-label">Mô Tả</label>
							<form:textarea path="promotionDescription" class="form-control"
								rows="3" />
						</div>
						<div class="mb-3">
							<label class="form-label">Giá Trị Giảm (%)</label>
							<form:input type="number" path="promotionValue"
								class="form-control" min="1" max="100" required="true" />
						</div>
						<div class="mb-3">
							<label class="form-label">Ngày Bắt Đầu</label>
							<form:input type="date" path="promotionStart"
								class="form-control" required="true" />
						</div>
						<div class="mb-3">
							<label class="form-label">Ngày Kết Thúc</label>
							<form:input type="date" path="promotionEnd" class="form-control"
								required="true" />
						</div>
						<div class="text-center">
							<button type="submit" class="btn btn-custom">
								<i class="fas fa-save"></i> Lưu Khuyến Mãi
							</button>
							<a href="../promotion" class="btn btn-secondary"> <i
								class="fas fa-arrow-left"></i> Quay lại
							</a>
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