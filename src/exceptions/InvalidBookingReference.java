package exceptions;

public class InvalidBookingReference extends Exception {
  public InvalidBookingReference(String errorMessage) {
    super(errorMessage);
  }
}
