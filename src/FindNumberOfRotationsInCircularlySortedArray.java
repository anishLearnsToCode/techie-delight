// https://www.techiedelight.com/find-number-rotations-circularly-sorted-array/
public class FindNumberOfRotationsInCircularlySortedArray {
    public static void main(String[] args) {
        int[] array = StdIn.takeInputAndGetIntArray();
        System.out.println(numberOfRotationsIn(array));
        System.out.println(numberOfRotations(array));
    }

    private static int numberOfRotationsIn(int[] array) {
        for (int index = 0; index < array.length ; index++) {
            if (array[(index + 1) % array.length] < array[index]) {
                return (index + 1) % array.length;
            }
        }

        return 0;
    }

    // alternate approach using binary search and O(log n) time
    private static int numberOfRotations(int[] array) {
        for (int start = 0, end = array.length-1, mid = end / 2 ; start < end ; mid = (start + end) / 2) {
            if (array[start] <= array[end]) {
                return start;
            }

            if (array[start] <= array[mid]) {
                start = (mid + 1) % array.length;
            } else {
                end = (mid - 1) % array.length;
            }
        }

        return -1;
    }
}
