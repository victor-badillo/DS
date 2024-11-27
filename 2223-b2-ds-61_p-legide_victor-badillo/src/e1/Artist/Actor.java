package e1.Artist;

import e1.Crew;

public class Actor extends Crew {

    private String role;


    public Actor(String name, String surname, String id, int telephone, String nationality, int hours,String role) {
        super(name, surname, id, telephone, nationality, hours);
        if(!(role.equals("protagonist")) && !(role.equals("secondary")) && !(role.equals("figurant")) ){
            throw new IllegalArgumentException();
        }
        this.setSalary(200);
        this.setRole(role);
        this.setCategory("Actor");
        this.setRoyalties(0);
    }

    @Override
    public double calculatePayment(){
        if(this.getRole().equals("protagonist")){         //actor will triplicate their salary if they are protagonists
           return getSalary()*getHours()*3;
        }else{
           return getSalary()*getHours();
        }
    }
    public String getRole(){ return this.role; }

    public void setRole(String role){ this.role=role; }


}
