package lesson15;

import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static void main(String[] args) {
//        createThread();
//        createRunnable();
//        trySynchronized();
//        createAtomic();
        tryJoin();
    }

/*Задание: Создайте класс Counter, который будет увеличивать счетчик на 1 в каждом потоке, используя класс Thread.*/
    public static void createThread() {
        Counter count = new Counter();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                count.increment();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                count.increment();
            }
        });

        try {
            thread1.start();
            thread1.join();

            thread2.start();
            thread2.join();

            System.out.println(count.getCounter());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

/*Задание: Реализуйте интерфейс Runnable, который будет выводить номер потока 100 раз.*/
    public static void createRunnable() {
        Thread myThread = new Thread(new MyThread(), "My Thread");
        myThread.start();
    }

/*Задание: Создайте два потока, которые одновременно увеличивают значение переменной. Синхронизируйте доступ к этой переменной.*/
    public static void trySynchronized() {
        Counter count = new Counter();
        Counter count2 = new Counter();

        Thread thread1 = new Thread(() -> {
            synchronized (count) {
                for (int i = 0; i < 1000; i++) {
                    synchronized (count2) {
                        count.increment();
                    }
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (count2) {
                for (int i = 0; i < 1000; i++) {
                    synchronized (count) {
                        count.increment();
                    }
                }
            }
        });

        try {
            thread1.start();
            thread2.start();

            System.out.println(count.getCounter());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    /*Задача 4: Использование атомарного типа AtomicInteger
Задание: Используйте AtomicInteger для безопасного увеличения счетчика в многозадачной среде.*/
    public static void createAtomic() {
        AtomicInteger number = new AtomicInteger(0);

        Thread thread1 = new Thread(()->{
            for (int i=0; i<100; i++) {
                number.incrementAndGet();
            }
        });

        Thread thread2 = new Thread(()->{
            for (int i=0; i<100; i++) {
                number.incrementAndGet();
            }
        });
        try {
            thread1.start();
            thread2.start();
            thread1.join();
            thread2.join();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("Final " + number.get());
    }
    /*Задание: Создайте два потока и используйте метод join для ожидания их завершения перед выводом результата.*/
    public static void tryJoin() {
        Counter count = new Counter();

        Thread thread1 = new Thread(() -> {
            System.out.println("Первый поток начал выполнение");
        });

        Thread thread2 = new Thread(() -> {
            System.out.println("Второй поток начал выполнение");
        });

        try {
            thread1.start();
            thread1.join();

            thread2.start();
            thread2.join();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}

class MyThread implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " iteration: " + i);
        }
    }
}

class Counter {
    private int count = 0;

    public void increment() {
        count++;
        System.out.println(Thread.currentThread().getName() + ": " + count);
    }

    public int getCounter() {
        return count;
    }
}
