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


DELIMITER $$
CREATE PROCEDURE SearchMaintenance(
    IN p_mtn_id VARCHAR(255),
    IN p_room_id VARCHAR(255),
    IN p_staff_id VARCHAR(255)
)
BEGIN
    SELECT *
    FROM Maintenance
    WHERE 
        (p_mtn_id IS NULL OR mtn_id = p_mtn_id)
        AND (p_room_id IS NULL OR room_id = p_room_id)
        AND (p_staff_id IS NULL OR staff_id = p_staff_id);
END $$
DELIMITER ;

CALL SearchMaintenance(NULL ,NULL, 'ST00003');