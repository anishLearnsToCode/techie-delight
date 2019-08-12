import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// https://www.techiedelight.com/find-distinct-combinations-given-length-2/
public class FindAllDistinctCombinationsOfGivenLength {
    public static void main(String[] args) {
        int[] array = StdIn.takeInputAndGetIntArray();
        int combinationLength = StdIn.getIntInput();
        System.out.println(distinctCombinations(array, combinationLength));
    }

    private static Set<Tuple> distinctCombinations(int[] array, int combinationLength) {
        Set<Tuple> result = new HashSet<>();
        distinctCombinations(array, combinationLength, new Tuple(), result, 0);
        return result;
    }

    private static void distinctCombinations(int[] array, int combinationLength, Tuple tuple, Set<Tuple> distinctCombinations, int index) {
        if (combinationLength == 0) {
            distinctCombinations.add(tuple);
            return;
        }

        if (index == array.length) {
            return;
        }

        distinctCombinations(array, combinationLength - 1, tuple.add(array[index]), distinctCombinations, index + 1);
        distinctCombinations(array, combinationLength, tuple, distinctCombinations, index + 1);
    }

    private static class Tuple {
        private final Map<Integer, Integer> frequencies = new HashMap<>();

        Tuple() { }

        private Tuple(Map<Integer, Integer> frequencies) {
            this.frequencies.putAll(frequencies);
        }

        private Tuple(Map<Integer, Integer> frequencies, int element) {
            this(frequencies);
            this.frequencies.put(element, frequencies.getOrDefault(element, 0) + 1);
        }

        Tuple add (int element) {
            return new Tuple(frequencies, element);
        }

        @Override
        public boolean equals(Object obj) {
            return obj instanceof Tuple && equals((Tuple) obj);
        }

        private boolean equals(Tuple tuple) {
            if (this.frequencies.size() != tuple.frequencies.size()) {
                return false;
            }

            for (Map.Entry<Integer, Integer> entry : this.frequencies.entrySet()) {
                int number = entry.getKey();
                int frequency = entry.getValue();
                if (tuple.frequencies.getOrDefault(number, 0) != frequency) {
                    return false;
                }
            }

            return true;
        }

        @Override
        public int hashCode() {
            int hashcode = 0;
            for (Map.Entry<Integer, Integer> entry : this.frequencies.entrySet()) {
                Integer number = entry.getKey();
                int frequency = entry.getValue();
                hashcode += number.hashCode() * frequency ;
            }
            return hashcode;
        }

        @Override
        public String toString() {
            return frequencies.toString();
        }
    }
}
