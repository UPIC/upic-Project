package com.upic.po;

import javax.persistence.Embeddable;


@Embeddable
public class Address {

	private String address;
	private String city;
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Address(String address, String city) {
		super();
		this.address = address;
		this.city = city;
	}
	public Address() {
		super();
	}
}
