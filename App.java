package app;

import java.io.FileNotFoundException;
import exceptions.*;

// Launch class of the Application
public class App {
	
	/**
     * Create the Data object to read the text files
     * Get Controller object from Data read method to access the data in the files
     * Create a Gui object and pass it the Controller object
     * 
     */
	public static void main(String[] args) throws FileNotFoundException, InvalidBookingReference, CheckInFailed, BookingNotFound {
		Data reader = new Data("src/bookings.csv", "src/flights.csv");
	    Controller controller = reader.read();
		Gui gui = new Gui(controller);
	}
}
