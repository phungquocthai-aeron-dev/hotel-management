<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Đăng Ký - Blue Heaven</title>
    <link
      href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.11.0/font/bootstrap-icons.min.css"
      rel="stylesheet"
    />
    <style>
      :root {
        --primary: #3b82f6;
        --secondary: #64748b;
        --background: linear-gradient(to right, #93c5fd, #bfdbfe);
        --glass-bg: rgba(255, 255, 255, 0.2);
        --glass-border: rgba(255, 255, 255, 0.3);
        --text-dark: #1e293b;
        --text-light: #475569;
      }

      * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
        font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
      }

      body {
        background: linear-gradient(to right, #dbeafe, #bfdbfe);
        display: flex;
        align-items: center;
        justify-content: center;
        height: 100vh;
        backdrop-filter: blur(8px);
      }

      .register-wrapper {
        display: flex;
        width: 800px;
        background: var(--glass-bg);
        backdrop-filter: blur(12px);
        border-radius: 16px;
        overflow: hidden;
        box-shadow: 0 8px 32px rgba(0, 0, 0, 0.2);
        border: 1px solid var(--glass-border);
      }

      /* Phần chứa logo + thương hiệu */
      .register-left {
        width: 40%;
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        text-align: center;
        padding: 20px;
        background: var(--glass-bg);
        backdrop-filter: blur(12px);
      }

      .logo {
        width: 200px;
        height: 200px;
        object-fit: contain;
        margin-bottom: 10px;
      }

      .brand-name {
        font-size: 32px;
        font-weight: bold;
        color: var(--primary);
        text-shadow: 2px 2px 8px rgba(59, 130, 246, 0.5);
      }

      .register-container {
        width: 60%;
        padding: 40px;
        text-align: center;
        color: var(--text-dark);
        background: var(--glass-bg);
        backdrop-filter: blur(12px);
      }

      .register-container h2 {
        margin-bottom: 15px;
        font-size: 22px;
        font-weight: bold;
      }

      .description {
        font-size: 14px;
        color: var(--text-light);
        margin-bottom: 20px;
      }

      .input-group {
        position: relative;
        margin-bottom: 15px;
      }

      .input-group i {
        position: absolute;
        top: 50%;
        left: 12px;
        transform: translateY(-50%);
        color: var(--secondary);
      }

      .input-group input {
        width: 100%;
        padding: 12px 12px 12px 40px;
        border-radius: 8px;
        border: 1px solid rgba(255, 255, 255, 0.5);
        background: rgba(255, 255, 255, 0.3);
        color: var(--text-dark);
        outline: none;
        transition: all 0.3s ease;
      }

      .input-group input:focus {
        border-color: var(--primary);
        background: rgba(255, 255, 255, 0.7);
      }

      .btn-register {
        width: 100%;
        padding: 12px;
        border: none;
        border-radius: 8px;
        background: var(--primary);
        color: white;
        font-size: 16px;
        font-weight: bold;
        cursor: pointer;
        transition: all 0.3s ease;
        box-shadow: 0 4px 10px rgba(59, 130, 246, 0.3);
      }

      .btn-register:hover {
        background: #2563eb;
        transform: translateY(-2px);
      }

      .login-link {
        margin-top: 15px;
        font-size: 14px;
        color: var(--text-light);
      }

      .login-link a {
        color: var(--primary);
        text-decoration: none;
      }

      .login-link a:hover {
        text-decoration: underline;
      }

      @media (max-width: 768px) {
        .register-wrapper {
          flex-direction: column;
          width: 90%;
        }
        .register-left {
          width: 100%;
          padding: 30px 0;
        }
        .register-container {
          width: 100%;
          padding: 20px;
        }
      }
    </style>
  </head>
  <body>
    <div class="register-wrapper">
      <!-- Phần bên trái: Logo + Thương hiệu -->
      <div class="register-left">
        <img class="logo" src="logo.png/" alt="Blue Heaven Logo" />
        <div class="brand-name">Blue Heaven</div>
      </div>

      <!-- Phần bên phải: Form đăng ký -->
      <div class="register-container">
        <h2>Đăng Ký</h2>
        <p class="description">
          Hãy tạo tài khoản mới để trải nghiệm Blue Heaven!
        </p>
        <form action="#" method="POST">
          <div class="input-group">
            <i class="bi bi-person"></i>
            <input type="text" placeholder="Họ và Tên" required />
          </div>
          <div class="input-group">
            <i class="bi bi-phone"></i>
            <input type="staff_phone" placeholder="Số điện thoại" required />
          </div>
          <div class="input-group">
            <i class="bi bi-lock"></i>
            <input type="password" placeholder="Mật khẩu" required />
          </div>
          <div class="input-group">
            <i class="bi bi-lock"></i>
            <input type="password" placeholder="Nhập lại mật khẩu" required />
          </div>
          <button type="submit" class="btn-register">Đăng Ký</button>
        </form>
        <p class="login-link">
          Đã có tài khoản? <a href="login">Đăng nhập ngay</a>
        </p>
      </div>
    </div>
  </body>
</html>
