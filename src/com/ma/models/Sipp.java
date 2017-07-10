package com.ma.models;

public class Sipp {

	private String carType;
	private String doors;
	private String transmission;
	private String fuel;
	private String ac;
	
	
	public Sipp(String carType, String doors, String transmission, String fuel, String ac) {
		this.carType = carType;
		this.doors = doors;
		this.transmission = transmission;
		this.fuel = fuel;
		this.ac = ac;
	}

	public String getCarType() {
		return carType;
	}
	public void setCarType(String carType) {
		this.carType = carType;
	}
	public String getDoors() {
		return doors;
	}
	public void setDoors(String doors) {
		this.doors = doors;
	}
	public String getTransmission() {
		return transmission;
	}
	public void setTransmission(String transmission) {
		this.transmission = transmission;
	}
	public String getFuel() {
		return fuel;
	}
	public void setFuel(String fuel) {
		this.fuel = fuel;
	}
	public String getAc() {
		return ac;
	}
	public void setAc(String ac) {
		this.ac = ac;
	}

	@Override
	public String toString() {
		return "Sipp [carType=" + carType + ", doors=" + doors + ", transmission=" + transmission + ", fuel=" + fuel
				+ ", ac=" + ac + "]";
	}
	
	
}
