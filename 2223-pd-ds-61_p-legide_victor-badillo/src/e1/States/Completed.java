package e1.States;

import e1.Order;
import e1.OrderState;

public class Completed implements OrderState {
    private static Completed uniqueInstance = null;
    private static final String phase = "Completed";

    private Completed(){}

    public static Completed instance(){
        if(uniqueInstance == null){
            uniqueInstance = new Completed();
        }
        return uniqueInstance;
    }

    private String getPhase(){ return phase; }


    @Override
    public void changeState(Order order) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String printPhase(Order order){
        return getPhase() + " Order: " + order.getMyCart().size() + " products\n";
    }
}
