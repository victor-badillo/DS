package e2;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class TVRealityList<T> extends Reality implements Iterable<T> {

    private List<T> namesList;
    private boolean rebound;        //variable created for choosing the iterator applied (TRUE = REBOUND | FALSE = CIRCULAR)
    private Rebound<T> reb;
    private Circular<T> cir;


    TVRealityList(boolean rebound){
        this.namesList= new ArrayList<>();
        this.setRebound(rebound);

        if(rebound){
            this.reb = new Rebound<>(this.namesList);
        }else{
            this.cir = new Circular<>(this.namesList);
        }
    }
    public List<T> getNamesList() { return this.namesList; }

    public boolean getIsRebound() { return this.rebound; }
    public void setRebound(boolean rebound){ this.rebound=rebound; }

    public void addPerson(T element){
        getNamesList().add(element);
    }

    @Override
    public Iterator<T> iterator() {
        if(getIsRebound()){
            return this.reb;
        }else{
            return this.cir;
        }
    }

}
