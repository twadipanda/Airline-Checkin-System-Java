package tests;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import app.Data;
import app.Controller;
import exceptions.*;

/**
 * unit test for the Data class
 */
public class TestData {
	
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
      Data data = new Data("src/invalid_bookings.csv", "src/flights.csv");
      data.read();
    });
 }

 /*
	 * tests whether the Data read methods returns a Controller object
	*/
 @Test
  public void assertReturnTypeIsController() throws InvalidBookingReference, IOException {
    Data data = new Data("src/bookings.csv", "src/flights.csv");
    assertEquals(data.read().getClass(), Controller.class);
 }
}
