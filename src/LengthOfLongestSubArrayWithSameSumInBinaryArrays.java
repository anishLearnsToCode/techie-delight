import java.util.HashMap;
import java.util.Map;

// https://www.techiedelight.com/length-longest-continuous-sequence-same-sum-binary-arrays/
public class LengthOfLongestSubArrayWithSameSumInBinaryArrays {
  public static void main(String[] args) {
    int[] first = StdIn.takeInputAndGetIntArray();
    int[] second = StdIn.takeInputAndGetIntArray();
    System.out.println(lengthOfLongestSubArrayWithSameSum(first, second));
  }

  private static int lengthOfLongestSubArrayWithSameSum(int[] first, int[] second) {
    int[] firstArraySum = subArraySums(first);
    int[] secondArraySum = subArraySums(second);
    int[] difference = difference(firstArraySum, secondArraySum);
    Map<Integer, Integer> differenceIndices = new HashMap<>();
    differenceIndices.put(0, -1);

    int max = 0;
    for (int index = 0 ; index < difference.length ; index++) {
      if (differenceIndices.containsKey(difference[index])) {
        max = Math.max(max, index - differenceIndices.get(difference[index]));
      } else {
        differenceIndices.put(difference[index], index);
      }
    }

    return max;
  }

  private static int[] subArraySums(int[] array) {
    int[] result = new int[array.length];
    result[0] = array[0];
    for (int index = 1 ; index < array.length ; index++) {
      result[index] = result[index - 1] + array[index];
    }

    return result;
  }

  private static int[] difference(int[] first, int[] second) {
    int[] result = new int[first.length];
    for (int index = 0 ; index < first.length ; index++) {
      result[index] = first[index] - second[index];
    }
    return result;
  }
}
