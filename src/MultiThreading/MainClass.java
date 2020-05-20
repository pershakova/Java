package MultiThreading;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MainClass {
    public static final int CARS_COUNT = 4;
    static boolean start = false;
    public static void main(String[] args) {

        CyclicBarrier cb = new CyclicBarrier(CARS_COUNT);
        final CountDownLatch cdl = new CountDownLatch(CARS_COUNT);
        Lock lock = new ReentrantLock();

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];

        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }

       for (int i = 0; i < cars.length; i++) {
            final int w = i;
            new Thread(() -> {
                try {
                    cars[w].prepare();
                    cb.await();
                    try{
                        lock.lock();
                        if (!start){
                            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
                            start = true;
                        }
                    }
                    finally {
                        lock.unlock();
                    }
                    cars[w].run();
                    cdl.countDown();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }).start();
        }

        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }
}
