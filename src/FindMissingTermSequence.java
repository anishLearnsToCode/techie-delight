// https://www.techiedelight.com/find-missing-term-sequence-ologn-time/
public class FindMissingTermSequence {
    public static void main(String[] args) {
        int[] array = StdIn.takeInputAndGetIntArray();
        System.out.println(missingTerm(array));
    }

    private static Integer missingTerm(int[] array) {
        for (int start = 0, end = array.length - 1, middle = end / 2 ; start <= end && middle < array.length ; middle = (start + end) / 2) {
            if (middle == 0) {
                start = middle + 1;
            } else if (middle == array.length - 1) {
                end = middle - 1;
            } else if (array[middle] - array[middle - 1] != array[middle + 1] - array[middle]) {
                return missingTerm(array[middle -1], array[middle], array[middle + 1]);
            }

            int difference = array[middle] - array[middle - 1];
            int expectedStartValue = array[middle] - difference * (middle - start);

            if (array[start] != expectedStartValue) {
                end = middle - 1;
            } else {
                start = middle + 1;
            }
        }

        return null;
    }

    private static Integer missingTerm(int first, int middle, int last) {
        int firstDifference = middle - first;
        int secondDifference = last - middle;

        if (firstDifference / secondDifference == 2) {
            return middle - secondDifference;
        } else {
            return middle + firstDifference;
        }
    }
}
