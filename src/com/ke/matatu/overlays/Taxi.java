package com.ke.matatu.overlays;

public class Taxi {

	public Taxi(String companyName, String registration, String cellphone,
			String vehicleColor, double latitude, double longitude) {
		setCompany(companyName);
		setRegistration(registration);
		setCellPhone(cellphone);
		setColor(vehicleColor);
		setLatitude(latitude);
		setLongitude(longitude);
	}
	
	private void setRegistration(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}
	
	private void setCellPhone(String cellphoneNumber) {
		this.cellphoneNumber = cellphoneNumber;
	}

	private void setCompany(String companyName) {
		this.companyName = companyName;
	}
	
	private void setColor(String vehicleColor) {
		this.vehicleColor = vehicleColor;
	}
	
	private void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	private void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	public String getRegistration() {
		return  registrationNumber;
	}
	
	public String getCellPhone() {
		return cellphoneNumber;
	}

	public String getCompany() {
		return companyName;
	}
	
	public String getColor() {
		return vehicleColor;
	}
	
	public double getLatitude() {
		return latitude;
	}
	
	public double getLongitude() {
		return longitude;
	}
	
	private double latitude;
	private double longitude;
	private String cellphoneNumber = null;
	private String companyName = null;
	private String vehicleColor = null;
	private String registrationNumber = null;
}