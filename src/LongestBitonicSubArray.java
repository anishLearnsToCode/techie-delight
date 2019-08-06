// https://www.techiedelight.com/find-longest-bitonic-subarray-array/
public class LongestBitonicSubArray {
  public static void main(String[] args) {
    int[] array = StdIn.takeInputAndGetIntArray();
    int result = lengthOfLongestBitonicSubArray(array);
    System.out.println(result);
  }

  private static int lengthOfLongestBitonicSubArray(int[] array) {
    int[] left = getIncreasingLengths(array);
    int[] right = getDecreasingLengths(array);

    int max = 0;
    for (int index = 0 ; index < array.length ; index++) {
      max = Math.max(max, left[index] + right[index] - 1);
    }

    return max;
  }

  private static int[] getIncreasingLengths(int[] array) {
    int[] result = new int[array.length];
    result[0] = 1;
    for (int index = 1 ; index < result.length ; index++) {
      result[index] = 1 + (array[index] > array[index - 1] ? result[index-1] : 0);
    }
    return result;
  }

  private static int[] getDecreasingLengths(int[] array) {
    int[] result = new int[array.length];
    result[array.length - 1] = 1;
    for (int index = array.length - 2; index >= 0 ; index--) {
      result[index] = 1 + (array[index] > array[index + 1] ? result[index + 1] : 0) ;
    }
    return result;
  }
}
