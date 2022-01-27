package com.cg.services1.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="services")
public class Services {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@GeneratedValue(strategy = GenerationType.AUTO, generator = "auto_gen")
	@SequenceGenerator(name = "services_servicesid_seq")
	@Column(name="servicesid")
	public Long servicesId;
	@Column(name="servicesname")
	public String servicesName;
	@Column(name="servicescategory")
	public String servicesCategory;
	@Column(name="servicesprice")
	public double servicesPrice;
	@Column(name="merchantid")
	public Long merchantId;
	
	
public Services() {
	
}


public Services(Long servicesId, String servicesName, String servicesCategory, double servicesPrice, Long merchantId) {
	super();
	this.servicesId = servicesId;
	this.servicesName = servicesName;
	this.servicesCategory = servicesCategory;
	this.servicesPrice = servicesPrice;
	this.merchantId = merchantId;
}


public Long getServicesId() {
	return servicesId;
}


public void setServicesId(Long servicesId) {
	this.servicesId = servicesId;
}


public String getServicesName() {
	return servicesName;
}


public void setServicesName(String servicesName) {
	this.servicesName = servicesName;
}


public String getServicesCategory() {
	return servicesCategory;
}


public void setServicesCategory(String servicesCategory) {
	this.servicesCategory = servicesCategory;
}


public double getServicesPrice() {
	return servicesPrice;
}


public void setServicesPrice(double servicesPrice) {
	this.servicesPrice = servicesPrice;
}


public Long getMerchantId() {
	return merchantId;
}


public void setMerchantId(Long merchantId) {
	this.merchantId = merchantId;
}


@Override
public String toString() {
	return "Services [servicesId=" + servicesId + ", servicesName=" + servicesName + ", servicesCategory="
			+ servicesCategory + ", servicesPrice=" + servicesPrice + ", merchantId=" + merchantId + "]";
}

}
