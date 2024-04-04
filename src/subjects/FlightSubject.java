package subjects;

import models.Flight;
import observers.FlightObserver;

/**
 * The FlightSubject interface represents a subject that manages observers interested in flight-related events.
 * Classes implementing this interface can register observers and notify them of changes related to flights.
 */
public interface FlightSubject {
	
	/**
     * Adds a flight observer to the list of observers.
     * 
     * @param observer The flight observer to be added.
     */
	public  void addFlightObserver(FlightObserver observer);
	
	 /**
     * Notifies all registered flight observers of a change related to the specified flight.
     * 
     * @param flight The flight for which the observers are being notified.
     */
	public void notifyFlightObservers(Flight flight);
}
