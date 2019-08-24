import java.util.*;

// https://www.techiedelight.com/print-distinct-subsets-given-set/
public class DistinctSubsetsOfGivenSet {
    public static void main(String[] args) {
        int[] array = StdIn.takeInputAndGetIntArray();
        System.out.println(distinctSubsetOf(array));
        System.out.println(distinctSubsets(array));
    }

    private static Set<Map<Integer, Integer>> distinctSubsetOf(int[] array) {
        return distinctSubsetOf(array, new Range(0, array.length), new HashMap<>());
    }

    private static Set<Map<Integer, Integer>> distinctSubsetOf(int[] array, Range range, Map<Range, Set<Map<Integer, Integer>>> answerData) {
        if (answerData.containsKey(range)) {
            return answerData.get(range);
        }

        switch (range.length) {
            case 0 :
                Set<Map<Integer, Integer>> result = new HashSet<>(Collections.singleton(new HashMap<Integer, Integer>()));
                answerData.put(range, result);
                return result;

            case 1 :
                Set<Map<Integer, Integer>> phi = distinctSubsetOf(array, new Range(range.startIndex + 1, range.endIndex), answerData);
                phi.add(Collections.singletonMap(array[range.startIndex], 1));
                answerData.put(range, phi);
                return phi;
        }

        Set<Map<Integer, Integer>> result = distinctSubsetOf(array, new Range(range.startIndex + 1, range.endIndex), answerData);
        Set<Map<Integer, Integer>> answer = from(result, array[range.startIndex]);
        answerData.put(range, answer);
        return answer;
    }

    private static Set<List<Integer>> distinctSubsets(int[] array) {
        return distinctSubsets(array, 0, new HashMap<>());
    }

    private static Set<List<Integer>> distinctSubsets(int[] array, int index, Map<Integer, Set<List<Integer>>> answerData) {
        if (answerData.containsKey(index)) {
            return answerData.get(index);
        }

        if (index == array.length) {
            return Collections.singleton(new ArrayList<>());
        } else if (index == array.length - 1) {
            Set<List<Integer>> result = new HashSet<>();
            result.add(new ArrayList<>());
            result.add(Collections.singletonList(array[index]));
            answerData.put(index, result);
            return result;
        }

        Set<List<Integer>> result = distinctSubsets(array, index + 1, answerData);
        Set<List<Integer>> answer = new HashSet<>(result);
        for (List<Integer> tuple : result) {
            List<Integer> withCurrentElement = new ArrayList<>(tuple);
            withCurrentElement.add(array[index]);
            answer.add(withCurrentElement);
        }
        answerData.put(index, answer);
        return answer;
    }

    private static Set<Map<Integer, Integer>> from(Set<Map<Integer, Integer>> set, int number) {
        Set<Map<Integer, Integer>> result = new HashSet<>(set);
        for (Map<Integer, Integer> tuple : set) {
            Map<Integer, Integer> clone = new HashMap<>(tuple);
            clone.put(number, clone.getOrDefault(number, 0) + 1);
            result.add(clone);
        }
        return result;
    }

    private static class Range {
        final int startIndex;
        final int endIndex;
        final int length;

        Range(int startIndex, int endIndex) {
            this.startIndex = startIndex;
            this.endIndex = endIndex;
            this.length = endIndex - startIndex;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj.getClass() == Range.class) {
                Range other = (Range) obj;
                return this.startIndex == other.startIndex && this.endIndex == other.endIndex;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(startIndex, endIndex);
        }
    }
}
