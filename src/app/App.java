package app;
import java.io.IOException;

import exceptions.BookingNotFound;
import exceptions.CheckInFailed;
import exceptions.InvalidBookingReference;

/**
 * The main class of the application.
 * <p>
 * This class contains the main method responsible for starting the application.
 * It initializes the {@link Manager} class, which controls the overall flow of the application.
 */

public class App {	
	
	/**
     * The main method of the application.
     * <p>
     * This method initializes the {@link Manager} class, which controls the overall flow of the application.
     * 
     * @param args the command-line arguments (not used)
     * @throws InvalidBookingReference if an invalid booking reference is encountered during initialization
     * @throws CheckInFailed if check-in process fails during initialization
     * @throws BookingNotFound if a booking is not found during initialization
     * @throws IOException if an I/O error occurs during initialization
     */
	
  public static void main(String[] args) throws InvalidBookingReference, CheckInFailed, BookingNotFound, IOException {
	  
	// Initialize the Manager class to start the application
	  
	  new Manager();
  }
}
