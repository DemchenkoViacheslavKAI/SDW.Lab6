import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        System.setErr(new PrintStream(System.err, true, StandardCharsets.UTF_8));

        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);

        System.out.println("=== Лабораторна робота №6 ===");
        System.out.println("Дослідження колекції ArrayList: циклічний зсув списку цілих чисел вправо.");

        try {
            int size = readPositiveInt(scanner, "\nВведіть розмірність списку: ");
            List<Integer> numbers = readIntegerList(scanner, size);
            int shift = readShift(scanner, size);

            System.out.println("\nПочаткові дані:");
            printList("Початковий список", numbers);
            System.out.println("N = " + shift);

            System.out.println("\nВиконуємо циклічний зсув вправо...");
            List<Integer> shiftedNumbers = shiftRight(numbers, shift);

            printList("Список після зсуву", shiftedNumbers);
            System.out.println("Операцію завершено успішно.");
        } finally {
            scanner.close();
        }
    }

    private static List<Integer> readIntegerList(Scanner scanner, int size) {
        List<Integer> numbers = new ArrayList<>();

        System.out.println("\nВведіть " + size + " цілих чисел для списку ArrayList:");
        for (int i = 0; i < size; i++) {
            int value = readInteger(scanner, "Елемент [" + i + "]: ");
            numbers.add(value);
            System.out.println("Додано число " + value + " до списку.");
        }

        return numbers;
    }

    private static List<Integer> shiftRight(List<Integer> numbers, int shift) {
        List<Integer> shiftedNumbers = new ArrayList<>(numbers);
        int normalizedShift = shift % shiftedNumbers.size();

        System.out.println("Фактичний зсув після нормалізації: " + normalizedShift);
        Collections.rotate(shiftedNumbers, normalizedShift);

        return shiftedNumbers;
    }

    private static int readShift(Scanner scanner, int size) {
        while (true) {
            int shift = readInteger(scanner, "Введіть N для зсуву вправо (0.." + size + "): ");

            if (shift >= 0 && shift <= size) {
                return shift;
            }

            System.out.println("Помилка: N має бути в межах від 0 до " + size + ".");
        }
    }

    private static int readPositiveInt(Scanner scanner, String prompt) {
        while (true) {
            int value = readInteger(scanner, prompt);

            if (value > 0) {
                return value;
            }

            System.out.println("Помилка: введіть додатне ціле число.");
        }
    }

    private static int readInteger(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Помилка: введіть коректне ціле число.");
            }
        }
    }

    private static void printList(String title, List<Integer> numbers) {
        System.out.println(title + ": " + numbers);
    }
}
