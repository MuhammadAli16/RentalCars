package com.ma.enums;

public enum Doors {
	
	B("2 doors"),
	C("4 doors"),
	D("5 doors"),
	W("Estate"),
	T("Convertible"),
	F("SUV"),
	P("Pick up"),
	V("Passenger Van");
	
	private String text;

	Doors(String text) {
	    this.text = text;
	  }

	  public String getText() {
	    return this.text;
	  }

	  public static Doors fromString(String text) {
	    for (Doors b : Doors.values()) {
	      if (b.text.equalsIgnoreCase(text)) {
	        return b;
	      }
	    }
	    return null;
	  }
}
