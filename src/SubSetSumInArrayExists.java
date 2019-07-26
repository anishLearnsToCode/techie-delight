// https://www.techiedelight.com/find-subarray-having-given-sum-given-array/
public class SubSetSumInArrayExists {
    public static void main(String[] args) {
        int[] array = StdIn.takeInputAndGetArray();
        int sum = StdIn.getIntInput();
        System.out.println(subArraySumExists(array, sum));
    }

    private static boolean subArraySumExists(int[] array, int sum) {
        int rows = array.length, columns = sum + 1;
        boolean[][] subArraySumExists = new boolean[rows][columns];

        // first column -- all true: can be formed by taking empty sub set
        for (int row = 0, column = 0 ; row < rows ; row++) {
            subArraySumExists[row][column] = true;
        }

        // first row - depends on the first element in the array
        for (int row = 0, column = 1 ; column < columns ; column++) {
            subArraySumExists[row][column] = array[row] == column ;
        }

        // rest of the grid
        for (int row = 1 ; row < rows ; row++) {
            for (int column = 1 ; column < columns ; column++) {
                subArraySumExists[row][column] =
                        subArraySumExists[row - 1][column]
                        || array[row] == column
                        || (array[row] < column && subArraySumExists[row - 1][column - array[row]]) ;
            }
        }

        return subArraySumExists[rows - 1][columns - 1];
    }
}
