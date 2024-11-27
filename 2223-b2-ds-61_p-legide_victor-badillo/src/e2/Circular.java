package e2;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class Circular<E> implements Iterator<E> {


    private List<E> inList;
    private static int index;      //default value is 0
    private static int countRemove;   //default value is 0
    private static boolean isCalledNext;   //default value is false

    public Circular(List <E> inList){
        this.inList = inList;
    }

    @Override
    public boolean hasNext() {
        return inList.size() > 1;
    }


    @Override
    public E next() {
        if(!hasNext()){
            throw new NoSuchElementException();
        }else{
            countRemove=0;
            isCalledNext=true;
            E value;
            if(this.inList.size()-1 < index){
                value = inList.get(0);
                index=0;
            }else{
                value = inList.get(index);
            }
            index++;
            return value;
        }

    }

    @Override
    public void remove(){

        if(countRemove > 0 || !isCalledNext){       //call remove more than time before calling next or next has not been called
            throw new IllegalStateException();
        }else{
            countRemove++;

            if(index-1 == 0){                       //remove the element in the first position
                this.inList.remove(0);
            }else{
                this.inList.remove(index-1);
            }
            index--;

        }
    }
}
