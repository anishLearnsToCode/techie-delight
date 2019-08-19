// https://www.techiedelight.com/positive-and-negative-integers-segregate/
public class SegregatePositiveAndNegativeIntegersInLinearTime {
    public static void main(String[] args) {
        int[] array = StdIn.takeInputAndGetIntArray();
        segregateIntegers(array);
        Printer.print(array);
    }

    private static void segregateIntegers(int[] array) {
        for (int positiveIndex = 0, negativeIndex = 1 ; negativeIndex < array.length ; ) {
            if (isPositive(array[positiveIndex]) && isPositive(array[negativeIndex])) {
                negativeIndex++;
            } else if (isPositive(array[positiveIndex]) && isNegative(array[negativeIndex])) {
                swap(array, positiveIndex++, negativeIndex++);
            } else if (isNegative(array[positiveIndex]) && isPositive(array[negativeIndex])) {
                positiveIndex++;
                negativeIndex++;
            } else {
                positiveIndex += 2;
                negativeIndex += 2;
            }
        }
    }

    private static void swap(int[] array, int first, int second) {
        int temp = array[first];
        array[first] = array[second];
        array[second] = temp;
    }

    private static boolean isPositive(int number) {
        return number >= 0;
    }

    private static boolean isNegative(int number) {
        return number < 0 ;
    }
}
