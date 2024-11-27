package e3;

public class User extends Data{

    private MFAStrategy mfaStrategy;

    public User(String email, String id, String phone,String password, MFAStrategy mfaStrategy){

        if(checkFormat(email,id,phone)){
            this.mfaStrategy=mfaStrategy;

            getListUsers().add(this);

            getListMaps().get(0).put(email,password);
            getListMaps().get(1).put(id,password);
            getListMaps().get(2).put(phone,password);

        }else{
            throw new IllegalArgumentException();
        }
    }

    private boolean checkFormat(String email, String id, String phone){
        return email.matches("^[^@]+@udc\\.es$") && id.matches("^[0-9]{8}[A-Z]$") && phone.matches("^[0-9]{9}$");
    }

    public MFAStrategy getMfaStrategy(){ return this.mfaStrategy; }

    public void setMFAStrategy (MFAStrategy mfaStrategy) {
        this.mfaStrategy = mfaStrategy;
    }

}
