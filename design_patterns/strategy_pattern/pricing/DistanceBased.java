package design_patterns.strategy_pattern.pricing;

public class DistanceBased implements PricingStrategy
{
    @Override
    public Double calculatePrice(RideDetails rideDetails)
    {
        return BASE_FARE + PER_KILOMETER_RATE * rideDetails.getDistance();
    }
}
