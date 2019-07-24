// https://www.techiedelight.com/inplace-merge-two-sorted-arrays/
public class InPlaceMerge2SortedArrays {
    public static void main(String[] args) {
        int length1 = StdIn.getIntInput();
        int[] array1 = new int[length1];
        StdIn.getIntInput(array1);

        int length2 = StdIn.getIntInput();
        int[] array2 = new int[length2];
        StdIn.getIntInput(array2);

        mergeInPlace(array1, array2);
        Printer.print(array1);
        Printer.print(array2);
    }

    private static void mergeInPlace(int[] first, int[] second) {
        int[] proxyFirst = new int[first.length];
        int[] proxySecond = new int[second.length];
        int[] insertionArray = proxyFirst;
        int insertionIndex = 0;

        for (int firstIndex = 0, secondIndex = 0 ; firstIndex <= first.length && secondIndex <= second.length ; insertionIndex++) {
            if (insertionArray == proxyFirst && insertionIndex == proxyFirst.length) {
                insertionArray = proxySecond;
                insertionIndex = 0;
            }

            if (firstIndex == first.length) {
                for (int index = secondIndex ; index < second.length ; index++) {
                    insertionArray[insertionIndex++] = second[index];
                }
                break;
            }

            if (secondIndex == second.length) {
                for (int index = firstIndex ; index < first.length ; index++) {
                    insertionArray[insertionIndex++] = first[index];
                }
                break;
            }

            if (first[firstIndex] <= second[secondIndex]) {
                insertionArray[insertionIndex] = first[firstIndex++];
            } else {
                insertionArray[insertionIndex] = second[secondIndex++];
            }
        }

        replaceWithElementsOf(first, proxyFirst);
        replaceWithElementsOf(second, proxySecond);
    }

    private static void replaceWithElementsOf(int[] array, int[] of) {
        System.arraycopy(of, 0, array, 0, array.length);
    }
}
