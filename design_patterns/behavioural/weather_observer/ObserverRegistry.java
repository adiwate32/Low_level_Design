package design_patterns.behavioural.weather_observer;

public interface ObserverRegistry {
    void addObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObservers(double value);
}
