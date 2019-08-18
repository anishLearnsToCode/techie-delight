import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// https://www.techiedelight.com/sort-k-sorted-array/
public class SortAKSortedArray {
    public static void main(String[] args) {
        int[] array = StdIn.takeInputAndGetIntArray();
        int k = StdIn.getIntInput();
        System.out.println(sort(array, k));
    }

    private static List<Integer> sort(int[] array, int k) {
        MinHeap minHeap = new MinHeap(k + 1);
        List<Integer> list = new ArrayList<>();
        for (int element : array) {
            Integer integer = minHeap.add(element);
            if (!Objects.isNull(integer)) {
                list.add(integer);
            }
        }
        while (!minHeap.isEmpty()) {
            list.add(minHeap.pop());
        }
        return list;
    }

    private static class MinHeap {
        private final int MAX_SIZE;
        private final int[] array;
        private int size = 0;

        MinHeap(int size) {
            MAX_SIZE = size;
            array = new int[MAX_SIZE];
        }

        boolean isEmpty() {
            return size == 0 ;
        }

        boolean isFull() {
            return size == MAX_SIZE ;
        }

        Integer peek() {
            return isEmpty() ? null : array[size - 1];
        }

        Integer add(int element) {
            if (isFull()) {
                int root = array[0];
                array[0] = element;
                bubbleDown(0);
                return root;
            } else {
                array[size++] = element;
                bubbleUp(size - 1);
            }

            return null;
        }

        private void bubbleUp(int index) {
            int parentIndex = (index - 1) / 2;
            if (parentIndex < 0) {
                return;
            }

            if (array[parentIndex] > array[index]) {
                swap(parentIndex, index);
                bubbleUp(parentIndex);
            }
        }

        private void bubbleDown(int index) {
            int smallestChildIndex = smallestChildIndex(index);
            if (smallestChildIndex == -1) {
                return;
            }

            swap(index, smallestChildIndex);
            bubbleDown(smallestChildIndex);
        }

        private void swap(int first, int second) {
            int temp = array[first];
            array[first] = array[second];
            array[second] = temp;
        }

        private int smallestChildIndex(int parentIndex) {
            int leftChildIndex = 2 * parentIndex + 1;
            int rightChildIndex = leftChildIndex + 1;

            if (leftChildIndex >= size) {
                return -1;
            } else if (rightChildIndex >= size) {
                return array[leftChildIndex] < array[parentIndex] ? leftChildIndex : -1 ;
            }

            int smallestIndex = array[leftChildIndex] < array[rightChildIndex] ? leftChildIndex : rightChildIndex ;
            return array[smallestIndex] < array[parentIndex] ? smallestIndex : -1 ;
        }

        private int pop() {
            if (isEmpty()) {
                throw new RuntimeException("min heap is empty");
            }

            int root = array[0];
            placeLastElementAtRoot();
            removeLastElement();
            bubbleDown(0);
            return root;
        }

        private void placeLastElementAtRoot() {
            array[0] = array[size - 1];
        }

        private void removeLastElement() {
            size--;
        }
    }
}
