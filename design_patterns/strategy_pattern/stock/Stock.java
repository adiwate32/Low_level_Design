package design_patterns.strategy_pattern.stock;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Stock {
    private Double price;
    private Double previousPrice;
}
