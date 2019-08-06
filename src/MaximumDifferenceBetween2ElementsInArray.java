// https://www.techiedelight.com/find-maximum-difference-between-two-elements-array/
public class MaximumDifferenceBetween2ElementsInArray {
    public static void main(String[] args) {
        int[] array = StdIn.takeInputAndGetIntArray();
        System.out.println(maximumDifference(array));
    }

    private static int maximumDifference(int[] array) {
        int[] bestDifferences = getBestPossibleDifferences(array);
        return max(bestDifferences);
    }

    private static int max(int[] array) {
        int max = Integer.MIN_VALUE;
        for (int element : array) {
            if (element > max) {
                max = element;
            }
        }
        return max;
    }

    private static int[] getBestPossibleDifferences(int[] array) {
        int[] result = new int[array.length];
        result[result.length - 1] = 0;

        for (int index = result.length - 2, max = array[array.length - 1]; index >= 0 ; index--) {
            if (array[index] > max) {
                max = array[index];
                result[index] = 0;
            } else {
                result[index] = max - array[index];
            }
        }

        return result;
    }
}
