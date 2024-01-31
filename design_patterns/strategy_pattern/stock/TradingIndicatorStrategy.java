package design_patterns.strategy_pattern.stock;

public interface TradingIndicatorStrategy {
    TradingStrategyType supportsType();

    Double calculateIndicator(Stock stock);
}
