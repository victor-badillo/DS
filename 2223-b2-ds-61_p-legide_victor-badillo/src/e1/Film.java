package e1;

import java.util.ArrayList;

public class Film{
    private String title;
    private int revenue;

    private ArrayList<Crew> filmList;

    public Film(String title, int revenue){
        this.setTitle(title);
        this.setRevenue(revenue);
         filmList = new ArrayList<>();      //create a list for this film
    }

    public <T extends Crew> void addWorker(T member){
        filmList.add(member);
    }

    public String getTitle(){
        return this.title;
    }
    public void setTitle(String title){
        this.title=title;
    }


    public int getRevenue(){
        return this.revenue;
    }

    public void setRevenue(int revenue){
        this.revenue=revenue;
    }
    public ArrayList<Crew> getFilmList(){
        return this.filmList;
    }

    public String printSalaries(){

        String str="";
        double totalPayroll=0;
        double amount;

        if(this.getFilmList().size() > 0) {
            for (Crew i : this.getFilmList()) {
                str = str.concat(i.toString());     //concatenate the stats of each member of the crew
                amount = i.calculatePayment() + i.getRoyalties() * getRevenue();        //calculate earnings
                str = str.concat(amount + " euro\n");
                totalPayroll += i.calculatePayment() + i.getRoyalties() * getRevenue();     //sum of all crew members salary
            }
            return str + "The total payroll for " + this.getTitle() + " is " + totalPayroll + " euro\n";
        }else{
            return "There are no crew members for movie: "+this.getTitle()+"\n";
        }
    }

    public String printRoyalties(){
        String str="";
        if(this.getFilmList().size() > 0){
            for (Crew i : this.getFilmList()) {
                if (!(i.getCategory().equals("Actor")) && !(i.getCategory().equals("Dubber")) && !(i.getCategory().equals("Stunt performer")))
                    str = str.concat(i.toString() + i.getRoyalties() * this.getRevenue() + " euros\n");
            }
            return str;
        }else{
            return "There are no crew members for movie: "+this.getTitle()+"\n";
        }
    }

}
