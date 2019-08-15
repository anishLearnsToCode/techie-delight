import javafx.util.Pair;

// https://www.techiedelight.com/find-maximum-length-sequence-continuous-ones-sliding-window/
public class FindMaximumLengthSequenceOfOneByReplacingOneZero {
    public static void main(String[] args) {
        int[] binaryArray = StdIn.takeInputAndGetIntArray();
        System.out.println(zeroReplacementIndex(binaryArray));
    }

    private static int zeroReplacementIndex(int[] array) {
        replaceAllZeroWithNegativeOne(array);
        Pair<Integer, Integer> maximumSubArraySumIndices = getMaximumSumSubarrayIndices(array);
        int zeroIndex = findFirstNegativeOne(array, maximumSubArraySumIndices.getKey());
        return zeroIndex;
    }

    private static void replaceAllZeroWithNegativeOne(int[] array) {
        for (int index = 0 ; index < array.length ; index++) {
            array[index] = array[index] == 0 ? -1 : array[index] ;
        }
    }

    private static Pair<Integer, Integer> getMaximumSumSubarrayIndices(int[] array) {
        int endIndex = -1, max = Integer.MIN_VALUE;

        for (int index = 0, sum = 0 ; index < array.length ; index++) {
            sum += array[index];
            endIndex = sum > max ? index : endIndex;
            sum = sum < 0 ? 0 : sum;
            max = Math.max(max, sum);
        }

        return new Pair<>(endIndex - max, endIndex);
    }

    private static int findFirstNegativeOne(int[] array, int startIndex) {
        for (int index = startIndex ; index < array.length ; index++) {
            if (array[index] == -1) {
                return index;
            }
        }
        return -1;
    }
}
