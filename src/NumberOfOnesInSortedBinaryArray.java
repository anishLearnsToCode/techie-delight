// https://www.techiedelight.com/find-number-1s-sorted-binary-array/
public class NumberOfOnesInSortedBinaryArray {
    public static void main(String[] args) {
        int[] array = StdIn.takeInputAndGetIntArray();
        System.out.println(numberOfOnes(array));
    }

    private static int numberOfOnes(int[] array) {
        int ceil = ceil(array);
        if (ceil == -1) {
            return 0;
        }

        return ceil - floor(array) + 1;
    }

    private static int floor(int[] array) {
        for (int start = 0, end = array.length, middle = end / 2 ; start <= end && middle < array.length ; middle = (start + end) / 2) {
            if (array[middle] == 1) {
                if (middle - 1 >= 0 && array[middle - 1] == 1) {
                    end = middle - 1;
                    continue;
                }
                return middle;
            }

            if (array[middle] < 1) {
                start = middle + 1;
            } else {
                end = middle -1;
            }
        }

        return -1;
    }

    private static int ceil(int[] array) {
        for (int start = 0, end = array.length - 1, middle = end / 2 ; start <= end && middle < array.length ; middle = (start + end) / 2) {
            if (array[middle] == 1) {
                if (middle + 1 < array.length && array[middle + 1] == 1) {
                    start = middle + 1;
                    continue;
                }
                return middle;
            }

            if (array[middle] < 1) {
                start = middle + 1;
            } else {
                end = middle - 1;
            }
        }

        return -1;
    }
}
