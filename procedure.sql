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

CREATE FUNCTION getCustomerIdByInvoice(invoiceIdParam VARCHAR(255)) 
RETURNS VARCHAR(255)
DETERMINISTIC
BEGIN
    DECLARE customerId VARCHAR(255);

    SELECT rr.customer_id 
    INTO customerId
    FROM Invoice i
    JOIN Use_Service us ON i.us_id = us.us_id
    JOIN Room_Rental rr ON us.rent_id = rr.rent_id
    WHERE i.invoice_id = invoiceIdParam
    LIMIT 1;

    RETURN customerId;
END$$

DELIMITER ;

SELECT getCustomerIdByInvoice("INV00001");








-- 1. Function lấy doanh thu theo ngày
DELIMITER //
CREATE FUNCTION GetDailyRevenue(dateParam DATE) 
RETURNS DECIMAL(10,2)
DETERMINISTIC
BEGIN
    DECLARE total_revenue DECIMAL(10,2);
    
    SELECT SUM(i.total_amount) INTO total_revenue
    FROM Invoice i
    WHERE DATE(i.invoice_date) = dateParam;
    
    IF total_revenue IS NULL THEN
        RETURN 0;
    ELSE
        RETURN total_revenue;
    END IF;
END //
DELIMITER ;

-- 2. Function lấy doanh thu theo tháng
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

-- 3. Function lấy doanh thu theo quý
DELIMITER //
CREATE FUNCTION GetQuarterlyRevenue(yearParam INT, quarterParam INT) 
RETURNS DECIMAL(10,2)
DETERMINISTIC
BEGIN
    DECLARE total_revenue DECIMAL(10,2);
    
    SELECT SUM(i.total_amount) INTO total_revenue
    FROM Invoice i
    WHERE YEAR(i.invoice_date) = yearParam 
      AND QUARTER(i.invoice_date) = quarterParam;
    
    IF total_revenue IS NULL THEN
        RETURN 0;
    ELSE
        RETURN total_revenue;
    END IF;
END //
DELIMITER ;

-- 4. Function lấy doanh thu theo năm
DELIMITER //
CREATE FUNCTION GetYearlyRevenue(yearParam INT) 
RETURNS DECIMAL(10,2)
DETERMINISTIC
BEGIN
    DECLARE total_revenue DECIMAL(10,2);
    
    SELECT SUM(i.total_amount) INTO total_revenue
    FROM Invoice i
    WHERE YEAR(i.invoice_date) = yearParam;
    
    IF total_revenue IS NULL THEN
        RETURN 0;
    ELSE
        RETURN total_revenue;
    END IF;
END //
DELIMITER ;

-- 5. Function lấy số phòng đang được sử dụng
DELIMITER //
CREATE FUNCTION GetOccupiedRoomsCount() 
RETURNS INT
DETERMINISTIC
BEGIN
    DECLARE room_count INT;
    
    SELECT COUNT(*) INTO room_count
    FROM Room r
    WHERE r.status = 'Occupied';
    
    RETURN room_count;
END //
DELIMITER ;

-- 6. Function kiểm tra phòng có khả dụng cho đặt phòng
DELIMITER //
CREATE FUNCTION IsRoomAvailable(roomIdParam VARCHAR(50), checkInDate DATE, checkOutDate DATE) 
RETURNS BOOLEAN
DETERMINISTIC
BEGIN
    DECLARE booking_count INT;
    
    SELECT COUNT(*) INTO booking_count
    FROM Room_Rental rr
    WHERE rr.room_id = roomIdParam
      AND (
          (rr.rental_date <= checkInDate AND rr.check_out_date > checkInDate) OR
          (rr.rental_date < checkOutDate AND rr.check_out_date >= checkOutDate) OR
          (rr.rental_date >= checkInDate AND rr.check_out_date <= checkOutDate)
      );
    
    IF booking_count > 0 THEN
        RETURN FALSE;
    ELSE
        RETURN TRUE;
    END IF;
END //
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








