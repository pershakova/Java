package MultiThreading;
import java.util.Arrays;

public class MainMultiThreading {
    static final int SIZE = 10000000;
    static final int HALF = SIZE / 2;
    static float[] arr = new float[SIZE];

    public static void main(String[] args) throws InterruptedException {
        methodMain();

        methodTwoThreads();
    }

    private static void methodMain(){
        float[] array = initializeArray(arr);
        long startTime = System.currentTimeMillis();
        calculateItems(array, 0);
        System.out.println(System.currentTimeMillis() - startTime);
    }

    private static float[] initializeArray(float[] array){
        Arrays.fill(array, 1);
        return array;
    }

    private static float[] calculateItems(float[] arr, int offset){
        for (int i =0; i< arr.length; i++){
            float currentValue = arr[i];
            arr[i] = computeValue(i+offset, currentValue);
        }
        return arr;
    }

    private static float computeValue(int index, float currentValue){
        return (float)(currentValue * Math.sin(0.2f + index/5) * Math.cos(0.2f + index/5) * Math.cos(0.4f + index/2));
    }

    private static void methodTwoThreads() throws InterruptedException {
        initializeArray(arr);

        float[] arrayDividedPart1 = new float[HALF];
        float[] arrayDividedPart2 = new float[HALF];

        long startTime = System.currentTimeMillis();

        System.arraycopy(arr, 0, arrayDividedPart1, 0, HALF);
        System.arraycopy(arr, HALF, arrayDividedPart2, 0, HALF);

        Thread thread1 = new Thread(() -> calculateAndGlue(arrayDividedPart1, 0));
        Thread thread2 = new Thread(() -> calculateAndGlue(arrayDividedPart2, HALF));

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(System.currentTimeMillis() - startTime);
    }

    private static void calculateAndGlue(float[] array, int destinationPosition){
        float[] calculatedArray = calculateItems(array, destinationPosition);
        System.arraycopy(calculatedArray, 0, arr, destinationPosition, HALF);
    }
}
