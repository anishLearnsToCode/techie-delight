import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//https://www.techiedelight.com/print-combinations-integers-sum-given-number/
public class PrintAllCombinationsOfPositiveIntegersInIncreasingOrderThatSumToAGivenNumber {
  public static void main(String[] args) {
    int sum = StdIn.getIntInput();
    System.out.println(allCombinationsThatSumTo(sum));
  }

  private static List<List<Integer>> allCombinationsThatSumTo(int sum) {
    Map<Integer, List<List<Integer>>> combinations = new HashMap<>();
    combinations.put(0, new ArrayList<>());
    return allCombinationsThatSumTo(sum, combinations);
  }

  private static List<List<Integer>> allCombinationsThatSumTo(int sum, Map<Integer, List<List<Integer>>> combinations) {
    if (combinations.containsKey(sum)) {
      return combinations.get(sum);
    }

    List<List<Integer>> combinationsForSum = new ArrayList<>();
    for (int number = 1 ; number <= sum / 2 ; number++) {
      combinationsForSum.addAll(combine(number, allCombinationsThatSumTo(sum - number, combinations)));
    }

    List<Integer> sole = new ArrayList<>();
    sole.add(sum);
    combinationsForSum.add(sole);
    combinations.put(sum, combinationsForSum);
    return combinationsForSum;
  }

  private static List<List<Integer>> combine(final int number, final List<List<Integer>> combinations) {
    List<List<Integer>> result = new ArrayList<>();
    for (List<Integer> combination : combinations) {
      if (combination.get(0) < number) {
        continue;
      }

      List<Integer> possiblePathToSum = new ArrayList<>();
      possiblePathToSum.add(number);
      possiblePathToSum.addAll(combination);
      result.add(possiblePathToSum);
    }

    return result;
  }
}
