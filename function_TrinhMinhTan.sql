DELIMITER $$

-- Thủ tục tìm kiếm hóa đơn
CREATE PROCEDURE SearchInvoice(
    IN p_invoice_id VARCHAR(255),
    IN p_staff_id VARCHAR(255),
    IN p_from_date DATE,
    IN p_to_date DATE
)
BEGIN
    SELECT *
    FROM Invoice
    WHERE 
        (p_invoice_id IS NULL OR invoice_id = p_invoice_id)
        AND (p_staff_id IS NULL OR staff_id = p_staff_id)
        AND (p_from_date IS NULL OR invoice_date >= p_from_date)
        AND (p_to_date IS NULL OR invoice_date <= p_to_date);
END $$

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
