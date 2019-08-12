import java.util.HashSet;
import java.util.Set;


// https://www.techiedelight.com/find-odd-occurring-elements-array/
public class FindAllOddOccurringElementsWithConstantSpace {
  public static void main(String[] args) {
    int[] array = StdIn.takeInputAndGetIntArray();
    System.out.println(oddOccurringElements(array));
  }

  private static Set<Integer> oddOccurringElements(int[] array) {
    Set<Integer> oddOccurringElements = new HashSet<>();
    for (int element : array) {
      if (oddOccurringElements.contains(element)) {
        oddOccurringElements.remove(element);
      } else {
        oddOccurringElements.add(element);
      }
    }
    return oddOccurringElements;
  }
}
