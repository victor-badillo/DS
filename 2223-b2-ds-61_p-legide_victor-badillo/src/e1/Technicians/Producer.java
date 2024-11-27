package e1.Technicians;

import e1.Crew;

public class Producer extends Crew {
    public Producer(String name, String surname, String id, int telephone, String nationality, int hours) {
        super(name, surname, id, telephone, nationality, hours);
        this.setSalary(90);
        this.setRoyalties(0.02);        //2%
        this.setCategory("Producer");
    }
    @Override
    public double calculatePayment(){
        return getSalary()*getHours();
    }
}
