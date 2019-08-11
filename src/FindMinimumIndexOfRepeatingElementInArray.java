import java.util.HashMap;
import java.util.Map;

// https://www.techiedelight.com/find-minimum-index-repeating-element-array/
public class FindMinimumIndexOfRepeatingElementInArray {
    public static void main(String[] args) {
        int[] array = StdIn.takeInputAndGetIntArray();
        System.out.println(minimumIndexOfRepeatingElementIn(array));
    }

    private static int minimumIndexOfRepeatingElementIn(int[] array) {
        Map<Integer, Integer> elementFrequencies = getFrequencies(array);

        for (int index = 0 ; index < array.length ; index++) {
            if (elementFrequencies.get(array[index]) > 1) {
                return index;
            }
        }

        return -1;
    }

    private static Map<Integer, Integer> getFrequencies(int[] array) {
        Map<Integer, Integer> elementFrequencies = new HashMap<>();
        for (int element : array) {
            elementFrequencies.put(element, elementFrequencies.getOrDefault(element, 0) + 1);
        }
        return elementFrequencies;
    }
}
