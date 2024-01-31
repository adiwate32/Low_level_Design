package design_patterns.behavioural.weather_observer.services;

import design_patterns.behavioural.weather_observer.Observer;
import design_patterns.behavioural.weather_observer.Publisher;
import design_patterns.behavioural.weather_observer.utils.NotificationUtils;

public class TemperatureService implements Observer {

    private double temperatureThreshold;

    public TemperatureService (double temperatureThreshold)
    {
        this.temperatureThreshold = temperatureThreshold;
        Publisher publisher = Publisher.getInstance();
        publisher.addObserver(this);
    }

    @Override
    public void notifyObserver(double value) {
        if(value > temperatureThreshold)
        {
            NotificationUtils.sendNotification("New value is " + value);
        }
    }
}
