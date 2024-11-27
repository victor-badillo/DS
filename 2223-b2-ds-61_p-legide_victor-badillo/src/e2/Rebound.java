package e2;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class Rebound<E> implements Iterator<E>{

    private static int index;
    private static int countRemove;
    private static boolean isCalledNext=false;
    private List<E> inList;
    private static boolean direction=true;     //boolean because we only have 2 values: right(true) and left(false)

    public Rebound(List <E> inList){
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
            isCalledNext=true;
            countRemove=0;
            E value;
            if(direction){     //flow to the right
                if(this.inList.size()-1 < index ){            //get the penultimate in this direction
                    value = inList.get(index - 2);
                    index=index-3;              //index is one position ahead the last called by next
                    direction=false;           //change of direction
                }else{
                    value = inList.get(index);
                    index++;
                }
            }else{                   //flow to the left
                if(index < 0){                          //get the penultimate in this direction
                    value = inList.get(index + 2);
                    index=index+3;              //index is one position ahead the last called by next
                    direction=true;            //change of direction
                }else{
                    value = inList.get(index);
                    index--;
                }

            }
            return value;
        }
    }
    @Override
    public void remove(){
        if(countRemove > 0 || !isCalledNext){           //call remove more than time before calling next or next has not been called
            throw new IllegalStateException();
        }else{
            countRemove++;
            isCalledNext=false;

            if(direction){         //if the flow is to the right
                this.inList.remove(index - 1);
                if(index-1 == this.inList.size()){
                    index = index -2;
                }else{
                    index--;
                }

            }else{                      //if the flow is to the left
                this.inList.remove(index + 1);      //creo que asi esta bien

                if(index + 1 == 0){
                    index--;
                }

            }
        }
    }

}
