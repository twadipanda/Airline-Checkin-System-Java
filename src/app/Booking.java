package app;
import app.Enums.BookingClass;
import exceptions.InvalidBookingReference;

public class Booking {
  private String bookingReference;
  private String firstName;
  private String lastName;
  private Flight flight;
  private String flightCode;
  private boolean checkedIn = false;
  private BookingClass bClass;

  public Booking(String bookingReference, String firstName, String lastName, Flight flight, String flightCode, BookingClass bClass) {
    this.bookingReference = bookingReference;
    this.firstName = firstName;
    this.lastName = lastName;
    this.flight = flight;
    this.flightCode = flightCode;
    this.bClass = bClass;
  }

  public boolean checkIn() throws InvalidBookingReference {
    if (checkedIn) {
      throw new InvalidBookingReference("Booking with reference: " + bookingReference + " already checked in...");
    }
    checkedIn = true;
    return checkedIn;
  }

  public Flight getFlight() {
    return flight;
  }

  public String getReference() {
    return bookingReference;
  }

  public String getLName() {
    return lastName;
  }

  public String getFName() {
    return firstName;
  }

  public String getFlightCode() {
      return flightCode;
  }

  public BookingClass getbClass() {
      return bClass;
  }
}
