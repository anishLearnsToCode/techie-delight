// https://www.techiedelight.com/convert-max-heap-min-heap-linear-time/
public class ConvertMaxHeapToMinHeapInLinearTime {
    public static void main(String[] args) {
        int[] array = StdIn.takeInputAndGetIntArray();
        minHeap(array);
        Printer.print(array);
    }

    private static void minHeap(int[] maxHeap) {
        for (int index = (maxHeap.length - 2) / 2 ; index >= 0 ; index--) {
            heapify(maxHeap, index);
        }
    }

    private static void heapify(int[] heap, int index) {
        int smallestChildIndex = getSmallestChildIndex(heap, index);
        if (smallestChildIndex == -1) {
            return;
        }

        swap(heap, index, smallestChildIndex);
        heapify(heap, smallestChildIndex);
    }

    private static int getSmallestChildIndex(int[] heap, int parentIndex) {
        int leftChildIndex = 2 * parentIndex + 1;
        int rightChildIndex = 2 * parentIndex + 2;

        if (leftChildIndex >= heap.length) {
            return -1;
        } else if (rightChildIndex >= heap.length) {
            return leftChildIndex ;
        }

        return heap[leftChildIndex] < heap[rightChildIndex] ? leftChildIndex : rightChildIndex ;
    }

    private static void swap(int[] array, int first, int second) {
        int temp = array[first];
        array[first] = array[second];
        array[second] = temp;
    }
}
