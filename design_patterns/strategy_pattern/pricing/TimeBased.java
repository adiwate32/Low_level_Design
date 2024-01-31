package design_patterns.strategy_pattern.pricing;

public class TimeBased implements PricingStrategy
{
    @Override
    public Double calculatePrice(RideDetails rideDetails)
    {
        return BASE_FARE + PER_MINUTE_RATE * rideDetails.getDuration();
    }
}