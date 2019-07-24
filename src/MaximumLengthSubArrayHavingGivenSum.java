import java.util.*;

public class MaximumLengthSubArrayHavingGivenSum {
    public static void main(String[] args) {
        int numberOfElements = StdIn.getIntInput();
        int desiredSum = StdIn.getIntInput();
        int[] array = new int[numberOfElements];
        StdIn.getIntInput(array);
        System.out.println(getMaximumLengthSubArrayRange(array, desiredSum));
    }

    private static Range getMaximumLengthSubArrayRange(int[] array, int desiredSum) {
        Map<Integer, Integer> indexRanges = new HashMap<>();
        Map<Integer, List<Integer>> sumIndices = new HashMap<>();
        sumIndices.put(0, new ArrayList<>());
        sumIndices.get(0).add(-1);

        for (int index = 0, currentSum = 0; index < array.length ; index++) {
            currentSum += array[index];

            if (sumIndices.containsKey(currentSum - desiredSum)) {
                for (int sumIndex : sumIndices.get(currentSum - desiredSum)) {
                    indexRanges.put(sumIndex + 1, index);
                }
            }

            if (sumIndices.containsKey(currentSum)) {
                sumIndices.get(currentSum).add(index);
            } else {
                sumIndices.put(currentSum, new ArrayList<>());
                sumIndices.get(currentSum).add(index);
            }
        }

        if (indexRanges.isEmpty()) {
            return null;
        }

        int startIndex = 0, endIndex = -1;
        for (Map.Entry<Integer, Integer> range : indexRanges.entrySet()) {
            if (endIndex - startIndex < range.getValue() - range.getKey()) {
                endIndex = range.getValue();
                startIndex = range.getKey();
            }
        }

        return new Range(startIndex, endIndex);
    }

    private static void initializeIncremental(int[] array) {
        for (int index = 0 ; index < array.length ; index++) {
            array[index] = index;
        }
    }
}
