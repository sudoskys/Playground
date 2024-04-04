import java.util.Scanner;
import java.util.TreeMap;

public class PrintNumber {

    public static void main(String[] args) {
        //System.out.println("Start");
        Scanner input = new Scanner(System.in);
        String input_string = input.nextLine();
        //System.out.println(input_string);
        TreeMap<Character, Integer> map = new TreeMap<>();
        for (char c : input_string.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        System.out.println(map);
    }
}
