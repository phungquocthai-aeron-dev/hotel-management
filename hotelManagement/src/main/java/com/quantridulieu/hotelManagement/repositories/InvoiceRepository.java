package com.quantridulieu.hotelManagement.repositories;

import java.util.List;
import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.quantridulieu.hotelManagement.entities.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, String> {
	List<Invoice> findByInvoiceId(String invoiceId);
    List<Invoice> findByInvoiceDate(Date invoiceDate);
    List<Invoice> findByTotalAmount(float totalAmount);
    List<Invoice> findByUsId(String usId);
    List<Invoice> findByStaffId(String staffId);
}
