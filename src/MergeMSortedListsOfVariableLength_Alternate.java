import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// https://www.techiedelight.com/merge-m-sorted-lists-variable-length/
public class MergeMSortedListsOfVariableLength_Alternate {
    public static void main(String[] args) {
        int numberOfLists = StdIn.getIntInput();
        int[][] arrays = new int[numberOfLists][];

        for (int index = 0 ; index < numberOfLists ; index++) {
            arrays[index] = StdIn.takeInputAndGetIntArray();
        }

        System.out.println(merged(arrays));
    }

    private static List<Integer> merged(int[][] arrays) {
        MinHeap minHeap = new MinHeap(arrays.length);
        insertFirstElementsFromAll(arrays, minHeap);
        int[] indices = initializeIndicesArray(arrays.length);
        List<Integer> result = new ArrayList<>();

        while (!minHeap.isEmpty()) {
            int listIndex = minHeap.peek().listIndex;
            if (indices[listIndex] >= arrays[listIndex].length) {
                result.add(minHeap.pop().value);
            } else {
                Element element = minHeap.add(new Element(arrays[listIndex][indices[listIndex]++], listIndex));
                if (!Objects.isNull(element)) {
                    result.add(element.value);
                }
            }
        }

        return result;
    }

    private static void insertFirstElementsFromAll(int[][] arrays, MinHeap minHeap) {
        for (int row = 0, column = 0 ; row < arrays.length ; row++) {
            minHeap.add(new Element(arrays[row][column], row));
        }
    }

    private static int[] initializeIndicesArray(int length) {
        int[] indices = new int[length];
        for (int index = 0 ; index < length ; indices[index++] = 1);
        return indices;
    }

    private static class MinHeap {
        private final int MAX_SIZE;
        private final Element[] array;
        private int size = 0;

        MinHeap(int maximumSize) {
            MAX_SIZE = maximumSize;
            array = new Element[maximumSize];
        }

        boolean isEmpty() {
            return size == 0;
        }

        boolean isFull() {
            return size == MAX_SIZE;
        }

        Element peek() {
            return isEmpty() ? null : array[0];
        }

        Element pop() {
            if (isEmpty()) {
                return null;
            }

            Element root = array[0];
            assignLastElementToRoot();
            bubbleDown(0);
            return root;
        }

        private void bubbleDown(int index) {
            int smallestChildIndex = smallestChildIndex(index);
            if (smallestChildIndex == -1) {
                return;
            }

            swap(smallestChildIndex, index);
            bubbleDown(smallestChildIndex);
        }

        private int smallestChildIndex(int parentIndex) {
            int leftChildIndex = 2 * parentIndex + 1;
            int rightChildIndex = leftChildIndex + 1;

            if (leftChildIndex >= size) {
                return -1;
            } else if (rightChildIndex >= size) {
                return array[leftChildIndex].value < array[parentIndex].value ? leftChildIndex : -1 ;
            }

            int smallestChildIndex = array[leftChildIndex].value < array[rightChildIndex].value ?
                    leftChildIndex : rightChildIndex ;
            return array[smallestChildIndex].value < array[parentIndex].value ? smallestChildIndex : -1;
        }

        private void swap(int firstIndex, int secondIndex) {
            Element element = array[firstIndex];
            array[firstIndex] = array[secondIndex];
            array[secondIndex] = element;
        }

        private void assignLastElementToRoot() {
            array[0] = array[size - 1];
            size--;
        }

        private Element add(Element element) {
            if (isFull()) {
                Element root = array[0];
                array[0] = element;
                bubbleDown(0);
                return root;
            } else {
                array[size++] = element;
                bubbleUp(size - 1);
                return null;
            }
        }

        private void bubbleUp(int index) {
            int parentIndex = (index - 1) / 2;
            if (parentIndex < 0) {
                return;
            }

            if (array[parentIndex].value > array[index].value) {
                swap(parentIndex, index);
                bubbleUp(parentIndex);
            }
        }
    }

    private static class Element {
        private final int value;
        private final int listIndex;

        Element(int value, int listIndex) {
            this.value = value;
            this.listIndex = listIndex;
        }
    }
}
