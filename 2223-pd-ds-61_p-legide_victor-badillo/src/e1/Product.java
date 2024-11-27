package e1;

public class Product{

    private int stock;
    private final int numItem;

    public Product(int numItem, int stock){
        if(numItem<0 || stock<0){
            throw new IllegalArgumentException();
        }
        this.numItem=numItem;
        this.stock=stock;
    }

    public int getNumItem(){
        return this.numItem;
    }

    public void setStock(int stock){
        this.stock=stock;
    }
    public int getStock(){
        return this.stock;
    }
}
