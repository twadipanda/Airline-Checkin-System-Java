package constants_and_singleton_classes;

/**
 * The Enums class defines several enums used in the application.
 * Enums include severity levels, booking classes, and flight classes.
 */

public class Enums {
	
	/**
	   * The Severity enum represents different levels of severity for log messages.
	   */
  public static enum Severity {
    INFO,
    DEBUG,
    WARNING,
    ERROR,
    CRITICAL
  }

  /**
   * The Severity enum represents different levels of severity for log messages.
   */
  public static enum BookingClass {
    ECONOMY,
    BUISINESS,
    PRIORITY
  }

  /**
   * The FlightClass enum represents different classes of flights.
   */
  public static enum FlightClass {
    LAST_MINUTE,
    STANDARD
  }
}
