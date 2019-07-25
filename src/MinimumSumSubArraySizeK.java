// https://www.techiedelight.com/find-minimum-sum-subarray-given-size-k/
public class MinimumSumSubArraySizeK {
    public static void main(String[] args) {
        int[] array = StdIn.takenputAndGetArray();
        int size = StdIn.getIntInput();
        System.out.println(minimumSumSubArrayOfSaxe(array, size));
    }

    private static int minimumSumSubArrayOfSaxe(int[] array, int size) {
        int sum = 0;
        for (int index = 0 ; index < size ; index++) {
            sum += array[index];
        }

        int min = sum;
        for (int index = 0 ; index < array.length - size ; index++) {
            sum += array[index + size] - array[index];
            min = Math.min(min, sum);
        }

        return min;
    }
}
