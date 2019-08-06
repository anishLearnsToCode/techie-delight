import java.util.ArrayList;
import java.util.List;

// https://www.techiedelight.com/maximum-subarray-problem-kadanes-algorithm/
public class MaximumSubarrayProblem_Kadanes {
    public static void main(String[] args) {
        int[] array = StdIn.takeInputAndGetIntArray();
        System.out.println(maximumSubArraySum(array));
    }

    private static List<Range> maximumSubArraySum(int[] array) {
        int[] subArraySums = new int[array.length];
        int result = 0, currentSum = 0;
        List<Range> subArrayIndices = new ArrayList<>();

        for(int index = 0, startIndex = 0 ; index < array.length ; index++) {
            currentSum += array[index];
            if (currentSum <= 0) {
                currentSum = 0;
                startIndex = index + 1;
            }

            if (currentSum > result) {
                subArrayIndices = new ArrayList<>();
                subArrayIndices.add(new Range(startIndex, index));
                result = currentSum;
            } else if (currentSum == result) {
                subArrayIndices.add(new Range(startIndex, index));
            }

            result = Math.max(result, currentSum);
        }

        return subArrayIndices;
    }
}
