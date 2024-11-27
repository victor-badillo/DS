package e2;


import java.util.ArrayList;
import java.util.List;

public class Tank {
    private String name, location;
    private ControlDevice controlDevices;
    private List<Sensor> sensors = new ArrayList<>();

    public Tank(String name, String location){
        this.name = name;
        this.location = location;
        this.controlDevices = new ControlDevice(this);
    }

    public String getName(){return name;}
    public String getLocation(){return location;}

    public ControlDevice getControlDevice() {
        return controlDevices;
    }

    public void addSensor(Sensor sensor){
        sensors.add(sensor);
        sensor.setTank(this);
    }

}




