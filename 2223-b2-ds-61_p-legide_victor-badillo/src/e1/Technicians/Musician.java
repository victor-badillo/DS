package e1.Technicians;

import e1.Crew;

public class Musician extends Crew {
    public Musician(String name, String surname, String id, int telephone, String nationality, int hours) {
        super(name, surname, id, telephone, nationality, hours);
        this.setSalary(60);
        this.setRoyalties(0.04);        //4%
        this.setCategory("Musician");
    }

    @Override
    public double calculatePayment(){
        return getSalary()*getHours();
    }
}
