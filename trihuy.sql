DELIMITER $$
CREATE PROCEDURE SearchStaff(
    IN p_staff_id VARCHAR(255),
    IN p_staff_name VARCHAR(255),
    IN p_staff_phone VARCHAR(255)
)
BEGIN
    SELECT *
    FROM Staff
    WHERE 
        (p_staff_id IS NULL OR staff_id = p_staff_id)
        AND (p_staff_name IS NULL OR staff_name LIKE CONCAT('%', p_staff_name, '%'))
        AND (p_staff_phone IS NULL OR staff_phone = p_staff_phone);
END $$
DELIMITER ;