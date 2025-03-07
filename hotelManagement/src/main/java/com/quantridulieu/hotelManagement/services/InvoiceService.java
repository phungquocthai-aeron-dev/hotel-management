package com.quantridulieu.hotelManagement.services;

import java.util.List;
import java.util.Date;

import com.quantridulieu.hotelManagement.repositories.InvoiceRepository;
import com.quantridulieu.hotelManagement.entities.Invoice;

import org.springframework.beans.factory.annotation.Autowired;


public class InvoiceService {
	@Autowired
    InvoiceRepository invoiceRepository;

    public void save(Invoice invoice) {
        if (invoice.getInvoiceId() == null) invoice.setInvoiceId(generateId());
        invoiceRepository.save(invoice);
    }

    public void delete(String id) {
        invoiceRepository.deleteById(id);
    }

    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    public Invoice getInvoiceById(String id) {
        return invoiceRepository.findById(id).orElseThrow();
    }

    public List<Invoice> getInvoicesByDate(Date invoiceDate) {
        return invoiceRepository.findByInvoiceDate(invoiceDate);
    }

    public List<Invoice> getInvoicesByTotalAmount(float totalAmount) {
        return invoiceRepository.findByTotalAmount(totalAmount);
    }

    public List<Invoice> getInvoicesByUsId(String usId) {
        return invoiceRepository.findByUsId(usId);
    }

    public List<Invoice> getInvoicesByStaffId(String staffId) {
        return invoiceRepository.findByStaffId(staffId);
    }

    private String generateId() {
        Long count = invoiceRepository.count();
        return String.format("INV%05d", count + 1);
    }
}
