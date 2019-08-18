// https://www.techiedelight.com/find-kth-largest-element-array/
public class FindKthLargestElementInArray {
    public static void main(String[] args) {
        int[] array = StdIn.takeInputAndGetIntArray();
        int k = StdIn.getIntInput();
        System.out.println(kthLargest(array, k));
    }

    private static int kthLargest(int[] array, int k) {
        FixedSizeMinHeap minHeap = new FixedSizeMinHeap(k);
        for (int index = 0 ; index < k ; index++) {
            minHeap.push(array[index]);
        }

        for (int index = k ; index < array.length ; index++) {
            if (!minHeap.isEmpty() && minHeap.peek() < array[index]) {
                minHeap.pop();
                minHeap.push(array[index]);
            }
        }

        return minHeap.peek();
    }

    private static class FixedSizeMinHeap {
        private final int[] array ;
        private int size = 0;

        FixedSizeMinHeap(int size) {
            array = new int[size];
        }

        void push(int element) {
            array[size++] = element;
            bubbleUp(size - 1);
        }

        private void bubbleUp(int index) {
            int parentIndex = (index - 1) / 2;
            if (parentIndex < 0) {
                return;
            }
            if (array[index] < array[parentIndex]) {
                swap(index, parentIndex);
                bubbleUp(parentIndex);
            }
        }

        private void swap(int first, int second) {
            int temp = array[first];
            array[first] = array[second];
            array[second] = temp;
        }

        int pop() {
            if (isEmpty()) {
                throw new RuntimeException("heap is empty");
            }

            int value = array[0];
            addLastElementToTop();
            removeLastElement();
            bubbleDown(0);

            return value;
        }

        private void addLastElementToTop() {
            array[0] = array[size - 1];
        }

        private void removeLastElement() {
            size--;
        }

        private void bubbleDown(int index) {
            int smallestChildIndex = smallestChildIndex(index);
            if (smallestChildIndex == -1) {
                return;
            }

            swap(index, smallestChildIndex);
            bubbleDown(smallestChildIndex);
        }

        private int smallestChildIndex(int parentIndex) {
            int leftChildIndex = 2 * parentIndex + 1;
            int rightChildIndex = 2 * parentIndex + 2;

            if (leftChildIndex >= size) {
                return -1;
            } else if (rightChildIndex >= size) {
                return array[parentIndex] < array[leftChildIndex] ? -1 : leftChildIndex ;
            }

            return array[leftChildIndex] < array[rightChildIndex] ?
                   array[leftChildIndex] <  array[parentIndex] ? leftChildIndex : -1
                   : array[rightChildIndex] < array[parentIndex] ? rightChildIndex : -1 ;
        }

        boolean isEmpty() {
            return size == 0 ;
        }

        int peek() {
            if (isEmpty()) {
                throw new RuntimeException("heap is empty");
            }

            return array[0];
        }
    }
}
