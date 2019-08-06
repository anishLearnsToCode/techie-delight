import java.util.Arrays;

public class MaximumProductSubArray {
    public static void main(String[] args) {
        int[] array = StdIn.takeInputAndGetArray();
        System.out.println(maximumProductSubArray(array));
    }

    private static long maximumProductSubArray(int[] array) {
        long[] max = new long[array.length];
        long[] min = new long[array.length];
        max[0] = min[0] = array[0];

        for (int index = 1 ; index < array.length ; index++) {
            if (array[index] == 0) {
                max[index] = min[index] = 0;
                continue;
            }

            max[index] = isPositive(array[index])
                    ? Math.max(array[index], array[index] * max[index - 1])
                    : Math.max(array[index], array[index] * min[index - 1]);

            min[index] = isPositive(array[index])
                    ? array[index] * min[index - 1]
                    : Math.min(array[index], array[index] * max[index - 1]);
        }

        return Arrays.stream(max).max().getAsLong();
    }

    private static boolean isPositive(int element) {
        return element > 0;
    }
}
