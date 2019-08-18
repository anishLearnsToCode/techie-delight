// https://www.techiedelight.com/check-given-array-represents-min-heap-not/
public class CheckIfArrayRepresentsMinHeapOrNot {
    public static void main(String[] args) {
        int[] array = StdIn.takeInputAndGetIntArray();
        System.out.println(isMinHeapRepresentation(array));
    }

    private static boolean isMinHeapRepresentation(int[] array) {
        return isMinHeapRepresentation(array, 0);
    }

    private static boolean isMinHeapRepresentation(int[] array, int index) {
        int leftChildIndex = 2 * index + 1;
        int rightChildIndex = 2 * index + 2;

        if (leftChildIndex >= array.length) {
            return true;
        } else if (rightChildIndex >= array.length) {
            return array[index] < array[leftChildIndex];
        }

        return array[index] < array[leftChildIndex] &&
               array[index] < array[rightChildIndex] &&
               isMinHeapRepresentation(array, leftChildIndex) &&
               isMinHeapRepresentation(array, rightChildIndex);
    }
}
