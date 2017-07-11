package com.ma.main;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.ma.JSON.SearchOuter;
import com.ma.enums.CarType;
import com.ma.enums.Doors;
import com.ma.enums.FuelAC;
import com.ma.enums.Transmission;
import com.ma.models.Sipp;
import com.ma.models.Vehicle;
import com.ma.models.VehicleSpecModel;


/**
 * This class reads in the json with the data input into a list of vehicles
 * Also consists of methods that solve tasks
 * @author Muhammad
 */
public class Analysis {

	public List<Vehicle> VehicleList = null;
	
	// Method that creates vehicle list from url JSON
	public void initialise(){
		URL jsonUrl = null;
		try {
			jsonUrl = new URL("http://www.rentalcars.com/js/vehicles.json");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

        ObjectMapper mapper = new ObjectMapper();
        
        try {
			SearchOuter City = mapper.readValue(jsonUrl, SearchOuter.class);
			VehicleList = City.getSearch().getVehicleList();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/* The tasks */
	
	/** Task 1 Display by price ascending
	 * @param list of vehicles
	 * @return list of vehicles on prices ascending order
	 */
	public static List<Vehicle> priceAscending(List<Vehicle> x){
			Collections.sort(x, Analysis.vehiclePriceComparatorAscending);
			return x;
	}
	
	/** Task 2 Display the Sipp details of cars
	 * @param list of vehicles
	 * @return list of vehicles with there corresponding sipp values
	 */
	public static List<VehicleSpecModel> vehicleSpec(List<Vehicle> x){
	
		// Model object for the vehicle and Sipp 
		VehicleSpecModel mm = new VehicleSpecModel();
		
		// List to add Sipp details too
		List<Sipp> sippList = new ArrayList<>();
		
      	List<VehicleSpecModel> xyz = new ArrayList<>();
      	
      	// Loop through each car and get/store the Sipp details
	    for (Vehicle car : x) {
	      		//Init variables
	      		String type = null, doors = null, transmission = null, fuel = null, ac = null;
		    
	      		String sipp = car.getSipp();
	      		
	      		type = sippLetterValue(sipp,0);
	      		doors = sippLetterValue(sipp,1);
	      		transmission = sippLetterValue(sipp,2);
	      		fuel = sippLetterValue(sipp,3, 0);
	      		ac = sippLetterValue(sipp,3, 1);
	      		
		      	Sipp Sipp = new Sipp(type, doors, transmission, fuel, ac);
		      	sippList.add(Sipp);
			}
	    	// Store the vehicle and Sipp objects in another combined object
	      	mm.setvListDTO(x);
	      	mm.setSippDTO(sippList);
	      	xyz.add(mm);
	      	
			return xyz;
		}
	
	/** Task 3 Get the highest rated supplier for each car type
	 * @param List of vehicles
	 * @return Highest rated vehicle from each supplier including sipp values
	 */
	public static List<VehicleSpecModel> supplierRatingByType(List<Vehicle> x){
		
		// Used to store the highest rated vehicles for each car type
		List<Vehicle> tempVehicle = new ArrayList<>();
		List<Sipp> tempSipp = new ArrayList<>();
		
		
		List<VehicleSpecModel> zxc = vehicleSpec(x);
		List<Sipp> Sipp = zxc.get(0).getSippDTO();
		List<Vehicle> vehicle =  zxc.get(0).getvListDTO();
		
		List<VehicleSpecModel> xyz = new ArrayList<>();
		
		// loop through each of the cartypes
		// for each car type then find highest rated car within it
		// Then store in temp list to be returned
		
		for (CarType type : CarType.values()) {
	
			// Now loop through cars to get highest from each supplier
			Double max = Double.MIN_VALUE;
			Vehicle theHighestVehicle = null;
			Sipp theHighestSipp = null;
			for(int i = 0; i < x.size(); i++) {

			      if(vehicle.get(i).getRating() > max && Sipp.get(i).getCarType().equals(type.getText()) ) {
			         max = vehicle.get(i).getRating();
			         theHighestVehicle = vehicle.get(i);
			         theHighestSipp = Sipp.get(i);
			      }
			      
			}
			

			// Now we got highest rating
			if (theHighestVehicle != null){
				tempVehicle.add(theHighestVehicle);
				tempSipp.add(theHighestSipp);
			}
			
			
		}
		VehicleSpecModel mm = new VehicleSpecModel();
		mm.setvListDTO(tempVehicle);
		mm.setSippDTO(tempSipp);
		
		xyz.add(mm);

		return xyz;
	}
	
	//task 4
	public static List<Vehicle> ratingBreakdownDescending(List<Vehicle> x){
		
		List<VehicleSpecModel> zxc = vehicleSpec(x);
		List<Vehicle> vehicleList = zxc.get(0).getvListDTO();
	
		String transmission = null;
		String ac = null;
		int rates = 0;
		double combinedRates = 0;
		
		for (int i = 0; i < vehicleList.size(); i++ ){
			rates = 0;
			
			String sipp = vehicleList.get(i).getSipp();
			
			transmission = sippLetterValue(sipp, 2);
			ac = sippLetterValue(sipp, 3, 1);
	        
			// Work out transmission rating
			if (transmission.equals(Transmission.A.getText())){
				rates += 5;
			} else{
				rates += 1;
			}
			
			// Work out if has ac and give score
			if (ac.equals(FuelAC.R.getAcText())){
				rates += 2;
			}
			// Set the vehicle score and the combined
			vehicleList.get(i).setBreakdownRating(rates);
			combinedRates = rates + vehicleList.get(i).getRating();
			vehicleList.get(i).setCombinedRating(combinedRates);
		}
		
	      	// Sort the list by the breakdown rating
	      	Collections.sort(x, vehicleCombinedRatingComparatorDescending);
	      	return x;
		
	}
	
	/** Get values from sipp code
	 * @param sipp The sipp code
	 * @param index The index location of char to be read from sipp code
	 * @return The value of the sipp char
	 */
	public static String sippLetterValue(String sipp, int index){
		String sippValue = null;
		try {
			switch(index){
			case 0:
				sippValue = CarType.valueOf(Character.toString(sipp.charAt(index))).getText();
				break;
			case 1:
				sippValue = Doors.valueOf(Character.toString(sipp.charAt(index))).getText();
				break;
			case 2:
				sippValue = Transmission.valueOf(Character.toString(sipp.charAt(index))).getText();
				break;
			default:
				break;
			}
        } catch (IllegalArgumentException e) {}
		
		return sippValue;
	}
		
	/** Get values from sipp code
	 * @param sipp The sipp code
	 * @param index The index location of char to be read from sipp code
	 * @param fuelAc Used to indicate whether to return ac or fuel result
	 * @return The value of the sipp char
	 */
	public static String sippLetterValue(String sipp, int index, int fuelAc){
		String sippValue = null;
		switch (index){
			case 3:
				if (fuelAc == 0){
					sippValue = FuelAC.valueOf(Character.toString(sipp.charAt(index))).getFuelText();
				} else{
					sippValue = FuelAC.valueOf(Character.toString(sipp.charAt(index))).getAcText();
				}
				break;
			default:
			break;
		}
		return sippValue;
	}
	

	/* COMPARATORS */
	
	/** sorts list of vehicles by Student Name in ascending order */
    public static Comparator<Vehicle> vehiclePriceComparatorAscending = new Comparator<Vehicle>() {

	public int compare(Vehicle s1, Vehicle s2) {
	   double vehicle1 = s1.getPrice();
	   double vehicle2 = s2.getPrice();
	   
	   //ascending order
	   return Double.compare(vehicle1, vehicle2);
    }};
    
        
    /** sorts list of vehicles by Rating in descending order */
    public static Comparator<Vehicle> vehicleCombinedRatingComparatorDescending = new Comparator<Vehicle>() {

    public int compare(Vehicle s1, Vehicle s2) {
    	   double vehicle1 = s1.getCombinedRating();
    	   double vehicle2 = s2.getCombinedRating();

    	   //descending order
    	   return Double.compare(vehicle2, vehicle1);
    	   
    }};
	
	
	
}
