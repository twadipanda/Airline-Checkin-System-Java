package tests;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import app.Booking;
import app.Flight;
import exceptions.*;
import java.util.ArrayList;
import java.util.HashMap;

public class TestBooking {
  Booking booking = null;
  @BeforeEach
  void init() throws InvalidBookingReference {
    booking = new Booking("PK-201", "Muhammad", "Mahad", new Flight("LIA", "PIA", 30, 30, 30, "PK-201"), "PK-201");
  }

  @Test
  public void assertThrowsInvalidBookingReference() throws InvalidBookingReference {
    booking.checkIn();
    assertThrows(InvalidBookingReference.class, () -> {
      booking.checkIn();
    });
 }

 @Test
  public void assertCheckInIsTrue() throws InvalidBookingReference {
    assertEquals(true, booking.checkIn());
 }

}
