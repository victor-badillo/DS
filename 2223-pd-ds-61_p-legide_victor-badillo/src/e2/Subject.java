package e2;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Subject {
    private List<Observer> observers = new ArrayList<>();
    public void attach(Observer o){observers.add(o);}
    public void detach(Observer o){observers.remove(o); }
    public void notifyObservers(Sensor sensor, String sensorName, String parameterName, double parameterLevel, String tankName, String tankLocation, Date date, Date time, Tank tank){
        for (Observer o : observers)
            o.update(sensor, sensorName, parameterName, parameterLevel, tankName, tankLocation, date, time, tank);
    }
    public List<Observer> getObservers(){return observers;}//Function needed to see if attach or detach work in the tests
}
