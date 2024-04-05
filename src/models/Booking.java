package models;
import constants_and_singleton_classes.Enums.BookingClass;
import exceptions.InvalidBookingReference;

/**
 * The Booking class represents a booking made by a passenger for a flight.
 */
public class Booking {
  private String bookingReference;
  private String firstName;
  private String lastName;
  private Flight flight;
  private String flightCode;
  private boolean checkedIn = false;
  private BookingClass bClass;

  /**
   * Constructs a new Booking object with the specified parameters.
   *
   * @param bookingReference The reference code for the booking.
   * @param firstName The first name of the passenger.
   * @param lastName The last name of the passenger.
   * @param flight The flight associated with the booking.
   * @param flightCode The code of the flight.
   * @param bClass The booking class of the passenger.
   */
  public Booking(String bookingReference, String firstName, String lastName, Flight flight, String flightCode, BookingClass bClass) {
    this.bookingReference = bookingReference;
    this.firstName = firstName;
    this.lastName = lastName;
    this.flight = flight;
    this.flightCode = flightCode;
    this.bClass = bClass;
  }

  /**
   * Marks the booking as checked in.
   *
   * @return true if the booking was successfully checked in, false otherwise.
   * @throws InvalidBookingReference if the booking is already checked in.
   */
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
