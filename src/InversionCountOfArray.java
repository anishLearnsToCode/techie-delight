import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://www.techiedelight.com/inversion-count-array/
public class InversionCountOfArray {
    public static void main(String[] args) {
        int[] array = StdIn.takeInputAndGetIntArray();
        System.out.println(inversionCount(array));
    }

    private static int inversionCount(int[] array) {
        int[] result = new int[array.length];
        MaxHeap maxHeap = new MaxHeap();
        for (int index = array.length - 1 ; index >= 0 ; index--) {
            result[index] = maxHeap.push(array[index]);
        }

        Printer.print(result);
        return Arrays.stream(result).sum();
    }

    private static class MaxHeap {
        private final List<Integer> array = new ArrayList<>();
        private int size;

        int push(int element) {
            array.add(element);
            return bubbleUp(size++);
        }

        private int bubbleUp(int index) {
            int parentIndex = (index - 1) / 2;
            if (parentIndex < 0) {
                return size - 1 ;
            }

            if (array.get(parentIndex) < array.get(index)) {
                swap(index, parentIndex);
                return bubbleUp(parentIndex);
            }

            return size - index - 1;
        }

        private void swap(int firstIndex, int secondIndex) {
            int temp = array.get(firstIndex);
            array.set(firstIndex, array.get(secondIndex));
            array.set(secondIndex, temp);
        }
    }
}
