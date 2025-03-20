import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {
//        tryVolatile();
//        bankWithdraw();
//        sharedResource();
//        tryLock();
//        tryCountDown();
//        tryCyclicBarrier();
        tryThreadPool();
//        queue();
//        fourThreads();
    }

    /*Создай класс Counter, в котором один поток увеличивает volatile int count, а другой
    поток ждет, пока count достигнет 10, и затем выводит "Достигнуто 10".*/
    public static void tryVolatile() {
        Counter counter = new Counter();

        Thread incrementThread = new Thread(counter::increment);
        Thread waitThread = new Thread(counter::waitForTen);

        incrementThread.start();
        waitThread.start();
    }

    /*Реализуй класс BankAccount, в котором два потока одновременно пытаются снять
деньги. Используй synchronized, чтобы избежать состояния гонки.*/
    public static void bankWithdraw() {
        BankAccount account = new BankAccount();
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                account.withdrawMoney(1);
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                account.withdrawMoney(1);
            }
        });
        thread.start();
        thread2.start();
    }

    /*Создай класс SharedResource, к которому могут обращаться несколько потоков.
Используй ReentrantLock, чтобы предотвратить одновременный доступ.*/
    public static void sharedResource() {
        SharedResource resource = new SharedResource();
        Thread t1 = new Thread(resource::message);
        Thread t2 = new Thread(resource::message);
        t1.start();
        t2.start();
    }
/*Напиши программу, где два потока пытаются одновременно захватить ReentrantLock.
Один поток использует tryLock() и, если не может захватить, выполняет другую работу.*/

    public static void tryLock() {
        ReentrantLock lock = new ReentrantLock();
        Thread t1 = new Thread(() -> {
            if (lock.tryLock()) {
                try {
                    System.out.println(Thread.currentThread().getName() + " successful");
                } finally {
                    lock.unlock();
                }
            } else {
                System.out.println(Thread.currentThread().getName() + " not successful");
            }
        });
        Thread t2 = new Thread(() -> {
            if (lock.tryLock()) {
                try {
                    System.out.println(Thread.currentThread().getName() + " successful");
                } finally {
                    lock.unlock();
                }
            } else {
                System.out.println(Thread.currentThread().getName() + " not successful");
            }
        });
        t1.start();
        t2.start();
    }

    /*Запусти 5 потоков, каждый из которых выполняет задачу 2 секунды, а затем
вызывает countDown(). Основной поток должен дождаться всех потоков перед
продолжением.*/
    public static void tryCountDown() {
        CountDownLatch latch = new CountDownLatch(5);
        Runnable worker = () -> {
            try {
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                latch.countDown();
            }
        };

        new Thread(worker).start();
        new Thread(worker).start();
        new Thread(worker).start();
        new Thread(worker).start();
        new Thread(worker).start();

        try {
            latch.await();
            System.out.println("Все потоки завершены. Основной поток продолжает.");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    /*Реализуй программу, где 3 потока выполняют работу и ждут друг друга на
CyclicBarrier. Когда все потоки достигли барьера, они продолжают выполнение.*/
    public static void tryCyclicBarrier() {
        CyclicBarrier barrier = new CyclicBarrier(3, () -> {
            System.out.println("Все потоки достигли барьера, продолжаем выполнение...");
        });

        Runnable worker = () -> {
            System.out.println(Thread.currentThread().getName() + " начал работу...");
            try {
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName() + " ждет на барьере...");
                barrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " закончил работу...");
        };

        new Thread(worker).start();
        new Thread(worker).start();
        new Thread(worker).start();
    }
    /*Создай класс ParkingLot с Semaphore(3), где 5 машин пытаются припарковаться, но
одновременно могут занять только 3 места.*/

    public static void parking() {
        ParkingLot parkingLot = new ParkingLot(3); // Максимум 3 машины

        for (int i = 1; i <= 5; i++) {
            int carNumber = i;
            new Thread(() -> parkingLot.parkCar(carNumber)).start();
        }
    }

    /*Используй ThreadPoolExecutor для обработки 10 задач, каждая из которых
выполняется 1 секунду.*/
    public static void tryThreadPool() {
        ExecutorService executor = Executors.newFixedThreadPool(5);

        for (int i = 1; i <= 10; i++) {
            final int taskNumber = i;
            executor.submit(() -> {
                try {
                    System.out.println("Task " + taskNumber + " is running.");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("Task " + taskNumber + " was interrupted.");
                }
                System.out.println("Task " + taskNumber + " is completed.");
            });
        }

        executor.shutdown();
        try {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }

        System.out.println("All tasks are completed.");
    }

    /*Реализуй очередь с BlockingQueue, куда один поток добавляет элементы, а другой
извлекает их с интервалом в 1 секунду.*/
    public static void queue() {
        BlockingQueue<Integer> integerBlockingQueue = new ArrayBlockingQueue<>(5);

        Thread put = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    System.out.println("Producing: " + i);
                    integerBlockingQueue.put(i);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        Thread take = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    Integer value = integerBlockingQueue.take();
                    System.out.println("Consuming: " + value);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        put.start();
        take.start();
    }
    /*Есть 4 потока, каждый из которых выполняет часть задачи. Все потоки должны завершить
свои работы, прежде чем главный поток продолжит выполнение. Нужно синхронизировать
потоки так, чтобы они все начинали работать одновременно и завершили выполнение
одновременно.*/

    public static void fourThreads() {
        AtomicInteger i = new AtomicInteger();
        CyclicBarrier barrier = new CyclicBarrier(4, () -> {
            System.out.println("Все потоки достигли барьера, продолжаем выполнение...");
            System.out.println(i);
        });

        Thread thread1 = new Thread(() -> {
            try {
                i.incrementAndGet();
                System.out.println(Thread.currentThread().getName() + " " + i.get());
                barrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        Thread thread2 = new Thread(() -> {
            try {
                i.addAndGet(3);
                System.out.println(Thread.currentThread().getName() + " " + i.get());
                barrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        Thread thread3 = new Thread(() -> {
            try {
                i.incrementAndGet();
                System.out.println(Thread.currentThread().getName() + " " + i.get());
                barrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        Thread thread4 = new Thread(() -> {
            try {
                i.incrementAndGet();
                System.out.println(Thread.currentThread().getName() + " " + i.get());
                barrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }
}

class ParkingLot {
    private final Semaphore semaphore;

    public ParkingLot(int maxCars) {
        this.semaphore = new Semaphore(maxCars);
    }

    public void parkCar(int carNumber) {
        try {
            System.out.println("Car " + carNumber + " is trying to park.");
            semaphore.acquire();
            System.out.println("Car " + carNumber + " parked.");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Car " + carNumber + " was interrupted.");
        } finally {
            System.out.println("Car " + carNumber + " is leaving.");
            semaphore.release();
        }
    }
}

class SharedResource {
    private final ReentrantLock lock = new ReentrantLock();

    public void message() {
        lock.lock();
        try {
            System.out.println("message " + Thread.currentThread().getName());
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

class BankAccount {
    private int money = 1_000;
    private Object obj = new Object();

    public void withdrawMoney(int value) {
        synchronized (obj) {
            money -= value;
            System.out.println(Thread.currentThread().getName() + " " + money);
        }
    }
}

class Counter {
    private volatile int count = 0;

    public void increment() {
        while (count < 10) {
            count++;
            System.out.println("Count: " + count);
        }
    }

    public void waitForTen() {
        while (count < 10) {

        }
        System.out.println("Достигнуто 10");
    }

}
