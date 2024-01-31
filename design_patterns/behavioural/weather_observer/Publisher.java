package design_patterns.behavioural.weather_observer;

import java.util.ArrayList;
import java.util.List;

public class Publisher implements ObserverRegistry{
    private List<Observer> observers = new ArrayList<>();
    private static Publisher publisher = null;

    public static Publisher getInstance()
    {
        if(publisher == null)
        {
            return new Publisher();
        }
        return publisher;
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers(double value) {
        observers.forEach(observer -> observer.notifyObserver(value));
    }
}
