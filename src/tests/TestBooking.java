package tests;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import app.Controller;
import app.Data;
import exceptions.*;
import models.Booking;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class contains JUnit test cases for the Booking-related functionality.
 */
public class TestBooking {
 
  @Test
  public void assertThrowsFileNotFoundException() {
    assertThrows(FileNotFoundException.class, () -> {
      Data data = new Data("etc.csv", "etc.csv");
      data.read();
    });
 }

 @Test
  public void assertThrowsInvalidBookingReference() {
    assertThrows(InvalidBookingReference.class, () -> {
      Data data = new Data("invalid_bookings.csv", "flights.csv");
      data.read();
    });
 }

 @Test
  public void assertReturnTypeIsController() throws InvalidBookingReference, IOException {
    Data data = new Data("bookings.csv", "flights.csv");
    assertEquals(data.read().getClass(), Controller.class);
 }
}
