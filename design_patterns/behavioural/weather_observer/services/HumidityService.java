package design_patterns.behavioural.weather_observer.services;

import design_patterns.behavioural.weather_observer.Observer;
import design_patterns.behavioural.weather_observer.Publisher;
import design_patterns.behavioural.weather_observer.utils.NotificationUtils;

public class HumidityService implements Observer {

    public HumidityService ()
    {
        Publisher publisher = Publisher.getInstance();
        publisher.addObserver(this);
    }

    @Override
    public void notifyObserver(double value) {
        NotificationUtils.sendNotification("New value is " + value);
    }
}
