package com.ma.main;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ma.models.Vehicle;
import com.ma.models.VehicleSpecModel;

/**
 * @author Muhammad
 */
@RestController
public class TheController {
	
	
	/**
	 * @return The full list of vehicles in arraylist
	 */
	 public List<Vehicle> theList(){
		 Analysis an = new Analysis();
		 an.initialise();
	     List<Vehicle> vehicles = an.VehicleList;
		 return vehicles;
	 }
	
	// Returns full list of vehicles
	 @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json" )
	 public List<Vehicle> all() {
	    return theList();
	 }
	 
	// Task 1
	@RequestMapping(value = "/priceascending", method = RequestMethod.GET, produces = "application/json" )
	public List<Vehicle> priceascending() {
	
	    List<Vehicle> found = Analysis.priceAscending(theList());
	    return found;
	 }
	
	// Task 2
	@RequestMapping(value = "/vehiclespec", method = RequestMethod.GET, produces = "application/json" )
	public List<VehicleSpecModel> vehiclespec() {
	        
		List<VehicleSpecModel> x = Analysis.vehicleSpec(theList());
        return x;
	}
				 
	// Task 3
	@RequestMapping(value = "/highestratedsuppliers", method = RequestMethod.GET, produces = "application/json" )
    public List<VehicleSpecModel> highestratedsuppliers() {
	
       List<VehicleSpecModel> x = Analysis.supplierRatingByType(theList());
       return x;
	}
				 
	// Task 4
	 @RequestMapping(value = "/combinedratings", method = RequestMethod.GET, produces = "application/json" )
	 public List<Vehicle> combinedratings() {
	
        List<Vehicle> x = Analysis.ratingBreakdownDescending(theList());
        return x;
	 }
	
}
