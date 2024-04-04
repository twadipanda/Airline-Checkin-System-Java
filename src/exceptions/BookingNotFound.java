package exceptions;

public class BookingNotFound extends Exception {
  public BookingNotFound(String errorMessage) {
    super(errorMessage);
  }
}
