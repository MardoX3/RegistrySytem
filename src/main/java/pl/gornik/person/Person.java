package pl.gornik.person;

public class Person {
    private String firstName;
    private String lastName;
    private String gender;
    private final String dateOfBirth;
    private String dateOfDeath;
    private String residentalAdress;
    private String identificationNumber;
    private final boolean isDeath;
    private boolean spouse;

    public void setSpouse(boolean spouse) {
        this.spouse = spouse;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getSpouse() {
        return spouse;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " | Gender: " + gender +
                " | dateOfBirth: "+ dateOfBirth;
    }

    private int id;
    private static int lastAssignedId = 0;

    public Person(String firstName, String lastName, String gender, String dateOfBirth,String dateOfDeath, String residentalAdress, String identificationNumber, boolean isDeath) {
        this.id = generujUnikalneId();
        this.firstName = firstName != null ? firstName : "";
        this.lastName = lastName != null ? lastName : "";
        this.gender = gender != null ? gender : "";
        this.dateOfBirth = dateOfBirth != null ? dateOfBirth : "";
        this.dateOfDeath = dateOfDeath != null ? dateOfDeath : "";
        this.residentalAdress = residentalAdress != null ? residentalAdress : "";
        if(identificationNumber.matches("\\d{11}")){
            this.identificationNumber = identificationNumber;
        }
        this.isDeath = isDeath;
    }

    public Person(String firstName, String lastName, String gender,String dateOfBirth,boolean isDeath) {
        this.firstName = firstName != null ? firstName : "";
        this.lastName = lastName != null ? lastName : "";
        this.gender = gender != null ? gender : "";
        this.dateOfBirth= dateOfBirth != null ? dateOfBirth : "";
        this.isDeath = isDeath;
        this.id = generujUnikalneId();
    }
    public Person(String firstName, String lastName, String gender,String dateOfBirth,String residentalAdress,String identificationNumber,boolean isDeath) {
        this.id = generujUnikalneId();
        this.firstName = firstName != null ? firstName : "";
        this.lastName = lastName != null ? lastName : "";
        this.gender = gender != null ? gender : "";
        this.dateOfBirth = dateOfBirth != null ? dateOfBirth : "";
        this.residentalAdress = residentalAdress != null ? residentalAdress : "";
        if(identificationNumber.matches("\\d{11}")){
            this.identificationNumber = identificationNumber;
        }
        this.isDeath = isDeath;

    }

    private static synchronized int generujUnikalneId() {
        return ++lastAssignedId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setResidentalAdress(String residentalAdress) {
        this.residentalAdress = residentalAdress;
    }

    public String getResidentalAdress() {
        return residentalAdress;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public static int getLastAssignedId() {
        return lastAssignedId;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }
}