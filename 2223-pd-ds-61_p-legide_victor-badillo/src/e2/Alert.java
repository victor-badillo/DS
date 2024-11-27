package e2;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Alert implements Observer{

    private boolean deactivate;
    private Range orangeRange;
    private Range redRange;

    private List<Personnel> personnelSubscribed = new ArrayList<>();

    public Alert(double orangeMin, double orangeMax, double redMin, double redMax) {
        this.orangeRange=new Range(orangeMin, orangeMax);
        this.redRange=new Range(redMin, redMax);
    }

    public void setDeactivated() {
        this.deactivate = true;
    }

    public void setActivated() {
        this.deactivate = false;
    }

    public void subscribePersonnel(Personnel p) {
        personnelSubscribed.add(p);
    }

    @Override
    public void update(Sensor sensor, String sensorName, String parameterName, double parameterLevel, String tankName, String tankLocation, Date date, Date time, Tank tank){
        if (!orangeRange.contains(parameterLevel)) {
            if (!redRange.contains(parameterLevel)) { //Alerta roja
                Report report1 = new Report(1, "RED", tankName, tankLocation, sensorName, parameterName, parameterLevel, date, time);
                for (Personnel p : personnelSubscribed) {
                    if (!deactivate) {
                        p.getReportsQueue().add(report1);
                    }
                }
                tank.getControlDevice().fixSensor(sensor,(orangeRange.max()+ orangeRange.min())/2);
            }
            else { //Alerta naranja
                Report report2 = new Report(2, "ORANGE", tankName, tankLocation, sensorName, parameterName, parameterLevel, date, time);
                for (Personnel p : personnelSubscribed) {
                    if (!deactivate) {
                        p.getReportsQueue().add(report2);
                    }
                    tank.getControlDevice().fixSensor(sensor,(orangeRange.max()+ orangeRange.min())/2);
                }
            }
        }
        //No hay alerta
    }

}
