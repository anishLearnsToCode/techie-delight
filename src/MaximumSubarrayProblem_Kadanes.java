// https://www.techiedelight.com/maximum-subarray-problem-kadanes-algorithm/
public class MaximumSubarrayProblem_Kadanes {
    public static void main(String[] args) {
        int[] array = StdIn.takenputAndGetArray();
        System.out.println(maximumSubArraySum(array));
    }

    private static int maximumSubArraySum(int[] array) {
        int[] subArraySums = new int[array.length];
        int result = 0, currentSum = 0;

        for(int element : array) {
            currentSum += element;
            if (currentSum <= 0) {
                currentSum = 0;
            }

            result = Math.max(result, currentSum);
        }

        return result;
    }
}
