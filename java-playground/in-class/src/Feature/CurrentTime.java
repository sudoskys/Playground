package Feature;

import java.time.LocalDateTime;

public class CurrentTime {
    public static void main(String[] args) {
        LocalDateTime currentTime = LocalDateTime.now();
        System.out.println("Current Time: " + currentTime);
    }
}