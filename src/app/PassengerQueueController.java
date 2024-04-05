package app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

import constants_and_singleton_classes.Enums;
import constants_and_singleton_classes.Logger;
import constants_and_singleton_classes.SimulationControls;
import constants_and_singleton_classes.Enums.BookingClass;
import models.Booking;
import models.Passenger;
import observers.PassengerObserver;
import subjects.PassengerSubject;

/**
 * The PassengerQueueController class manages the addition of passengers to the passenger queue.
 * It implements the QueueInterface and the Runnable interface.
 * It also acts as a subject for passenger observers.
 */
public class PassengerQueueController implements QueueInterface, Runnable, PassengerSubject {
	
  private Queue<Passenger> passengerQueue;
  private HashMap<String, Booking> bookings;
  List<PassengerObserver> observers = new ArrayList<PassengerObserver>();
  private BookingClass class_;

  
  /**
   * Constructs a PassengerQueueController object with the specified parameters.
   * 
   * @param passengerQueue the queue of passengers to be managed
   * @param bookings the map of bookings associated with passengers
   * @param class_ the booking class of passengers to be added to the queue
   */
  public PassengerQueueController(Queue<Passenger> passengerQueue, HashMap<String, Booking> bookings, BookingClass class_) {
    this.passengerQueue = passengerQueue;
    this.bookings = bookings;
    this.class_ = class_;
  }

  /**
   * Adds passengers to the passenger queue based on their booking class.
   * 
   * @throws InterruptedException if the thread is interrupted while sleeping
   */
  @Override
  public synchronized void addPassengers() throws InterruptedException {
		    for (Booking booking : bookings.values()) {
		      if (booking.getbClass() == class_) {  
		    	  Passenger p = new Passenger(booking.getReference(), booking.getFName(), booking.getLName(), booking.getFlightCode());
		    	  passengerQueue.add(p);
		    	  notifyPassengerObservers(p);
		    	  Logger.getLogger().log(String.format("Added passenger with name %s %s and booking reference %s, booking class %s to queue", booking.getFName(), booking.getLName(), booking.getReference(), class_), Enums.Severity.INFO);
		    	  TimeUnit.SECONDS.sleep(SimulationControls.getInstance().adjustSpeed(2));
		      }
		    }
  }

  /**
   * The run method of the Runnable interface.
   * It starts the process of adding passengers to the queue.
   */
  public void run() {
    try {
      addPassengers();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
  
  /**
   * Adds a passenger observer to the passenger queue controller.
   * 
   * @param observer the passenger observer to be added
   */ 
	@Override
	public void addPassengerObserver(PassengerObserver observer) {
		this.observers.add(observer);
	}

	/**
     * Notifies all passenger observers with the given passenger object.
     * 
     * @param p the passenger object to be notified
     */
	@Override
	public void notifyPassengerObservers(Passenger p) {
		for(PassengerObserver o: observers) {
			o.addPassenger(p);
		}
	}
}
