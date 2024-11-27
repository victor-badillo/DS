package e3.LoginStrategys;

import e3.Data;
import e3.LoginStrategy;

public class emailStrategy extends Data implements LoginStrategy{

    public emailStrategy(){ }

    @Override
    public boolean validateId(String id) {
        return id.matches("^[^@]+@udc\\.es$");
    }

    @Override
    public boolean authenticatePasswords(String id, String password) {
        return getListMaps().get(0).get(id).equals(password);
    }


}
