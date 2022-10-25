
public abstract class Person {
    private final String firstName;
    private final String lastName;

    private final String nationality;
    private final int age;

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
}
