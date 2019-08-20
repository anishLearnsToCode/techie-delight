// https://www.techiedelight.com/search-nearly-sorted-array-ologn-time/
public class SearchInNearlySortedArray {
    public static void main(String[] args) {
        int[] array = StdIn.takeInputAndGetIntArray();
        int number = StdIn.getIntInput();
        System.out.println(indexOf(array, number));
    }

    private static int indexOf(int[] array, int number) {
        for (int start = 0, end = array.length, middle = end / 2 ; start <= end && middle < array.length ; middle = (start + end) / 2) {
            if (array[middle] == number) {
                return middle;
            }

            if (middle - 1 >= 0 && array[middle - 1] == number) {
                return middle - 1;
            } else if (middle + 1 < array.length && array[middle + 1] == number) {
                return middle + 1;
            }

            if (array[middle] < number) {
                start = middle + 1;
            } else {
                end = middle - 1;
            }
        }

        return -1;
    }
}
