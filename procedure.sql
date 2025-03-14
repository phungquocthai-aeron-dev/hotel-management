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
