package e2;

public abstract class Reality{
    public String selectCandidates(TVRealityList<String> list, int k ){
        if(list.getNamesList().size()==0){
            throw new IllegalArgumentException();
        }
        while(list.iterator().hasNext()){    //iterate until the list has only one element
            for(int i=0; i < k ; i++){                  //jump k times to find the element
                list.iterator().next();
            }
            list.iterator().remove();                       //remove last element
        }
        return list.getNamesList().get(0);
    }

}
