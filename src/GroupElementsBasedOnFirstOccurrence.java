import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

// https://www.techiedelight.com/group-elements-array-based-first-occurrence/
public class GroupElementsBasedOnFirstOccurrence {
    public static void main(String[] args) {
        int[] array = StdIn.takeInputAndGetIntArray();
        Printer.print(groupedElements(array));
    }

    private static int[] groupedElements(int[] array) {
        Map<Integer, Integer> elementFrequencies = getElementFrequencies(array);
        List<Integer> elementOrder = getElementOrder(array);
        return groupedElements(array, elementFrequencies, elementOrder);
    }

    private static Map<Integer, Integer> getElementFrequencies(int[] array) {
        Map<Integer, Integer> elementFrequencies = new HashMap<>();
        for (int element : array) {
            elementFrequencies.put(element, elementFrequencies.getOrDefault(element, 0) + 1);
        }
        return elementFrequencies;
    }

    private static List<Integer> getElementOrder(int[] array) {
        List<Integer> order = new ArrayList<>();
        Set<Integer> elements = new HashSet<>();
        for (int element : array) {
            if (!elements.contains(element)) {
                order.add(element);
                elements.add(element);
            }
        }
        return order;
    }

    private static int[] groupedElements(int[] array, Map<Integer, Integer> elementFrequencies, List<Integer> elementOrder) {
        int[] result = new int[array.length];
        int from = 0;
        for (int element : elementOrder) {
            from = insertInto(result, element, from, from + elementFrequencies.get(element));
        }
        return result;
    }

    private static int insertInto(int[] array, int element, int startIndex, int endIndex) {
        while (startIndex++ < endIndex) {
            array[startIndex - 1] = element;
        }
        return endIndex;
    }
}
