import java.util.ArrayList;
import java.util.List;

public class SortArrayInOneSwap {
    public static void main(String[] args) {
        int[] array = StdIn.takeInputAndGetIntArray();
        Printer.print(sortedArray(array));
    }

    private static int[] sortedArray(int[] array) {
        List<Integer> markers = new ArrayList<>();
        for (int index = 1 ; index < array.length ; index++) {
            if (array[index] < array[index - 1]) {
                markers.add(index);
            }
        }

        if (markers.size() == 1) {
            swap(array, markers.get(0), markers.get(0) - 1);
        } else {
            swap(array, markers.get(0) - 1, markers.get(1));
        }

        return array;
    }

    private static void swap(int[] array, int firstIndex, int secondIndex) {
        int temporary = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temporary;
    }
}
