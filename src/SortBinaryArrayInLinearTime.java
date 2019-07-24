import java.util.Scanner;

// https://www.techiedelight.com/sort-binary-array-linear-time/
public class SortBinaryArrayInLinearTime {
    private static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
        int elements = scanner.nextInt();
        int[] array = new int[elements];
        getInput(array);

        int[] sortedArray = sortBinaryArray(array);
        Printer.print(sortedArray);
    }

    private static void getInput(int[] array) {
        for (int index = 0 ; index < array.length ; index++) {
            array[index] = scanner.nextInt();
        }
    }

    private static int[] sortBinaryArray(int[] array) {
        int zeroes = 0;
        for (int element : array) {
            if (element == 0) {
                zeroes++;
            }
        }

        int[] result = new int[array.length];
        for (int index = zeroes ; index < result.length ; index++) {
            result[index] = 1;
        }

        return result;
    }
}
