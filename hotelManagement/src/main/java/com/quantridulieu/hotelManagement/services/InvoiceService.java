package com.quantridulieu.hotelManagement.services;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quantridulieu.hotelManagement.entities.Invoice;
import com.quantridulieu.hotelManagement.repositories.InvoiceRepository;

@Service
public class InvoiceService {

    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    ExcelExportUtil excelExportUtil;

    // Xuất file excel
    public byte[] exportStaffToExcel() throws IOException {
        return excelExportUtil.exportToExcel(invoiceRepository.findAll(), null, "Danh sách hóa đơn");
    }

    public byte[] exportInvoiceToExcelByListIds(List<String> ids) throws IOException {
        return excelExportUtil.exportToExcel(invoiceRepository.findByInvoiceIds(ids), null, "Danh sách hóa đơn");
    }

    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    public Invoice getInvoiceById(String id) {
        return invoiceRepository.findById(id).orElseThrow();
    }

    public List<Invoice> getInvoicesByDateRange(Date fromDate, Date toDate) {
        return invoiceRepository.findByInvoiceDateBetween(fromDate, toDate);
    }

    @Transactional
    public void save(Invoice invoice) {
        if (invoice.getInvoiceId() == null)
            invoice.setInvoiceId(generateId());
        invoiceRepository.save(invoice);
    }

    @Transactional
    public void delete(String id) {
        invoiceRepository.deleteById(id);
    }

    public double getDailyRevenue(Date date) {
        return invoiceRepository.getDailyRevenue(date);
    }

    private String generateId() {
        Long count = invoiceRepository.count();
        return String.format("INV%05d", count + 1);
    }

    @Transactional
    public List<Invoice> searchInvoices(String invoiceId, String staffId, String fromDate, String toDate) {
        return invoiceRepository.searchInvoices(invoiceId, staffId, fromDate, toDate);
    }
}
