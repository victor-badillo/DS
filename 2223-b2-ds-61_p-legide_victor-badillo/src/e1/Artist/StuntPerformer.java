package e1.Artist;

import e1.Crew;

public class StuntPerformer extends Crew{

    private boolean hasDangerousScenes;

    public StuntPerformer(String name, String surname, String id, int telephone, String nationality,int hours, boolean hasDangerousScenes){
        super(name,surname,id,telephone,nationality,hours);
        this.setHasDangerousScenes(hasDangerousScenes);
        this.setSalary(40);
        this.setCategory("Stunt performer");
        this.setRoyalties(0);
    }

    @Override
    public double calculatePayment(){
        if(getHasDangerousScenes()){
            return getSalary()*getHours()+1000; //Stunt performers receive a bonus if they have participated in dangerous scenes
        }else{
            return getSalary()*getHours();
        }
    }
    public boolean getHasDangerousScenes(){
        return this.hasDangerousScenes;
    }

    public void setHasDangerousScenes(boolean hasDangerousScenes){
        this.hasDangerousScenes=hasDangerousScenes;
    }

    @Override
    public String toString(){
        if(getHasDangerousScenes()) {
            return getName() + " " + getSurname() + " (" + getCategory() + " with extra for danger)" + ": ";
        }else{
            return getName() + " " + getSurname() + " (" + getCategory() + " with no extra for danger)" + ": ";
        }
    }
}
