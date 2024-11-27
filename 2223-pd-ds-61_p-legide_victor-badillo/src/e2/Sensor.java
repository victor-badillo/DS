package e2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Sensor extends Subject{

    private String sensorName, parameterName;
    private double parameterLevel;
    private Date date, time;
    private Tank tank;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

    public Sensor(String sensorName, String parameterName) { //Constructor

        this.sensorName = sensorName;
        this.parameterLevel = 0.0;
        this.parameterName = parameterName;
    }

    public Date getDate(){return this.date;}

    public Date getTime(){return this.time;}

    public String getTankName(){ return tank.getName();}

    public String getTankLocation(){return tank.getLocation();}

    public String getSensorName(){ return sensorName;}

    public double getParameterLevel() {
        return parameterLevel;
    }

    public Tank getTank(){
        return tank;
    }

    public String getParameterName() {
        return parameterName;
    }

    public void setParameterLevel(double value, String date, String time) {
        this.parameterLevel=value;
        try {
            this.date = dateFormat.parse(date);
            this.time = timeFormat.parse(time);
        }
        catch (ParseException e) {//If the date is not in correct format
            throw new IllegalArgumentException();
        }
        notifyObservers(this, getSensorName(), getParameterName(), getParameterLevel(), getTankName(), getTankLocation(), getDate(), getTime(), getTank());
    }

    public void setTank(Tank tank) {
        this.tank = tank;
    }

}
