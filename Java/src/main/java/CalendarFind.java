import java.util.Calendar;
import java.util.Scanner;

public class CalendarFind {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //System.out.println("Enter year: ");
        int year = sc.nextInt();
        //System.out.println("Enter month: ");
        int month = sc.nextInt();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1);

        int firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        System.out.println("\t日\t一\t二\t三\t四\t五\t六");
        for (int i = 0; i < firstDayOfWeek; i++) {
            System.out.print("\t");
        }

        for (int day = 1; day <= daysInMonth; day++) {
            System.out.printf("\t%d", day);
            if ((day + firstDayOfWeek) % 7 == 0 || day == daysInMonth) {
                System.out.println();
            }
        }
    }
}