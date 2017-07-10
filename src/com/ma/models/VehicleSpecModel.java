package com.ma.models;

import java.util.List;

public class VehicleSpecModel {
	private List<Vehicle> vListDTO;
	private List<Sipp> sippDTO;
	
	public List<Vehicle> getvListDTO() {
		return vListDTO;
	}
	public void setvListDTO(List<Vehicle> vListDTO) {
		this.vListDTO = vListDTO;
	}
	public List<Sipp> getSippDTO() {
		return sippDTO;
	}
	public void setSippDTO(List<Sipp> sippDTO) {
		this.sippDTO = sippDTO;
	}

}