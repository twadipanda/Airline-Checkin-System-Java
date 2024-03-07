package tests;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import app.Controller;
import app.Data;
import app.Flight;
import exceptions.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * unit test for the Flight class
 */
public class TestFlight {
 
	/*
	 * tests whether the Data read methods throws a FileNotFoundException
	 * when provided with a file that does not exist
	*/
	@Test
	public void assertThrowsFileNotFoundException() {
		assertThrows(FileNotFoundException.class, () -> {
		Data data = new Data("etc.csv", "etc.csv");
		data.read();
    });
	}

	/*
	 * tests whether the Data read methods throws a InvalidBookingReference
	 * when provided with an invalid booking
	*/
	@Test
	public void assertThrowsInvalidBookingReference() {
		assertThrows(InvalidBookingReference.class, () -> {
		Data data = new Data("invalid_bookings.csv", "flights.csv");
		data.read();
		});
	}

	/*
	 * tests whether the Data read methods returns a Controller object
	*/
	@Test
	public void assertReturnTypeIsController() throws FileNotFoundException, InvalidBookingReference {
		Data data = new Data("bookings.csv", "flights.csv");
		assertEquals(data.read().getClass(), Controller.class);
	}
}
