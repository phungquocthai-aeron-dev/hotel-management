DELIMITER $$
CREATE PROCEDURE SearchRoomRental(
    IN p_rent_id VARCHAR(255),
    IN p_rental_date DATE,
    IN p_check_in_date DATE,
    IN p_check_out_date DATE,
    IN p_rental_status VARCHAR(255),
    IN p_room_id VARCHAR(255),
    IN p_customer_id VARCHAR(255)
)
BEGIN
    SELECT *
    FROM Room_Rental
    WHERE 
        (p_rent_id IS NULL OR rent_id = p_rent_id)
        AND (p_rental_date IS NULL OR rental_date = p_rental_date)
        AND (p_check_in_date IS NULL OR check_in_date = p_check_in_date)
        AND (p_check_out_date IS NULL OR check_out_date = p_check_out_date)
        AND (p_rental_status IS NULL OR rental_status = p_rental_status)
        AND (p_room_id IS NULL OR room_id = p_room_id)
        AND (p_customer_id IS NULL OR customer_id = p_customer_id);
END $$
DELIMITER ;

CALL SearchRoomRental(NULL, NULL, NULL,NULL,NULL,NULL,'RM00009');


DELIMITER $$
CREATE PROCEDURE SearchCustomer(
    IN p_customer_id VARCHAR(255),
    IN p_customer_name VARCHAR(255),
    IN p_customer_phone VARCHAR(255),
    IN p_address VARCHAR(255)
)
BEGIN
    SELECT *
    FROM Customer
    WHERE 
        (p_customer_id IS NULL OR customer_id = p_customer_id)
        AND (p_customer_name IS NULL OR customer_name LIKE CONCAT('%', p_customer_name, '%'))
        AND (p_customer_phone IS NULL OR customer_phone = p_customer_phone)
        AND (p_address IS NULL OR address LIKE CONCAT('%', p_address, '%'));
END $$
DELIMITER ;

CALL SearchCustomer('CS00001',NULL, NULL, NULL);



DELIMITER $$

CREATE PROCEDURE SearchService(
    IN p_service_id VARCHAR(10),
    IN p_service_name VARCHAR(255),
    IN p_service_price INT
)
BEGIN
    SELECT * 
    FROM Service
    WHERE 
        (p_service_id IS NULL OR service_id = p_service_id)
        AND (p_service_name IS NULL OR service_name LIKE CONCAT('%', p_service_name, '%'))
        AND (p_service_price IS NULL OR service_price = p_service_price);
END $$

DELIMITER ;

CALL SearchService('S00006', NULL, NULL);


DELIMITER $$

CREATE PROCEDURE SearchRoom(
    IN p_room_id VARCHAR(10),
    IN p_room_number INT,
    IN p_status VARCHAR(50),
    IN p_category_id VARCHAR(10),
    IN p_category_name VARCHAR(255)  -- Thêm tham số tìm theo tên danh mục
)
BEGIN
    SELECT r.*, c.category_name
    FROM Room r
    JOIN Category c ON r.category_id = c.category_id
    WHERE 
        (p_room_id IS NULL OR r.room_id = p_room_id)
        AND (p_room_number IS NULL OR r.room_number = p_room_number)
        AND (p_status IS NULL OR r.status LIKE CONCAT('%', p_status, '%'))
        AND (p_category_id IS NULL OR r.category_id = p_category_id)
        AND (p_category_name IS NULL OR c.category_name LIKE CONCAT('%', p_category_name, '%'));  -- Tìm theo tên danh mục
END $$

DELIMITER ;
CALL SearchRoom(NULL, NULL, NULL, NULL, 'Luxury');  -- Tìm phòng thuộc danh mục "Luxury"
CALL SearchRoom(NULL, NULL, 'Available', NULL, 'Standard');  -- Phòng "Available" thuộc danh mục "Standard"
CALL SearchRoom('R001', NULL, NULL, NULL, NULL);  -- Tìm phòng theo ID


DELIMITER $$

