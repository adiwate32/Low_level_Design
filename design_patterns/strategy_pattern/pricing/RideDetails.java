package design_patterns.strategy_pattern.pricing;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RideDetails {
    private double distance; // Distance in kilometers
    private int duration; // Duration in minutes
}
