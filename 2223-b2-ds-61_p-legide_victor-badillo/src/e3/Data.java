package e3;

import java.util.ArrayList;
import java.util.HashMap;

public class Data {

    //0 -> email , 1 -> id , 2 -> phone
    private static ArrayList<HashMap<String,String>> listMaps = new ArrayList<>(); //This list will save the hash maps for each strategy
    private static ArrayList<User> listUsers = new ArrayList<>(); //This list will save all the user in order to know the preferred mfa
    private HashMap<String,String> email = new HashMap<>();
    private HashMap<String,String> id = new HashMap<>();
    private HashMap<String,String> phone = new HashMap<>();


    protected Data(){
        listMaps.add(email);
        listMaps.add(id);
        listMaps.add(phone);
    }

    public ArrayList<HashMap<String, String>> getListMaps(){ return listMaps; }
    public ArrayList<User> getListUsers(){ return listUsers; }
}
