import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String str = scan.next();
        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < str.length(); i++) {
            Character c = Character.toUpperCase(str.charAt(i));
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        List<Map.Entry<Character, Integer>> entryList = new ArrayList<>(map.entrySet());
        entryList.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));

        if (entryList.size() > 1 && entryList.get(0).getValue().equals(entryList.get(1).getValue()))
            System.out.println("?");
        else System.out.println(entryList.get(0).getKey());
    }
}