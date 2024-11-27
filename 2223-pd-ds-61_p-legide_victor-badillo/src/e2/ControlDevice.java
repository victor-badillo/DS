package e2;

public class ControlDevice{

    private Tank tank1;

    public ControlDevice(Tank tank){
        this.tank1 = tank;
    }

    public void fixSensor(Sensor sensor, double fix){
            sensor.setParameterLevel(fix,"00/00/0000", "00:00");
    }
}