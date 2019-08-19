import javafx.util.Pair;

// https://www.techiedelight.com/find-first-or-last-occurrence-of-a-given-number-sorted-array/
public class FindFirstOrLastOccurenceOfGivenNumberInSortedArray {
    public static void main(String[] args) {
        int[] array = StdIn.takeInputAndGetIntArray();
        int element = StdIn.getIntInput();
        System.out.println(firstAndLastOccurrence(array, element));
    }

    private static Pair<Integer, Integer> firstAndLastOccurrence(int[] array, int element) {
        return new Pair<Integer, Integer>(firstIndexOf(array, element), lastIndexOf(array, element));
    }

    private static int firstIndexOf(int[] array, int element) {
        for (int start = 0, end = array.length - 1, mid = end / 2 ; start <= end ; mid = (start + end) / 2) {
            if (array[mid] == element) {
                if (mid - 1 >= 0 && array[mid - 1] == element) {
                    end = mid - 1;
                    continue;
                }

                return mid;
            }

            if (element < array[mid]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return -1;
    }

    private static int lastIndexOf(int[] array, int element) {
        for (int start = 0, end = array.length - 1, mid = end / 2 ; start <= end ; mid = (start + end) / 2) {
            if (element == array[mid]) {
                if (mid + 1 < array.length && array[mid + 1] == element) {
                    start = mid + 1;
                    continue;
                }

                return mid;
            }

            if (element > array[mid]) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return -1;
    }
}
