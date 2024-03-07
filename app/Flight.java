package app;

import exceptions.InvalidBookingReference;

/**
 * Flight
 * Manages Flight Details
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

  public Flight(String destinationAirport, String carrier, int passengerCapacity, int maxBaggageWeight, int maxBaggageVolume, String flightCode) {
    this.destinationAirport = destinationAirport;
    this.passengerCapacity = passengerCapacity;
    this.maxBaggageWeight = maxBaggageWeight;
    this.maxBaggageVolume = maxBaggageVolume;
    this.flightCode = flightCode;
    this.carrier = carrier;
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

  /**
   * incrementPassengers
   * increments the total number of passengers checked in
   * @return float
   */
  public int incrementPassengers(int value) {
    this.numberOfPassengers += value;
    return this.numberOfPassengers;
  }

  /**
   * incrementFee
   * increments the total excess baggage feess collected
   * @return float
   */
  public float incrementFee(float value) {
    this.totalFee += value;
    return this.totalFee;
  }

  /**
   * incrementWeight
   * increments the total weight
   * @return float
   */
  public float incrementWeight(float value) {
    totalBaggageWeight += value;
    return totalBaggageWeight;
  }

  /**
   * incrementVolume
   * increments the total volume
   * @return float
   */
  public float incrementVolume(float value) {
    totalBaggageVolume += value;
    return totalBaggageVolume;
  }

  public String getCode() {
    return flightCode; 
  }
}



