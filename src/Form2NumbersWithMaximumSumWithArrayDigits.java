import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://www.techiedelight.com/find-two-numbers-maximum-sum-array-digits/
public class Form2NumbersWithMaximumSumWithArrayDigits {
    public static void main(String[] args) {
        int[] array = StdIn.takeInputAndGetIntArray();
        System.out.println(numbersForMaximumSum(array));
    }

    private static Pair<List<Integer>, List<Integer>> numbersForMaximumSum(int[] array) {
        final int[] sortedArray = reverse(Arrays.stream(array).sorted().toArray());
        List<Integer> first = getAlternativeElementsFrom(sortedArray, 0);
        List<Integer> second = getAlternativeElementsFrom(sortedArray, 1);
        return new Pair<>(first, second);
    }

    private static int[] reverse (int[] array) {
        int[] result = new int[array.length];
        for (int index = 0 ; index < array.length ; index++) {
            result[array.length - index - 1] = array[index];
        }
        return result;
    }

    private static List<Integer> getAlternativeElementsFrom(int[] array, int startIndex) {
        List<Integer> list = new ArrayList<>();
        for (int index = startIndex ; index < array.length ; index += 2) {
            list.add(array[index]);
        }
        return list;
    }
}
