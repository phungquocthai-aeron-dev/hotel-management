DELIMITER $$

CREATE PROCEDURE SearchInvoice(
    IN p_invoice_id VARCHAR(255),
    IN p_staff_id VARCHAR(255),
    IN p_totalAmountRange VARCHAR(50),
    IN p_serviceDate DATE
)
BEGIN
    SELECT *
    FROM Invoice
    WHERE 
        (p_invoice_id IS NULL OR invoice_id = p_invoice_id)
        AND (p_staff_id IS NULL OR staff_id = p_staff_id)
        AND (p_serviceDate IS NULL OR DATE(invoice_date) = p_serviceDate)
        AND (
            p_totalAmountRange IS NULL 
            OR (p_totalAmountRange = 'under500' AND total_amount < 500000)
            OR (p_totalAmountRange = '500to2000' AND total_amount BETWEEN 500000 AND 2000000)
            OR (p_totalAmountRange = 'above2000' AND total_amount > 2000000)
        );
END $$

DELIMITER ;


-- Thủ tục thêm mới hóa đơn
CREATE PROCEDURE InsertInvoice(
    IN p_invoice_id VARCHAR(255),
    IN p_staff_id VARCHAR(255),
    IN p_customer_id VARCHAR(255),
    IN p_invoice_date DATE,
    IN p_total_amount DECIMAL(10,2)
)
BEGIN
    INSERT INTO Invoice (invoice_id, staff_id, customer_id, invoice_date, total_amount)
    VALUES (p_invoice_id, p_staff_id, p_customer_id, p_invoice_date, p_total_amount);
END $$

-- Thủ tục xóa hóa đơn
CREATE PROCEDURE DeleteInvoice(
    IN p_invoice_id VARCHAR(255)
)
BEGIN
    DELETE FROM Invoice WHERE invoice_id = p_invoice_id;
END $$

-- Thủ tục xuất danh sách hóa đơn theo danh sách ID
CREATE PROCEDURE ExportInvoicesByIds(
    IN p_invoice_ids TEXT
)
BEGIN
    SET @query = CONCAT('SELECT * FROM Invoice WHERE FIND_IN_SET(invoice_id, "', p_invoice_ids, '")');
    PREPARE stmt FROM @query;
    EXECUTE stmt;
    DEALLOCATE PREPARE stmt;
END $$

DELIMITER ;

--PROMOTION
DELIMITER $$

CREATE PROCEDURE SearchPromotion(
    IN p_promotion_id VARCHAR(50),
    IN p_promotion_name VARCHAR(255),
    IN p_valueRange VARCHAR(50),
    IN p_startDate DATE
)
BEGIN
    SELECT *
    FROM promotion
    WHERE 
        (p_promotion_id IS NULL OR promotion_id = p_promotion_id)
        AND (p_promotion_name IS NULL OR promotion_name LIKE CONCAT('%', p_promotion_name, '%'))
        AND (p_startDate IS NULL OR DATE(promotion_start) = p_startDate)
        AND (p_valueRange IS NULL OR (promotion_value = p_valueRange)
            );
END $$

DELIMITER ;


--
DELIMITER $$

CREATE PROCEDURE InsertPromotion(
    IN p_promotion_id VARCHAR(50),
    IN p_promotion_name VARCHAR(255),
    IN p_promotion_value FLOAT,
    IN p_promotion_description TEXT,
    IN p_promotion_start DATE,
    IN p_promotion_end DATE
)
BEGIN
    INSERT INTO promotion (promotion_id, promotion_name, promotion_value, promotion_description, promotion_start, promotion_end)
    VALUES (p_promotion_id, p_promotion_name, p_promotion_value, p_promotion_description, p_promotion_start, p_promotion_end);
END $$

DELIMITER ;

--DELIMITER $$

CREATE PROCEDURE DeletePromotion(
    IN p_promotion_id VARCHAR(50)
)
BEGIN
    DELETE FROM promotion WHERE promotion_id = p_promotion_id;
END $$

DELIMITER ;


--
DELIMITER $$

CREATE PROCEDURE ExportPromotionsByIds(
    IN p_promotion_ids TEXT
)
BEGIN
    SET @query = CONCAT('SELECT * FROM promotion WHERE FIND_IN_SET(promotion_id, "', p_promotion_ids, '")');
    PREPARE stmt FROM @query;
    EXECUTE stmt;
    DEALLOCATE PREPARE stmt;
END $$

DELIMITER ;

DELIMITER //

CREATE TRIGGER trg_update_total_amount
BEFORE INSERT ON invoice
FOR EACH ROW
BEGIN
    DECLARE v_quantity INT;
    DECLARE v_service_price FLOAT;
    DECLARE v_room_price FLOAT;
    DECLARE v_promotion_value FLOAT;

    -- Lấy thông tin từ bảng use_service
    SELECT us.quantity, s.service_price, c.room_price, COALESCE(p.promotion_value, 0)
    INTO v_quantity, v_service_price, v_room_price, v_promotion_value
    FROM use_service us
    JOIN service s ON us.service_id = s.service_id
    JOIN room_rental rr ON us.rent_id = rr.rent_id
    JOIN room r ON rr.room_id = r.room_id
    JOIN category c ON r.category_id = c.category_id
    LEFT JOIN promotion p ON us.promotion_id = p.promotion_id
    WHERE us.us_id = NEW.us_id;

    -- Tính toán total_amount theo công thức
    SET NEW.total_amount = (v_quantity * v_service_price + v_room_price * (1 - (v_promotion_value / 100)));
END;

//

DELIMITER ;



