import java.util.ArrayList;
import java.util.List;

public class TripletWithGivenSumInArray {
    public static void main(String[] args) {
        int[] array = StdIn.takeInputAndGetIntArray();
        int sum = StdIn.getIntInput();
        System.out.println(tripletWithGivenSumInArrayExists(array, sum));
        System.out.println(sumExistsInArraySubSequence(array, 0, sum, 3));
    }

    private static boolean tripletWithGivenSumInArrayExists(int[] array, int sum) {
        return !subArrayIndicesWithGivenSum(array, sum, 3).isEmpty() ;
    }

    private static List<Integer> subArrayIndicesWithGivenSum(int[] array, int sum, int subArrayLength) {
        List<Integer> indices = new ArrayList<>();
        int initialSum = subArraySum(array, 0, subArrayLength);
        if (initialSum == sum) {
            indices.add(0);
        }

        for (int index = 0 ; index < array.length - subArrayLength ; index++) {
            initialSum += array[index + subArrayLength] - array[index];
            if (initialSum == sum) {
                indices.add(index + 1);
            }
        }

        return indices;
    }

    private static int subArraySum(int[] array, int startIndex, int subArrayLength) {
        int sum = 0;
        for (int index = startIndex ; index < startIndex + subArrayLength ; index++) {
            sum += array[index];
        }
        return sum;
    }

    private static boolean  sumExistsInArraySubSequence(int[] array, int index, int sum, int subSequenceLength) {
        if (sum < 0 || index >= array.length) {
            return false;
        }

        if (subSequenceLength == 0) {
            return sum == 0;
        }

        return sumExistsInArraySubSequence(array, index + 1, sum - array[index], subSequenceLength - 1)
                || sumExistsInArraySubSequence(array, index + 1, sum, subSequenceLength);
    }
}
