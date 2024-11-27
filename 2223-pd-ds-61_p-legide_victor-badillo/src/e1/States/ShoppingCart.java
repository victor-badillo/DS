package e1.States;

import e1.Order;
import e1.OrderState;
import e1.Product;

public class ShoppingCart implements OrderState {
    private static final ShoppingCart uniqueInstance = new ShoppingCart();
    private static final String phase = "Shopping";
    private ShoppingCart(){ }

    public static ShoppingCart instance() { return uniqueInstance; }

    @Override
    public void addItem(Order order, Product product, int quantity){
        if(product.getStock() < quantity)                                                   //stock insuficiente
            throw new IllegalArgumentException();
        if(order.getMyCart().containsKey(product)){
            order.getMyCart().replace(product,quantity + order.getMyCart().get(product));      //si ya esta e producto en el carrito añadir
        }else{
            order.getMyCart().put(product,quantity);                                        // no esta en el carrito
        }

        product.setStock(product.getStock()-quantity);                                      //cambiar stock

        order.setLogInfo( order.getLogInfo().concat("- Add: Item: "+ product.getNumItem() + " - Quantity: "+ quantity+ " -> Shopping Cart -- Products : "+ order.getMyCart().size() +"\n"));

    }

    @Override
    public void removeItem(Order order, Product product){
        if(! (order.getMyCart().containsKey(product))){                         //no existe el producto en mi carrito
            throw new IllegalArgumentException();
        }
        product.setStock(product.getStock() + order.getMyCart().get(product)); //devolvemos stock cuando me lo quito del carrito
        order.getMyCart().remove(product);                                      //borro del carrito

        order.setLogInfo(order.getLogInfo().concat("- Remove: Item: " + product.getNumItem() + " -> Shopping Cart -- Products : " + order.getMyCart().size() +"\n"));
    }

    @Override
    public void changeState(Order order) {
        order.setLogInfo(order.getLogInfo().concat("Order "+ order.getNumOrder() + ": Check Out Phase"+"\n"));
        order.setState(CheckOut.instance());
    }

    private String getPhase(){ return phase; }
    @Override
    public String printPhase(Order order){
        if( order.getMyCart().size() == 0)
            return getPhase()+ " -- Welcome to online shopping\n";
        return getPhase() + " -- " + order.getMyCart().size() + " products\n";

    }


}
