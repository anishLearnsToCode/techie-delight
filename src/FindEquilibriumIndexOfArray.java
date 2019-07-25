import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


// https://www.techiedelight.com/find-equilibrium-index-array/
public class FindEquilibriumIndexOfArray {
  public static void main(String[] args) {
    int length = StdIn.getIntInput();
    int[] array = new int[length];
    StdIn.getIntInput(array);
    List<Integer> equilibriumIndices = getAllEquilibriumIndices(array);
    System.out.println(equilibriumIndices);
  }

  private static List<Integer> getAllEquilibriumIndices(int[] array) {
    List<Integer> result = new ArrayList<>();
    int sum = Arrays.stream(array).sum();
    if (sum == 0) {
      result.add(-1);
    }

    for (int index = 0, currentSum = 0 ; index < array.length ; index++) {
      currentSum += array[index];
      sum -= array[index];

      if (currentSum == sum) {
        result.add(index);
      }
    }

    return result;
  }
}
