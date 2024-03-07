package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import app.Booking;
import app.Controller;
import app.Data;
import app.Flight;
import exceptions.*;

import java.io.FileNotFoundException;

/**
 * unit test for the Controller class
 */
public class TestController {
	
	/*
	 * tests whether the Controller calculateFee method returns a correct value for the excess baggage fee
	*/
	@Test
	public void assertEqualCalculateFee() throws FileNotFoundException, InvalidBookingReference {
		Data readData = new Data("src/bookings.csv","src/flights.csv");
		Controller ctrl = readData.read();
		
		Flight flight = new Flight("Edinburgh","BA",50,30,210,"BA-301");
		assertEquals(Math.round(ctrl.calculateFee(45, 195, flight)),2);
		assertEquals(Math.round(ctrl.calculateFee(15, 310, flight)),2200);
		assertEquals(Math.round(ctrl.calculateFee(15, 100, flight)),0);
	}
	
	/*
	 * tests whether the Controller getBooking method returns a Booking object
	*/
	@Test
	public void assertReturnTypeIsBooking() throws FileNotFoundException, InvalidBookingReference {
		Data readData = new Data("src/bookings.csv","src/flights.csv");
		Controller ctrl = readData.read();
		assertEquals(ctrl.getBooking("Mahad", "PK-201").getClass(),Booking.class);
	}
	
	/*
	 * tests whether the Data read methods throws a BookingNotFound
	 * when provided with a booking that does not exist
	*/
	@Test
	public void assertCheckInThrowsBookingNotFound() throws FileNotFoundException, InvalidBookingReference {
		Data readData = new Data("src/bookings.csv","src/flights.csv");
		Controller ctrl = readData.read();
		assertThrows(BookingNotFound.class, () ->  {
			ctrl.checkIn("Abc", "PK-110", 10, 10);
		});
	}
 
  }
