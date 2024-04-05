package observers;

import models.Passenger;

/**
 * The PassengerObserver interface represents an observer interested in changes to passenger information.
 * Classes implementing this interface can be notified when a passenger is added, removed, or updated.
 */
public interface PassengerObserver {
	
	/**
     * Notifies the observer that a passenger has been added.
     * 
     * @param passenger The passenger that has been added.
     */
	void addPassenger(Passenger passenger);
	
	/**
     * Notifies the observer that a passenger has been removed.
     * 
     * @param passenger The passenger that has been removed.
     */
	void removePassenger(Passenger passenger);
	
	/**
     * Notifies the observer that a passenger has been updated.
     * 
     * @param passenger The passenger that has been updated.
     */
	void updatePassenger(Passenger passenger);
}
