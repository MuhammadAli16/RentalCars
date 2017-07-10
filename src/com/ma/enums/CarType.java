package com.ma.enums;

public enum CarType {

	M ("Mini"),
	E ("Economy"),
	C ("Compact"),
	I ("Intermediate"),
	S ("Standard"),
	F ("FullSize"),
	P ("Premium"),
	L ("Luxury"),
	X ("Special");

	private String text;

	CarType(String text) {
	    this.text = text;
	 }

	  public String getText() { 
	    return this.text;
	  }

	  public static CarType fromString(String text) {
	    for (CarType b : CarType.values()) {
	      if (b.text.equalsIgnoreCase(text)) {
	        return b;
	      }
	    }
	    return null;
	  }

}
