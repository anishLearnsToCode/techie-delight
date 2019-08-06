import java.util.LinkedList;

// https://www.techiedelight.com/find-maximum-sequence-of-continuous-1s-can-formed-replacing-k-zeroes-ones/
public class MaximumSequenceOf1sToBeFormedByReplacingKZeroes {
    public static void main(String[] args) {
        int[] array = StdIn.takeInputAndGetIntArray();
        int k = StdIn.getIntInput();
        System.out.println(maximumLengthFormed(array, k));
    }

    private static int maximumLengthFormed(int[] array, int replacementZeroes) {
        LinkedList<Integer> zeroIndices = new LinkedList<>();
        int max = 0;

        for (int index = 0, count = 0, startIndex = 0 ; index < array.length ; index++) {
            if (array[index] == 1) {
                count++;
            } else {
                if (replacementZeroes > 0) {
                    zeroIndices.add(index);
                    count++;
                    replacementZeroes--;
                } else if (!zeroIndices.isEmpty()){
                    int firstIndex = zeroIndices.removeFirst();
                    count -= firstIndex - startIndex ;
                    startIndex = firstIndex + 1;
                    zeroIndices.add(index);
                } else {
                    count = 0;
                }
            }

            if (count > max) {
                max = count;
            }
        }

        return max;
    }
}
