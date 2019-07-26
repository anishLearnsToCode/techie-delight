import java.util.ArrayList;
import java.util.List;
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

    private static int[] getArray(int length) {
        int[] array = new int[length];
        getIntInput(array);
        return array;
    }

    public static int[] takeInputAndGetArray() {
        int length = getIntInput();
        return getArray(length);
    }

    public static List<Integer> takeInputAndGetList() {
        int length = getIntInput();
        List<Integer> list = new ArrayList<>();
        for (int index = 0 ; index < length ; index++) {
            int value = scanner.nextInt();
            list.add(value);
        }

        return list;
    }
}
