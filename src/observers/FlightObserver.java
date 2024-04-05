package observers;

import models.Flight;

/**
 * The FlightObserver interface represents an observer interested in changes to flight information.
 * Classes implementing this interface can be notified when a flight is updated.
 */
public interface FlightObserver {
	
	 /**
     * Notifies the observer that a flight has been updated.
     * 
     * @param flight The flight that has been updated.
     */
	void update(Flight flight);
}
