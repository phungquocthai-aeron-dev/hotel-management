package com.quantridulieu.hotelManagement.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.quantridulieu.hotelManagement.entities.Invoice;
import com.quantridulieu.hotelManagement.entities.Staff;
import com.quantridulieu.hotelManagement.services.InvoiceService;

import jakarta.servlet.http.HttpSession;

@Controller
@Transactional
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("/invoice")
    public String listInvoices(Model model, HttpSession session) {
//        Staff staff = (Staff) session.getAttribute("loggedInStaff");
//        if (staff == null) return "redirect:/login";

        List<Invoice> invoices = (List<Invoice>) model.getAttribute("searchResult");
        if (invoices == null) invoices = invoiceService.getAllInvoices();

        model.addAttribute("invoices", invoices);
//        model.addAttribute("staff", staff);
        return "invoice";
    }

    @GetMapping("invoice/search")
    public String searchInvoices(
            RedirectAttributes redirectAttributes,
            @RequestParam(required = false) String invoiceId,
            @RequestParam(required = false) String staffId,
            @RequestParam(required = false) String fromDate,
            @RequestParam(required = false) String toDate) {

        List<Invoice> invoices = invoiceService.searchInvoices(invoiceId, staffId, fromDate, toDate);
        redirectAttributes.addFlashAttribute("searchResult", invoices);
        return "redirect:/invoice";
    }

    @GetMapping("/invoice/detail")
    public String getInvoiceById(@RequestParam("id") String invoiceId, Model model, HttpSession session) {
//        Staff staff = (Staff) session.getAttribute("loggedInStaff");
//        if (staff == null) return "redirect:/login";

        Invoice invoice = invoiceService.getInvoiceById(invoiceId);
        model.addAttribute("invoice", invoice);
//        model.addAttribute("staff", staff);
        return "invoice_detail";
    }

    @PostMapping("/invoice/save")
    public String saveInvoice(@ModelAttribute Invoice invoice) {
        invoiceService.save(invoice);
        return "redirect:/invoice";
    }

    @PostMapping("/invoice/delete")
    public String deleteInvoice(@RequestParam("id") String invoiceId) {
        invoiceService.delete(invoiceId);
        return "redirect:/invoice";
    }

    @PostMapping("/invoice/export")
    public ResponseEntity<byte[]> exportInvoicesToExcel(
            HttpSession session,
            @RequestParam("invoicesExport") List<String> invoiceIds) {

//        Staff staff = (Staff) session.getAttribute("loggedInStaff");
//        if (staff == null) return ResponseEntity.status(403).build();

        byte[] excelData;
        try {
            excelData = invoiceService.exportInvoiceToExcelByListIds(invoiceIds);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=danh_sach_hoa_don.xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(excelData);
    }
}