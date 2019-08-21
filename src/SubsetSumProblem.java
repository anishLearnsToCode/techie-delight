// https://www.techiedelight.com/subset-sum-problem/
public class SubsetSumProblem {
    public static void main(String[] args) {
        int[] array = StdIn.takeInputAndGetIntArray();
        int sum = StdIn.getIntInput();
        System.out.println(subsetSumExists(array, sum));
    }

    private static boolean subsetSumExists(int[] array, int sum) {
        boolean[][] subsetSumData = subsetSumData(array, sum);
        return subsetSumData[subsetSumData.length - 1][subsetSumData[0].length - 1];
    }

    @SuppressWarnings("StatementWithEmptyBody")
    private static boolean[][] subsetSumData(int[] array, int sum) {
        int rows = sum + 1, columns = array.length;
        boolean[][] data = new boolean[rows][columns];

        for (int row = 0, column = 0 ; column < columns ; data[row][column++] = true);
        for (int row = 1, column = 0 ; row < rows ; data[row][column] = row == array[column], row++);

        for (int row = 1 ; row < rows ; row++) {
            for (int column = 1 ; column < columns ; column++) {
                data[row][column] = data[row][column - 1]
                        || row == array[column]
                        || row >= array[column] && data[row - array[column]][column - 1];
            }
        }

        return data;
    }
}
