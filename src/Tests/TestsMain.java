package Tests;

public class TestsMain {
    final static String ExceptionText = "4 is not found";
    public static void main(String[] args){

    }

    public static int[] arrayAfterLastFour(int[] arr) {
        for (int i = arr.length-1; i >= 0; i--) {
            if (arr[i] == 4) {
                int[] arrayAfterFour = new int[arr.length -1 - i];
                int m=0;
                for (int j = i+1; j < arr.length; j++) {
                    arrayAfterFour[m] = arr[j];
                    System.out.println(arrayAfterFour[m] + ", ");
                    m++;
                }
                return arrayAfterFour;
            }
        }
        throw new RuntimeException(ExceptionText);
    }

    public static boolean arrayFromFourAndOne (int[] inputArr) {
        boolean oneIsFound = false;
        boolean fourIsFound = false;
        for (int a : inputArr) {
            if (a != 1 && a !=4)
                return false;
            if (a == 1)
                oneIsFound = true;
            if (a == 4)
                fourIsFound = true;
        }
        return (oneIsFound && fourIsFound);
    }
}
