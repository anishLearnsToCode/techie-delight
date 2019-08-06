import java.util.HashMap;
import java.util.Map;


// https://www.techiedelight.com/find-majority-element-in-an-array-boyer-moore-majority-vote-algorithm/
public class MajorityElementInArray {
  public static void main(String[] args) {
    int [] array = StdIn.takeInputAndGetIntArray();
    System.out.println(majorityElement(array));
  }

  private static int majorityElement(int[] array) {
    int frequency = 0, value = -1;
    Map<Integer, Integer> elementFrequencies = new HashMap<>();

    for (int element : array) {
      elementFrequencies.put(element, elementFrequencies.getOrDefault(element, 0) + 1);
      if (elementFrequencies.get(element) > frequency) {
        frequency = elementFrequencies.get(element);
        value = element;
      }
    }

    if (frequency < array.length / 2) {
      return -1;
    }

    return value;
  }
}
