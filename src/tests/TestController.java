package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import app.Controller;
import app.Data;
import exceptions.*;
import models.Booking;

/**
 * unit test for the Controller class
 */
public class TestController {
	
	/*
	 * tests whether the Controller getBooking method returns a Booking object
	*/
	@Test
	public void assertReturnTypeIsBooking() throws InvalidBookingReference, IOException {
		Data readData = new Data("src/bookings.csv","src/flights.csv");
		Controller ctrl = readData.read();
		assertEquals(ctrl.getBooking("Mahad", "PK-201").getClass(),Booking.class);
	}
	 
  }
