package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import app.CheckInController;
import observers.PassengerObserver;
import subjects.PassengerSubject;

/**
 * The CheckInDeskModel class represents a check-in desk in the airline system.
 * It maintains a list of observers who are interested in updates about passengers
 * checking in at this desk.
 */
public class CheckInDeskModel implements PassengerObserver,PassengerSubject {
	List<PassengerObserver> observers = new ArrayList<PassengerObserver>();
	String deskName;
	private HashMap<String, Booking> bookings;
	Passenger passenger;
	
	 /**
     * Constructs a CheckInDeskModel object with the specified desk name and controller.
     * Registers itself as an observer with the provided controller.
     * 
     * @param deskName The name of the check-in desk.
     * @param controller The CheckInController responsible for managing check-in operations.
     */
	public CheckInDeskModel(String deskName, CheckInController controller) {
		this.deskName = deskName;
		controller.addPassengerObserver(this);
		this.bookings = controller.getBookings();
	}
	

	public String getDeskName() {
		return this.deskName;
	}

	@Override
	public void updatePassenger(Passenger passenger) {
		this.passenger = passenger;
		notifyPassengerObservers(passenger);
	}
	
	public Flight getPassengerFlight() {
		return bookings.get(this.passenger.getLastName()+passenger.getBookingReference()).getFlight();
	}

	@Override
	public void addPassenger(Passenger passenger) {
		
	}

	@Override
	public void removePassenger(Passenger passenger) {
		
	}

	@Override
	public void addPassengerObserver(PassengerObserver observer) {
		this.observers.add(observer);
	}

	@Override
	public void notifyPassengerObservers(Passenger p) {
		for(PassengerObserver o: observers) {
			o.updatePassenger(p);
		}
		
	}

}
