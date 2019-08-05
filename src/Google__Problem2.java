import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Google__Problem2 {

    static Integer[] solution(Integer[] N, int K) {
        List<Integer> allPossibleSubArrayIndices = getPossibleSubArrayIndices(N, K);
        return maximumSubArray(N, allPossibleSubArrayIndices, K);
    }

    private static Integer[] maximumSubArray(Integer[] array, List<Integer> subArrayIndices, int subArrayLength) {
        int maxIndex = getMaxSubArrayIndex(array, subArrayIndices, subArrayLength);
        return subArray(array, maxIndex, maxIndex + subArrayLength);
    }

    private static int getMaxSubArrayIndex(Integer[] array, List<Integer> subArrayIndices, int subArrayLength) {
        int maxIndex = subArrayIndices.get(0);
        for (int index = 1 ; index < subArrayIndices.size() ; index++) {
            maxIndex = getMaxSubArrayIndex(array, maxIndex, index, subArrayLength);
        }
        return maxIndex;
    }

    private static int getMaxSubArrayIndex(Integer[] array, int maxIndex, int otherIndex, int subArrayLength) {
        for (int index = maxIndex, currentCount = 0 ; index < maxIndex + subArrayLength ; index++, currentCount++) {
            if (array[index] > array[otherIndex + currentCount]) {
                return maxIndex;
            } else if (array[index] < array[otherIndex + currentCount]) {
                return otherIndex;
            }
        }

        return maxIndex;
    }

    private static Integer[] subArray(Integer[] array, int startIndex, int endIndex) {
        Integer[] result = new Integer[endIndex - startIndex];
        for (int index = startIndex, currentIndex = 0 ; index < endIndex ; index++, currentIndex++) {
            result[currentIndex] = array[index];
        }
        return result;
    }

    private static List<Integer> getPossibleSubArrayIndices(Integer[] array, int subArrayLength) {
        int max = Integer.MIN_VALUE;
        List<Integer> result = new ArrayList<>();

        for (int index = 0 ; index < array.length - subArrayLength + 1 ; index++) {
            if (array[index] > max) {
                max = array[index];
                result = new ArrayList<>();
                result.add(index);
            } else if (max == array[index]) {
                result.add(index);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Integer[] N = getIntegerArray(in.next());
        Integer K = Integer.parseInt(in.next());
        System.out.print(fromIntArray(solution(N, K)));
    }

    private static Integer[] getIntegerArray(String str) {
        return Stream.of(str.split("\\,"))
                .map(Integer::valueOf)
                .toArray(Integer[]::new);
    }

    private static String fromIntArray(Integer[] input) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length; i++) {
            sb.append(input[i]);
            if (i < input.length - 1) {
                sb.append(',');
            }
        }
        return sb.toString();
    }
}
