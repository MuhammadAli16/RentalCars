package com.ma.JSON;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ma.models.Vehicle;

public class SearchObject {
	
	@JsonProperty
	private List<Vehicle> VehicleList;

	public List<Vehicle> getVehicleList() {
		return VehicleList;
	}

	public void setVehicleList(List<Vehicle> vehicleList) {
		VehicleList = vehicleList;
	}	
	
}
