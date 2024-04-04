package app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

import app.Enums.BookingClass;
import exceptions.InvalidBookingReference;
import observers.FlightObserver;
import observers.PassengerObserver;

public class CheckInController implements Runnable, PassengerSubject, FlightSubject {
  private Queue<Passenger> passengerQueue;
  private HashMap<String, Booking> bookings;
  List<PassengerObserver> passengerObservers = new ArrayList<PassengerObserver>();
  List<FlightObserver> flightObservers = new ArrayList<FlightObserver>();
  private int n;
  private long endTime;
  private volatile boolean keepRunning=true;
  
  
  public CheckInController(Queue<Passenger> passengerQueue, HashMap<String, Booking> bookings, long endTime) {
    this.passengerQueue = passengerQueue;
    this.bookings = bookings;
    this.endTime = endTime;
  }

  public synchronized void checkIn() {
    while(System.nanoTime() < endTime) {
      Passenger passenger = passengerQueue.poll();
      if (passenger == null) {
        System.out.println("No more passengers left to serve...");
        notifyPassengerObservers(passenger);
        try {
          TimeUnit.SECONDS.sleep(SimulationControls.getInstance().adjustSpeed(4));
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      } else {
    	notifyPassengerObservers(passenger);
        System.out.println("Serving passenger: " + passenger.getFirstName() + " " + passenger.getLastName());
        float fee;
        Booking booking = bookings.get(passenger.getLastName()+passenger.getBookingReference());
        try {
          booking.checkIn();
        } catch (InvalidBookingReference e) {
          e.printStackTrace();
        }
        Flight flight = booking.getFlight();
        fee = FeeController.calculateFee(passenger.getTotalBaggageWeight(), passenger.getTotalBaggageVolume(), flight);
        System.out.println(String.format("Passenger with name %s %s and booking reference %s checked into flight %s with a fee of %f. Served by thread %s.", passenger.getFirstName(), passenger.getLastName(), passenger.getBookingReference(), passenger.getFlightCode(), fee, Thread.currentThread().getName()));
        Logger.getLogger().log(String.format("Passenger with name %s %s and booking reference %s checked into flight %s with a fee of %f", passenger.getFirstName(), passenger.getLastName(), passenger.getBookingReference(), passenger.getFlightCode(), fee), Enums.Severity.INFO);
        try {
          TimeUnit.SECONDS.sleep(SimulationControls.getInstance().adjustSpeed(5));
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        flight.incrementPassengers(1);
        flight.incrementFee(fee);
        flight.incrementWeight(passenger.getTotalBaggageWeight());
        flight.incrementVolume(passenger.getTotalBaggageVolume());
        notifyFlightObservers(flight);
      }
    }
  }
  
  public void run() {
		  checkIn();
  }
  
  public void stopThread() {
	  Thread.currentThread().interrupt();
  }
  
  void setN(int n) {
	  this.n = n;
  }
  
  int getN() {
	  return this.n;
  }
  
  public synchronized void addPassengerObserver(PassengerObserver observer) {
	  this.passengerObservers.add(observer);
  }
  
  public synchronized void addFlightObserver(FlightObserver observer) {
	  this.flightObservers.add(observer);
  }

	public synchronized void notifyPassengerObservers(Passenger p) {
		for(PassengerObserver o: passengerObservers) {
			o.updatePassenger(p);
			o.removePassenger(p);
		}
	}
	
	public synchronized void notifyFlightObservers(Flight flight) {
		for(FlightObserver f: flightObservers) {
			f.update(flight);
		}
	}
	
	public HashMap<String, Booking> getBookings(){
		return this.bookings;
	}
}
