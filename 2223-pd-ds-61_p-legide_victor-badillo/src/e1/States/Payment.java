package e1.States;

import e1.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Payment implements OrderState{
    private static final Payment uniqueInstance = new Payment();
    private static final String phase = "Payment";
    private boolean isCancelled;
    private Date date = new Date();
    private SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    private Payment() {}

    public static Payment instance() { return uniqueInstance; }

    private boolean timePast(){
        Date currentDate = new Date();
        long dif = date.getTime() - currentDate.getTime();
        long days = TimeUnit.MILLISECONDS.toDays(dif);
        return days > 1;
    }

    public void setCancelled(boolean isCancelled){
        this.isCancelled = isCancelled;
    }

    @Override
    public void cancelPurchase(Order order){
        order.setLogInfo(order.getLogInfo().concat("Cancelled purchase\n"));
        setCancelled(false);

    }
    @Override
    public void confirmPurchase(Order order){
        order.setLogInfo(order.getLogInfo().concat("Confirmed purchase\n"));
        setCancelled(true);
    }

    @Override
    public void changeState(Order order) {
        if (timePast() && !isCancelled) {             //han pasado mas de 24 horas y no se ha cancelado
            order.setLogInfo(order.getLogInfo().concat("Order " + order.getNumOrder() + ":  Completed" + "\n"));
            order.setState(Completed.instance());
        } else if (!timePast() && isCancelled) {          //no han pasado 24 horas y es cancelado
            order.setLogInfo(order.getLogInfo().concat("Order " + order.getNumOrder() + ": Cancelled" + "\n"));
            order.setState(Cancelled.instance());
        } else {
            throw new UnsupportedOperationException();
        }
    }
    private String getPhase(){ return phase; }

    @Override
    public String printPhase(Order order){
        return getPhase() + ": " + order.getMyCart().size() + " products -- date " + formatter.format(date) + "\n";
    }

}
