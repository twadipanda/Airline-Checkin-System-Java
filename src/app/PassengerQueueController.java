package app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

import app.Enums.BookingClass;
import observers.PassengerObserver;

public class PassengerQueueController implements QueueInterface, Runnable, PassengerSubject {
  private Queue<Passenger> passengerQueue;
  private HashMap<String, Booking> bookings;
  List<PassengerObserver> observers = new ArrayList<PassengerObserver>();
  private BookingClass class_;

  public PassengerQueueController(Queue<Passenger> passengerQueue, HashMap<String, Booking> bookings, BookingClass class_) {
    this.passengerQueue = passengerQueue;
    this.bookings = bookings;
    this.class_ = class_;
  }

  @Override
  public synchronized void addPassengers() throws InterruptedException {
		    for (Booking booking : bookings.values()) {
		      if (booking.getbClass() == class_) {  
		    	System.out.println("Adding Passenger to queue");
		        Passenger p = new Passenger(booking.getReference(), booking.getFName(), booking.getLName(), booking.getFlightCode());
		        passengerQueue.add(p);
		        notifyPassengerObservers(p);
		        Logger.getLogger().log(String.format("Added passenger with name %s %s and booking reference %s, booking class %s to queue", booking.getFName(), booking.getLName(), booking.getReference(), class_), Enums.Severity.INFO);
		        System.out.println(String.format("Added passenger with name %s %s and booking reference %s, booking class %s to queue", booking.getFName(), booking.getLName(), booking.getReference(), class_).toString());
		        TimeUnit.SECONDS.sleep(SimulationControls.getInstance().adjustSpeed(2));
		      }
		    }
  }

  public void run() {
    try {
      addPassengers();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
  
	 
	@Override
	public void addPassengerObserver(PassengerObserver observer) {
		this.observers.add(observer);
	}

	@Override
	public void notifyPassengerObservers(Passenger p) {
		for(PassengerObserver o: observers) {
			o.addPassenger(p);
		}
	}
}
