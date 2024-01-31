package design_patterns.strategy_pattern.stock;

public class MovingAverage implements TradingIndicatorStrategy{
    @Override
    public TradingStrategyType supportsType() {
        return TradingStrategyType.MOVING_AVERAGES;
    }

    @Override
    public Double calculateIndicator(Stock stock) {
        return (stock.getPrice() + stock.getPreviousPrice()) / 2;
    }
}
