package design_patterns.strategy_pattern.pricing;

public class PricingManager {


    private PricingStrategy pricingStrategy;


    public PricingManager(PricingStrategy pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
    }

    public Double calculatePrice(RideDetails rideDetails) {
        return pricingStrategy.calculatePrice(rideDetails);
    }

}
