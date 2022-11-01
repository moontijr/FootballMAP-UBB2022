package Model;

public abstract class Person {
    private String firstName;
    private  String lastName;

    private  String nationality;
    private  int age;

    public Person(String firstName, String lastName, int age, String nationality) {
        this.firstName=firstName;
        this.lastName=lastName;
        this.age=age;
        this.nationality=nationality;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNationality() {
        return nationality;
    }

    public int getAge() {
        return age;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
