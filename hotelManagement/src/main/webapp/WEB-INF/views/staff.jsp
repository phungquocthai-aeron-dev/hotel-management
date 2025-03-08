<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Blue Heaven Hotel Management System</title>
    <link rel="icon" type="image/png" href="logo.png" />

    <!-- Bootstrap CSS -->
    <link
      href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css"
      rel="stylesheet"
    />
    <!-- Bootstrap Icons -->
    <link
      href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.11.0/font/bootstrap-icons.min.css"
      rel="stylesheet"
    />
    <style>
      :root {
        --primary: #1e3a8a;
        --secondary: #475569;
        --accent: #f59e0b;
        --light: #f8fafc;
        --dark: #0f172a;
      }

      body {
        font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
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

      .nav-link:hover,
      .nav-link.active {
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
      <nav id="sidebar" class="sidebar" style="max-width: 250px">
        <div class="p-3">
          <div class="d-flex align-items-center mb-4 mt-2">
            <div class="bg-white p-2 rounded me-2">
              <img src="logo.png" alt="Blue Heaven Logo" class="hotel-logo" />
            </div>
            <h4 class="mb-0">Blue Heaven</h4>
          </div>

          <div class="user-info d-flex align-items-center mb-2">
            <img
              style="width: 40px; height: 40px; border-radius: 50%"
              src="https://static.vecteezy.com/system/resources/thumbnails/012/210/707/small_2x/worker-employee-businessman-avatar-profile-icon-vector.jpg"
              alt="User"
              class="rounded-circle me-2"
            />
            <div>
              <h6 class="mb-0">Nguyễn Văn A</h6>
              <small>Quản lý</small>
            </div>
          </div>
          <div class="mb-4">
            <a href="logout" class="btn bg-white text-primary fw-bolder"
              >Đăng xuất</a
            >
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
              <a href="#rental" class="nav-link" data-bs-toggle="pill">
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
              <a href="staff" class="nav-link active" data-bs-toggle="pill">
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
                <input
                  type="text"
                  class="form-control"
                  placeholder="Tìm kiếm..."
                />
              </div>
              <div class="dropdown">
                <div class="profile-menu" data-bs-toggle="dropdown">
                  <i class="bi bi-bell position-relative">
                    <span
                      class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger"
                    >
                      3
                    </span>
                  </i>
                </div>
                <ul class="dropdown-menu dropdown-menu-end alert-section">
                  <li><h6 class="dropdown-header">Thông báo</h6></li>
                  <li>
                    <a class="dropdown-item" href="#"
                      >Khách mới đặt phòng 101</a
                    >
                  </li>
                  <li>
                    <a class="dropdown-item" href="#"
                      >Bảo trì phòng 205 hoàn tất</a
                    >
                  </li>
                  <li>
                    <a class="dropdown-item" href="#"
                      >Nhắc nhở: check-out phòng 304</a
                    >
                  </li>
                </ul>
              </div>
            </div>
          </div>
        </header>

        <div class="tab-content">
          <div class="container mt-4">
            <h2 class="mb-3">Quản lý nhân viên</h2>

            <!-- Nút Duyệt thành viên -->
            <button
              class="btn btn-primary mb-3"
              data-bs-toggle="modal"
              data-bs-target="#approveMemberModal"
            >
              <i class="bi bi-check-circle"></i> Duyệt thành viên
            </button>

            <!-- Thống kê nhân viên -->
            <div class="row mb-4">
              <div class="col-md-3">
                <div class="card stat-card">
                  <div class="card-body">
                    <h5 class="card-title">Tổng nhân viên</h5>
                    <p class="card-text fs-4 fw-bold">${totalStaff}</p>
                  </div>
                </div>
              </div>
            </div>

            <!-- Bộ lọc tìm kiếm -->
            <div class="row mb-3">
              <div class="col-md-4">
                <input
                  type="text"
                  class="form-control"
                  placeholder="Tìm kiếm theo tên..."
                />
              </div>
              <div class="col-md-3">
                <select class="form-select">
                  <option selected>Chọn trạng thái</option>
                  <option>Đang làm việc</option>
                  <option>Nghỉ phép</option>
                  <option>Nghỉ việc</option>
                </select>
              </div>
              <div class="col-md-2">
                <button class="btn btn-primary w-100">Tìm kiếm</button>
              </div>
              <div class="col-md-2">
                <button id="exportExcel" class="btn btn-success mb-3">
                  Xuất Excel
                </button>
              </div>
            </div>

            <!-- Danh sách nhân viên -->
            <div class="table-responsive">
              <table class="table table-bordered text-center">
                <thead>
                  <tr>
                    <th>#</th>
                    <th>Mã nhân viên</th>
                    <th>Tên nhân viên</th>
                    <th>Số điện thoại</th>
                    <th>Hành động</th>
                  </tr>
                </thead>
                <tbody>
                  <c:forEach
                    var="staff"
                    items="${staffList}"
                    varStatus="status"
                  >
                    <tr>
                      <td>${status.index + 1}</td>
                      <td>${staff.staffId}</td>
                      <td>${staff.staffName}</td>
                      <td>${staff.staffPhone}</td>
                      <td>
                        <button class="btn btn-warning btn-sm">
                          <i class="bi bi-pencil"></i>
                        </button>
                        <button class="btn btn-danger btn-sm">
                          <i class="bi bi-trash"></i>
                        </button>
                      </td>
                    </tr>
                  </c:forEach>
                </tbody>
              </table>
            </div>
          </div>

          <!-- Modal Duyệt thành viên -->
          <div class="modal fade" id="approveMemberModal" tabindex="-1">
            <div class="modal-dialog">
              <div class="modal-content">
                <div class="modal-header">
                  <h5 class="modal-title">Duyệt thành viên</h5>
                  <button
                    type="button"
                    class="btn-close"
                    data-bs-dismiss="modal"
                  ></button>
                </div>
                <div class="modal-body">
                  <p>Bạn có chắc chắn muốn duyệt thành viên này không?</p>
                  <button class="btn btn-success w-100">Duyệt</button>
                </div>
              </div>
            </div>
          </div>

          <!-- Bootstrap JS -->
          <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
        </div>
      </div>
    </div>
  </body>
</html>
