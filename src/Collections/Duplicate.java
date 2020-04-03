package Collections;

import java.util.*;

public class Duplicate {
    public static void main(String[] args){

        printUniqueItems();

        PhoneBook phoneBook = new PhoneBook();

        phoneBook.add("bat","300");
        phoneBook.add("cat","301");
        phoneBook.add("goat","302");
        phoneBook.get("goat");
        phoneBook.get("bat");
        phoneBook.get("owl");
        phoneBook.get(null);
    }

    private static void printUniqueItems(){
        List<String> list = getInitialList();

        Set<String> set = new HashSet<>(list);

        for (String uniqueItem : set) {
            System.out.printf("%s: %d%n", uniqueItem, Collections.frequency(list, uniqueItem));
        }
    }

    private static List<String> getInitialList(){
        List<String> list = new ArrayList<>();

        list.add("cat");
        list.add("dog");
        list.add("rat");
        list.add("bat");
        list.add("cat");
        list.add("cat");
        list.add("rat");
        list.add("dad");
        list.add("hen");
        list.add("pig");
        list.add("dog");
        list.add("cat");

        return  list;
    }
}
