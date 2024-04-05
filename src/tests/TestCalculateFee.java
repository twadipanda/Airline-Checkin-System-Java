package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import constants_and_singleton_classes.FeeController;
import constants_and_singleton_classes.Enums.FlightClass;
import models.Flight;


/**
 * This class contains JUnit test cases for the Fee-related calculation functionality.
 */
public class TestCalculateFee {
	
	    @Test
	    public void testCalculateFeeWithinLimits() {
	        Flight flight = new Flight("Destination", "Carrier", 100, 50, 100, "FL-001", FlightClass.STANDARD, 0);
	        float weight = 40;
	        float volume = 80;
	        float expectedFee = 0;
	        assertEquals(expectedFee, FeeController.calculateFee(weight, volume, flight), 0);
	    }

	    @Test
	    public void testCalculateFeeExceedingWeightLimit() {
	        Flight flight = new Flight("Destination", "Carrier", 100, 50, 100, "FL-001", FlightClass.STANDARD, 0);
	        float weight = 60;
	        float volume = 80;
	        float expectedFee = (float) Math.pow((1 + 0.05), weight - flight.getMaxBaggageWeight());
	        assertEquals(expectedFee, FeeController.calculateFee(weight, volume, flight), 0);
	    }

	    @Test
	    public void testCalculateFeeExceedingVolumeLimit() {
	        Flight flight = new Flight("Destination", "Carrier", 100, 50, 100, "FL-001", FlightClass.STANDARD, 0);
	        float weight = 40;
	        float volume = 120;
	        float expectedFee = (float) Math.pow((1 + 0.08), volume - flight.getMaxBaggageVolume());
	        assertEquals(expectedFee, FeeController.calculateFee(weight, volume, flight), 0);
	    }

	    @Test
	    public void testCalculateFeeExceedingBothLimits() {
	        Flight flight = new Flight("Destination", "Carrier", 100, 50, 100, "FL-001", FlightClass.STANDARD, 0);
	        float weight = 60;
	        float volume = 120;
	        float expectedFee = (float) (Math.pow((1 + 0.05), weight - flight.getMaxBaggageWeight())
	                + Math.pow((1 + 0.08), volume - flight.getMaxBaggageVolume()));
	        assertEquals(expectedFee, FeeController.calculateFee(weight, volume, flight), 0);
	    }

	
}
