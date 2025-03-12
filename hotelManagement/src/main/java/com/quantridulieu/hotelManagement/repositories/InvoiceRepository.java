package com.quantridulieu.hotelManagement.repositories;

import java.util.List;
import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import com.quantridulieu.hotelManagement.entities.Invoice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface InvoiceRepository extends JpaRepository<Invoice, String> {
	  @Query(value = "SELECT * FROM invoice WHERE invoice_date = :invoiceDate", nativeQuery = true)
	    List<Invoice> findByInvoiceDate(@Param("invoiceDate") Date invoiceDate);

	    @Query(value = "SELECT * FROM invoice WHERE total_amount = :totalAmount", nativeQuery = true)
	    List<Invoice> findByTotalAmount(@Param("totalAmount") float totalAmount);

	    @Query(value = "SELECT * FROM invoice WHERE us_id = :usId", nativeQuery = true)
	    List<Invoice> findByUsId(@Param("usId") String usId);

	    @Query(value = "SELECT * FROM invoice WHERE staff_id = :staffId", nativeQuery = true)
	    List<Invoice> findByStaffId(@Param("staffId") String staffId);
	    
	    @Query(value = "SELECT GetDailyRevenue(:date)", nativeQuery = true)
	    double getDailyRevenue(@Param("date") Date date);

	    @Query(value = "SELECT GetMonthlyRevenue(:year, :month)", nativeQuery = true)
	    double getMonthlyRevenue(@Param("year") Integer year, @Param("month") Integer month);

	    @Query(value = "SELECT GetQuarterlyRevenue(:year, :quarter)", nativeQuery = true)
	    double getQuarterlyRevenue(@Param("year") Integer year, @Param("quarter") Integer quarter);

	    @Query(value = "SELECT GetYearlyRevenue(:year)", nativeQuery = true)
	    double getYearlyRevenue(@Param("year") Integer year);
}
