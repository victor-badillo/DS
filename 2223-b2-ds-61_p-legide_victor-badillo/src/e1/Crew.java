package e1;

public abstract class Crew {

    private String name;
    private String surname;
    private String id;
    private int telephone;
    private String nationality;
    private int salary;
    private int hours;
    private String category;
    private double royalties;

    public Crew(String name, String surname, String id, int telephone, String nationality, int hours){
        if(name.length() > 30 || surname.length() > 30 || id.length()!=9  || telephone<0 || String.valueOf(telephone).length() != 9 || nationality.length() >30 || hours<1){
            throw new IllegalArgumentException();
        }
        this.setName(name);
        this.setSurname(surname);
        this.setId(id);
        this.setTelephone(telephone);
        this.setNationality(nationality);
        this.setHours(hours);
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name){
        this.name=name;
    }

    public String getSurname() {
        return this.surname;
    }
    public void setSurname(String surname){
        this.surname=surname;
    }
    public String getId() {return this.id;}
    public void setId(String id){ this.id=id; }
    public int getTelephone() {return this.telephone;}
    public void setTelephone(int telephone){ this.telephone=telephone; }
    public String getNationality() {return this.nationality;}
    public void setNationality(String nationality){
        this.nationality=nationality;
    }

    public int getSalary(){
        return this.salary;
    }
    public void setSalary(int salary){
        this.salary=salary;
    }

    public int getHours(){ return this.hours; }
    public void setHours(int hours){ this.hours=hours; }

    public String getCategory(){ return this.category; }
    public void setCategory(String category){ this.category=category; }

    public double getRoyalties(){ return this.royalties; }
    public void setRoyalties(double royalties){
        this.royalties=royalties;
    }
    public abstract double calculatePayment();

    @Override
    public String toString(){
        return getName()+" "+getSurname()+" ("+getCategory()+")"+": ";
    }

}
