package e1;

import e1.States.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.*;


import java.text.SimpleDateFormat;
import java.util.Date;

class OrderTest {
    private static final Order PLorder = new Order(1);
    private static final Order VBorder = new Order(2);
    private static final Order DDorder = new Order(3);
    private static final Order MSorder = new Order(4);
    private static final Order JDorder = new Order(5);
    private static final Order LGorder = new Order(6);
    private static final Order TSorder = new Order(7);
    private static final Order SSorder = new Order(8);
    private static final Order XCorder = new Order(9);


    private static final Product milk = new Product(1, 10);
    private static final Product eggs = new Product(2,12);
    private static final Product wheyProtein = new Product(3,4);
    private static final Product cookies = new Product(4, 6);
    private static final Product lettuce = new Product(5,8);

    private static final Payment payment = Payment.instance();
    private static final Cancelled cancelled = Cancelled.instance();

    private static final Date date = new Date();
    private SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    @BeforeAll
    static void insertProducts(){
        PLorder.addItem(milk,1);
        VBorder.addItem(eggs,6);
        DDorder.addItem(wheyProtein,4);
        MSorder.addItem(eggs,6);
        LGorder.addItem(cookies,2);
        TSorder.addItem(milk,1);
        TSorder.addItem(cookies,3);
        JDorder.addItem(lettuce,1);
        SSorder.addItem(lettuce, 4);

    }

    @Test
    void order(){
        assertThrows(IllegalArgumentException.class, () -> new Order(-2));
    }

    @Test
    void product(){
        assertThrows(IllegalArgumentException.class, () -> new Product(-1,5));
        assertThrows(IllegalArgumentException.class, () -> new Product(3,-5));
    }

    @Test
    void addItem() {
        assertEquals(1, PLorder.getMyCart().size());
        assertThrows(IllegalArgumentException.class, () -> TSorder.addItem(milk,12));
        assertEquals(2, TSorder.getMyCart().size());
        TSorder.addItem(milk, 2);
        assertEquals(3, (int) TSorder.getMyCart().get(milk));
        assertEquals(2, TSorder.getMyCart().size());
    }

    @Test
    void removeItem() {
        VBorder.removeItem(eggs);
        assertEquals(0, VBorder.getMyCart().size());
        assertThrows(IllegalArgumentException.class, () -> TSorder.removeItem(eggs));
        TSorder.removeItem(milk);
        assertEquals("""
                Order 7: Shopping Phase
                - Add: Item: 1 - Quantity: 1 -> Shopping Cart -- Products : 1
                - Add: Item: 4 - Quantity: 3 -> Shopping Cart -- Products : 2
                - Add: Item: 1 - Quantity: 2 -> Shopping Cart -- Products : 2
                - Remove: Item: 1 -> Shopping Cart -- Products : 1
                """, TSorder.getLogInfo());
        assertThrows(IllegalArgumentException.class, () -> VBorder.removeItem(wheyProtein));
    }

    @Test
    void modifyQuantity() {
        VBorder.changeState();
        VBorder.modifyQuantity(eggs, 2);
        assertEquals(8, (int) VBorder.getMyCart().get(eggs));
        assertThrows(IllegalArgumentException.class, () -> VBorder.modifyQuantity(wheyProtein,1));
    }

    @Test
    void changeState() {
        PLorder.changeState();
        assertNotEquals("Shopping Phase", PLorder.getState().printPhase(PLorder));
        JDorder.changeState();
        JDorder.changeState();
        JDorder.confirmPurchase();
        JDorder.cancelPurchase();
        assertThrows(UnsupportedOperationException.class, () -> JDorder.changeState());
        assertThrows(UnsupportedOperationException.class, () -> cancelled.changeState(JDorder));
        SSorder.changeState();
        SSorder.changeState();
        SSorder.confirmPurchase();
        SSorder.setState(Completed.instance());
        assertEquals("Completed Order: 1 products\n", SSorder.getState().printPhase(SSorder));
        assertThrows(UnsupportedOperationException.class, () -> SSorder.changeState());
    }

    @Test
    void stepBack() {
        MSorder.changeState();
        MSorder.stepBack();
        assertEquals("Shopping -- 1 products\n", MSorder.getState().printPhase(MSorder));
        VBorder.stepBack();
        assertEquals("Shopping -- Welcome to online shopping\n", VBorder.getState().printPhase(VBorder));
    }

    @Test
    void confirmPurchase() {
        PLorder.changeState();
        PLorder.confirmPurchase();
        assertEquals("Payment: 1 products -- date " +  formatter.format(date) + "\n", PLorder.getState().printPhase(PLorder));

    }

    @Test
    void cancelPurchase() {
        DDorder.changeState();
        DDorder.changeState();
        DDorder.cancelPurchase();
        assertEquals("Cancellation Order\n", PLorder.getState().printPhase(PLorder));
    }

    @Test
    void screenInfo() {
        assertEquals("Order Number: 1\nPhase: " + PLorder.getState().printPhase(PLorder), PLorder.screenInfo());
        PLorder.changeState();
        assertEquals("Order Number: 1\nPhase: " + PLorder.getState().printPhase(PLorder), PLorder.screenInfo());

    }

    @Test
    void getNumOrder() {
        assertEquals(1, PLorder.getNumOrder());
    }

    @Test
    void getLogInfo() {
        assertEquals("""
                Order 1: Shopping Phase
                - Add: Item: 1 - Quantity: 1 -> Shopping Cart -- Products : 1
                Order 1: Check Out Phase
                Order 1: Payment Phase
                Confirmed purchase
                Order 1: Cancelled
                """, PLorder.getLogInfo());

        assertEquals("""
                Order 4: Shopping Phase
                - Add: Item: 2 - Quantity: 6 -> Shopping Cart -- Products : 1
                """, MSorder.getLogInfo());

        assertEquals("""
                Order 6: Shopping Phase
                - Add: Item: 4 - Quantity: 2 -> Shopping Cart -- Products : 1
                """, LGorder.getLogInfo());

    }

    @Test
    void getState() {
        assertEquals("Cancelled", PLorder.getState().getClass().getSimpleName());
    }

    @Test
    void notAllowedOperations(){
        assertThrows(UnsupportedOperationException.class, () -> XCorder.modifyQuantity(milk, 2));
        assertThrows(UnsupportedOperationException.class, () -> XCorder.cancelPurchase());
        assertThrows(UnsupportedOperationException.class, () -> XCorder.confirmPurchase());
        assertThrows(UnsupportedOperationException.class, () -> XCorder.stepBack());
        XCorder.changeState();
        assertThrows(UnsupportedOperationException.class, () -> XCorder.addItem(milk, 2));
        XCorder.changeState();
        assertThrows(UnsupportedOperationException.class, () -> XCorder.removeItem(milk));
    }

}