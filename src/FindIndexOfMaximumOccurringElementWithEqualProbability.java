import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://www.techiedelight.com/find-index-maximum-occurring-element-equal-probability/
public class FindIndexOfMaximumOccurringElementWithEqualProbability {
    public static void main(String[] args) {
        int[] array = StdIn.takeInputAndGetIntArray();
        System.out.println(indexOfMaximumOccurringElement(array));
    }

    private static int indexOfMaximumOccurringElement(int[] array) {
        Map<Integer, List<Integer>> elementIndices = getElementIndices(array);
        int maximumOccurringElement = maximumOccurringElement(array);
        List<Integer> indices = elementIndices.get(maximumOccurringElement);
        int randomIndex = (int) (indices.size() * Math.random());
        return indices.get(randomIndex);
    }

    private static Map<Integer, List<Integer>> getElementIndices(int[] array) {
        Map<Integer, List<Integer>> elementIndices = new HashMap<>();
        for (int index = 0 ; index < array.length ; index++) {
            List<Integer> indices = elementIndices.getOrDefault(array[index], new ArrayList<>());
            indices.add(index);
            elementIndices.put(array[index], indices);
        }
        return elementIndices;
    }

    private static int maximumOccurringElement(int[] array) {
        Map<Integer, Integer> frequencies = getFrequencies(array);
        int maximumOccurringElement = Integer.MIN_VALUE, max = Integer.MIN_VALUE;
        for (Map.Entry<Integer, Integer> entry : frequencies.entrySet()) {
            int element = entry.getKey();
            int frequency = entry.getValue();
            if (frequency > max) {
                maximumOccurringElement = element;
                max = frequency;
            }
        }
        return maximumOccurringElement;
    }

    private static Map<Integer, Integer> getFrequencies(int[] array) {
        Map<Integer, Integer> frequencies = new HashMap<>();
        for (int element : array) {
            frequencies.put(element, frequencies.getOrDefault(element, 0) + 1);
        }
        return frequencies;
    }
}
