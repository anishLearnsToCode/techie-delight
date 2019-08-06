public class MaximumSumSubSequenceWithoutAdjacentElements {
    public static void main(String[] args) {
        int[] array = StdIn.takeInputAndGetArray();
        System.out.println(maximumSumSubSequenceWithoutAdjacentElements(array));
    }

    private static int maximumSumSubSequenceWithoutAdjacentElements(int[] array) {
        int withPreviousElementMax = array[1], withoutPreviousElementMax = array[0];
        int max = Math.max(withoutPreviousElementMax, withPreviousElementMax);

        for (int index = 2 ; index < array.length ; index++) {
            int currentElementMax = Math.max(array[index] + withoutPreviousElementMax, withPreviousElementMax);
            max = Math.max(currentElementMax, max);
            withoutPreviousElementMax = withPreviousElementMax;
            withPreviousElementMax = currentElementMax;
        }

        return max;
    }
}
