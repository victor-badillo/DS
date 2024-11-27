package e1.States;

import e1.Order;
import e1.OrderState;

public class Cancelled implements OrderState {
    private static Cancelled uniqueInstance = null;
    private static final String phase = "Cancellation";

    private Cancelled() { }

    public static Cancelled instance(){
        if(uniqueInstance == null){
            uniqueInstance = new Cancelled();
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
        return getPhase() + " Order\n";
    }
}
