import javafx.util.Pair;

import java.util.Objects;

// https://www.techiedelight.com/count-occurrences-number-sorted-array-duplicates/
public class CountOccurrencesOfNumberInSortedArray {
    public static void main(String[] args) {
        int[] array = StdIn.takeInputAndGetIntArray();
        int number = StdIn.getIntInput();
        System.out.println(numberOfOccurrences(array, number));
    }

    private static int numberOfOccurrences(int[] array, int number) {
        Pair<Integer, Integer> indexRanges = getFirstAndLastIndex(array, number);
        Integer lastIndex = indexRanges.getValue();
        Integer startIndex = indexRanges.getKey();
        return Objects.isNull(startIndex) && Objects.isNull(lastIndex) ? 0 :
               Objects.isNull(startIndex) || Objects.isNull(lastIndex) ? 1 :
               lastIndex - startIndex + 1;
    }

    private static Pair<Integer, Integer> getFirstAndLastIndex(int[] array, int number) {
        return new Pair<>(
                firstIndexOf(array, number),
                lastIndexOf(array, number)
        );
    }

    private static Integer firstIndexOf(int[] array, int number) {
        for (int start = 0, end = array.length - 1, middle = end / 2 ; start <= end ; middle = (start + end) / 2) {
            if (array[middle] == number) {
                if (middle - 1 >= 0 && array[middle - 1] == number) {
                    end = middle - 1;
                    continue;
                }

                return middle;
            }

            if (number < array[middle]) {
                end = middle - 1;
            } else {
                start = middle + 1;
            }
        }

        return null;
    }

    private static Integer lastIndexOf(int[] array, int number) {
        for (int start = 0, end = array.length - 1, middle = end / 2 ; start <= end ; middle = (start + end) / 2) {
            if (array[middle] == number) {
                if (middle + 1 < array.length && array[middle + 1] == number) {
                    start = middle + 1;
                    continue;
                }

                return middle;
            }

            if (number < array[middle]) {
                end = middle - 1;
            } else {
                start = middle + 1;
            }
        }

        return null;
    }
}
