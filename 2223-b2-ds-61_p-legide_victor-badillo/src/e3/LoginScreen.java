package e3;


public class LoginScreen extends Data{

    private LoginStrategy loginStrategy;

    public LoginScreen (LoginStrategy strategy){
        this.loginStrategy = strategy;
    }

    public boolean checkId(String id){
      return loginStrategy.validateId(id);
    }


    public boolean checkPassword(String id, String password){
        return loginStrategy.authenticatePasswords(id, password );
    }

    public String knowMFA(User user){
        return user.getMfaStrategy().generateCode();
    }


    public String process(String id,String password ,User user){
        if(checkId(id)){
            if(checkPassword(id,password)){
                return knowMFA(user);
            }else{
                return "Incorrect password";
            }
        }else{
            return "Not valid format";
        }
    }

    public void setLoginStrategy(LoginStrategy loginStrategy) {
        this.loginStrategy = loginStrategy;
    }

}
