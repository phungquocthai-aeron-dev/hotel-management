package com.quantridulieu.hotelManagement.entities;

import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name = "Promotion")
public class Promotion {
    @Id
    @Column(name = "promotion_id")
    private String promotionId;
    
    @Column(name = "promotion_name")
    private String promotionName;
    
    @Column(name = "promotion_value")
    private Float promotionValue;
    
    @Column(name = "promotion_description")
    private String promotionDescription;
    
    @Column(name = "promotion_start")
    private Date promotionStart;
    
    @Column(name = "promotion_end")
    private Date promotionEnd;
    
    public Promotion() {}

	public Promotion(String promotionId, String promotionName, Float promotionValue, String promotionDescription,
			Date promotionStart, Date promotionEnd) {
		super();
		this.promotionId = promotionId;
		this.promotionName = promotionName;
		this.promotionValue = promotionValue;
		this.promotionDescription = promotionDescription;
		this.promotionStart = promotionStart;
		this.promotionEnd = promotionEnd;
	}

	public String getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(String promotionId) {
		this.promotionId = promotionId;
	}

	public String getPromotionName() {
		return promotionName;
	}

	public void setPromotionName(String promotionName) {
		this.promotionName = promotionName;
	}

	public Float getPromotionValue() {
		return promotionValue;
	}

	public void setPromotionValue(Float promotionValue) {
		this.promotionValue = promotionValue;
	}

	public String getPromotionDescription() {
		return promotionDescription;
	}

	public void setPromotionDescription(String promotionDescription) {
		this.promotionDescription = promotionDescription;
	}

	public Date getPromotionStart() {
		return promotionStart;
	}

	public void setPromotionStart(Date promotionStart) {
		this.promotionStart = promotionStart;
	}

	public Date getPromotionEnd() {
		return promotionEnd;
	}

	public void setPromotionEnd(Date promotionEnd) {
		this.promotionEnd = promotionEnd;
	}
    
}
