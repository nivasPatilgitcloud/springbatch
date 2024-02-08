package com.example.springbatch.model;

public class Foodtb {

	
	/*
	 * 
	 * id INT PRIMARY KEY,
    date VARCHAR(255),
    region VARCHAR(255),
    city VARCHAR(255),
    category VARCHAR(255),
    product VARCHAR(255),
    qty INT,
    unit_price INT,
    total_price INT
	 * */
	
	private String id;
	private String date;
	private String region;
	private String city;
	private String category;
	private String product;
	private String qty;
	private String unit_price;
	private String total_price;
	public Foodtb(String id, String date, String region, String city, String category, String product, String qty,
			String unit_price, String total_price) {
		super();
		this.id = id;
		this.date = date;
		this.region = region;
		this.city = city;
		this.category = category;
		this.product = product;
		this.qty = qty;
		this.unit_price = unit_price;
		this.total_price = total_price;
	}
	public Foodtb() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getQty() {
		return qty;
	}
	public void setQty(String qty) {
		this.qty = qty;
	}
	public String getUnit_price() {
		return unit_price;
	}
	public void setUnit_price(String unit_price) {
		this.unit_price = unit_price;
	}
	public String getTotal_price() {
		return total_price;
	}
	public void setTotal_price(String total_price) {
		this.total_price = total_price;
	}
	@Override
	public String toString() {
		return "Foodtb [id=" + id + ", date=" + date + ", region=" + region + ", city=" + city + ", category="
				+ category + ", product=" + product + ", qty=" + qty + ", unit_price=" + unit_price + ", total_price="
				+ total_price + "]";
	}
	
	

}
