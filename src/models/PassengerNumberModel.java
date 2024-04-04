

package models;

import app.PassengerQueueController;
import observers.PassengerNumberObserver;
import observers.PassengerObserver;

/**
 * The PassengerNumberModel class represents a model that tracks the number of passengers in the system.
 * It observes changes in the passenger queue and updates the count accordingly.
 */
public class PassengerNumberModel implements PassengerObserver {
	PassengerNumberObserver observer;
	
	 /**
     * Constructs a PassengerNumberModel object with the specified PassengerQueueController.
     * 
     * @param passengerQueueController The PassengerQueueController to observe for changes in the passenger queue.
     */
	public PassengerNumberModel(PassengerQueueController passengerQueueController) {
		passengerQueueController.addPassengerObserver(this);
	}

	@Override
	public void addPassenger(Passenger passenger) {
		if(passenger != null)
			notifyObserver(1);
	}

	@Override
	public void removePassenger(Passenger passenger) {
		if(passenger != null)
			notifyObserver(-1);
	}

	@Override
	public void updatePassenger(Passenger passenger) {
		
	}
	
	public void attachObserver(PassengerNumberObserver observer) {
		  this.observer = observer;
	  }
	
	public void notifyObserver(int n) {
		observer.update(n);
	}

}
