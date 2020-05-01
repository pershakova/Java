package Generic;
import java.util.ArrayList;
import java.util.Arrays;

public class MainGeneric {

    public static void main(String[] args) {

        //1
        Boolean[] array = new Boolean[2];

        array[0] = false;
        array[1] = true;

        swap(array, 0, 1);
        System.out.println(Arrays.deepToString(array));

        //2
        Integer[] arrayInt = new Integer[2];

        arrayInt[0] = 2;
        arrayInt[1] = 3;

        ArrayList<Integer> arrList = toArrayList(arrayInt);

        //3
        Apple apple1 = new Apple();
        Apple apple2 = new Apple();
        Apple apple3 = new Apple();

        Orange orange1 = new Orange();
        Orange orange2 = new Orange();
        Orange orange3 = new Orange();

        Box<Apple> box1 = new Box<>(apple1, apple2, apple3);
        Box<Orange> box2 = new Box<>(orange1, orange2);

        System.out.println(box1.compare(box2));
        box2.add(orange3);
        System.out.println(box1.compare(box2));

        Box<Orange> box3 = new Box<>();
        box2.moveTo(box3);
        System.out.println("Box3: " + box3.getItems().size());
        System.out.println("Box2: " + box2.getItems().size());
    }

    public static void swap(Object[] array, int index1, int index2) {
        Object tmp = array[index1];
        array[index1] = array[index2];
        array[index2] = tmp;
    }

    public static <T> ArrayList<T> toArrayList(T[] array) {
        return new ArrayList<>(Arrays.asList(array));
    }
}
