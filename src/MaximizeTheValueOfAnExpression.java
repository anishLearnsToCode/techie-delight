// https://www.techiedelight.com/maximize-value-of-the-expression/
public class MaximizeTheValueOfAnExpression {
    public static void main(String[] args) {
        int[] array = StdIn.takeInputAndGetIntArray();
        System.out.println(maximumValue(array));
    }

    private static int maximumValue(int[] array) {
        int[] maximumValuesInLeftSubArrays = maximumValuesInLeftSubArrays(array);
        int[] maximumValuesInRightSubArrays = maximumValuesInRightSubArrays(array);

        int maxValueOfExpression = Integer.MIN_VALUE;
        for (int index = 0 ; index < array.length - 1 ; index++) {
            if (maxValueOfExpression < maximumValuesInLeftSubArrays[index] + maximumValuesInRightSubArrays[index + 1]) {
                maxValueOfExpression = maximumValuesInLeftSubArrays[index] + maximumValuesInRightSubArrays[index + 1];
            }
        }

        return maxValueOfExpression;
    }

    private static int[] maximumValuesInLeftSubArrays(int[] array) {
        int[] result = new int[array.length];
        result[0] = Integer.MIN_VALUE;
        int min = array[0];

        for (int index = 1 ; index < array.length ; index++) {
            result[index] = Math.max(result[index - 1], array[index] - min);
            min = Math.min(min, array[index]);
        }

        return result;
    }

    private static int[] maximumValuesInRightSubArrays(int[] array) {
        int[] result = new int[array.length];
        result[array.length - 1] = Integer.MIN_VALUE;
        int max = array[array.length - 1];

        for (int index = array.length - 2 ; index >= 0 ; index--) {
            result[index] = Math.max(result[index + 1], max - array[index]);
            max = Math.max(max, array[index]);
        }

        return result;
    }
}
