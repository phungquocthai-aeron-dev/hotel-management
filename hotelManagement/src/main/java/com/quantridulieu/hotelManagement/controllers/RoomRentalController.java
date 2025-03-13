package com.quantridulieu.hotelManagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.quantridulieu.hotelManagement.repositories.CategoryRepository;
import com.quantridulieu.hotelManagement.repositories.RoomRentalRepository;
import com.quantridulieu.hotelManagement.entities.Customer;
import com.quantridulieu.hotelManagement.entities.Room;
import com.quantridulieu.hotelManagement.entities.RoomRental;

import java.awt.SystemColor;
import java.util.List;
import com.quantridulieu.hotelManagement.repositories.RoomRentalRepository;
import com.quantridulieu.hotelManagement.services.RoomRentalService;
import com.quantridulieu.hotelManagement.entities.RoomRental;
import com.quantridulieu.hotelManagement.repositories.RoomRepository;
import com.quantridulieu.hotelManagement.repositories.CustomerRepository;

@Controller
@RequestMapping("roomrental")
public class RoomRentalController {

	@Autowired
	private RoomRentalRepository roomRentalRepository;
	
	@Autowired
	private	RoomRepository roomRepository;
	@Autowired
	private	CustomerRepository customerRepository;
	@Autowired
	private RoomRentalService roomRentalService; // Thêm @Autowired

	@GetMapping
	public String getAllRoomRentals(Model model) {
		// Lấy danh sách thuê phòng từ repository
		model.addAttribute("roomRentals", roomRentalRepository.findAll());
		List<RoomRental> list = (List<RoomRental>) model.getAttribute("searchResult");

		// Nếu có dữ liệu từ tìm kiếm thì giữ nguyên, nếu không thì lấy toàn bộ danh
		// sách
		if (list == null)
			list = roomRentalService.getAllRoomRentals();

		model.addAttribute("roomRentals", list);
		return "roomrental"; // Trả về file roomrental.jsp
	}

	@PostMapping("/save")
	public String saveRoomRental(@ModelAttribute RoomRental roomRental, RedirectAttributes redirectAttributes) {
		try {
			roomRentalService.save(roomRental);
			redirectAttributes.addFlashAttribute("success", "Thêm phòng thuê thành công!");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", "Có lỗi xảy ra! Vui lòng thử lại.");
		}
		return "redirect:/roomrental";
	}

	@GetMapping(value = "/details")
	public String getRoomRentalById(@RequestParam("id") String rentId, Model model) {
		RoomRental roomRental = roomRentalService.getRentalById(rentId);
		model.addAttribute("roomRental", roomRental);
		System.out.println("PhanHoangKhang");
		Map<String, String> errors = (Map<String, String>) model.getAttribute("errors");
		if (errors != null) {
			model.addAttribute("errors", errors);
		}

		return "update_roomrental"; // Trả về trang update_room_rental.jsp
	}

	@PostMapping("/update")
	public String updateRoomRental(@ModelAttribute RoomRental roomRental, @RequestParam("roomId") String roomId,
			@RequestParam("customerId") String customerId, RedirectAttributes redirectAttributes) {

		// Kiểm tra nếu roomId hoặc customerId bị null hoặc rỗng
		if (roomId == null || roomId.trim().isEmpty()) {
			redirectAttributes.addFlashAttribute("error", "Mã phòng không hợp lệ.");
			return "redirect:/rental/details?id=" + roomRental.getRentId();
		}

		if (customerId == null || customerId.trim().isEmpty()) {
			redirectAttributes.addFlashAttribute("error", "Mã khách hàng không hợp lệ.");
			return "redirect:/rental/details?id=" + roomRental.getRentId();
		}

		// Kiểm tra phòng tồn tại
		Room room = roomRepository.findById(roomId).orElse(null);
		if (room == null) {
			redirectAttributes.addFlashAttribute("error", "Phòng không tồn tại.");
			return "redirect:/rental/details?id=" + roomRental.getRentId();
		}

		// Kiểm tra khách hàng tồn tại
		Customer customer = customerRepository.findById(customerId).orElse(null);
		if (customer == null) {
			redirectAttributes.addFlashAttribute("error", "Khách hàng không tồn tại.");
			return "redirect:/rental/details?id=" + roomRental.getRentId();
		}

		// Gán phòng và khách hàng vào RoomRental
		roomRental.setRoom(room);
		roomRental.setCustomer(customer);

		// Lưu cập nhật
		roomRentalService.save(roomRental);

		return "redirect:/roomrental";
	}
	
	
	
	@GetMapping("/search")
    public String searchBooking(
            RedirectAttributes redirectAttributes,
            @RequestParam(required = false, defaultValue = "") String rentId,
            @RequestParam(required = false, defaultValue = "") String roomId,
            @RequestParam(required = false, defaultValue = "") String customerName) {

        List<RoomRental> roomRentals = roomRentalService.searchBooking(rentId, roomId, customerName);

        redirectAttributes.addFlashAttribute("searchResult", roomRentals);
        return "redirect:/roomrental";
    }
	@PostMapping("/delete")
    public String deleteRoomRental(@RequestParam("id") String rentalId) {
      roomRentalService.delete(rentalId);
      return "redirect:/roomrental";
    }
	
}
