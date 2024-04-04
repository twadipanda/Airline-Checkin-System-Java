package app;
import java.util.Random;

public class Passenger {
  private String bookingReference;
  private String firstName;
  private String lastName;
  private String flightCode;
  private float totalBaggageWeight;
  private float totalBaggageVolume;

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
