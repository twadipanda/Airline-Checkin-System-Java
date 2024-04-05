package app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

import constants_and_singleton_classes.Enums;
import constants_and_singleton_classes.FeeController;
import constants_and_singleton_classes.Logger;
import constants_and_singleton_classes.SimulationControls;
import exceptions.InvalidBookingReference;
import models.Booking;
import models.Flight;
import models.Passenger;
import observers.FlightObserver;
import observers.PassengerObserver;
import subjects.FlightSubject;
import subjects.PassengerSubject;

/**
 * The CheckInController class manages the check-in process for passengers.
 * It implements the Runnable interface to enable concurrent execution.
 * It also acts as a subject for passenger and flight observers.
 */

public class CheckInController implements Runnable, PassengerSubject, FlightSubject {
  private Queue<Passenger> passengerQueue;
  private HashMap<String, Booking> bookings;
  List<PassengerObserver> passengerObservers = new ArrayList<PassengerObserver>();
  List<FlightObserver> flightObservers = new ArrayList<FlightObserver>();
  private int deskNumber;
  private long endTime;
  
  
  /**
   * Constructs a CheckInController object with the specified parameters.
   * 
   * @param passengerQueue the queue of passengers to be checked in
   * @param bookings the map of bookings associated with passengers
   * @param endTime the time when the check-in process ends
   */
  public CheckInController(Queue<Passenger> passengerQueue, HashMap<String, Booking> bookings, long endTime) {
    this.passengerQueue = passengerQueue;
    this.bookings = bookings;
    this.endTime = endTime;
  }

  /**
   * Initiates the check-in process.
   */
  public synchronized void checkIn() {
    while(System.nanoTime() < System.nanoTime() + TimeUnit.NANOSECONDS.convert(1L, TimeUnit.MINUTES)) {
      Passenger passenger = passengerQueue.poll();
      if (passenger == null) {
        notifyPassengerObservers(passenger);
        try {
          TimeUnit.SECONDS.sleep(SimulationControls.getInstance().adjustSpeed(4));
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      } else {
        float fee;
        Booking booking = bookings.get(passenger.getLastName()+passenger.getBookingReference());
        try {
          booking.checkIn();
        } catch (InvalidBookingReference e) {
          e.printStackTrace();
        }
        notifyPassengerObservers(passenger);
        try {
            TimeUnit.SECONDS.sleep(SimulationControls.getInstance().adjustSpeed(5));
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
             
        Flight flight = booking.getFlight();
        fee = FeeController.calculateFee(passenger.getTotalBaggageWeight(), passenger.getTotalBaggageVolume(), flight);
        
        flight.incrementPassengers(1);
        flight.incrementFee(fee);
        flight.incrementWeight(passenger.getTotalBaggageWeight());
        flight.incrementVolume(passenger.getTotalBaggageVolume());
        notifyFlightObservers(flight);
        Logger.getLogger().log(String.format("Passenger with name %s %s and booking reference %s checked into flight %s with a fee of %f", passenger.getFirstName(), passenger.getLastName(), passenger.getBookingReference(), passenger.getFlightCode(), fee), Enums.Severity.INFO);
      }
    }
  }
  
  /**
   * The run method of the Runnable interface.
   * It starts the check-in process.
   */
  public void run() {
	  checkIn();
  }
  
  /**
   * Stops the current thread.
   */
  public void stopThread() {
	  Thread.currentThread().interrupt();
  }
  
  /**
   * Sets the desk number for the check-in controller.
   * 
   * @param n the desk number to be set
   */
  void setDeskNumber(int n) {
	  this.deskNumber = n;
  }
  
  /**
   * Retrieves the desk number of the check-in controller.
   * 
   * @return the desk number
   */
  int getDeskNumber() {
	  return this.deskNumber;
  }
  
  /**
   * Adds a passenger observer to the check-in controller.
   * 
   * @param observer the passenger observer to be added
   */
  public void addPassengerObserver(PassengerObserver observer) {
	  this.passengerObservers.add(observer);
  }
  
  /**
   * Adds a flight observer to the check-in controller.
   * 
   * @param observer the flight observer to be added
   */
  public void addFlightObserver(FlightObserver observer) {
	  this.flightObservers.add(observer);
  }

  /**
   * Notifies all passenger observers with the given passenger object.
   * 
   * @param passenger the passenger object to be notified
   */
	public void notifyPassengerObservers(Passenger p) {
		for(PassengerObserver o: passengerObservers) {
			o.updatePassenger(p);
			o.removePassenger(p);
		}
	}
	
	/**
     * Notifies all flight observers with the given flight object.
     * 
     * @param flight the flight object to be notified
     */
	public void notifyFlightObservers(Flight flight) {
		for(FlightObserver f: flightObservers) {
			f.update(flight);
		}
	}
	
	/**
     * Retrieves the map of bookings associated with passengers.
     * 
     * @return the map of bookings
     */
	public HashMap<String, Booking> getBookings(){
		return this.bookings;
	}
}
