import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

//https://www.techiedelight.com/combinations-of-words-formed-replacing-given-numbers-corresponding-english-alphabet/
public class CombinationOfWordsFormedByReplacingGivenNumbersWithCorrespondingAlphabets {
    public static void main(String[] args) {
        int[] array = StdIn.takeInputAndGetIntArray();
        System.out.println(combinationOfWordsFormed(array));
    }

    private static Set<String> combinationOfWordsFormed(int[] array) {
        return combinationOfWordsFormed(array, new Range(0, array.length), new HashMap<>());
    }

    private static Set<String> combinationOfWordsFormed(int[] array, Range range, Map<Range, Set<String>> answerData) {
        if (answerData.containsKey(range)) {
            return answerData.get(range);
        }

        switch (range.length) {
            case 0 : return new HashSet<>();
            case 1 :
                Set<String> set = new HashSet<>();
                set.add(toAlphabet(array[range.startIndex]));
                answerData.put(range, set);
                return set;
            case 2 :
                set = new HashSet<>();
                set.add(toAlphabet(array[range.startIndex]) + toAlphabet(array[range.startIndex + 1]));
                String combined = toAlphabet(array[range.startIndex], array[range.startIndex + 1]);
                if (Objects.nonNull(combined)) {
                    set.add(combined);
                }
                answerData.put(range, set);
                return set;
        }

        Set<String> answer = new HashSet<>();
        for (int index = range.startIndex ; index < range.startIndex + 2 ; index++) {
            Set<String> first = combinationOfWordsFormed(array, new Range(range.startIndex, index + 1), answerData);
            Set<String> second = combinationOfWordsFormed(array, new Range(index + 1, range.endIndex), answerData);
            answer.addAll(combine(first, second));
        }
        answerData.put(range, answer);
        return answer;
    }

    private static Set<String> combine(Set<String> first, Set<String> second) {
        Set<String> result = new HashSet<>();
        for (String firstString : first) {
            for (String secondString : second) {
                result.add(firstString + secondString);
            }
        }
        return result;
    }

    private static String toAlphabet(int number) {
        char character = (char)(number + 'A' - 1);
        return String.valueOf(character);
    }

    private static String toAlphabet(int tensDigit, int onesDigit) {
        if (tensDigit * 10 + onesDigit > 26) {
            return null;
        }
        return toAlphabet(tensDigit * 10 + onesDigit);
    }

    private static class Range {
        final int startIndex;
        final int endIndex;
        final int length;

        Range(final int startIndex, final int endIndex) {
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
