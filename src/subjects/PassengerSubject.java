package subjects;

import models.Passenger;
import observers.PassengerObserver;

/**
 * The PassengerSubject interface represents a subject that manages observers interested in passenger-related events.
 * Classes implementing this interface can register observers and notify them of changes related to passengers.
 */
public interface PassengerSubject {
	
	/**
     * Adds a passenger observer to the list of observers.
     * 
     * @param observer The passenger observer to be added.
     */
	public void addPassengerObserver(PassengerObserver observer);
	
	/**
     * Notifies all registered passenger observers of a change related to the specified passenger.
     * 
     * @param p The passenger for which the observers are being notified.
     */
	public void notifyPassengerObservers(Passenger p);
}
