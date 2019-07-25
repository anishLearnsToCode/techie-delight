// https://www.techiedelight.com/find-index-0-replaced-get-maximum-length-sequence-of-continuous-ones/
public class IndexOf0TooeplaceToGetMaximumLengthSequence {
  public static void main(String[] args) {
    int length = StdIn.getIntInput();
    int[] array = new int[length];
    StdIn.getIntInput(array);
    int index = get0ReplacementIndex(array);
    System.out.println(index);
  }

  private static int get0ReplacementIndex(int[] array) {
    int result = 0;

    for (int index = 0, count = 0, last0Index = -1 ; index < array.length ; index++) {
      if (array[index] == 1) {
        count++;
      } else {
        count = index - last0Index;
        last0Index = index;
      }

      if (count > result) {
        result = count;
      }
    }

    return result;
  }
}
