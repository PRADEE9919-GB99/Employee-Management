package com.pradeep.Employee_Management.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AddressDTO {
	private Integer addressId;
	@NotBlank(message = "{address.city.notBlank}")
	@Size(max = 100, message = "{address.city.size}")
	private String city;
	@NotBlank(message = "{address.state.notBlank}")
	@Size(max = 100, message = "{address.state.size}")
	private String state;
	@NotBlank(message = "{address.country.notBlank}")
	@Size(max = 100, message = "{address.country.size}")
	private String country;

	public Integer getAddressId() {
		return addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
}
