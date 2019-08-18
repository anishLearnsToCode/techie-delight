import java.util.List;

// https://www.techiedelight.com/merge-m-sorted-lists-variable-length/
public class MergeMSortedListsOfVariableLength {
    public static void main(String[] args) {
        int numberOfLists = StdIn.getIntInput();
        int[][] arrays = new int[numberOfLists][];

        for (int index = 0 ; index < numberOfLists ; index++) {
            arrays[index] = StdIn.takeInputAndGetIntArray();
        }

        Printer.print(mergedLists(arrays));
    }

    private static int[] mergedLists(int[][] arrays) {
        int[] mergedArray = arrays[0];
        for (int index = 1 ; index < arrays.length ; index++) {
            mergedArray = merge(mergedArray, arrays[index]);
        }
        return mergedArray;
    }

    private static int[] merge(int[] first, int[] second) {
        int[] result = new int[first.length + second.length];

        for (int leftIndex = 0, rightIndex = 0, index = 0 ; leftIndex <= first.length && rightIndex <= second.length ; ) {
            if (leftIndex == first.length) {
                fill(result, second, index, rightIndex);
                break;
            } else if (rightIndex == second.length) {
                fill(result, first, index, leftIndex);
                break;
            }

            if (first[leftIndex] < second[rightIndex]) {
                result[index++] = first[leftIndex++];
            } else {
                result[index++] = second[rightIndex++];
            }
        }

        return result;
    }

    private static void fill(int[] filled, int[] filler, int startIndex, int fillerStartIndex) {
        while (fillerStartIndex < filler.length) {
            filled[startIndex++] = filler[fillerStartIndex++];
        }
    }
}
