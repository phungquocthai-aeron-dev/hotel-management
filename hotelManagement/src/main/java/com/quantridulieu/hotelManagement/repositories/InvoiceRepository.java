package com.quantridulieu.hotelManagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.quantridulieu.hotelManagement.entities.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, String> {}
