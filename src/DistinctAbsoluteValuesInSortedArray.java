import java.util.HashSet;
import java.util.Set;


// https://www.techiedelight.com/count-distinct-absolute-values-sorted-array/
public class DistinctAbsoluteValuesInSortedArray {
  public static void main(String[] args) {
    int[] array = StdIn.takeInputAndGetIntArray();
    System.out.println(distinctAbsoluteValuesIn(array));
  }

  private static Set<Integer> distinctAbsoluteValuesIn(int[] array) {
    Set<Integer> distinctValues = new HashSet<>();
    for (int element : array) {
      distinctValues.add(Math.abs(element));
    }
    return distinctValues;
  }
}
