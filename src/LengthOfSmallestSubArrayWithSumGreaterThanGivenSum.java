// https://www.techiedelight.com/length-of-smallest-subarray-with-sum-greater-number/
public class LengthOfSmallestSubArrayWithSumGreaterThanGivenSum {
    public static void main(String[] args) {
        int[] array = StdIn.takeInputAndGetIntArray();
        int sum = StdIn.getIntInput();
        System.out.println(lengthOfSmallestSubArrayWithSumGreaterThan(array, sum));
    }

    private static int lengthOfSmallestSubArrayWithSumGreaterThan(int[] array, int sum) {
        int length = Integer.MAX_VALUE;

        for (int startIndex = 0, endIndex = 0, currentSum = array[0] ; startIndex < array.length && endIndex < array.length ; ) {
            if (currentSum < sum) {
                if (endIndex == array.length - 1) {
                    break;
                }
                currentSum += array[++endIndex];
            } else {
                length = Math.min(length, endIndex - startIndex + 1);
                currentSum -= array[startIndex++];
            }
        }

        return length == Integer.MAX_VALUE ? -1 : length ;
    }
}
