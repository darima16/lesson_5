package task1;

public class PersonBuilderImp implements PersonBuilder{
    private Person person;

    PersonBuilderImp() {
        person = new Person();
    }



    @Override
    public Person build() {
        return person;
    }

    @Override
    public PersonBuilder firstName(String firstName) {
        person.setFirstName(firstName);
        return this;
    }

    @Override
    public PersonBuilder surName(String surName) {
        person.setSurName(surName);
        return this;
    }

    @Override
    public PersonBuilder city(String city) {
        person.setCity(city);
        return this;
    }

    @Override
    public PersonBuilder age(int age) {
        person.setAge(age);
        return this;
    }
}
