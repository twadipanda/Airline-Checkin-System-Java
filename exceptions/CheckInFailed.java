package exceptions;

public class CheckInFailed extends Exception {
  public CheckInFailed(String errorMessage) {
    super(errorMessage);
  }
}
