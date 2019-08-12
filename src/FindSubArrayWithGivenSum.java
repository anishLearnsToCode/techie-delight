import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://www.techiedelight.com/find-subarrays-given-sum-array/
public class FindSubArrayWithGivenSum {
    public static void main(String[] args) {
        int[] array = StdIn.takeInputAndGetIntArray();
        int sum = StdIn.getIntInput();
        System.out.println(subArraysWithSum(array, sum));
    }

    private static List<Pair<Integer, Integer>> subArraysWithSum(int[] array, int sum) {
        int[] sumSubArray = sumSubArrayFromLeft(array);
        List<Pair<Integer, Integer>> subArrayIndexPairs = new ArrayList<>();
        Map<Integer, List<Integer>> sumIndices = getSumIndices();

        for (int index = 0 ; index < sumSubArray.length ; index++) {
            int requiredSum = sumSubArray[index] - sum;
            if (sumIndices.containsKey(requiredSum)) {
                subArrayIndexPairs.addAll(indexPairs(sumIndices.get(requiredSum), index));
            }

            if (!sumIndices.containsKey(sumSubArray[index])) {
                sumIndices.put(sumSubArray[index], new ArrayList<>());
            }

            sumIndices.get(sumSubArray[index]).add(index);
        }

        return subArrayIndexPairs;
    }

    private static List<Pair<Integer, Integer>> indexPairs(List<Integer> indices, int endIndex) {
        List<Pair<Integer, Integer>> indexPairs = new ArrayList<>();
        for (int startIndex : indices) {
            indexPairs.add(new Pair<>(startIndex + 1, endIndex));
        }
        return indexPairs;
    }

    private static int[] sumSubArrayFromLeft(int[] array) {
        int[] result = new int[array.length];
        result[0] = array[0];
        for (int index = 1 ; index < array.length ; index++) {
            result[index] = result[index - 1] + array[index];
        }
        return result;
    }

    private static Map<Integer, List<Integer>> getSumIndices() {
        Map<Integer, List<Integer>> sumIndices = new HashMap<>();
        sumIndices.put(0, new ArrayList<>());
        sumIndices.get(0).add(-1);
        return sumIndices;
    }
}
