package e3.LoginStrategys;

import e3.Data;
import e3.LoginStrategy;

public class phoneStrategy extends Data implements LoginStrategy{

    public phoneStrategy(){ }

    @Override
    public boolean validateId(String id) {
        return id.matches("^[0-9]{9}$");
    }

    @Override
    public boolean authenticatePasswords(String id, String password) {
        return getListMaps().get(2).get(id).equals(password);
    }
}