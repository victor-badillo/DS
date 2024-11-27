package e3;


public interface LoginStrategy {

    boolean validateId(String id);
    boolean authenticatePasswords(String id, String password);
}