CREATE PROCEDURE SearchBooking(
    IN p_rent_id VARCHAR(10),      -- Mã đặt phòng
    IN p_room_id VARCHAR(10),      -- Mã số phòng
    IN p_customer_name VARCHAR(255) -- Tên khách hàng
)
BEGIN
    SELECT rr.rent_id, r.room_id, r.customer_name 
    FROM RoomRental rr
    JOIN Room r ON rr.room_id = r.room_id  -- Kết nối bảng Room để lấy tên khách hàng
    WHERE 
        (p_rent_id IS NULL OR rr.rent_id = p_rent_id)
        AND (p_room_id IS NULL OR r.room_id LIKE CONCAT('%', p_room_id, '%'))
        AND (p_customer_name IS NULL OR r.customer_name LIKE CONCAT('%', p_customer_name, '%'));
END $$

DELIMITER ;

-- 7. Function tính tổng doanh thu từ một phòng cụ thể
DELIMITER //
CREATE FUNCTION GetRoomRevenue(roomIdParam VARCHAR(50), startDate DATE, endDate DATE) 
RETURNS DECIMAL(10,2)
DETERMINISTIC
BEGIN
    DECLARE total_revenue DECIMAL(10,2);
    
    SELECT SUM(i.total_amount) INTO total_revenue
    FROM Invoice i
    JOIN Room_Rental rr ON i.rent_id = rr.rent_id
    WHERE rr.room_id = roomIdParam
      AND i.invoice_date BETWEEN startDate AND endDate;
    
    IF total_revenue IS NULL THEN
        RETURN 0;
    ELSE
        RETURN total_revenue;
    END IF;
END //
DELIMITER ;

-- 8. Function tính tổng doanh thu từ một khách hàng
DELIMITER //
CREATE FUNCTION GetCustomerRevenue(customerIdParam VARCHAR(50)) 
RETURNS DECIMAL(10,2)
DETERMINISTIC
BEGIN
    DECLARE total_revenue DECIMAL(10,2);
    
    SELECT SUM(i.total_amount) INTO total_revenue
    FROM Invoice i
    JOIN Room_Rental rr ON i.rent_id = rr.rent_id
    WHERE rr.customer_id = customerIdParam;
    
    IF total_revenue IS NULL THEN
        RETURN 0;
    ELSE
        RETURN total_revenue;
    END IF;
END //
DELIMITER ;



DELIMITER //
CREATE FUNCTION GetMonthlyRevenue(yearParam INT, monthParam INT) 
RETURNS DECIMAL(10,2)
DETERMINISTIC
BEGIN
    DECLARE total_revenue DECIMAL(10,2);
    
    SELECT SUM(i.total_amount) INTO total_revenue
    FROM Invoice i
    WHERE YEAR(i.invoice_date) = yearParam 
      AND MONTH(i.invoice_date) = monthParam;
    
    IF total_revenue IS NULL THEN
        RETURN 0;
    ELSE
        RETURN total_revenue;
    END IF;
END //
DELIMITER ;

SELECT GetMonthlyRevenue(2025, 3) AS DoanhThuThang3;
SELECT GetMonthlyExpenses(2025, 1) AS DoanhThuThang3;

DELIMITER //
CREATE FUNCTION GetMonthlyExpenses(yearParam INT, monthParam INT) 
RETURNS DECIMAL(10,2)
DETERMINISTIC
BEGIN
    DECLARE total_expenses DECIMAL(10,2);
    
    SELECT SUM(m.mtn_fee) INTO total_expenses
    FROM Maintenance m
    WHERE YEAR(m.mtn_date) = yearParam 
      AND MONTH(m.mtn_date) = monthParam;
    
    IF total_expenses IS NULL THEN
        RETURN 0;
    ELSE
        RETURN total_expenses;
    END IF;
END //
DELIMITER ;


SELECT  GetMonthlyExpenses(2025, 1) as test;



DELIMITER //
CREATE TRIGGER check_maintenance_dates
BEFORE INSERT ON maintenance
FOR EACH ROW
BEGIN
  IF NEW.mtn_end <= NEW.mtn_date THEN
    SIGNAL SQLSTATE '45000'
    SET MESSAGE_TEXT = 'Ngày kết thúc phải sau ngày bảo trì';
  END IF;
END;
//
DELIMITER ;

DELIMITER //
CREATE TRIGGER check_maintenance_dates_update
BEFORE UPDATE ON maintenance
FOR EACH ROW
BEGIN
  IF NEW.mtn_end <= NEW.mtn_date THEN
    SIGNAL SQLSTATE '45000'
    SET MESSAGE_TEXT = 'Ngày kết thúc phải sau ngày bảo trì';
  END IF;
END;
//
DELIMITER ;