import java.util.HashMap;
import java.util.Map;

// https://www.techiedelight.com/find-odd-occurring-element-array-single-traversal/
public class FindOddOccurringElements {
    public static void main(String[] args) {
        int[] array = StdIn.takeInputAndGetIntArray();
        System.out.println(oddOccurringElement(array));
        System.out.println(oddOccurringElementAlternative(array));
    }

    // Using XOR Time complexity: O(n) Space Complexity: O(1)
    private static int oddOccurringElementAlternative(int[] array) {
        int result = 0;
        for (int number : array) {
            result ^= number;
        }
        return result;
    }

    // Using Hashing: Time complexity: O(n) and space complexity O(n)
    private static int oddOccurringElement(int[] array) {
        Map<Integer, Integer> numberFrequencies = getFrequenciesOf(array);
        for (Map.Entry<Integer, Integer> numberFrequency : numberFrequencies.entrySet()) {
            int number = numberFrequency.getKey();
            int frequency = numberFrequency.getValue();
            if (isOdd(frequency)) {
                return number;
            }
        }

        return Integer.MIN_VALUE;
    }

    private static boolean isOdd(int number) {
        return number % 2 == 1;
    }

    private static Map<Integer, Integer> getFrequenciesOf(int[] array) {
        Map<Integer, Integer> frequencies = new HashMap<>();
        for (int number : array) {
            frequencies.put(number, frequencies.getOrDefault(number, 0) + 1);
        }
        return frequencies;
    }
}
