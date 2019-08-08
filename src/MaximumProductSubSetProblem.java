import java.util.Arrays;
import java.util.OptionalInt;
import java.util.OptionalLong;

// https://www.techiedelight.com/maximum-product-subset-problem/
public class MaximumProductSubSetProblem {
    public static void main(String[] args) {
        int[] array = StdIn.takeInputAndGetIntArray();
        System.out.println(maximumProductSubSet(array));
    }

    private static long maximumProductSubSet(int[] array) {
        long[] max = new long[array.length], min = new long[array.length];
        max[0] = isPositive(array[0]) ? array[0] : 1;
        min[0] = isNegative(array[0]) ? array[0] : 1;

        for (int index = 1 ; index < array.length ; index++) {
            if (array[index] == 0) {
                max[index] = max[index - 1];
                min[index] = min[index - 1];
            }

            max[index] = isPositive(array[index]) ?
                    array[index] * max[index - 1] :
                    Math.max(array[index] * min[index - 1], max[index - 1]);

            min[index] = isPositive(array[index]) ?
                    Math.min(array[index], array[index] * min[index - 1]) :
                    Math.min(min[index - 1], array[index] * max[index - 1]);
        }

        Printer.print(max);
        Printer.print(min);

        return Arrays.stream(max).max().getAsLong();
    }

    private static boolean isPositive(int number) {
        return number > 0;
    }

    private static boolean isNegative(int number) {
        return number < 0;
    }
}
