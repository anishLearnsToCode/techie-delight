// https://www.techiedelight.com/find-maximum-absolute-difference-subarrays/
public class FindMaximumAbsoluteDifferenceBetweenSumOf2NonOverlappingSubArrays {
    public static void main(String[] args) {
        int[] array = StdIn.takeInputAndGetIntArray();
        System.out.println(maximumAbsoluteDifference(array));
        System.out.println(GFG.findMaxAbsDiff(array, array.length));
    }

    private static int maximumAbsoluteDifference(int[] array) {
        return maximumAbsoluteDifference(
                maximumSumSubArrayFromLeft(array),
                minimumSumSubArrayFromLeft(array),
                maximumSumSubArrayFromRight(array),
                minimumSumSubArrayFromRight(array)
        );
    }

    private static int[] maximumSumSubArrayFromLeft(int[] array) {
        int[] result = new int[array.length];
        for (int index = 0, sum = 0, max = array[0] ; index < array.length ; index++, max = result[index - 1]) {
            sum += array[index];
            result[index] = Math.max(sum, max);
            sum = sum < 0 ? 0 : sum ;
        }
        return result;
    }

    private static int[] minimumSumSubArrayFromLeft(int[] array) {
        int[] result = new int[array.length];
        for (int index = 0, sum = 0, min = array[0] ; index < array.length ; index++, min = result[index - 1]) {
            sum += array[index];
            result[index] = Math.min(sum, min);
            sum = sum > 0 ? 0 : sum;
        }
        return result;
    }

    private static int[] maximumSumSubArrayFromRight(int[] array) {
        int[] result = new int[array.length];
        for (int index = array.length - 1, sum = 0, max = array[array.length - 1] ; index >= 0 ; index--, max = result[index + 1]) {
            sum += array[index];
            result[index] = Math.max(sum, max);
            sum = sum < 0 ? 0 : sum ;
        }
        return result;
    }

    private static int[] minimumSumSubArrayFromRight(int[] array) {
        int[] result = new int[array.length];
        for (int index = array.length - 1, sum = 0, min = array[array.length - 1] ; index >= 0 ; index--, min = result[index + 1]) {
            sum += array[index];
            result[index] = Math.min(sum, min);
            sum = sum > 0 ? 0 : sum ;
        }
        return result;
    }

    private static int maximumAbsoluteDifference(int[] maxLeft, int[] minLeft, int[] maxRight, int[] minRight) {
        int max = Integer.MIN_VALUE;
        for (int index = 0 ; index < maxLeft.length - 1; index++) {
            max = Math.max(max, Math.abs(maxLeft[index] - minRight[index + 1]));
            max = Math.max(max, Math.abs(maxRight[index + 1] - minLeft[index]));
        }
        return max;
    }
}

class GFG {

    // Find maximum subarray sum for subarray
    // [0..i] using standard Kadane's algorithm.
    // This version of Kadane's Algorithm will
    // work if all numbers are negative.
    private static int maxLeftSubArraySum(int a[], int size, int sum[]) {
        int max_so_far = a[0];
        int curr_max = a[0];
        sum[0] = max_so_far;

        for (int i = 1; i < size; i++) {
            curr_max = Math.max(a[i], curr_max + a[i]);
            max_so_far = Math.max(max_so_far, curr_max);
            sum[i] = max_so_far;
        }

        return max_so_far;
    }

    // Find maximum subarray sum for subarray [i..n]
    // using Kadane's algorithm. This version of Kadane's
    // Algorithm will work if all numbers are negative
    private static int maxRightSubArraySum(int a[], int n, int sum[]) {
        int max_so_far = a[n];
        int curr_max = a[n];
        sum[n] = max_so_far;

        for (int i = n - 1; i >= 0; i--) {
            curr_max = Math.max(a[i], curr_max + a[i]);
            max_so_far = Math.max(max_so_far, curr_max);
            sum[i] = max_so_far;
        }

        return max_so_far;
    }

    // The function finds two non-overlapping contiguous
    // sub-arrays such that the absolute difference
    // between the sum of two sub-array is maximum.
    static int findMaxAbsDiff(int arr[], int n) {
        // create and build an array that stores
        // maximum sums of subarrays that lie in
        // arr[0...i]
        int leftMax[] = new int[n];
        maxLeftSubArraySum(arr, n, leftMax);

        // create and build an array that stores
        // maximum sums of subarrays that lie in
        // arr[i+1...n-1]
        int rightMax[] = new int[n];
        maxRightSubArraySum(arr, n - 1, rightMax);

        // Invert array (change sign) to find minumum
        // sum subarrays.
        int invertArr[] = new int[n];
        for (int i = 0; i < n; i++)
            invertArr[i] = -arr[i];

        // create and build an array that stores
        // minimum sums of subarrays that lie in
        // arr[0...i]
        int leftMin[] = new int[n];
        maxLeftSubArraySum(invertArr, n, leftMin);
        for (int i = 0; i < n; i++)
            leftMin[i] = -leftMin[i];

        // create and build an array that stores
        // minimum sums of subarrays that lie in
        // arr[i+1...n-1]
        int rightMin[] = new int[n];
        maxRightSubArraySum(invertArr, n - 1, rightMin);
        for (int i = 0; i < n; i++)
            rightMin[i] = -rightMin[i];

        int result = Integer.MIN_VALUE;
        for (int i = 0; i < n - 1; i++) {

        /* For each index i, take maximum of
        1. abs(max sum subarray that lies in arr[0...i] -
            min sum subarray that lies in arr[i+1...n-1])
        2. abs(min sum subarray that lies in arr[0...i] -
            max sum subarray that lies in arr[i+1...n-1]) */
            int absValue = Math.max(Math.abs(leftMax[i] - rightMin[i + 1]),
                    Math.abs(leftMin[i] - rightMax[i + 1]));
            if (absValue > result)
                result = absValue;
        }

        return result;
    }
}