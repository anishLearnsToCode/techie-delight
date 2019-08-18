import javafx.util.Pair;

import java.util.*;

// https://www.techiedelight.com/find-smallest-range-least-one-element-given-lists/
public class FindSmallestRangeWithAtLeastOneElementFromEachOfGivenLists {
    public static void main(String[] args) {
        int numberOfLists = StdIn.getIntInput();
        int[][] arrays = new int[numberOfLists][];

        for (int index = 0 ; index < numberOfLists ; index++) {
            arrays[index] = StdIn.takeInputAndGetIntArray();
        }

        System.out.println(minimumRange(arrays));
    }

    private static Range minimumRange(int[][] arrays) {
        List<List<Element>> elementLists = getElementListsFrom(arrays);
        List<Element> sortedList = merge(elementLists);

        MinHeap minHeap = new MinHeap(arrays.length);
        Range range = new Range(-Integer.MAX_VALUE, -1);

        for (Element element : sortedList) {
            minHeap.add(element);
            if (minHeap.isFull() && (element.value - minHeap.peek().getKey() + 1 < range.length())) {
                range = new Range(minHeap.peek().getKey(), element.value);
            }
        }

        return range;
    }

    private static List<Element> merge(List<List<Element>> sortedLists) {
        List<Element> result = sortedLists.get(0);
        for (int index = 1 ; index < sortedLists.size() ; index++) {
            result = merge(result, sortedLists.get(index));
        }
        return result;
    }

    private static List<Element> merge(List<Element> first, List<Element> second) {
        List<Element> result = new ArrayList<>();
        for (int firstIndex = 0, secondIndex = 0 ; firstIndex <= first.size() && secondIndex <= second.size() ; ) {
            if (firstIndex == first.size()) {
                fill(result, second, secondIndex);
                break;
            } else if (secondIndex == second.size()) {
                fill(result, first, firstIndex);
                break;
            }

            if (second.get(secondIndex).equals(first.get(firstIndex))) {
                add(result, first.get(firstIndex++));
                result.get(result.size() - 1).addLineNumbers(second.get(secondIndex++).presentInLines);
            } else if (first.get(firstIndex).compareTo(second.get(secondIndex)) < 0) {
                add(result, first.get(firstIndex++));
            } else {
                add(result, second.get(secondIndex++));
            }
        }

        return result;
    }

    private static void add(List<Element> list, Element element) {
        if (!list.isEmpty() && list.get(list.size() - 1).equals(element)) {
            list.get(list.size() - 1).addLineNumbers(element.presentInLines);
        } else {
            list.add(element);
        }
    }

    private static void fill(List<Element> list, List<Element> filler, int startIndex) {
        for (int index = startIndex ; index < filler.size() ; index++) {
            add(list, filler.get(index));
        }
    }

    private static List<List<Element>> getElementListsFrom(int[][] arrays) {
        List<List<Element>> result = new ArrayList<>();
        for (int index = 0 ; index < arrays.length ; index++) {
            result.add(getElementsFrom(arrays[index], index));
        }
        return result;
    }

    private static List<Element> getElementsFrom(int[] array, int lineNumber) {
        List<Element> result = new ArrayList<>();
        for (int value : array) {
            result.add(new Element(value, lineNumber));
        }
        return result;
    }

    private static class Range {
        private final int start;
        private final int end;

        Range(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "(" + start + "," + end + ")";
        }

        int length() {
            return end - start + 1;
        }
    }

    private static class Element implements Comparable<Element> {
        private final int value;
        private final Set<Integer> presentInLines = new HashSet<>();

        Element(int value, int lineNumber) {
            this.value = value;
            this.presentInLines.add(lineNumber);
        }

        @Override
        public int compareTo(Element other) {
            return Integer.compare(this.value, other.value);
        }

        @Override
        public boolean equals(Object obj) {
            return obj.getClass() == Element.class && this.value == ((Element) obj).value;
        }

        @Override
        public int hashCode() {
            return value;
        }

        @Override
        public String toString() {
            return "(" + value + ":" + presentInLines + ")";
        }

        void addLineNumbers(Set<Integer> lineNumbers) {
            presentInLines.addAll(lineNumbers);
        }
    }

    private static class MinHeap {
        private final int MAX_SIZE;
        private final Pair<Integer, Integer>[] array;
        private final Map<Integer, Integer> lineNumberIndices = new HashMap<>();
        private int size = 0;

        MinHeap(int size) {
            MAX_SIZE = size;
            array = new Pair[size];
        }

        boolean isEmpty() {
            return size == 0;
        }

        boolean isFull() {
            return size == MAX_SIZE;
        }

        Pair<Integer, Integer> peek() {
            if (isEmpty()) {
                throw new RuntimeException("min heap is empty");
            }

            return array[0];
        }

        void add(Element element) {
            for (int lineNumber : element.presentInLines) {
                Pair<Integer, Integer> elementWithLineNumber = new Pair<>(element.value, lineNumber);
                if (lineNumberIndices.containsKey(lineNumber)) {
                    int correspondingIndex = lineNumberIndices.get(lineNumber);
                    array[correspondingIndex] = elementWithLineNumber;
                    heapify();
                } else {
                    array[size++] = elementWithLineNumber;
                    lineNumberIndices.put(lineNumber, size - 1);
                    bubbleUp(size - 1);
                }
            }
        }

        private void heapify() {
            for (int index = (size - 2) / 2 ; index >= 0 ; index--) {
                heapify(index);
            }
        }

        private void heapify(int index) {
            int smallestChildIndex = smallestChildIndex(index);
            if (smallestChildIndex == -1) {
                return;
            }

            swap(index, smallestChildIndex);
            heapify(smallestChildIndex);
        }

        private int smallestChildIndex(int parentIndex) {
            int leftChildIndex = 2 * parentIndex + 1;
            int rightChildIndex = leftChildIndex + 1;

            if (leftChildIndex >= size) {
                return -1;
            } else if (rightChildIndex >= size) {
                return array[leftChildIndex].getKey() < array[parentIndex].getKey() ?
                        leftChildIndex : -1 ;
            }

            int smallestChildIndex = array[leftChildIndex].getKey() < array[rightChildIndex].getKey() ?
                    leftChildIndex : rightChildIndex ;
            return array[smallestChildIndex].getKey() < array[parentIndex].getKey() ? smallestChildIndex : -1 ;
        }

        void bubbleUp(int index) {
            int parentIndex = (index - 1) / 2;
            if (parentIndex < 0) {
                return;
            }

            if (array[parentIndex].getKey() > array[index].getKey()) {
                swap(index, parentIndex);
                bubbleUp(parentIndex);
            }
        }

        void swap(int firstIndex, int secondIndex) {
            Pair<Integer, Integer> temp = array[firstIndex];
            array[firstIndex] = array[secondIndex];
            array[secondIndex] = temp;
            lineNumberIndices.put(array[firstIndex].getValue(), firstIndex);
            lineNumberIndices.put(array[secondIndex].getValue(), secondIndex);
        }
    }
}
