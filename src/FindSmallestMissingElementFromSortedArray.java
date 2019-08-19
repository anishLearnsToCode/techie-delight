// https://www.techiedelight.com/find-smallest-missing-element-sorted-array/
public class FindSmallestMissingElementFromSortedArray {
    public static void main(String[] args) {
        int[] array = StdIn.takeInputAndGetIntArray();
        System.out.println(smallestMissingElement(array));
    }

    private static int smallestMissingElement(int[] array) {
        for (int start = 0, end = array.length - 1, middle = end / 2 ; start <= end ; middle = (start + end) / 2) {
            if (array[middle] != middle) {
                if (middle == 0) {
                    return 0;
                } else if (array[middle - 1] == middle - 1) {
                    return middle;
                }
                end = middle - 1;
            }

            if (array[middle] == middle) {
                start = middle + 1;
            } else {
                end = middle - 1;
            }
        }

        return array.length;
    }
}
