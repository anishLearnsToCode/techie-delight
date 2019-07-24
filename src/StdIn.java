import java.util.Scanner;

public class StdIn {
    private static Scanner scanner = new Scanner(System.in);

    public static void getIntInput(int[] array) {
        getInput(array);
    }

    public static int getIntInput() {
        return scanner.nextInt();
    }

    private static void getInput(int[] array) {
        for (int index = 0 ; index < array.length ; index++) {
            array[index] = scanner.nextInt();
        }
    }
}
