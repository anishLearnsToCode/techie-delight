import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://www.techiedelight.com/k-partition-problem-print-all-subsets/
public class KPartitionProblem {
    public static void main(String[] args) {
        int[] array = StdIn.takeInputAndGetIntArray();
        int k = StdIn.getIntInput();
        System.out.println(canBePartitionedInto(array, k));
    }

    private static boolean canBePartitionedInto(int[] array, int k) {
        int sum = Arrays.stream(array).sum();
        if (k == 0 || sum % k != 0) {
            return false;
        } else if (k == 1) {
          return true;
        }

        List<Integer> sums = getSums(sum / k, k);
        return canBePartitionedInto(array, 0, sums, new HashMap<>());
    }

    private static boolean canBePartitionedInto(int[] array, int index, List<Integer> sums, Map<StringBuilder, Boolean> memory) {
        StringBuilder key = getKey(sums, index);
        if (memory.containsKey(key)) {
            return memory.get(key);
        }

        if (index == array.length) {
            boolean answer = allSumsAreResolved(sums);
            memory.put(key, answer);
            return answer;
        }

        if (sumIsNegative(sums)) {
            memory.put(key, false);
            return false;
        }

        boolean answer = false;
        for (int i= 0 ; i < sums.size() ; i++) {
            sums.set(i, sums.get(i) - array[index]);
            answer = canBePartitionedInto(array, index + 1, sums, memory);
            sums.set(i, sums.get(i) + array[index]);

            if (answer) {
                break;
            }
        }
        memory.put(key, answer);
        return answer;
    }

    private static List<Integer> getSums(int sum, int length) {
        List<Integer> result = new ArrayList<>();
        while (length-- > 0) {
            result.add(sum);
        }
        return result;
    }

    private static boolean allSumsAreResolved(List<Integer> sums) {
        for (int sum : sums) {
            if (sum != 0) {
                return false;
            }
        }
        return true;
    }

    private static boolean sumIsNegative(List<Integer> sums) {
        for (int sum : sums) {
            if (sum < 0) {
                return true;
            }
        }
        return false;
    }

    private static StringBuilder getKey(List<Integer> sums, int index) {
        StringBuilder key = new StringBuilder(new StringBuilder());
        for (int sum : sums) {
            key.append(sum).append("|");
        }
        return key.append(index);
    }
}
