package tests;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

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

  Flight flight = null;
  @BeforeEach
  void init() {
    flight = new Flight("LIA", "PIA", 50, 40, 50, "PK-201");
  }
 
	@Test
	public void assertPassengerCountIsIncremented() {
		assertEquals(2, flight.incrementPassengers(2));
	}

	@Test
	public void assertWeightIsIncremented() {
		assertEquals(30, flight.incrementWeight(30));
	}
	@Test
	public void assertVolumeIsIncremented() {
		assertEquals(30, flight.incrementVolume(30));
	}

}
