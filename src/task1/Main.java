package task1;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Main {
    private int len = 10;
    private ArrayList<Person> list_person = new ArrayList<Person>();
    final Object obj = new Object();
    private Thread timeThread;

    public void increment(){
        timeThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.MILLISECONDS.sleep(10000);
                }
                catch (InterruptedException e) {

                }
            }
        });
        Thread producer = new Thread(new Runnable() {
            @Override
            public void run() {
                while (timeThread.isAlive()) {
                        System.out.println(timeThread.getState());
                        synchronized (obj) {
                            if (list_person.size() < len) {

                                PersonBuilder builder = new PersonBuilderImp();
                                Person person = builder.firstName("Vanya").build();

                                list_person.add(person);

                                System.out.println("new pers is added");
                                System.out.println(list_person.size());
                                try {
                                    obj.wait(500);
                                } catch (InterruptedException e) {

                                }
                            }
                        }
                }
            }
        });

        Thread consumer = new Thread(new Runnable() {
            @Override
            public void run() {
                while (timeThread.isAlive()) {

                    synchronized (obj) {
                        if (!list_person.isEmpty()) {
                            list_person.remove(0);
                            System.out.println("pers removed");

                            System.out.println(list_person.size());
                            try {
                                int random_number = (int) (Math.random() * (1000-100)+100);
                                obj.wait(random_number);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }



            }}
        });
        timeThread.start();
        producer.start();
        consumer.start();


    }
    public static void main(String[] args) {
        task1.Main main = new task1.Main();
        main.increment();
    }
}