import java.util.ArrayList;
import java.util.List;

// https://www.techiedelight.com/print-triplets-array-sum-less-equal-given-number/
public class PrintAllTripletsIArrayWithSumEqualToOrLessThanGivenSum {
    public static void main(String[] args) {
        int[] array = StdIn.takeInputAndGetIntArray();
        int sum = StdIn.getIntInput();
        printAllTripletsWithSumLessThanEquals(array, sum);
    }

    private static void printAllTripletsWithSumLessThanEquals(int[] array, int sum) {
        printAllTripletsWithSumLessThanEquals(array, sum, 0, new ArrayList<>(), 0);
    }

    private static void printAllTripletsWithSumLessThanEquals(int[] array, int sum, int subArrayLength, List<Integer> list, int index) {
        if (subArrayLength == 3) {
            if (sum >= 0) {
                System.out.println(list);
            }
            return;
        }

        if (index == array.length) {
            return;
        }

        printAllTripletsWithSumLessThanEquals(array, sum, subArrayLength, list, index + 1);
        list.add(array[index]);
        printAllTripletsWithSumLessThanEquals(array, sum - array[index], subArrayLength + 1, list, index + 1);
        list.remove(list.size() - 1);
    }
}
