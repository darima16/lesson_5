package task1;

public interface PersonBuilder {
    Person build();
    PersonBuilder firstName(String firstName);
    PersonBuilder surName(String serName);
    PersonBuilder city(String city);
    PersonBuilder age(int age);

}
