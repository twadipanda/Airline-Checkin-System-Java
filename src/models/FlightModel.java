package models;

import observers.FlightObserver;
import subjects.FlightSubject;

/**
 * The FlightModel class represents a model for the view component of a flight in the airline system.
 * It observes changes in flight status and notifies observers about updates.
 */
public class FlightModel implements FlightObserver, FlightSubject {
	
	FlightObserver observer; 
	Flight flight;
	
	/**
     * Constructs a FlightModel object with the specified flight.
     * 
     * @param flight The flight to be associated with this model.
     */
	public FlightModel(Flight flight) {
		this.flight = flight;
	}
	
	public Flight getFlight() {
		return this.flight;
	}
	

	@Override
	public void update(Flight flight) {
		if(this.flight.getCode() == flight.getCode())
			notifyFlightObservers(flight);
	}
	

	@Override
	public void addFlightObserver(FlightObserver observer) {
		this.observer = observer;
	}

	@Override
	public void notifyFlightObservers(Flight flight) {
		observer.update(flight);
	}

}
