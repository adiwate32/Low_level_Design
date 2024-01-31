package design_patterns.strategy_pattern.pricing;

public class SurgeBased implements PricingStrategy{

    @Override
    public Double calculatePrice(RideDetails rideDetails) {
        return BASE_FARE * SURGE_MULTIPLIER;
    }
}
