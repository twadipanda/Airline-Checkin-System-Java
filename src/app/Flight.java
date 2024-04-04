package app;

import app.Enums.FlightClass;

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



