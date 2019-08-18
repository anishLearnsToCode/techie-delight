// https://www.techiedelight.com/introduction-priority-queues-using-binary-heaps/
public class PriorityQueue {
    public static void main(String[] args) {
        int[] array = StdIn.takeInputAndGetIntArray();
        Heap<Integer> maxHeap = new MaxHeap<>();
        for (int element : array) {
            maxHeap.push(element);
        }
        for (int data : maxHeap) {
            System.out.println(data);
        }
    }
}
