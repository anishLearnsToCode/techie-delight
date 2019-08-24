import javafx.util.Pair;

import javax.security.auth.login.Configuration;
import java.util.*;

// https://www.techiedelight.com/find-combinations-of-elements-satisfies-given-constraints/
public class AllCombinationsOfElementsSatisfyingGivenConstraints {
    public static void main(String[] args) {
        int number = StdIn.getIntInput();
        System.out.println(allPossibleCombinations(number));
    }

    private static Set<List<Integer>> allPossibleCombinations(int number) {
        Set<Integer> unusedIndices = getUnusedIndices(number);
        Set<Integer> numbers = getNumbers(number);
        List<Integer> configuration = getConfiguration(number);
        Set<List<Integer>> answer = allPossibleCombinations(unusedIndices, numbers, configuration);
        System.out.println(answer.size());
        return answer;
    }

    private static Set<List<Integer>> allPossibleCombinations(
            Set<Integer> unusedIndices, Set<Integer> numbers, List<Integer> currentConfiguration) {

        if (numbers.isEmpty()) {
            return new HashSet<>(Collections.singleton(currentConfiguration));
        }

        Set<List<Integer>> result = new HashSet<>();
        for (int index : unusedIndices) {
            for (int possibleNumber : numbers) {
                if (isPossibleToPlace(possibleNumber, index, currentConfiguration)) {
                    Set<Integer> unusedIndicesNew = removeFromUnusedIndices(unusedIndices, index, possibleNumber);
                    Set<Integer> numbersNew = removeCurrentNumberFromNumbers(numbers, possibleNumber);
                    List<Integer> currentConfigurationNew = addToConfiguration(currentConfiguration, possibleNumber, index);
                    Set<List<Integer>> possibleCombinations = allPossibleCombinations(unusedIndicesNew, numbersNew, currentConfigurationNew);
                    result.addAll(possibleCombinations);
                }
            }
            break;
        }

        return result;
    }

    private static Set<Integer> removeFromUnusedIndices(Set<Integer> unusedIndices, int index, int number) {
        Set<Integer> result = new HashSet<>(unusedIndices);
        result.remove(index);
        result.remove(index + number + 1);
        return result;
    }

    private static Set<Integer> removeCurrentNumberFromNumbers(Set<Integer> numbers, int number) {
        Set<Integer> result = new HashSet<>(numbers);
        result.remove(number);
        return result;
    }

    private static List<Integer> addToConfiguration(List<Integer> configuration, int number, int index) {
        List<Integer> result = new ArrayList<>(configuration);
        result.set(index, number);
        result.set(index + number + 1, number);
        return result;
    }

    private static void addToUnusedIndices(Set<Integer> unusedIndices, int index, int number) {
        unusedIndices.add(index);
        unusedIndices.add(index + number + 1);
    }

    private static void addCurrentNumberFromNumbers(Set<Integer> numbers, int number) {
        numbers.add(number);
    }

    private static void removeFromConfiguration(List<Integer> configuration, int number, int index) {
        configuration.set(index, 0);
        configuration.set(index + number + 1, 0);
    }

    private static boolean isPossibleToPlace(int number, int index, List<Integer> configuration) {
        return index + number + 1 < configuration.size()
               && configuration.get(index) == 0
               && configuration.get(index + number + 1) == 0;
    }

    private static Set<Integer> getNumbers(int number) {
        Set<Integer> result = new HashSet<>();
        for (int index = 1 ; index <= number ; index++) {
            result.add(index);
        }
        return result;
    }

    private static Set<Integer> getUnusedIndices(int number) {
        Set<Integer> result = new HashSet<>();
        for (int index = 0 ; index < number * 2 ; index++) {
            result.add(index);
        }
        return result;
    }

    private static List<Integer> getConfiguration(int number) {
        List<Integer> result = new ArrayList<>(number * 2);
        for (int index = 0 ; index < number * 2 ; index++) {
            result.add(0);
        }
        return result;
    }
}
