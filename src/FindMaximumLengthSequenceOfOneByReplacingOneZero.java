import javafx.util.Pair;

// https://www.techiedelight.com/find-maximum-length-sequence-continuous-ones-sliding-window/
public class FindMaximumLengthSequenceOfOneByReplacingOneZero {
    public static void main(String[] args) {
        int[] binaryArray = StdIn.takeInputAndGetIntArray();
        System.out.println(zeroReplacementIndex(binaryArray));
    }

    private static int zeroReplacementIndex(int[] array) {
        int replacementIndex = -1;
        int[] sequenceLengthEndingAt = new int[array.length];
        sequenceLengthEndingAt[0] = 1;

        for (int index = 1, maxLength = 0, lastZeroIndex = array[0] == 0 ? 0 : -1 ; index < array.length ; index++) {
            if (array[index] == 0) {
                sequenceLengthEndingAt[index] = sequenceLengthEndingAt[index - 1] -
                        (lastZeroIndex == -1 ? 0 : sequenceLengthEndingAt[lastZeroIndex]) + 1;
                lastZeroIndex = index;
            } else {
                sequenceLengthEndingAt[index] = sequenceLengthEndingAt[index - 1] + 1 ;
            }

            if (sequenceLengthEndingAt[index] > maxLength) {
                maxLength = sequenceLengthEndingAt[index];
                replacementIndex = lastZeroIndex;
            }
        }

        return replacementIndex;
    }
}
