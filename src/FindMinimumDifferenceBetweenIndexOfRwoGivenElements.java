// https://www.techiedelight.com/find-minimum-difference-index-two-given-elements-present-array/
public class FindMinimumDifferenceBetweenIndexOfRwoGivenElements {
    public static void main(String[] args) {
        int[] array = StdIn.takeInputAndGetIntArray();
        int a = StdIn.getIntInput();
        int b = StdIn.getIntInput();
        System.out.println(minimumIndexDifference(array, a, b));
    }

    private static int minimumIndexDifference(int[] array, int a, int b) {
        int min = Integer.MAX_VALUE, lastIndex = firstOccurrenceOf(array, a, b);
        if (lastIndex == -1) {
            return Integer.MAX_VALUE;
        }

        boolean withFirst = array[lastIndex] == a;

        for (int index = lastIndex + 1; index < array.length ; index++) {
            if (array[index] == a) {
                if (!withFirst) {
                    min = Math.min(min, index - lastIndex);
                }
                withFirst = true;
                lastIndex = index;
            } else if (array[index] == b) {
                if (withFirst) {
                    min = Math.min(min, index - lastIndex);
                }
                withFirst = false;
                lastIndex = index;
            }
        }

        return min;
    }

    private static int firstOccurrenceOf(int[] array, int a, int b) {
        for (int index = 0 ; index < array.length ; index++) {
            if (array[index] == a || array[index] == b) {
                return index;
            }
        }

        return -1;
    }
}
