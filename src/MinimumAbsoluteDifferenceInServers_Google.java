import java.util.Arrays;


public class MinimumAbsoluteDifferenceInServers_Google {
  public static void main(String[] args) {
    int length = StdIn.getIntInput();
    int[] array = new int[length];
    StdIn.getIntInput(array);
    int result = minimumAbsoluteDifference(array);
    System.out.println(result);
  }

  private static int[] getIntArray(Integer[] array) {
      int[] result = new int[array.length];
      int index = 0;
      for (int element : array) {
          result[index++] = element;
      }
      return result;
  }

  private static int minimumAbsoluteDifference(int[] array) {
//    int[] intArray = getIntArray(array);
    int sum = Arrays.stream(array).sum();
    boolean[][] canCreateSumUsingSubset = getSubsetSumsData(array, (sum + 1) / 2) ;

    for (int row = canCreateSumUsingSubset.length - 1, column = canCreateSumUsingSubset[0].length - 1 ; column >= 0 ; column--) {
      if (canCreateSumUsingSubset[row][column]) {
        return Math.abs(2 * column - sum ) ;
      }
    }

    return -1;
  }

  private static boolean[][] getSubsetSumsData(int[] array, int sum) {
    int rows = array.length, columns = sum + 1;
    boolean[][] subsetSumExists = new boolean[array.length][sum + 1];

    // first column
    for (int row = 0, column = 0; row < rows ; row++) {
      subsetSumExists[row][column] = true;
    }

    // first row
    for (int row = 0, column = 1 ; column < columns ; column++) {
      subsetSumExists[row][column] = column == array[row] ;
    }

    // rest of the table
    for (int row = 1 ; row < rows ; row++) {
      for (int column = 1 ; column < columns ; column++) {
        subsetSumExists[row][column] =
                subsetSumExists[row-1][column]
                || array[row] == column
                || (array[row] < column && subsetSumExists[row - 1][column - array[row]]) ;

      }
    }

    return subsetSumExists;
  }
}
