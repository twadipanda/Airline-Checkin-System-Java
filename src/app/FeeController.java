package app;

public class FeeController {
  public static float calculateFee(float weight, float volume, Flight flight) {
    float fee = 0;
    if (weight > flight.getMaxBaggageWeight()) {
      fee += Math.pow((1 + 0.05), weight - flight.getMaxBaggageWeight());
    }
    if (volume > flight.getMaxBaggageVolume()) {
      fee += Math.pow((1 + 0.08), volume - flight.getMaxBaggageVolume());
    }
    return fee;
  }
}
