import java.util.ArrayList;
import java.util.List;

// https://www.techiedelight.com/partition-array-into-two-sub-arrays-with-same-sum/
public class PartitionArrayInto2SubArraysWithSameSum {
    public static void main(String[] args) {
        int[] array = StdIn.takeInputAndGetIntArray();
        System.out.println(partitionedArrays(array));
    }

    private static List<List<Integer>> partitionedArrays(int[] array) {
        int partitionIndex = getPartitionIndex(array) + 1;
        System.out.println(partitionIndex);
        if (partitionIndex == 0) {
            return new ArrayList<>();
        }

        List<List<Integer>> partitionArrays = new ArrayList<>();
        partitionArrays.add(subArray(array, 0 , partitionIndex));
        partitionArrays.add(subArray(array, partitionIndex, array.length));
        return partitionArrays;
    }

    private static List<Integer> subArray(int[] array, int startIndex, int endIndex) {
        List<Integer> subArray = new ArrayList<>();
        while (startIndex< endIndex) {
            subArray.add(array[startIndex++]);
        }
        return subArray;
    }

    private static int getPartitionIndex(int[] array) {
        int[] subArraySum = subArraySumFromLeft(array);
        Printer.print(subArraySum);
        for (int index = 0 ; index < array.length ; index++) {
            int leftSubArraySum = subArraySum[index];
            int rightSubArraySum = subArraySum[subArraySum.length - 1] - subArraySum[index];

            if (leftSubArraySum == rightSubArraySum) {
                return index;
            }
        }

        return -1;
    }

    private static int[] subArraySumFromLeft(int[] array) {
        int[] result = new int[array.length];
        result[0] = array[0];
        for (int index = 1 ; index < array.length ; index++) {
            result[index] = result[index - 1] + array[index];
        }
        return result;
    }
}
