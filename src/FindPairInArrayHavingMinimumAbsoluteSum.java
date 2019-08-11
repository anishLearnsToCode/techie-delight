// https://www.techiedelight.com/find-pair-array-minimum-absolute-sum/
public class FindPairInArrayHavingMinimumAbsoluteSum {
    public static void main(String[] args) {
        int[] array = StdIn.takeInputAndGetIntArray();
        System.out.println(minimumAbsoluteSum(array));
    }

    private static int minimumAbsoluteSum(int[] array) {
        int min = Integer.MAX_VALUE;
        for (int start = 0, end = array.length - 1 ; start < end ; ) {
            min = Math.min(min, Math.abs(array[start] + array[end]));

            if (array[start] + array[end] < 0) {
                start++;
            } else {
                end--;
            }
        }
        return min;
    }
}
