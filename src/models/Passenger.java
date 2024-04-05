package models;
import java.util.Random;

/**
 * The Passenger class represents a passenger in the airline system.
 * It holds information about the passenger, including their booking reference,
 * first name, last name, flight code, total baggage weight, and total baggage volume.
 */
public class Passenger {
  private String bookingReference;
  private String firstName;
  private String lastName;
  private String flightCode;
  private float totalBaggageWeight;
  private float totalBaggageVolume;

  /**
   * Constructs a Passenger object with the specified parameters.
   * 
   * @param bookingReference The booking reference of the passenger.
   * @param firstName The first name of the passenger.
   * @param lastName The last name of the passenger.
   * @param flightCode The flight code of the flight the passenger is booked on.
   */
  public Passenger(String bookingReference, String firstName,
  String lastName, String flightCode) {
    Random rand = new Random();
    this.bookingReference = bookingReference;
    this.firstName = firstName;
    this.lastName = lastName;
    this.flightCode = flightCode;
    this.totalBaggageWeight = rand.nextFloat(500);;
    this.totalBaggageVolume = rand.nextFloat(500);;
  }

  public String getFirstName() {
      return firstName;
  }

  public String getLastName() {
      return lastName;
  }

  public String getBookingReference() {
      return bookingReference;
  }

  public String getFlightCode() {
      return flightCode;
  }

  public float getTotalBaggageVolume() {
      return totalBaggageVolume;
  }

  public float getTotalBaggageWeight() {
      return totalBaggageWeight;
  }
}
