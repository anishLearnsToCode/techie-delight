import java.util.ArrayList;
import java.util.List;

public class LongestDecreasingSubSequence {
    public static void main(String[] args) {
        int[] array = StdIn.takeInputAndGetIntArray();
        System.out.println(longestDecreasingSubSequence(array));
    }

    private static int longestDecreasingSubSequence(int[] array) {
        negate(array);
        return longestIncreasingSubSequence(array);
    }

    private static void negate(int[] array) {
        for (int index = 0 ; index < array.length ; array[index] *= -1, index++) {}
    }

    private static int longestIncreasingSubSequence(int[] array) {
        List<Integer> increasingSequence = new ArrayList<>();
        increasingSequence.add(array[0]);

        for (int index = 1, max = array[0] ; index < array.length ; index++) {
            if (array[index] > max) {
                increasingSequence.add(array[index]);
            } else if (array[index] < max) {
                int nextLargestIndex = getNextLargestIndex(increasingSequence, array[index]);
                increasingSequence.set(nextLargestIndex, array[index]);
            }

            max = increasingSequence.get(increasingSequence.size() - 1);
        }

        return increasingSequence.size();
    }

    private static int getNextLargestIndex(List<Integer> list, int element) {
        int middle = list.size() / 2 ;
        for (int startIndex = 0, endIndex = list.size() ; ; middle = (startIndex + endIndex) / 2) {
            if (list.get(middle) <= element) {
                if (middle == list.size() - 1) {
                    return middle;
                }
                if (list.get(middle + 1) > element) {
                    return middle + 1;
                }
                startIndex = middle;
            } else {
                if (middle == 0) {
                    return middle;
                }
                if (list.get(middle - 1) < element) {
                    return middle;
                }
                endIndex = middle;
            }
        }
    }
}
