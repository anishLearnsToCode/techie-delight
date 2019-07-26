import java.util.ArrayList;
import java.util.List;

public class StudentHeights__Google {
    public static void main(String[] args) {
        int[] array = StdIn.takeInputAndGetArray();
        System.out.println(minRows(array));
    }

    private static int minRows(int[] array) {
        List<Integer> rows = new ArrayList<>();
        rows.add(array[0]);
        int max = array[0];

        for (int index = 1 ; index < array.length ; index++) {
            if (array[index] < max) {
                addToAppropriateRow(rows, array[index]);
                max = rows.get(rows.size() - 1);
            } else {
                rows.add(array[index]);
                max = array[index];
            }
        }

        return rows.size();
    }

    private static void addToAppropriateRow(List<Integer> sortedList, int element) {
        int index = nextLargestBinarySearch(sortedList, element);
        sortedList.set(index, element);
    }

    private static int nextLargestBinarySearch(List<Integer> list, int element) {
        int middle = list.size() / 2 ;
        for (int startIndex = 0, endIndex = list.size() ; ; middle = (startIndex + endIndex) / 2) {
            if (list.get(middle) <= element) {
                if (list.get(middle + 1) > element) {
                    return middle + 1;
                }
                startIndex = middle;
            } else {
                if (middle == 0) {
                    return middle;
                }
                if (list.get(middle - 1) < element) {
                    return middle;
                }
                endIndex = middle;
            }
        }
    }
}
