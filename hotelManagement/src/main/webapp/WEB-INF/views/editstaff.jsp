<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c" %> <%@ taglib uri="http://www.springframework.org/tags/form"
prefix="form" %> <%@ page
import="com.quantridulieu.hotelManagement.entities.Staff" %> <%@ page
import="java.text.SimpleDateFormat" %> <%@ page import="java.util.Date" %>
<!DOCTYPE html>
<html lang="vi">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Chỉnh Sửa Nhân Viên - Blue Heaven</title>
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
    </style>
  </head>
  <body>
    <% 
    Staff staff = (Staff) request.getAttribute("staff");
    String rawDate = staff.getDateOfBirth().toString(); 
    SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S"); 
    SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd"); 
    Date date = inputFormat.parse(rawDate);
    String formattedDate = outputFormat.format(date);
    %>

    <div class="edit-wrapper">
      <h2>Chỉnh Sửa Nhân Viên</h2>
      <form id="staffEditForm" action="../staff/edit" method="POST">
        <div class="form-group">
          <label for="staff_id">Mã nhân viên</label>
          <input
            type="text"
            id="staff_id"
            name="staffId"
            value="${staff.staffId}"
            readonly
          />
        </div>
        <div class="form-group">
          <label for="staff_name">Tên nhân viên</label>
          <input
            type="text"
            id="staff_name"
            name="staffName"
            value="${staff.staffName}"
            required
          />
          <input type="text" name="password" value="${staff.password}" hidden />
          <input type="text" name="role" value="${staff.role}" hidden />
        </div>
        <div class="form-group">
          <label for="phone">Số điện thoại</label>
          <c:if test="${not empty errors['phone']}">
            <div class="text-danger">${errors['phone']}</div>
          </c:if>
          <input
            type="tel"
            id="staff_phone"
            name="staffPhone"
            value="${staff.staffPhone}"
            required
          />
        </div>
        <div class="form-group">
          <label for="phone">Ngày sinh</label>
          <c:if test="${not empty errors['dateOfBirth']}">
            <div class="text-danger">${errors['dateOfBirth']}</div>
          </c:if>
          <input
            type="date"
            class="form-control"
            name="dateOfBirth"
            value="<%= formattedDate %>"
          />
        </div>
        <button type="submit" class="btn-submit">Cập Nhật</button>
      </form>
    </div>
  </body>
</html>
