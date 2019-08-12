import java.util.ArrayList;
import java.util.List;


// https://www.techiedelight.com/add-elements-two-arrays/
public class AddElementsOf2Arrays {
  public static void main(String[] args) {
    int[] first = StdIn.takeInputAndGetIntArray();
    int[] second = StdIn.takeInputAndGetIntArray();
    System.out.println(sum(first, second));
  }

  private static List<Integer> sum(int[] first, int[] second) {
    List<Integer> result = new ArrayList<>();
    int maxLength = Math.max(first.length, second.length);

    for (int index = 0 ; index < maxLength ; index++) {
      result.addAll(digits(
              (index >= first.length ? 0 : first[index]) +
                      (index >= second.length ? 0 : second[index])
      ));
    }

    return result;
  }

  private static List<Integer> digits(int number) {
    List<Integer> digits = new ArrayList<>();
    String numberString = number + "";
    for (int index = 0 ; index < numberString.length() ; index++) {
      char digit = numberString.charAt(index);
      digits.add(digit - '0');
    }
    return digits;
  }
}
