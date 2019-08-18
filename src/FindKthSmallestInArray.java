// https://www.techiedelight.com/find-kth-smallest-element-array/
public class FindKthSmallestInArray {
    public static void main(String[] args) {
        int[] array = StdIn.takeInputAndGetIntArray();
        int k = StdIn.getIntInput();
        System.out.println(kthSmallestIn(array, k));
    }

    private static int kthSmallestIn(int[] array, int k) {
        MaxHeap maxHeap = new MaxHeap(k);
        for (int index = 0 ; index < k ; index++) {
            maxHeap.add(array[index]);
        }
        for (int index = k ; index < array.length ; index++) {
            if (array[index] < maxHeap.peek()) {
                maxHeap.add(array[index]);
            }
        }

        return maxHeap.peek();
    }

    private static class MaxHeap {
        private final int[] array;
        private final int MAX_SIZE;
        private int size = 0;

        MaxHeap(int size) {
            MAX_SIZE = size;
            array = new int[MAX_SIZE];
        }

        int peek() {
            if (isEmpty()) {
                throw new RuntimeException("max heap is empty");
            }

            return array[0];
        }

        boolean isEmpty() {
            return size == 0 ;
        }

        boolean isFull() {
            return size == MAX_SIZE ;
        }

        void add(int element) {
            if (isFull()) {
                array[0] = element;
                bubbleDown(0);
                return;
            }

            array[size++] = element;
            bubbleUp(size - 1);
        }

        private void bubbleDown(int index) {
            int largestChildIndex = largestChildIndex(index);
            if (largestChildIndex == -1) {
                return;
            }

            swap(index, largestChildIndex);
            bubbleDown(largestChildIndex);
        }

        private int largestChildIndex(int parentIndex) {
            int leftChildIndex = 2 * parentIndex + 1;
            int rightChildIndex = leftChildIndex + 1;

            if (leftChildIndex >= size) {
                return -1;
            } else if (rightChildIndex >= size) {
                return array[parentIndex] < array[leftChildIndex] ? leftChildIndex : -1 ;
            }

            int largestIndex = array[leftChildIndex] > array[rightChildIndex] ? leftChildIndex : rightChildIndex ;
            return array[parentIndex] > array[largestIndex] ? -1 : largestIndex ;
        }

        private void bubbleUp(int index) {
            int parentIndex = (index - 1) / 2;
            if (parentIndex < 0) {
                return;
            }

            if (array[parentIndex] < array[index]) {
                swap(parentIndex, index);
                bubbleUp(parentIndex);
            }
        }

        private void swap(int first, int second) {
            int temp = array[first];
            array[first] = array[second];
            array[second] = temp;
        }

        int[] toArray() {
            return array.clone();
        }
    }

    private static class MinHeap {
        private final int MAX_SIZE;
        private final int[] array;
        private int size = 0;

        MinHeap(final int size) {
            MAX_SIZE = size;
            array = new int[size];
        }

        private MinHeap(int[] array) {
            MAX_SIZE = array.length;
            this.array = array;
        }

        static MinHeap fromArray(int[] array) {
            return new MinHeap(array);
        }

        void add(int element) {
            array[0] = element;
            bubbleDown(0);
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
                return array[parentIndex] > array[leftChildIndex] ? leftChildIndex : -1 ;
            }

            int smallestIndex = array[leftChildIndex] < array[rightChildIndex] ? leftChildIndex : rightChildIndex ;
            return array[parentIndex] < array[smallestIndex] ? smallestIndex : -1 ;
        }

        boolean isEmpty() {
            return size == 0 ;
        }

        int peek() {
            if (isEmpty()) {
                throw new RuntimeException("min heap is empty");
            }

            return array[0];
        }
    }
}
