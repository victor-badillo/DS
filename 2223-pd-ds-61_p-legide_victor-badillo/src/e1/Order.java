package e1;

import e1.States.ShoppingCart;
import java.util.HashMap;

public class Order {
    private OrderState state = ShoppingCart.instance(); //igual hacer privado
    private String logInfo;
    private final int numOrder;
    private HashMap<Product,Integer> myCart;
    public HashMap<Product,Integer> getMyCart(){ return this.myCart; }


    public Order (int numOrder){
        if(numOrder < 0)
            throw new IllegalArgumentException();

        this.numOrder=numOrder;
        myCart = new HashMap<>();

        logInfo = "Order " + getNumOrder() + ": Shopping Phase\n" ;
    }


    public void addItem(Product product, int quantity){
        state.addItem(this,product,quantity);
    }

    public void removeItem(Product product){ state.removeItem(this,product); }

    public void modifyQuantity(Product product, int quantity){ state.modifyQuantity(this, product,quantity);}

    public void changeState(){ state.changeState(this); }

    public void stepBack(){ state.stepBack(this);}

    public void confirmPurchase(){ state.confirmPurchase(this); }

    public void cancelPurchase(){ state.cancelPurchase(this); }

    public String screenInfo(){
        return "Order Number: " + this.getNumOrder() + "\nPhase: " + this.getState().printPhase(this);
    }


    public int getNumOrder(){
        return this.numOrder;
    }
    public String getLogInfo(){
        return this.logInfo;
    }

    public void setLogInfo(String logInfo){ this.logInfo=logInfo; }

    public OrderState getState(){
        return this.state;
    }

    public void setState(OrderState state){
        this.state=state;
    }
}
