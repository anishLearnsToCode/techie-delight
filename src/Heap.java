import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

// https://www.techiedelight.com/introduction-priority-queues-using-binary-heaps/
abstract class Heap<T extends Comparable<T>> implements Iterable<T> {
    final List<T> data;
    abstract void push(T element);
    abstract T peek();
    abstract T pop();


    Heap() {
        data = new ArrayList<>();
    }

    Heap(int size) {
        data = new ArrayList<>(size);
    }

    @Override
    public Iterator<T> iterator() {
        return data.iterator();
    }

    boolean isEmpty() {
        return data.isEmpty();
    }
}

class MaxHeap<T extends Comparable<T>> extends Heap<T> {

    MaxHeap() { }

    MaxHeap(int size) {
        super(size);
    }

    @Override
    void push(T element) {
        data.add(element);
        bubbleUp(data.size() - 1);
    }

    private void bubbleUp(int index) {
        int parentIndex = (index - 1) / 2 ;
        if (parentIndex < 0) {
            return;
        }

        if (childIsGreaterThanParent(index, parentIndex)) {
            swap(index, parentIndex);
            bubbleUp(parentIndex);
        }
    }

    private boolean childIsGreaterThanParent(int childIndex, int parentIndex) {
        T childElement = data.get(childIndex);
        T parentElement = data.get(parentIndex);
        return childElement.compareTo(parentElement) > 0 ;
    }

    private void swap(int first, int second) {
        T temp = data.get(first);
        data.set(first, data.get(second));
        data.set(second, temp);
    }

    @Override
    T peek() {
        return isEmpty() ? null : data.get(0);
    }

    @Override
    T pop() {
        T max = peek();
        if (Objects.isNull(max)) {
            return null;
        }

        setLastElementOnTop();
        removeLastElement();
        bubbleDown(0);

        return max;
    }

    private void setLastElementOnTop() {
        data.set(0, data.get(data.size() - 1));
    }

    private void removeLastElement() {
        data.remove(data.size() - 1);
    }

    private void bubbleDown(int index) {
        int getSmallestChildIndex = smallestChildOf(index);
        if (getSmallestChildIndex == -1) {
            return;
        }

        swap(index, getSmallestChildIndex);
        bubbleDown(getSmallestChildIndex);
    }

    private int smallestChildOf(int parentIndex) {
        int leftChildIndex = 2 * parentIndex + 1;
        int rightChildIndex = leftChildIndex + 1;

        if (leftChildIndex >= data.size()) {
            return -1;
        } else if (rightChildIndex >= data.size()) {
            return leftChildIndex;
        }

        return data.get(leftChildIndex).compareTo(data.get(rightChildIndex)) < 0 ? leftChildIndex : rightChildIndex ;
    }
}

class MinHeap<T extends Comparable<T>> extends Heap<T> {

    @Override
    void push(T element) {
        data.add(element);
        bubbleUp(data.size() - 1);
    }

    private void bubbleUp(int index) {
        int parentIndex = (index - 1) / 2 ;
        if (parentIndex < 0) {
            return;
        }

        if (childIsSmallerThanParent(index, parentIndex)) {
            swap(index, parentIndex);
            bubbleUp(parentIndex);
        }
    }

    private boolean childIsSmallerThanParent(int childIndex, int parentIndex) {
        T childElement = data.get(childIndex);
        T parentElement = data.get(parentIndex);
        return childElement.compareTo(parentElement) < 0 ;
    }

    private void swap(int first, int second) {
        T temp = data.get(first);
        data.set(first, data.get(second));
        data.set(second, temp);
    }

    @Override
    T peek() {
        return isEmpty() ? null : data.get(0) ;
    }

    @Override
    T pop() {
        T min = peek();
        if (Objects.isNull(min)) {
            return null;
        }

        setLastElementOnTop();
        removeLastElement();
        bubbleDown(0);

        return min;
    }


    private void setLastElementOnTop() {
        data.set(0, data.get(data.size() - 1));
    }

    private void removeLastElement() {
        data.remove(data.size() - 1);
    }

    private void bubbleDown(int index) {
        int getSmallestChildIndex = largestChildOf(index);
        if (getSmallestChildIndex == -1) {
            return;
        }

        swap(index, getSmallestChildIndex);
        bubbleDown(getSmallestChildIndex);
    }

    private int largestChildOf(int parentIndex) {
        int leftChildIndex = 2 * parentIndex + 1;
        int rightChildIndex = leftChildIndex + 1;

        if (leftChildIndex >= data.size()) {
            return -1;
        } else if (rightChildIndex >= data.size()) {
            return leftChildIndex;
        }

        return data.get(leftChildIndex).compareTo(data.get(rightChildIndex)) > 0 ? leftChildIndex : rightChildIndex ;
    }
}
