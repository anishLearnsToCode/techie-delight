// https://www.techiedelight.com/move-zeros-present-array-end/
public class MoveAllZeroesToEnd {
  public static void main(String[] args) {
    int[] array = StdIn.takenputAndGetArray();
    int[] result = moveAllZeroesToEnd(array);
    Printer.print(result);
  }

  private static int[] moveAllZeroesToEnd(int[] array) {
    int[] result = new int[array.length];

    for (int index = 0, currentIndex = 0 ; index < array.length ; index++) {
      if (array[index] != 0) {
        result[currentIndex++] = array[index];
      }
    }

    return result;
  }
}
