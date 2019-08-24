import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// https://www.techiedelight.com/3-partition-problem/
public class ThreePartitionProblem {
    public static void main(String[] args) {
        int[] array = StdIn.takeInputAndGetIntArray();
        System.out.println(canBePartitionedInto3(array));
    }

    private static boolean canBePartitionedInto3(int[] array) {
        int sum = Arrays.stream(array).sum();
        return sum % 3 == 0 &&
               canBePartitionedInto3(array, sum / 3, sum / 3, sum / 3, 0, new HashMap<>());
    }

    private static boolean canBePartitionedInto3(int[] array, int firstSet, int secondSet, int thirdSet, int index,
                                                 Map<String, Boolean> answerData) {

        String key = getUniqueString(firstSet, secondSet, thirdSet, index);
        if (answerData.containsKey(key)) {
            return answerData.get(key);
        }

        if (index == array.length) {
            return firstSet == 0 && secondSet == 0 && thirdSet == 0 ;
        }

        if (firstSet < 0 || secondSet < 0 || thirdSet < 0) {
            answerData.put(key, false);
            return false;
        }

        boolean answer =
                canBePartitionedInto3(array, firstSet - array[index], secondSet, thirdSet, index + 1, answerData)
                || canBePartitionedInto3(array, firstSet, secondSet - array[index], thirdSet, index + 1, answerData)
                || canBePartitionedInto3(array, firstSet, secondSet, thirdSet - array[index], index + 1, answerData);
        answerData.put(key, answer);
        return answer;
    }

    private static String getUniqueString(int first, int second, int third, int index) {
        return first + "" + second + "" + third + "" + index;
    }
}
