package app;
import exceptions.InvalidBookingReference;
import java.util.regex.Pattern;

/*
 * Booking class that manages booking details
 * */
public class Booking {
  private String bookingReference;
  private String firstName;
  private String lastName;
  private Flight flight;
  private String flightCode;
  private boolean checkedIn = false;

  /**
   * Booking Constructor
   * @exception InvalidBookingReference
   */
  public Booking(String bookingReference, String firstName, String lastName, Flight flight, String flightCode) throws InvalidBookingReference {
    Pattern pattern = Pattern.compile("^[A-Z]{2}-\\d{3}$");
    if (!pattern.matcher(bookingReference).find()) {
      throw new InvalidBookingReference(bookingReference + ": is an invalid booking referene.");
    }

    this.bookingReference = bookingReference;
    this.firstName = firstName;
    this.lastName = lastName;
    this.flight = flight;
    this.flightCode = flightCode;
  }


  /**
   * checkIn
   * checks in a particular booking
   * @exception InvalidBookingReference
   * @return boolean
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

}
