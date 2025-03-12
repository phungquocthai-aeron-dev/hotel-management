<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c" %> <%@ taglib uri="http://www.springframework.org/tags/form"
prefix="form" %> <%@ page
import="com.quantridulieu.hotelManagement.entities.Maintenance" %> <%@ page
import="java.text.SimpleDateFormat" %> <%@ page import="java.util.Date" %>
<!DOCTYPE html>
<html lang="vi">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Chỉnh Sửa Bảo Trì - Blue Heaven</title>
    <link
      href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.11.0/font/bootstrap-icons.min.css"
      rel="stylesheet"
    />
    <style>
      body {
        background: linear-gradient(to right, #dbeafe, #bfdbfe);
        display: flex;
        align-items: center;
        justify-content: center;
        height: 100vh;
        font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
      }
      .edit-wrapper {
        width: 400px;
        padding: 20px;
        background: rgba(255, 255, 255, 0.9);
        border-radius: 12px;
        box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
      }
      h2 {
        text-align: center;
        color: #3b82f6;
        margin-bottom: 20px;
      }
      .form-group {
        margin-bottom: 15px;
        position: relative;
      }
      .form-group label {
        display: block;
        font-weight: bold;
        margin-bottom: 5px;
      }
      .form-group input {
        width: 100%;
        padding: 10px;
        border-radius: 8px;
        border: 1px solid #ccc;
        background: #f8fafc;
      }
      .form-group input[readonly] {
        background: #e5e7eb;
        cursor: not-allowed;
      }
      .btn-submit {
        width: 100%;
        padding: 10px;
        background: #3b82f6;
        color: white;
        border: none;
        border-radius: 8px;
        cursor: pointer;
        font-weight: bold;
        transition: 0.3s;
      }
      .btn-submit:hover {
        background: #2563eb;
      }
      .form-group input {
        width: 100%;
        padding: 10px 2px; /* Thêm padding ngang (14px) để không bị sát bên phải */
        border-radius: 8px;
        border: 1px solid #ccc;
        background: #f8fafc;
      }

      .form-container {
        display: flex;
        gap: 20px;
      }

      .form-column {
        flex: 1;
        display: flex;
        flex-direction: column;
        gap: 15px;
      }

      .form-group {
        display: flex;
        flex-direction: column;
      }

      .btn-submit {
        margin-top: 20px;
        padding: 10px 20px;
        background-color: #007bff;
        color: white;
        border: none;
        cursor: pointer;
      }

      .btn-submit:hover {
        background-color: #0056b3;
      }
    </style>
  </head>
  <body>
    <% Maintenance maintenance = (Maintenance)
    request.getAttribute("maintenance"); String rawDate1 =
    maintenance.getMtnDate().toString(); String rawDate2 =
    maintenance.getMtnEnd().toString(); SimpleDateFormat inputFormat = new
    SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S"); SimpleDateFormat outputFormat =
    new SimpleDateFormat("yyyy-MM-dd"); Date date1 =
    inputFormat.parse(rawDate1); Date date2 = inputFormat.parse(rawDate2);
    String formattedDate1 = outputFormat.format(date1); String formattedDate2 =
    outputFormat.format(date2); %>

    <div class="edit-wrapper">
      <h2>Chỉnh Sửa Bảo Trì</h2>
      <form
        id="maintenanceEditForm"
        action="../maintenance/update"
        method="POST"
      >
        <div class="form-container">
          <div class="form-column">
            <div class="form-group">
              <label for="mtn_id">Mã bảo trì</label>
              <input
                type="text"
                id="mtn_id"
                name="mtnId"
                value="${maintenance.mtnId}"
                readonly
              />
            </div>
            <div class="form-group">
              <label for="room_id">Phòng</label>
              <input
                type="text"
                id="room_id"
                name="room"
                value="${maintenance.room.getRoomId()}"
                required
              />
            </div>
            <div class="form-group">
              <label for="mtn_description">Mô tả</label>
              <input
                type="text"
                id="mtn_description"
                name="mtnDescription"
                value="${maintenance.mtnDescription}"
                required
              />
            </div>
            <div class="form-group">
              <label for="phone">Ngày bảo trì </label>
              <c:if test="${not empty errors['mtnDate']}">
                <div class="text-danger">${errors['mtnDate']}</div>
              </c:if>
              <input
                type="date"
                class="form-control"
                name="mtnDate"
                value="<%= formattedDate1 %>"
              />
            </div>
          </div>
          <div class="form-column">
            <div class="form-group">
              <label for="endDate">Ngày kết thúc bảo trì</label>
              <c:if test="${not empty errors['mtnEnd']}">
                <div class="text-danger">${errors['mtnEnd']}</div>
              </c:if>
              <input
                type="date"
                class="form-control"
                name="mtnEnd"
                value="<%= formattedDate2 %>"
              />
            </div>
            <div class="form-group">
              <label for="fee">Giá</label>
              <input
                type="text"
                id="fee"
                name="mtnFee"
                value="${maintenance.mtnFee}"
                required
              />
            </div>
            <div class="form-group">
              <label for="staff_employee">Nhân viên</label>
              <input
                type="text"
                id="staff_id"
                name="staff"
                value="${maintenance.staff.getStaffId()}"
                required
              />
            </div>
            <div class="form-group">
              <label for="status">Trạng thái</label>
              <input
                type="text"
                id="status"
                name="mtnStatus"
                value="${maintenance.mtnStatus}"
                required
              />
            </div>
          </div>
        </div>
        <button type="submit" class="btn-submit">Cập Nhật</button>
      </form>
    </div>
  </body>
</html>
