package task2;

import java.util.concurrent.TimeUnit;

public class Main {
    private int counter;
    final Object obj = new Object();
    Thread thread = new Thread();

    public void increment(){
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.MILLISECONDS.sleep(150);
                }
                catch (InterruptedException e) {

                }
                for(int i=0; i<5; i++){
                    synchronized (obj) {
                        System.out.print("First thread: ");
                        System.out.println(counter);
                        counter++;
                    }
                    try {
                        TimeUnit.MILLISECONDS.sleep(300);
                    }
                    catch (InterruptedException e) {

                    }
                }
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                //System.out.println(counter);
            }
        });
        System.out.println(thread1.getState());

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0; i<10; i++){
                    synchronized (obj) {
                        System.out.print("Second thread: ");
                        System.out.println(counter);
                        try {
                            TimeUnit.MILLISECONDS.sleep(100);
                        }
                        catch (InterruptedException e) {

                        }
                        counter++;
                    }
                    try {
                        TimeUnit.MILLISECONDS.sleep(300);
                    }
                    catch (InterruptedException e) {

                    }
                }
                //System.out.println(counter);
            }
        });
        thread = thread2;
        thread1.start();
        System.out.println(thread1.getState());
        thread2.start();
        try {
            TimeUnit.MILLISECONDS.sleep(465);
        }
        catch (InterruptedException e) {
            //throw new IllegalStateException("task interrupted", e);
        }
        System.out.println(thread1.getState());
        //System.out.println(thread2.getState());
        try {
            TimeUnit.MILLISECONDS.sleep(2100);
        }
        catch (InterruptedException e) {
            //throw new IllegalStateException("task interrupted", e);
        }
        System.out.println(thread1.getState());
        try {
            thread2.join();
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(thread1.getState());
    }
    public static void main(String[] args) {
        Main main = new Main();
        main.increment();
    }
}