package com.ma.enums;

public enum Transmission {
	
	M("Manual"),
	A("Automatic");

	private String text;

	Transmission(String text) {
	    this.text = text;
	  }

	  public String getText() {
	    return this.text;
	  }

	  public static Transmission fromString(String text) {
	    for (Transmission b : Transmission.values()) {
	      if (b.text.equalsIgnoreCase(text)) {
	        return b;
	      }
	    }
	    return null;
	  }
	  
}
