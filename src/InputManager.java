import java.util.Scanner;

public class InputManager {
    private final static Scanner sc = new Scanner(System.in);

    public static int getInt(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(sc.nextLine()); // читаємо рядок і парсимо в число
            } catch (NumberFormatException e) {
                System.out.println("❌ Please enter a valid integer!");
            }
        }
    }

    public static String getString(String message) {
        System.out.print(message);
        return sc.nextLine();
    }
    public static int getPositiveInt(String message) {
        while (true) {
            int value = getInt(message); // викликаємо вже готовий метод
            if (value > 0) {
                return value;
            }
            System.out.println("❌ Value must be greater than 0!");
        }
    }
}