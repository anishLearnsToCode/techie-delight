import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// https://www.techiedelight.com/count-distinct-elements-every-sub-array-size-k-array/
public class FindCountOfDistinctElementsInEverySubArrayOfSizeK {
    public static void main(String[] args) {
        int[] array = StdIn.takeInputAndGetIntArray();
        int k = StdIn.getIntInput();
        Printer.print(distinctElementsInSubArraySize(array, k));
    }

    private static int[] distinctElementsInSubArraySize(int[] array, int subArraySize) {
        Map<Integer, Integer> elementFrequencies = getFrequenciesOfElements(array, subArraySize);
        Set<Integer> distinctElements = distinctIn(elementFrequencies);
        int[] result = new int[array.length - subArraySize + 1];

        for (int index = 0 ; index < array.length - subArraySize + 1 ; index++) {
            result[index] = distinctElements.size();

            removeElementFromSubArray(elementFrequencies, array[index]);
            if (elementIsPresentInSubArray(array[index], elementFrequencies)) {
                distinctElements.add(array[index]);
            } else {
                distinctElements.remove(array[index]);
            }

            if (index < array.length - subArraySize) {
                addElementToSubArray(elementFrequencies, array[index + subArraySize]);
                if (elementIsPresentInSubArray(array[index + subArraySize], elementFrequencies)) {
                    distinctElements.add(array[index + subArraySize]);
                } else {
                    distinctElements.remove(array[index + subArraySize]);
                }
            }
        }

        return result;
    }

    private static boolean elementIsPresentInSubArray(int element, Map<Integer, Integer> frequencies) {
        return frequencies.getOrDefault(element, 0) > 0 ;
    }

    private static void removeElementFromSubArray(Map<Integer, Integer> frequencies, int element) {
        frequencies.put(element, frequencies.get(element) - 1);
    }

    private static void addElementToSubArray(Map<Integer, Integer> frequencies, int element) {
        frequencies.put(element, frequencies.getOrDefault(element, 0) + 1);
    }

    private static Map<Integer, Integer> getFrequenciesOfElements(int[] array, int subArraySize) {
        Map<Integer, Integer> frequencies = new HashMap<>();
        for (int index = 0 ; index < subArraySize ; index++) {
            frequencies.put(array[index], frequencies.getOrDefault(array[index], 0) + 1);
        }
        return frequencies;
    }

    private static Set<Integer> distinctIn(Map<Integer, Integer> frequencies) {
        return new HashSet<>(frequencies.keySet());
    }
}
