package e1.Technicians;

import e1.Crew;

public class Director extends Crew {

    private int seniority;
    public Director(String name, String surname, String id, int telephone, String nationality, int hours, int seniority){
        super(name,surname,id,telephone,nationality,hours);
        if(seniority < 0){
            throw new IllegalArgumentException();
        }
        this.setSeniority(seniority);
        this.setSalary(100);
        this.setRoyalties(0.05);        //5%
        this.setCategory("Director");
    }

    @Override       //directors receive an extra thousand for each year of seniority
    public double calculatePayment(){
        return getSalary()*getHours()+1000*getSeniority();
    }

    public int getSeniority(){ return this.seniority; }
    public void setSeniority(int seniority){ this.seniority=seniority; }

    @Override
    public String toString(){
            return getName() + " " + getSurname() + " (" + getCategory() + ", " + getSeniority() + " years of experience)" + ": ";
    }
}
