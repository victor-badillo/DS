package e2;

import java.util.*;

public class Personnel{

    private String personnelName;
    private Queue<Report> reportsQueue = new PriorityQueue<>();

    public Personnel(String name){
        this.reportsQueue= new PriorityQueue<>(reportComparator); //Use the reportComparator we implemented to create the Personnel queue
        this.personnelName=name;
    }

    public String getPersonnelName(){return personnelName;}

    public Queue<Report> getReportsQueue(){return reportsQueue;}

    public String getAllReports(){
        boolean allRedsPrinted=false;
        StringBuilder reports = new StringBuilder("Alerts of " + getPersonnelName() + "\n\nRED Alerts:\n");//String to concatenate
        List<Report> processed = new ArrayList<>(); //List which will store the elements of the queue temporarily so that we can iterate over them and return a single string with all of them

        while(!reportsQueue.isEmpty()){
            processed.add(reportsQueue.poll()); //Add objects to a list to iterate over them and return them, and poll them from the queue at the same time
        }
        for(Report report : processed){
            if (report.getAlertType() == 1){
                reports.append(report.getReportString());
            }
            else if (report.getAlertType() == 2){
                if (!allRedsPrinted){
                    reports.append("\nORANGE alerts:\n");
                    allRedsPrinted = true;
                }
                reports.append(report.getReportString());
            }
            reportsQueue.add(report); //Add back the reports in the queue
        }
        return reports.toString();
    }

    private static Comparator<Report> reportComparator = new Comparator<>(){

        public int compare(Report r1, Report r2){
            int alertTypeComparison = Integer.compare(r1.getAlertType(), r2.getAlertType()); //Compare alertType
            if (alertTypeComparison != 0){
                return alertTypeComparison;
            }
            int dateComparison = r1.getDate().compareTo(r2.getDate()); //Compare date
            if (dateComparison != 0){
                return dateComparison;
            }
            return r1.getTime().compareTo(r2.getTime()); //Compare time
        }
    };
}

