import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// https://www.techiedelight.com/smallest-window-sorting-which-make-array-sorted/
public class SmallestWindowInArraySortingThatMakesEntireArraySorted {
    public static void main(String[] args) {
        List<Integer> array = StdIn.takeInputAndGetList();
        System.out.println(smallestWindowLength(array));
        System.out.println(alternate(array));
    }

    // O(nlogn) solution
    private static int smallestWindowLength(List<Integer> array) {
        List<Integer> sortedArray = new ArrayList<>(array);
        Collections.sort(sortedArray);

        List<Integer> wrongIndices = new ArrayList<>();

        for (int index = 0 ; index < array.size() ; index++) {
            if (!array.get(index).equals(sortedArray.get(index))) {
                wrongIndices.add(index);
            }
        }

        return wrongIndices.isEmpty() ? 0 : wrongIndices.get(wrongIndices.size() - 1) - wrongIndices.get(0) + 1;
    }

    private static int alternate(List<Integer> array) {
        int endIndex = -1;
        for (int index = 1, max = array.get(0) ; index < array.size() ; index++) {
            if (array.get(index) < max) {
                endIndex = index;
            } else {
                max = array.get(index);
            }
        }

        int startIndex = 0;
        for (int index = array.size() - 1, min = array.get(index) ; index >= 0 ; index--) {
            if (array.get(index) > min) {
                startIndex = index;
            } else {
                min = array.get(index);
            }
        }

        return endIndex - startIndex + 1;
    }
}
