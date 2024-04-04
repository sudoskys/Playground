import java.util.Scanner;

public class StringFind {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = "AA01234A01234aa01234aA01234Aa01234aa";
        String s = "AA";

        // Part 1: Find the index of the substring in the string
        int index = str.indexOf(s);
        System.out.println(index);

        // Part 2: Ignore case when finding the substring
        String lowerCaseStr = str.toLowerCase();
        String lowerCaseS = s.toLowerCase();

        int i = 0;
        while ((i = lowerCaseStr.indexOf(lowerCaseS, i)) != -1) {
            System.out.print(i + " ");
            i++;
        }
    }
}