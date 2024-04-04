package app;

import java.util.HashMap;

import observers.FlightObserver;

import java.util.ArrayList;

public class Controller {
  private HashMap<String, Booking> bookings;
  private ArrayList<Flight> flights;

  public Controller(HashMap<String, Booking> bookings, ArrayList<Flight> flights) {
    this.bookings = bookings;
    this.flights = flights;
  }

  public Booking getBooking(String lastName, String bookingRef) {
    return bookings.get(lastName+bookingRef);
  }

  public void report() {
    String exceeded = "";
    System.out.println("Flight Code   |   Passengers Checked In   |   Total Baggage Weight   |   Total Baggage Volume   |   Excess Fee   |   Capacity Exceeded   ");
    for (Flight flight : flights) {
      if (flight.getWeight() > flight.getMaxBaggageWeight() * flight.getPassengers()) {
        exceeded += "T";
      }
      else {
        exceeded += "N";
      }
      if (flight.getVolume() > flight.getMaxBaggageVolume() * flight.getPassengers()) {
        exceeded += " T";
      }
      else {
        exceeded += " N";
      }
      System.out.println(flight.getCode() + "   |   " + flight.getPassengers() + "   |   " + flight.getWeight() + "   |   " + flight.getVolume() + "   |   " + flight.getFee() + "   |   " + exceeded);
      exceeded = "";
    }
  }

  public void addBooking(Booking booking) {
   bookings.put(booking.getLName()+booking.getReference(), booking); 
  }

  public void addFlight(Flight flight) {
    flights.add(flight);
  }

  public HashMap<String, Booking> getBookings() {
      return bookings;
  }
  
  public ArrayList<Flight> getFlights() {
	  return this.flights;
  }

}
