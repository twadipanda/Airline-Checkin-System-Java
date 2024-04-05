package constants_and_singleton_classes;

import models.Flight;

/**
 * The FeeController class calculates baggage fees based on weight, volume, and flight parameters.
 */
public class FeeController {
	
	/**
	   * Calculates the baggage fee for a given weight, volume, and flight.
	   *
	   * @param weight The weight of the baggage.
	   * @param volume The volume of the baggage.
	   * @param flight The flight for which the baggage fee is being calculated.
	   * @return The calculated baggage fee.
	   */
  public static float calculateFee(float weight, float volume, Flight flight) {
    float fee = 0;
 // Calculate fee based on weight exceeding the maximum baggage weight allowed for the flight
    if (weight > flight.getMaxBaggageWeight()) {
      fee += Math.pow((1 + 0.05), weight - flight.getMaxBaggageWeight());
    }
 // Calculate fee based on volume exceeding the maximum baggage volume allowed for the flight
    if (volume > flight.getMaxBaggageVolume()) {
      fee += Math.pow((1 + 0.08), volume - flight.getMaxBaggageVolume());
    }
    return fee;
  }
}
