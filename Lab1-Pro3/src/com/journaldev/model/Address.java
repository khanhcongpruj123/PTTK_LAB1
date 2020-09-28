package com.journaldev.model;

public class Address {

	private String city;
	private String district;
	private String commune;
	private String street;
	private String number;
	
	
	
	public Address(String city, String district, String commune, String street, String number) {
		this.city = city;
		this.district = district;
		this.commune = commune;
		this.street = street;
		this.number = number;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getCommune() {
		return commune;
	}
	public void setCommune(String commune) {
		this.commune = commune;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
	@Override
	public String toString() {
		return city + ", " + district + ", " + commune + ", " + street + ", " + number;
	}
}
