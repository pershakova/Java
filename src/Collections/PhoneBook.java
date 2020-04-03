package Collections;

import java.util.*;

public class PhoneBook {

    private Map<String, List<String>> listMap;

    public PhoneBook(){
        this.listMap = getInitialList();
    }

    public void add(String name, String number){
        if (name.equals("")){
            System.out.println("Name is empty");
            return;
        }
        if (number.equals("")){
            System.out.println("Number is empty");
            return;
        }

        if (!listMap.containsKey(name)){
            listMap.put(name, feelArray(number));
            System.out.printf("Name %s with phone %s was added%n", name, number);
        }
        else{
            List<String> phones = listMap.get(name);
            if (!phones.contains(number)){
                phones.add(number);
                System.out.printf("Name %s with phone %s was added%n", name, number);
            }
            else{
                System.out.printf("Name %s with phone %s already exists%n", name, number);
            }
        }
    }

    public void get(String name){
        if (name.equals("")){
            System.out.println("Name is empty");
            return;
        }

        if (!listMap.containsKey(name)){
            System.out.printf("Name %s does not exist in phone book %n", name);
            return;
        }

        System.out.printf("Numbers for name %s:%n", name);

        for (String phone : listMap.get(name)){
            System.out.println(phone);
        }
    }

    private  Map<String, List<String>> getInitialList(){
        Map<String, List<String>> map = new HashMap<>();

        map.put("cat", feelArray("123", "456", "911"));
        map.put("dog", feelArray("345"));
        map.put("rat", feelArray("678", "ac5"));
        map.put("bat", feelArray("300", "790"));
        map.put("pig", feelArray("123", "765", "987"));
        map.put("hen", feelArray("908"));

        return  map;
    }

    private ArrayList<String> feelArray(String...items){
        ArrayList<String> temp = new ArrayList<>();

        Collections.addAll(temp, items);

        return temp;
    }
}
