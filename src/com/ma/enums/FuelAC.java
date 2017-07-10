package com.ma.enums;

public enum FuelAC {

	N("Petrol","NO AC"),
	R("Petrol", "AC");

	private String fuel;
	private String ac;

	FuelAC(String fuel, String ac) {
	    this.fuel = fuel;
	    this.ac = ac;
	  }

	  public String getFuelText() {
	    return this.fuel;
	  }
	  
	  public String getAcText() {
		return this.ac;
	  }
	  
	  public String[] getText(){
		  return new String[] {this.fuel , this.ac}; 
	  }

	  public static FuelAC fromString(String text) {
	    for (FuelAC b : FuelAC.values()) {
	      if (b.fuel.equalsIgnoreCase(text)) {
	        return b;
	      }
	    }
	    return null;
	  }
	  
}
