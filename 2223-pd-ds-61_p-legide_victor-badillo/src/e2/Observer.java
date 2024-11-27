package e2;

import java.util.Date;

public interface Observer{
    void update(Sensor sensor, String sensorName, String parameterName, double parameterLevel, String tankName, String tankLocation, Date date, Date time, Tank tank);
}