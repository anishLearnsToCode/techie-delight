// https://www.techiedelight.com/binary-search/
public class BinarySearch__TechieDelight {
    public static void main(String[] args) {
        int[] array = StdIn.takeInputAndGetIntArray();
        int element = StdIn.getIntInput();
        System.out.println(existsAtIndex(array, element));
    }

    private static int existsAtIndex(int[] array, int element) {
        for (int start = 0, end = array.length, mid = end / 2 ; start <= end ; mid = (start + end) / 2) {
            if (mid >= array.length) {
                break;
            }

            if (array[mid] == element) {
                return mid;
            } else if (array[mid] < element) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return -1;
    }
}
