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

    private static void getLongInput(long[] array) {
        for (int index = 0 ; index < array.length ; index++) {
            array[index] = scanner.nextLong();
        }
    }

    private static void getDoubleInput(double[] array) {
        for (int index = 0 ; index < array.length ; index++) {
            array[index] = scanner.nextDouble();
        }
    }

    private static int[] getArray(int length) {
        int[] array = new int[length];
        getIntInput(array);
        return array;
    }

    private static long[] getLongArray(int length) {
        long[] array = new long[length];
        getLongInput(array);
        return array;
    }

    private static double[] getDoubleArray(int length) {
        double[] array = new double[length];
        getDoubleInput(array);
        return array;
    }

    public static int[] takeInputAndGetIntArray() {
        int length = getIntInput();
        return getArray(length);
    }

    public static long[] takeInputAndGetLongArray() {
        int length = getIntInput();
        return getLongArray(length);
    }

    public static double[] takeInputAndGetDoubleArray() {
        int length = getIntInput();
        return getDoubleArray(length);
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
