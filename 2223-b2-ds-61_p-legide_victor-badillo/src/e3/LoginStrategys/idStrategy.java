package e3.LoginStrategys;

import e3.Data;
import e3.LoginStrategy;


public class idStrategy extends Data implements LoginStrategy{
    public idStrategy(){ }

    @Override
    public boolean validateId(String id) {
        return id.matches("^[0-9]{8}[A-Z]$");
    }

    @Override
    public boolean authenticatePasswords(String id, String password) {
        return getListMaps().get(1).get(id).equals(password);
    }

}