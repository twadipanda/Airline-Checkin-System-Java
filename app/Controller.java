package app;

import java.util.HashMap;
import java.util.ArrayList;
import exceptions.*;

public class Controller {
  private HashMap<String, Booking> bookings;
  private ArrayList<Flight> flights;

  public Controller(HashMap<String, Booking> bookings, ArrayList<Flight> flights) {
    this.bookings = bookings;
    this.flights = flights;
  }

  public void checkIn(String lastName, String bookingRef, float weight, float volume) throws CheckInFailed, BookingNotFound, InvalidBookingReference {
    try {
      float fee;
      Booking booking = bookings.get(lastName+bookingRef);
      if (booking == null) {
        throw new BookingNotFound("Booking with last name " + lastName + " and reference " + bookingRef + " not found...");
      }
      booking.checkIn();
      Flight flight = booking.getFlight();
      fee = calculateFee(weight, volume, flight);
      flight.incrementPassengers(1);
      flight.incrementFee(fee);
      flight.incrementWeight(weight);
      flight.incrementVolume(volume);
    }
    catch (BookingNotFound e) {
      throw e;
    }
    catch (Exception e) {
      throw e;
    }
  }

  public float calculateFee(float weight, float volume, Flight flight) {
    float fee = 0;
    if (weight > flight.getMaxBaggageWeight()) {
      fee += Math.pow((1 + 0.05), weight - flight.getMaxBaggageWeight());
    }
    if (volume > flight.getMaxBaggageVolume()) {
      fee += Math.pow((1 + 0.08), volume - flight.getMaxBaggageVolume());
    }
    return fee;
  }

  public Booking getBooking(String lastName, String bookingRef) {
    return bookings.get(lastName+bookingRef);
  }

  public void updateFlight(Flight flight, float weight, float volume, float fee) {
    flight.incrementFee(fee);
    flight.incrementWeight(weight);
    flight.incrementVolume(volume);
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

}
