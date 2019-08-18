import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://www.techiedelight.com/sort-elements-by-their-frequency-and-index/
public class CustomSort__FrequencyAndIndex {
    public static void main(String[] args) {
        int[] array = StdIn.takeInputAndGetIntArray();
        System.out.println(sort(array));
    }

    private static List<Integer> sort(int[] array) {
        List<Element> elements = elementsFrom(array);
        elements.sort(Element::compareTo);
        return valuesFrom(elements);
    }

    private static List<Element> elementsFrom(int[] array) {
        Map<Integer, Integer> frequencies = frequenciesOf(array);
        Map<Integer, Integer> firstIndices = firstIndicesIn(array);
        List<Element> elements = new ArrayList<>();

        for (int value : array) {
            elements.add(new Element(value, frequencies.get(value), firstIndices.get(value)));
        }

        return elements;
    }

    private static List<Integer> valuesFrom(List<Element> elements) {
        List<Integer> result = new ArrayList<>();
        for (Element element : elements) {
            result.add(element.value);
        }
        return result;
    }

    private static Map<Integer, Integer> frequenciesOf(int[] array) {
        Map<Integer, Integer> frequencies = new HashMap<>();
        for (int value : array) {
            frequencies.put(value, frequencies.getOrDefault(value, 0) + 1);
        }
        return frequencies;
    }

    private static Map<Integer, Integer> firstIndicesIn(int[] array) {
        Map<Integer, Integer> firstIndicesOf = new HashMap<>();
        for (int index = 0 ; index < array.length ; index++) {
            if (!firstIndicesOf.containsKey(array[index])) {
                firstIndicesOf.put(array[index], index);
            }
        }
        return firstIndicesOf;
    }

    private static class Element implements Comparable<Element> {
        private final int value;
        private final int frequency;
        private final int firstIndex;

        Element(int value, int frequency, int firstIndex) {
            this.value = value;
            this.frequency = frequency;
            this.firstIndex = firstIndex;
        }

        @Override
        public int compareTo(Element other) {
            if (this.frequency == other.frequency) {
                return Integer.compare(this.firstIndex, other.firstIndex);
            }

            return Integer.compare(other.frequency, this.frequency);
        }
    }
}
