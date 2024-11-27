package e1.Technicians;

import e1.Crew;

public class Screenwriter extends Crew {

    private boolean isOriginalScreenplay;

    public Screenwriter(String name, String surname, String id, int telephone, String nationality, int hours, boolean isOriginalScreenplay){
        super(name,surname,id,telephone,nationality,hours);
        this.setOriginalScreenplay(isOriginalScreenplay);
        this.setSalary(70);
        this.setRoyalties(0.05);        //5%
        this.setCategory("Screenwriter");
    }

    @Override
    public double calculatePayment(){
        if(getIsOriginalScreenplay()){
            return getSalary()*getHours()+4000;     //screenwriters receive a bonus if it is an original screenplay
        }else{
            return getSalary()*getHours();
        }
    }

    public boolean getIsOriginalScreenplay(){
        return this.isOriginalScreenplay;
    }
    public void setOriginalScreenplay(boolean isOriginalScreenplay){
        this.isOriginalScreenplay=isOriginalScreenplay;
    }

    @Override
    public String toString(){
        if(getIsOriginalScreenplay()){
            return getName() + " " + getSurname() + " (" + getCategory() + ", is original screenplay)" + ": ";
        }else{
            return getName() + " " + getSurname() + " (" + getCategory() + ", not original screenplay)" + ": ";
        }
    }
}
