import java.util.HashMap;
import java.util.Map;

// https://www.techiedelight.com/find-ways-calculate-target-elements-array/
public class CalculateTargetFromElementsFromSpecifiedArray {
    public static void main(String[] args) {
        int[] array = StdIn.takeInputAndGetIntArray();
        int sum = StdIn.getIntInput();
        System.out.println(possibleToGetTargetIn(array, sum));
    }

    private static boolean possibleToGetTargetIn(int[] array, int target) {
        array = complementArrayWithNegativeElements(array);
        return possibleToGetTargetIn(array, target, new HashMap<>(), array.length - 1);
    }

    private static boolean possibleToGetTargetIn(int[] array, int target, Map<Integer, Map<Integer, Boolean>> targetData, int index) {
        if (target == 0) {
            updatePossibilityForTargetAndIndex(targetData, 0, index, true);
            return true;
        }

        if (index == 0) {
            updatePossibilityForTargetAndIndex(targetData, target, 0, array[index] == target);
            return array[index] == target;
        }

        if (targetData.containsKey(target)) {
            Map<Integer, Boolean> targetIndicesPossibility = targetData.get(target);
            if (targetIndicesPossibility.containsKey(index)) {
                return targetIndicesPossibility.get(index);
            }
        }

        boolean targetIsAcquirable =
                possibleToGetTargetIn(array, target - array[index], targetData, index - 1)
                || possibleToGetTargetIn(array, target, targetData, index - 1);
        updatePossibilityForTargetAndIndex(targetData, target, index, targetIsAcquirable);
        return targetIsAcquirable;
    }

    private static void updatePossibilityForTargetAndIndex(Map<Integer, Map<Integer, Boolean>> targetData, int target, int index, boolean isPossible) {
        Map<Integer, Boolean> indicesData = targetData.getOrDefault(target, new HashMap<>());
        indicesData.put(index, isPossible);
        targetData.put(target, indicesData);
    }

    private static int[] complementArrayWithNegativeElements(int[] array) {
        int[] result = new int[array.length * 2];
        for (int index = 0 ; index < array.length ; index++) {
            result[index] = array[index];
            result[index + 1] = -array[index];
        }
        return result;
    }
}
