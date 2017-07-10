package com.ma.main;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.SpringVersion;

import com.ma.models.Sipp;
import com.ma.models.Vehicle;
import com.ma.models.VehicleSpecModel;

/**
 * The class starts the application and prints the results to the console
 * @author Muhammad
 * 
 */

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		
	    SpringApplication.run(Application.class, args);
		System.out.println("Spring Version: " + SpringVersion.getVersion());
	
		Analysis an = new Analysis();
		an.initialise();
		List<Vehicle> vehicleList = an.VehicleList;

        
        // Task 1
        priceAscending(vehicleList);
        
        // Task 2
        printVehicleSpec(vehicleList);
        
        // Task 3
        printRatingDescending(vehicleList);
        
        // Task 4
        printRatingBreakdownDescending(vehicleList);
        
	}
	
	// Task 1
	public static void priceAscending(List<Vehicle> x){
        x = Analysis.priceAscending(x);
		System.out.println("--- Price Ascending! ---");
		System.out.println("Name - Price");
      	for (Vehicle car : x) {
			System.out.println(car.getName() + " - " + car.getPrice());
		}
	}
	
	//Task 2
	public static void printVehicleSpec(List<Vehicle> x){
		List<VehicleSpecModel> mm = Analysis.vehicleSpec(x);
	
		System.out.println("\n--- Vehicle Specs based on SIPP! ---");
		System.out.println("Name - Sipp - CarType - CarType/Doors - Transmission - Fuel - AirCon");

		for (VehicleSpecModel data : mm) {
			List<Vehicle> vehicle = data.getvListDTO();
			List<Sipp> s = data.getSippDTO();
			
			for (int y = 0; y < s.size(); y++){
				System.out.println(vehicle.get(y).getName() + " - " 
				      	+ vehicle.get(y).getSipp() + " - "
				      	+ s.get(y).getCarType() + " - "
				      	+ s.get(y).getDoors() + " - "
				      	+ s.get(y).getTransmission() + " - "
				      	+ s.get(y).getFuel() + " - "
				      	+ s.get(y).getAc()
						);
			}
			
		}
		
	}
	
	//Task 3
	public static void printRatingDescending(List<Vehicle> x){
		
		List<VehicleSpecModel> bn = Analysis.supplierRatingByType(x);
        List<Vehicle> vehicleList = bn.get(0).getvListDTO();
        List<Sipp> sippList = bn.get(0).getSippDTO();
        
		System.out.println("\n--- Highest Rated Supplier Per Car Type Rating Descending! ---");
		System.out.println("Name – CarType – Supplier – Rating");

      	
      	for (int i = 0;  i < vehicleList.size(); i++ ){
      		System.out.println(vehicleList.get(i).getName()  + " - "
      				+ sippList.get(i).getCarType() +" - " 
      				+ vehicleList.get(i).getSupplier() + " - "
      				+ vehicleList.get(i).getRating() );
      	}
		
	}
	
	//Task 4
	public static void printRatingBreakdownDescending(List<Vehicle> x){
		
		List<Vehicle> vehicleList = Analysis.ratingBreakdownDescending(x);
	
		System.out.println("\n--- Combined Ratings Descending! ---");
		System.out.println("Name - Vehicle Score - Rating - Sum");
		
		for (Vehicle vehicle : vehicleList ){
	  		System.out.println(vehicle.getName()  + " - "
	  				+ vehicle.getBreakdownRating() + " - " 
	  				+ vehicle.getRating() + " - "
	  				+ vehicle.getCombinedRating());
	  	}
			
	}
		

}