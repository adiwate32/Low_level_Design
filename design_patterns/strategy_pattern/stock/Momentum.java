package design_patterns.strategy_pattern.stock;

public class Momentum implements TradingIndicatorStrategy{
    @Override
    public TradingStrategyType supportsType() {
        return TradingStrategyType.MOMENTUM;
    }

    @Override
    public Double calculateIndicator(Stock stock) {
        return stock.getPrice() - stock.getPreviousPrice();
    }
}
