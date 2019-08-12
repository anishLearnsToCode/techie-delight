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
    for (int number = 1 ; number <= sum - 1 ; number++) {
      combinationsForSum.addAll(combineFromIndex(number, allCombinationsThatSumTo(sum - number, combinations), number - 1));
    }

    List<Integer> sole = new ArrayList<>();
    sole.add(sum);
    combinationsForSum.add(sole);
    combinations.put(sum, combinationsForSum);
    return combinationsForSum;
  }

  private static List<List<Integer>> combineFromIndex(final int number, final List<List<Integer>> combinations,
          final int startIndex) {

    List<List<Integer>> result = new ArrayList<>();
    for (int index = startIndex ; index < combinations.size() ; index++) {
      List<Integer> combination = new ArrayList<>();
      combination.add(number);
      combination.addAll(combinations.get(index));
      result.add(combination);
    }
    return result;
  }
}
