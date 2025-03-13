package com.quantridulieu.hotelManagement.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.quantridulieu.hotelManagement.entities.Staff;

@Repository
public interface StaffRepository extends JpaRepository<Staff, String> {
	@Query(value = "SELECT * FROM staff WHERE staff_name = :staff_name", nativeQuery = true)
	List<Staff> findStaffByStaffName(@Param("staffName") String staffName);

	@Query(value = "SELECT * FROM staff WHERE staff_phone = :staff_phone", nativeQuery = true)
	Staff findStaffByPhone(@Param("staff_phone") String staffPhone);

	@Query(value = "SELECT * FROM staff WHERE dob = :dob", nativeQuery = true)
	List<Staff> findStaffByDOB(@Param("dob") Date dob);

	@Query(value = "SELECT * FROM staff WHERE staff_id IN (:staffIds)", nativeQuery = true)
	List<Staff> findByStaffIds(@Param("staffIds") List<String> staffIds);

	@Query(value = "SELECT * FROM staff WHERE staff_role = :staff_role", nativeQuery = true)
	List<Staff> findStaffByStaffRole(@Param("staffRole") String staffRole);

	@Modifying
	@Procedure(name = "SearchStaff")
	List<Staff> SearchStaff(
			@Param("p_staff_id") String staffId,
			@Param("p_staff_name") String staffName,
			@Param("p_staff_phone") String staffPhone);

	@Query("SELECT COUNT(s) FROM Staff s WHERE s.role <> 'DISABLE'")
	Long getTotalStaff();
}
