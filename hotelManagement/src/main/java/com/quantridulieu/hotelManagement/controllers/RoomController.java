package com.quantridulieu.hotelManagement.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.quantridulieu.hotelManagement.entities.Category;
import com.quantridulieu.hotelManagement.entities.HotelService;
import com.quantridulieu.hotelManagement.entities.Room;
import com.quantridulieu.hotelManagement.entities.Staff;
import com.quantridulieu.hotelManagement.repositories.CategoryRepository;
import com.quantridulieu.hotelManagement.repositories.RoomRepository;
import com.quantridulieu.hotelManagement.services.RoomService;

import jakarta.servlet.http.HttpSession;
@Controller

@RequestMapping("/room")
public class RoomController {
	@Autowired
	private CategoryRepository categoryRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private RoomService roomService;
   @GetMapping
    public String getAllRooms(Model model, HttpSession session) {
		Staff staff = (Staff) session.getAttribute("loggedInStaff");
		if(staff == null) return "redirect:/login";
		
        // Lấy tất cả các phòng từ repository
        model.addAttribute("rooms", roomRepository.findAll());

        // Kiểm tra xem có dữ liệu tìm kiếm nào từ 'searchResult' không
        List<Room> list = (List<Room>) model.getAttribute("searchResult");

        // Nếu không có dữ liệu tìm kiếm thì lấy tất cả các phòng
        if (list == null) list = roomService.getAllRooms();
        
        List<Category> categories = categoryRepository.findAll();

        // Thêm danh sách phòng vào model
        model.addAttribute("rooms", list);
		model.addAttribute("staff", staff);
		model.addAttribute("categories", categories);


        // Trả về view phòng (room.jsp)
        return "room"; // Trả về file room.jsp
    }

   @PostMapping("/save")
   public String saveRoom(
           @ModelAttribute Room room,
           @RequestParam("categoryId") String categoryId,  // Lấy categoryId từ form
           RedirectAttributes redirectAttributes) {
       
       // Lấy category theo ID
       Category category = categoryRepository.findById(categoryId).orElse(null);
       if (category == null) {
           redirectAttributes.addFlashAttribute("error", "Mã danh mục không hợp lệ.");
           return "redirect:/room";
       }
       System.out.println(category.getCategoryName());

       // Gán category vào room
       room.setCategory(category);
      
       // Lưu phòng vào database
       roomService.save(room);
      
       return "redirect:/room";
   }

    
    @GetMapping(value = "/details")
    public String getRoomById(HttpSession session, 
    		@RequestParam("id") String roomId, Model model) {
		Staff staff = (Staff) session.getAttribute("loggedInStaff");
		if(staff == null) return "redirect:/login";
		
        Room room = roomService.getRoomById(roomId);
        model.addAttribute("room", room);

        Map<String, String> errors = (Map<String, String>) model.getAttribute("errors");
        if (errors != null) {
            model.addAttribute("errors", errors);
        }
        
        return "update_room"; // Trả về trang update_room.jsp
    }

    @PostMapping("/update")
    public String updateRoom(
            @ModelAttribute Room room,
            @RequestParam("categoryId") String categoryId,
            RedirectAttributes redirectAttributes) {
        
        // Kiểm tra nếu categoryId bị null hoặc rỗng
        if (categoryId == null || categoryId.trim().isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Mã danh mục không hợp lệ.");
            return "redirect:/room/details?id=" + room.getRoomId();
        }

        // Tìm category từ categoryId
        Category category = categoryRepository.findById(categoryId).orElse(null);
        
        if (category == null) {
            redirectAttributes.addFlashAttribute("error", "Danh mục không tồn tại.");
            return "redirect:/room/details?id=" + room.getRoomId();
        }

        // Gán category vào room
        room.setCategory(category);

        // Lưu cập nhật
        roomService.save(room);
        
        return "redirect:/room";
    }
    
    
    @GetMapping("/search")
    public String searchRoom(
            RedirectAttributes redirectAttributes,
            @RequestParam(required = false, defaultValue = "") String roomId,
            @RequestParam(required = false) Integer roomNumber,
            @RequestParam(required = false, defaultValue = "") String status,
            @RequestParam(required = false, defaultValue = "") String categoryId,
            @RequestParam(required = false, defaultValue = "") String categoryName) {
    	
        List<Room> rooms = roomService.searchRoom(
                roomId,
                roomNumber,
                status,
                categoryId,
                categoryName
        );
        
        redirectAttributes.addFlashAttribute("searchResult", rooms);
        return "redirect:/room";
    }
    @PostMapping("/delete")
    public String deleteRoom(@RequestParam("id") String roomId) {
     roomService.delete(roomId);
      return "redirect:/room";
    }
    @PostMapping("/export")
    public ResponseEntity<byte[]> exportRoomsToExcel(
            @RequestParam("roomIds") List<String> roomIds) { // Đổi từ roomsExport sang roomIds

        if (roomIds == null || roomIds.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        byte[] excelData;
        try {
            excelData = roomService.exportRoomsToExcelByListIds(roomIds);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=danh_sach_phong.xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(excelData);
    }




    
}