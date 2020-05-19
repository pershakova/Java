package Threads;

public class Threads {
    public static void main(String[] args){
        ThreadSequence runnable1=new ThreadSequence(1, "A");
        ThreadSequence runnable2=new ThreadSequence(2, "B");
        ThreadSequence runnable3=new ThreadSequence(0, "C");

        Thread t1=new Thread(runnable1);
        Thread t2=new Thread(runnable2);
        Thread t3=new Thread(runnable3);

        t1.start();
        t2.start();
        t3.start();
    }
}
