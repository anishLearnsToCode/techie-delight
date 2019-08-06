import java.util.ArrayList;
import java.util.List;

public class LongestIncreasingSubSequence {
    public static void main(String[] args) {
        int[] array = StdIn.takeInputAndGetIntArray();
        int result = longestIncreasingSubSequenceLength(array);
        System.out.println(result);
    }

    private static int longestIncreasingSubSequenceLength(int[] array) {
        List<Integer> increasingSubSequence = new ArrayList<>();
        increasingSubSequence.add(array[0]);

        for (int index = 1, max = array[0] ; index < array.length ; index++) {
            if (array[index] > max) {
                increasingSubSequence.add(array[index]);
            } else if (array[index] < max) {
                int nextLargestIndex = getNextLargestElementIndex(increasingSubSequence, array[index]);
                increasingSubSequence.set(nextLargestIndex, array[index]);
            }

            max = increasingSubSequence.get(increasingSubSequence.size() - 1);
        }

        return increasingSubSequence.size();
    }

    private static int getNextLargestElementIndex(List<Integer> list, int element) {
        return getFirstNextLargestElementIndex(list, element);
    }

    private static int getFirstNextLargestElementIndex(List<Integer> list, int element) {
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
