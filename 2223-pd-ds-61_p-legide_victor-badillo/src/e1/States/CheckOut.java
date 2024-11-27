package e1.States;

import e1.Order;
import e1.OrderState;
import e1.Product;

public class CheckOut implements OrderState {

    private static final CheckOut uniqueInstance = new CheckOut();
    private static final String phase = "Check Out";

    private CheckOut(){ }

    public static CheckOut instance() { return uniqueInstance; }

    //Para añadir hay que volver al estado anterior con stepback()

    @Override
    public void removeItem(Order order, Product product){
        if(! (order.getMyCart().containsKey(product))){                         //no existe el producto en mi carrito
            throw new IllegalArgumentException();
        }
        product.setStock(product.getStock() + order.getMyCart().get(product)); //devolvemos stock cuando me lo quito del carrito
        order.getMyCart().remove(product);                                      //borro del carrito

        order.setLogInfo(order.getLogInfo().concat("- Remove: Item: " + product.getNumItem() + " -> CheckOut Order -- Products : " + order.getMyCart().size() +"\n"));
    }

    @Override
    public void modifyQuantity(Order order, Product product, int quantity){
        if(! (order.getMyCart().containsKey(product))){                         //no existe el producto en mi carrito
            throw new IllegalArgumentException();
        }
        order.getMyCart().replace(product,quantity + order.getMyCart().get(product));  //modificar cantidad del producto en carrito
        product.setStock(product.getStock()-quantity);                                  //modificar stock del producto
        order.setLogInfo(order.getLogInfo().concat("- Modify: Item: " + product.getNumItem() + " - Quantity: " + quantity +" -> CheckOut Order -- Products : " + order.getMyCart().size() +"\n"));
    }

    @Override
    public void changeState(Order order) {
        order.setLogInfo(order.getLogInfo().concat("Order "+ order.getNumOrder() + ": Payment Phase"+"\n"));
        order.setState(Payment.instance());
    }

    @Override
    public void stepBack(Order order){
        order.setLogInfo(order.getLogInfo().concat("Order "+ order.getNumOrder() + ": Shopping Phase"+"\n"));
        order.setState(ShoppingCart.instance());
    }

    private String getPhase(){ return phase; }
    @Override
    public String printPhase(Order order){
        return getPhase() + ": " +  order.getMyCart().size() + " products\n";
    }
}
