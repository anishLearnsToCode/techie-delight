import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// https://www.techiedelight.com/find-duplicate-element-limited-range-array/
public class DuplicateElementInLimitedRangeArray {
    public static void main(String[] args) {
        int elements = StdIn.getIntInput();
        int[] array = new int[elements];
        StdIn.getIntInput(array);

        int result = getDuplicateInArray(array);
        System.out.println(result);
        System.out.println(alternate(array));
    }

    private static int getDuplicateInArray(int[] array) {
        Set<Integer> elements = new HashSet<>();
        for (int element : array) {
            if (elements.contains(element)) {
                return element;
            }

            elements.add(element);
        }

        return -1;
    }

    private static int alternate(int[] array) {
        int actualSum = Arrays.stream(array).sum();
        int expectedSum = (array.length * (array.length + 1)) / 2;
        return array.length - (expectedSum - actualSum);
    }
}
