package task1;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Main {
    private int len = 10;
    private ArrayList<Person> list_person = new ArrayList<Person>();
    final Object obj = new Object();
    private Thread timeThread;
    int id=0;
    int remove_id;

    public void funcmain(){
        timeThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.MILLISECONDS.sleep(10000);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread producer = new Thread(new Runnable() {
            @Override
            public void run() {
                while (timeThread.isAlive()) {
                        //System.out.println(timeThread.getState());
                        synchronized (obj) {
                            if (list_person.size() < len) {
                                id++;
                                PersonBuilder builder = new PersonBuilderImp();
                                Person person = builder.firstName("Vanya").id(id).build();

                                list_person.add(person);

                                System.out.print("Новый пользователь добавлен c id: ");
                                System.out.println(id);
                                //System.out.println(list_person.size());
                                try {
                                    obj.wait(500);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
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
                            remove_id=list_person.get(0).id;
                            list_person.remove(0);
                            System.out.print("Пользователь удален с id: ");
                            System.out.println(remove_id);

                            try {
                                int random_number = (int) (Math.random() * (1000-100)+100);
                                obj.wait(random_number);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });
        timeThread.start();
        producer.start();
        consumer.start();


    }
    public static void main(String[] args) {
        task1.Main main = new task1.Main();
        main.funcmain();
    }
}