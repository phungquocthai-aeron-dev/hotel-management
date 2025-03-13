package com.quantridulieu.hotelManagement.repositories;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.quantridulieu.hotelManagement.entities.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, String> {

    @Query(value = "SELECT * FROM invoice WHERE customer_id = :customerId", nativeQuery = true)
    List<Invoice> findByCustomerId(@Param("customerId") String customerId);

    @Query(value = "SELECT * FROM invoice WHERE staff_id = :staffId", nativeQuery = true)
    List<Invoice> findByStaffId(@Param("staffId") String staffId);

    @Query(value = "SELECT * FROM invoice WHERE invoice_date BETWEEN :fromDate AND :toDate", nativeQuery = true)
    List<Invoice> findByInvoiceDateBetween(@Param("fromDate") Date fromDate, @Param("toDate") Date toDate);

    @Query(value = "SELECT * FROM invoice WHERE invoice_id IN (:invoiceIds)", nativeQuery = true)
    List<Invoice> findByInvoiceIds(@Param("invoiceIds") List<String> invoiceIds);

    @Modifying
    @Procedure(name = "SearchInvoice")
    List<Invoice> SearchInvoice(
            @Param("p_invoice_id") String invoiceId,
            @Param("p_staff_id") String staffId,
            @Param("p_totalAmountRange") String totalAmountRange,
            @Param("p_serviceDate") LocalDate serviceDate);

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
