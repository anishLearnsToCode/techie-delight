import java.util.*;

// https://www.techiedelight.com/find-maximum-length-sub-array-equal-number-0s-1s/
public class MaximumLengthSubArrayWithEqualZerosAndOnes {
    public static void main(String[] args) {
        int length = StdIn.getIntInput();
        int[] array = new int[length];
        StdIn.getIntInput(array);
        System.out.println(maximumLengthSubArrayWithEqualZerosAndOnes (array));
        System.out.println(alternate(array));
    }

    private static int maximumLengthSubArrayWithEqualZerosAndOnes(int[] array) {
        int[] indexRange = new int[array.length];
        initializeIncremental(indexRange);
        int startIndex = 0, endIndex = -1;

        for (int index = 0 ; index < array.length ; ) {
            int numberOfZeroes = 0, numberOfOnes = 0;
            if (array[index] == 0) {
                numberOfZeroes++;
            } else {
                numberOfOnes++;
            }

            for (int currentIndex = index + 1 ; currentIndex < array.length ; currentIndex++) {
                if (array[currentIndex] == 0) {
                    numberOfZeroes++;
                } else {
                    numberOfOnes++;
                }

                if (numberOfOnes == numberOfZeroes) {
                    indexRange[index] = currentIndex;
                    if (endIndex - startIndex < currentIndex - index) {
                        endIndex = currentIndex;
                        startIndex = index;
                    }
                }
            }

            index = indexRange[index] + 1;
        }

        return endIndex == -1 ? 0 : endIndex - startIndex + 1;
    }

    private static int alternate(int[] array) {
        convertElementsTo(array, 0, -1);
        Range range = longestSubArrayWihSum(array, 0);
        return Objects.isNull(range) ? 0 : range.endIndex - range.startIndex + 1;
    }

    private static void convertElementsTo(int[] array, int from, int to) {
        for (int index = 0 ; index < array.length ; index++) {
            if (array[index] == from) {
                array[index] = to;
            }
        }
    }

    private static Range longestSubArrayWihSum(int[] array, int desiredSum) {
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
