import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {
//        useCompareAndSet();
//        createFiveThreads();
//        synchronizeThreads();
//        synchronizeObject();
//        checkThread();
//        counterDo();
        tryAtomicInteger();
 //       synchronizeDecrementIncrement();
    }
    /*Задание: Реализуйте пример, в котором используется метод compareAndSet из
AtomicInteger для условного изменения значения.*/

    public static void useCompareAndSet() {
        AtomicInteger num = new AtomicInteger(5);
        System.out.println("Value: " + num);
        Thread thread1 = new Thread(() -> {
            num.compareAndSet(5, 8);
        });

        try {
            thread1.start();
            thread1.join();

            System.out.println("Final value: " + num);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /*Создайте 5 потоков, каждый из которых будет выводить число от 1 до 100.*/
    public static void createFiveThreads() {

        Thread thread1 = new Thread(() -> {
            for (int i = 1; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + " count:" + i);
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 1; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + " count:" + i);
            }
        });
        Thread thread3 = new Thread(() -> {
            for (int i = 1; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + " count:" + i);
            }
        });
        Thread thread4 = new Thread(() -> {
            for (int i = 1; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + " count:" + i);
            }
        });
        Thread thread5 = new Thread(() -> {
            for (int i = 1; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + " count:" + i);
            }
        });

        try {
            thread1.start();
            thread2.start();
            thread3.start();
            thread4.start();
            thread5.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /*Используйте ключевое слово synchronized для синхронизации нескольких
    методов класса.*/

    public static void synchronizeThreads() {
        Wallet person = new Wallet();
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                person.incrementMoney();
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                person.decrementMoney();
            }
        });

        try {
            thread1.start();
            thread1.join();

            thread2.start();
            thread2.join();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /*Синхронизируйте доступ к переменной через внешний объект.*/
    public static void synchronizeObject() {
        NewPerson person = new NewPerson();
        Object object1 = new Object();
        Object object2 = new Object();

        Thread thread1 = new Thread(() -> {
            synchronized (object1) {
                for (int i = 0; i < 100; i++) {
                    person.incrementMoney();
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (object2) {
                for (int i = 0; i < 50; i++) {
                    person.incrementMoney();
                }
            }
        });
        try {
            thread1.start();
            thread2.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void checkThread() {
        AtomicBoolean isProcessing = new AtomicBoolean(false);

        Thread thread1 = new Thread(() -> {
            if (isProcessing.compareAndSet(false, true)) {
                try {
                    System.out.println(Thread.currentThread().getName() + " is start working");
                } finally {
                    isProcessing.set(false);
                    System.out.println(Thread.currentThread().getName() + " is stop working");
                }
            } else {
                System.out.println(Thread.currentThread().getName() + " cannot start working (already processing)");
            }
        });

        Thread thread2 = new Thread(() -> {
            if (isProcessing.compareAndSet(false, true)) {
                try {
                    System.out.println(Thread.currentThread().getName() + " is start working");
                } finally {
                    isProcessing.set(false);
                    System.out.println(Thread.currentThread().getName() + " is stop working");
                }
            } else {
                System.out.println(Thread.currentThread().getName() + " cannot start working (already processing)");
            }
        });

        thread1.start();
        thread2.start();

    }

    /* Вам нужно создать класс Counter, который будет иметь метод increment и
метод decrement. Эти методы должны увеличивать и уменьшать значение переменной
count в многопоточном режиме. Для синхронизации доступов используйте внешний
объект (не объект класса).*/
    public static void counterDo() {
        Counter counter = new Counter();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                counter.increment();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                counter.decrement();
            }
        });

        thread1.start();
        thread2.start();
    }
/*Описание: Напишите программу, в которой два потока одновременно увеличивают
счетчик. Вместо синхронизации используйте класс AtomicInteger, чтобы избежать
блокировок.*/

    public static void tryAtomicInteger() {
        AtomicCounter counter = new AtomicCounter();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                counter.increment();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                counter.decrement();
            }
        });

        thread1.start();
        thread2.start();
    }
    /*Описание: Напишите программу с двумя потоками, которые одновременно
увеличивают и уменьшают значение счетчика. Ваша задача — обеспечить, чтобы
операция увеличения и уменьшения была выполнена безопасно с помощью
синхронизации.*/
    public static void synchronizeDecrementIncrement() {
        SynchronizeCounter counter = new SynchronizeCounter();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                counter.increment();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                counter.decrement();
            }
        });

        thread1.start();
        thread2.start();
    }
}
class SynchronizeCounter {
    private int count = 0;

    public synchronized void increment() {
        count++;
        System.out.println(Thread.currentThread().getName() + " " + count);
    }

    public synchronized void decrement() {
        count--;
        System.out.println(Thread.currentThread().getName() + " " + count);
    }
}
class AtomicCounter {
    private final AtomicInteger count = new AtomicInteger(0);

    public void increment() {
        count.getAndIncrement();
        System.out.println(Thread.currentThread().getName() + " " + count.get());
    }

    public void decrement() {
        count.getAndDecrement();
        System.out.println(Thread.currentThread().getName() + " " + count.get());
    }
}

class Counter {
    private int count = 0;
    final Object object1 = new Object();

    public void increment() {
        synchronized (object1) {
            count++;
            System.out.println(Thread.currentThread().getName() + " " + count);
        }
    }

    public void decrement() {
        synchronized (object1) {
            count--;
            System.out.println(Thread.currentThread().getName() + " " + count);
        }
    }
}

class NewPerson {
    private int money = 0;

    public synchronized void incrementMoney() {
        money++;
        System.out.println(Thread.currentThread().getName() + " " + money);
    }

    public int getMoney() {
        return money;
    }
}

class Wallet {
    private int money = 0;

    public synchronized void incrementMoney() {
        money++;
        System.out.println(Thread.currentThread().getName() + " " + money);
    }

    public synchronized void decrementMoney() {
        money--;
        System.out.println(Thread.currentThread().getName() + " " + money);
    }

    public int getMoney() {
        return money;
    }
}
