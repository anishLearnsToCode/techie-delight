import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


// https://www.techiedelight.com/custom-sort-sort-elements-array-order-elements-defined-second-array/
public class CustomSort__SortArrayBasedOnAnotherArray {
  public static void main(String[] args) {
    int[] array = StdIn.takeInputAndGetIntArray();
    int[] other = StdIn.takeInputAndGetIntArray();
    System.out.println(sort(array, other));
  }

  private static List<Integer> sort(int[] array, int[] other) {
    List<Integer> list = toList(array);
    list.sort(new OtherArrayComparator(other));
    return list;
  }

  private static List<Integer> toList(int[] array) {
    List<Integer> list = new ArrayList<Integer>();
    for (int value : array) {
      list.add(value);
    }
    return list;
  }

  private static class OtherArrayComparator implements Comparator<Integer> {
    private final Set<Integer> otherArrayElements = new HashSet<>();
    private final Map<Integer, Integer> indices = new HashMap<>();

    OtherArrayComparator(int[] other) {
      for (int index = 0 ; index < other.length ; index++) {
        int element = other[index];
        otherArrayElements.add(element);
        indices.put(element, index);
      }
    }

    @Override
    public int compare(Integer first, Integer second) {
      if (otherArrayElements.contains(first) && otherArrayElements.contains(second)) {
        return Integer.compare(indices.get(first), indices.get(second));
      } else if (otherArrayElements.contains(first)) {
        return -1;
      } else if (otherArrayElements.contains(second)) {
        return 1;
      } else {
        return Integer.compare(first, second);
      }
    }
  }
}
