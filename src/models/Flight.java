package models;

import constants_and_singleton_classes.Enums.FlightClass;

/**
 * The Flight class represents a flight with various properties such as destination airport, carrier,
 * passenger capacity, baggage limits, flight code, class, departure time, and more.
 */
public class Flight {
  private String destinationAirport;
  private String carrier;
  private int passengerCapacity;
  private int maxBaggageWeight; // For a single passenger
  private int maxBaggageVolume;
  private String flightCode;
  private int numberOfPassengers;
  private float totalBaggageWeight;
  private float totalBaggageVolume;
  private float totalFee;
  private FlightClass class_;
  private int departureTime;
  private boolean departed = false;

  /**
   * Constructs a Flight object with the specified properties.
   * 
   * @param destinationAirport The destination airport of the flight.
   * @param carrier The carrier or airline operating the flight.
   * @param passengerCapacity The maximum number of passengers the flight can accommodate.
   * @param maxBaggageWeight The maximum baggage weight allowed per passenger.
   * @param maxBaggageVolume The maximum baggage volume allowed per passenger.
   * @param flightCode The unique code or identifier for the flight.
   * @param class_ The class of the flight, which can be LAST_MINUTE or STANDARD.
   * @param departureTime The departure time of the flight.
   */
  public Flight(String destinationAirport, String carrier, int passengerCapacity, int maxBaggageWeight, int maxBaggageVolume, String flightCode, FlightClass class_, int departureTime) {
    this.destinationAirport = destinationAirport;
    this.passengerCapacity = passengerCapacity;
    this.maxBaggageWeight = maxBaggageWeight;
    this.maxBaggageVolume = maxBaggageVolume;
    this.flightCode = flightCode;
    this.carrier = carrier;
    this.class_ = class_;
    this.departureTime = departureTime;
  }

  public int getPassengers() {
    return this.numberOfPassengers;
  }

  public float getWeight() {
    return totalBaggageWeight;
  }

  public float getMaxBaggageWeight() {
    return maxBaggageWeight;
  }

  public float getVolume() {
    return totalBaggageVolume;
  }

  public float getMaxBaggageVolume() {
    return maxBaggageVolume;
  }

  public float getFee() {
    return totalFee;
  }

  public float getCapacity() {
    return passengerCapacity;
  }

  public int incrementPassengers(int value) {
    this.numberOfPassengers += value;
    return this.numberOfPassengers;
  }

  public float incrementFee(float value) {
    this.totalFee += value;
    return this.totalFee;
  }

  public float incrementWeight(float value) {
    totalBaggageWeight += value;
    return totalBaggageWeight;
  }

  public float incrementVolume(float value) {
    totalBaggageVolume += value;
    return totalBaggageVolume;
  }

  public String getCode() {
    return flightCode; 
  }
  
  public String getDestinationAirport() {
	  return this.destinationAirport;
  }

  public FlightClass getClass_() {
      return class_;
  }
  public int getDepartureTime() {
      return departureTime;
  }
  
  public boolean getDeparted() {
	  return this.departed;
  }
  
  public void setDeparted() {
	  this.departed = true;
  }
}



