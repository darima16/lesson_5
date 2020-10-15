package task1;

public class Program {
    public static void main(String[] args) {
        PersonBuilder builder = new PersonBuilderImp();

        Person person = builder.id(5).build();
        System.out.println(person.id);
    }
}
