import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// https://www.techiedelight.com/find-pairs-with-given-difference-array/
public class FindPairsWithDifferenceKInTheArray {
    public static void main(String[] args) {
        int[] array = StdIn.takeInputAndGetIntArray();
        int difference = StdIn.getIntInput();
        System.out.println(allPairsWithDifference(array, difference));
    }

    private static List<Pair<Integer, Integer>> allPairsWithDifference(int[] array, int difference) {
        Set<Integer> distinctElementsInArray = getElementsInArray(array);
        List<Pair<Integer, Integer>> distinctPairs = new ArrayList<>();

        for (int element : distinctElementsInArray) {
            if (distinctElementsInArray.contains(element + difference)) {
                distinctPairs.add(new Pair<>(element, element + difference));
            }
        }

        return distinctPairs;
    }

    private static Set<Integer> getElementsInArray(int[] array) {
        Set<Integer> result = new HashSet<>();
        for (int element : array) {
            result.add(element);
        }
        return result;
    }
}
