import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// https://www.techiedelight.com/surpasser-count-each-element-array/
public class FindSurpasserCountInEachElementOfArray {
    public static void main(String[] args) {
        int[] array = StdIn.takeInputAndGetIntArray();
        Printer.print(surpasserCountArrayOf(array));
    }

    private static int[] surpasserCountArrayOf(int[] array) {
        List<Pair<Integer, Integer>> elementIndices = getElementsWithIndices(array);
        elementIndices.sort(new ElementIndexPairComparator());
        return surpasserCountArrayOf(elementIndices);
    }

    private static List<Pair<Integer, Integer>> getElementsWithIndices(int[] array) {
        List<Pair<Integer, Integer>>  elementIndices = new ArrayList<>();
        for (int index = 0 ; index < array.length ; index++) {
            elementIndices.add(new Pair<>(array[index], index));
        }
        return elementIndices;
    }

    private static int[] surpasserCountArrayOf(List<Pair<Integer, Integer>> elementIndices) {
        int[] result = new int[elementIndices.size()];
        int surpasser = elementIndices.size() - 1;
        for (Pair<Integer, Integer> pair : elementIndices) {
            result[pair.getValue()] = surpasser--;
        }

        return result;
    }

    private static class ElementIndexPairComparator implements Comparator<Pair<Integer, Integer>> {

        @Override
        public int compare(Pair<Integer, Integer> first, Pair<Integer, Integer> second) {
            return Integer.compare(first.getKey(), second.getKey());
        }
    }
}
