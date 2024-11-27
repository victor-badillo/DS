package e2;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Report {
    private int alertType;
    private String alertTypeName, tankName, tankLocation, sensorName, parameterName;
    private double parameterLevel;
    private Date date, time;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); //date format
    private SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm"); //time format

    public Report(int alertType, String alertTypeName, String tankName, String tankLocation, String sensorName, String parameterName, double parameterLevel, Date date, Date time){
        this.alertType = alertType;
        this.alertTypeName = alertTypeName;
        this.tankName = tankName;
        this.tankLocation = tankLocation;
        this.sensorName = sensorName;
        this.parameterName = parameterName;
        this.parameterLevel = parameterLevel;
        this.date = date;
        this.time = time;
    }

    public String getReportString(){
        return ("* "+ alertTypeName + " alert\n" + tankName + ", " + tankLocation + "\n" + sensorName + ": " + parameterName + " level = " + parameterLevel + "\n" + dateFormat.format(date) + ", " + timeFormat.format(time) + "\n");
    }

    public int getAlertType(){return alertType;}

    public Date getDate(){ return date; }

    public Date getTime(){ return time; }

}
