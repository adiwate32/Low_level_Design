package design_patterns.strategy_pattern.pricing;

public interface PricingStrategy {

    static final double BASE_FARE = 5.0; // Base fare amount
    static final double PER_KILOMETER_RATE = 2.0; // Rate per kilometer
    static final double PER_MINUTE_RATE = 0.5; // Rate per minute
    static final double SURGE_MULTIPLIER = 2.0; // Surge pricing multiplier


    Double calculatePrice(RideDetails rideDetails);
}
