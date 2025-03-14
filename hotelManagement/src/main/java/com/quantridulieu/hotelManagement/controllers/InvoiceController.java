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
import com.quantridulieu.hotelManagement.entities.UseService;
import com.quantridulieu.hotelManagement.services.InvoiceService;
import com.quantridulieu.hotelManagement.services.StaffService;
import com.quantridulieu.hotelManagement.services.UseServiceService;

import jakarta.servlet.http.HttpSession;

@Controller
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private StaffService staffService;

    @Autowired
   private UseServiceService useServiceService;
//    
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
    
    @GetMapping("/invoice/search")
    public String searchInvoices(
            RedirectAttributes redirectAttributes,
            @RequestParam(required = false, defaultValue = "") String invoiceId,
            @RequestParam(required = false, defaultValue = "") String staffId,
            @RequestParam(required = false, defaultValue = "") String totalAmountRange,
            @RequestParam(required = false, defaultValue = "") String serviceDate) {
    	
        List<Invoice> invoices = invoiceService.searchInvoices(invoiceId, staffId, totalAmountRange, serviceDate);
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
    public String deleteInvoice(@RequestParam("id") String invoiceId, RedirectAttributes redirectAttributes, HttpSession session) {
    	 Staff staff = (Staff) session.getAttribute("loggedInStaff");
    	 
    	 if (staff == null)
 			return "redirect:/login";
    	invoiceService.delete(invoiceId);
        
        redirectAttributes.addFlashAttribute("message", "Xóa hóa đơn " + invoiceId + " thành công!");
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
    
    @GetMapping("/invoice/add")
    public String showInvoiceForm(Model model, HttpSession session) {
        Invoice invoice;
            invoice = new Invoice();

        // Thêm dữ liệu vào model
        model.addAttribute("invoice", invoice);
        
        Staff staff = (Staff) session.getAttribute("loggedInStaff");
        if (staff == null)
			return "redirect:/login";
        
        
        //lấy các dịch vụ chưa có trong invoice
        List<UseService> useServiceList = useServiceService.getAllUseServiceNotInInvoice();
        model.addAttribute("staff", staff);
        model.addAttribute("useServiceList", useServiceList);

        return "invoice_add"; // Hiển thị form thêm/sửa hóa đơn
    }

}