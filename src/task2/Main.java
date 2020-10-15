package task2;

import java.util.concurrent.TimeUnit;

public class Main {
    private int counter=0;
    final Object obj = new Object();
    Thread thread2;

    public void mainfunc(){
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.MILLISECONDS.sleep(150);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for(int i=0; i<5; i++){
                    synchronized (obj) {
                        counter++;
                        System.out.print("First thread: ");
                        System.out.println(counter);
                    }
                    try {
                        TimeUnit.MILLISECONDS.sleep(300);
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    thread2.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        System.out.println(thread1.getState()); // NEW

        thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0; i<10; i++){
                    synchronized (obj) {

                        try {
                            TimeUnit.MILLISECONDS.sleep(100);
                        }
                        catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        counter++;
                        System.out.print("Second thread: ");
                        System.out.println(counter);
                    }
                    try {
                        TimeUnit.MILLISECONDS.sleep(300);
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread1.start();
        System.out.println(thread1.getState()); //RUNNABLE
        thread2.start();
        try {
            TimeUnit.MILLISECONDS.sleep(465);
        }
        catch (InterruptedException e) {
            throw new IllegalStateException("task interrupted", e);
        }

        System.out.println(thread1.getState()); //BLOCKED

        try {
            TimeUnit.MILLISECONDS.sleep(2100);
        }
        catch (InterruptedException e) {
            throw new IllegalStateException("task interrupted", e);
        }
        System.out.println(thread1.getState()); //WAITING
        try {
            thread2.join();
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(thread1.getState()); //TERMINATED
    }
    public static void main(String[] args) {
        Main main = new Main();
        main.mainfunc();
    }
}