package com.quantridulieu.hotelManagement.entities;

import java.util.Date;



public class USExport {
	private String usId;

	private Integer quantity;

	private Date usDate;

	private String serviceId;

	private String roomRentalId;

	private String promotionId;

	public USExport() {}

	public USExport(String usId, Integer quantity, Date usDate, String serviceId, String roomRentalId,
			String promotionId) {
		super();
		this.usId = usId;
		this.quantity = quantity;
		this.usDate = usDate;
		this.serviceId = serviceId;
		this.roomRentalId = roomRentalId;
		this.promotionId = promotionId;
	}

    public USExport(UseService useService) {
        this.usId = useService.getUsId();
        this.quantity = useService.getQuantity();
        this.usDate = useService.getUsDate();
        this.serviceId = (useService.getService() != null) ? useService.getService().getServiceId() : null;
        this.roomRentalId = (useService.getRoomRental() != null) ? useService.getRoomRental().getRentId() : null;
        this.promotionId = (useService.getPromotion() != null) ? useService.getPromotion().getPromotionId() : null;
    }
    
	public String getUsId() {
		return usId;
	}

	public void setUsId(String usId) {
		this.usId = usId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Date getUsDate() {
		return usDate;
	}

	public void setUsDate(Date usDate) {
		this.usDate = usDate;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getRoomRentalId() {
		return roomRentalId;
	}

	public void setRoomRentalId(String roomRentalId) {
		this.roomRentalId = roomRentalId;
	}

	public String getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(String promotionId) {
		this.promotionId = promotionId;
	}
	
	

}