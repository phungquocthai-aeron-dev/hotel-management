package com.quantridulieu.hotelManagement.services;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quantridulieu.hotelManagement.entities.Invoice;
import com.quantridulieu.hotelManagement.entities.InvoiceExport;
import com.quantridulieu.hotelManagement.repositories.InvoiceRepository;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private ExcelExportUtil excelExportUtil;

    public byte[] exportInvoiceToExcelByListIds(List<String> ids) throws IOException {
        List<InvoiceExport> list = new ArrayList<InvoiceExport>();
        List<Invoice> data = invoiceRepository.findByInvoiceIds(ids);
        int n = ids.size();
        for (int i = 0; i < n; i++) {
            Invoice invoice = data.get(i);
            InvoiceExport export = new InvoiceExport(invoice.getInvoiceId(), invoice.getInvoiceDate(),
                    invoice.getTotalAmount(), invoice.getUseService().getUsId(), invoice.getStaff().getStaffId());
            list.add(export);
        }
        return excelExportUtil.exportToExcel(list, null, "Danh sách hóa đơn");
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

    public List<Invoice> getInvoicesByStaffId(String staffId) {
        return invoiceRepository.findByStaffId(staffId);
    }

    public double getDailyRevenue(Date date) {
        return invoiceRepository.getDailyRevenue(date);
    }

    public double getMonthlyRevenue(Integer month, Integer year) {
        return invoiceRepository.getMonthlyRevenue(year, month);
    }

    private String generateId() {
        Long count = invoiceRepository.count();
        return String.format("INV%05d", count + 1);
    }

    @Transactional
    public List<Invoice> searchInvoices(String invoiceId, String staffId, String totalAmountRange, String serviceDate) {
        LocalDate parsedDate = null;
        if (serviceDate != null && !serviceDate.isEmpty()) {
            parsedDate = LocalDate.parse(serviceDate);
        }

        return invoiceRepository.SearchInvoice(
                (invoiceId == null || invoiceId.isEmpty()) ? null : invoiceId,
                (staffId == null || staffId.isEmpty()) ? null : staffId,
                (totalAmountRange == null || totalAmountRange.isEmpty()) ? null : totalAmountRange,
                parsedDate);
    }


}
