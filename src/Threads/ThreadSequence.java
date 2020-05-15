package Threads;

public class ThreadSequence implements Runnable {
    public int SIZE=5;
    static int  number=1;
    int order;
    String letter;
    static final Object lock=new Object();

    ThreadSequence(int order, String letter)
    {
        this.order=order;
        this.letter = letter;
    }

    @Override
    public void run() {
        while (number < SIZE*3-1) {
            synchronized (lock) {
                while (number % 3 != order) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(letter);
                number++;
                lock.notifyAll();
            }
        }
    }
}
