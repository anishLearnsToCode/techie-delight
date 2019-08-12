// https://www.techiedelight.com/rearrange-array-such-that-array-index-is-set-to-i/
public class RearrangeArray {
  public static void main(String[] args) {
    int[] array = StdIn.takeInputAndGetIntArray();
    Printer.print(rearrangedArray(array));
  }

  private static int[] rearrangedArray(int[] array) {
    int[] result = new int[array.length];
    for (int index = 0 ; index < array.length ; index++) {
      result[array[index]] = index;
    }
    return result;
  }
}
