package com.ma.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Vehicle {

	@JsonProperty
    private String sipp;
	@JsonProperty
    private String name;
	@JsonProperty
	private double price;
	@JsonProperty
	private String supplier;
	@JsonProperty
	private double rating;
	
	private int breakdownRating;
	private double combinedRating;
		
	public int getBreakdownRating() {
		return breakdownRating;
	}
	public void setBreakdownRating(int breakdownRating) {
		this.breakdownRating = breakdownRating;
	}
	
	public double getCombinedRating() {
		return combinedRating;
	}
	public void setCombinedRating(double combinedRating) {
		this.combinedRating = combinedRating;
	}
	
	public String getSipp() {
		return sipp;
	}
	public void setSipp(String sipp) {
		this.sipp = sipp;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public Double getRating() {
		return rating;
	}
	public void setRating(Double rating) {
		this.rating = rating;
	}
   
}
