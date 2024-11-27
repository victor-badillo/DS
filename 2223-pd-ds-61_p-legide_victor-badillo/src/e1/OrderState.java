package e1;

public interface OrderState {

    default void addItem(Order order, Product product, int quantity){
        throw new UnsupportedOperationException();
    }
    default void removeItem(Order order, Product product){
        throw new UnsupportedOperationException();
    }
    default void modifyQuantity(Order order, Product product, int quantity){ throw new UnsupportedOperationException(); }
    String printPhase(Order order);
    void changeState(Order order);
    default void stepBack(Order order){ throw new UnsupportedOperationException(); }
    default void cancelPurchase(Order order){ throw new UnsupportedOperationException(); }
    default void confirmPurchase(Order order){ throw new UnsupportedOperationException(); }

}
