import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubArrayWithSumExists {
    public static void main(String[] args) {
        int[] array = StdIn.takeInputAndGetIntArray();
        int sum = StdIn.getIntInput();
        System.out.println(subArraySumExists(array, sum));
    }

    private static boolean subArraySumExists(int[] array, int requiredSum) {
        return !subArraySumRanges(array, requiredSum).isEmpty();
    }

    private static List<Range> subArraySumRanges(int[] array, int requiredSum) {
        List<Range> ranges = new ArrayList<>();
        Map<Integer, List<Integer>> sumIndices = new HashMap<>();
        sumIndices.put(0, new ArrayList<>());
        sumIndices.get(0).add(-1);

        for (int index = 0, startIndex = 0, sum = 0 ; index < array.length ; index++) {
            sum += array[index];

            if (sumIndices.containsKey(sum - requiredSum)) {
                for (int sumIndex : sumIndices.get(sum - requiredSum)) {
                    ranges.add(new Range(sumIndex + 1, index));
                }
            }

            if (!sumIndices.containsKey(sum)) {
                sumIndices.put(sum, new ArrayList<>());
            }
            sumIndices.get(sum).add(index);
        }

        return ranges;
    }
}
