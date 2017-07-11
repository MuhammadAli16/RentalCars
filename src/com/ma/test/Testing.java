package com.ma.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import com.ma.main.Analysis;
import com.ma.models.Sipp;
import com.ma.models.Vehicle;
import com.ma.models.VehicleSpecModel;

public class Testing {
	   private static List<Vehicle> VehicleList;
	   private static Analysis an;
	   
	   
	   /**
	    * Set up data
	    */
	   @BeforeClass
	   public static void setup(){
		   an = new Analysis();
		   an.initialise();
		   VehicleList = an.VehicleList;
	   }
	   
	   
	   /* Check if data correctly input into list */
	   @Test
	   public void json(){
		   int size = 31;
		   assertEquals(size, VehicleList.size());
	   }
	   
	   
	   @Test
	   public void priceAscendingTest(){
		 List<Vehicle> tempList = Analysis.priceAscending(VehicleList);
		 // loop through list and check that next vehicle is cheaper
		 for (int i=0; i < tempList.size()-1; i++){
			 if (tempList.get(i).getPrice() > tempList.get(i+1).getPrice()){
				 assertTrue(false);
			 }
		 }
		 assertTrue(true);
	   }
	   
	   @Test
	   public void displaySpecsBasedOnSippTest(){
		   // create test vehicle and get the sipp values
		   Vehicle testVehicle = new Vehicle();
		   testVehicle.setName("Test Car");
		   testVehicle.setPrice(200.00);
		   testVehicle.setSupplier("TheTester");
		   testVehicle.setRating(5.00);
		   testVehicle.setSipp("ECMR");
		   testVehicle.setCombinedRating(0);
		   testVehicle.setBreakdownRating(0);
		   
		   List<Vehicle> aCarList = new ArrayList<Vehicle>();
		   aCarList.add(testVehicle);
		   
		   List<VehicleSpecModel> xyz = Analysis.vehicleSpec(aCarList);
		   List<Sipp> carSipp = xyz.get(0).getSippDTO();
		   
		   // create sipp values which do/don't match test data
		   Sipp testSippCorrect = new Sipp("Economy", "4 doors", "Manual", "Petrol", "AC");
		   Sipp testSippWrong = new Sipp("Economy", "2 doors", "Manual", "Petrol", "AC");
		   
		   String expected = testSippCorrect.toString();
		   String actual = carSipp.get(0).toString();
		   assertEquals(expected, actual);
		   
		   expected = testSippWrong.toString();
		   actual = carSipp.get(0).toString();
		   assertNotEquals(expected, actual);
	   }
	   
	   @Test
	   public void ratingBreakdownTest(){
		   //test vehicles
		   Vehicle testVehicle1 = new Vehicle();
		   testVehicle1.setName("Test Car 1");
		   testVehicle1.setPrice(200.00);
		   testVehicle1.setSupplier("TheTester");
		   testVehicle1.setRating(5.00);
		   testVehicle1.setSipp("ECMR");
		   testVehicle1.setCombinedRating(0);
		   testVehicle1.setBreakdownRating(0);
		   
		   Vehicle testVehicle2 = new Vehicle();
		   testVehicle2.setName("Test Car 2");
		   testVehicle2.setPrice(19.00);
		   testVehicle2.setSupplier("TheTester");
		   testVehicle2.setRating(3.00);
		   testVehicle2.setSipp("ECAR");
		   testVehicle2.setCombinedRating(0);
		   testVehicle2.setBreakdownRating(0);
		   
		   List<Vehicle> aCarList = new ArrayList<Vehicle>();
		   aCarList.add(testVehicle1);
		   aCarList.add(testVehicle2);
		   
		   // Car 1 Sipp = ECMR M = 1, R = 2 expected = 3 
		   // Car 2 Sipp = ECAR A = 5, R = 2 expected = 7
		   // Remember cars returned in descending order vehicle 1 index 1 vehicle 2 index 0
		   List<Vehicle> xyz = Analysis.ratingBreakdownDescending(aCarList);
		   
		   // Car 2
		   int expectedBreakdown = xyz.get(0).getBreakdownRating();
		   assertEquals(7, expectedBreakdown);
		   
		   //Car 1
		   expectedBreakdown = xyz.get(1).getBreakdownRating();
		   assertEquals(3, expectedBreakdown);
	   }
	   
	   @Test
	   public void ratingBreakdownDescendingTest(){
		   List<Vehicle> tempList = Analysis.ratingBreakdownDescending(VehicleList);
		// loop through list and check that next vehicle has lower rating
			 for (int i=0; i < tempList.size()-1; i++){
				 if (tempList.get(i).getCombinedRating() < tempList.get(i+1).getCombinedRating()){
					 assertTrue(false);
				 }
			 }
			 assertTrue(true);
		   
	   }
	   

}
