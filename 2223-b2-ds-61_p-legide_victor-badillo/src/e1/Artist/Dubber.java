package e1.Artist;

import e1.Crew;

public class Dubber extends Crew {

    public Dubber(String name, String surname, String id, int telephone, String nationality, int hours){
        super(name,surname,id,telephone,nationality,hours);
        this.setSalary(20);
        this.setCategory("Dubber");
        this.setRoyalties(0);
    }
    @Override
    public double calculatePayment(){
        return getHours()*getSalary();
    }

}
