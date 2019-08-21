import java.util.Arrays;

// https://www.techiedelight.com/minimum-sum-partition-problem/
public class MinimumSumPartitionProblem {
    public static void main(String[] args) {
        int[] array = StdIn.takeInputAndGetIntArray();
        System.out.println(minimumSumPartition(array));
    }

    private static int minimumSumPartition(int[] array) {
        int sum = Arrays.stream(array).sum();
        boolean[][] subsetSumData = subsetSumData(array, sum / 2);

        for (int row = subsetSumData.length - 1, column = subsetSumData[0].length - 1 ; row >= 0 ; row--) {
            if (subsetSumData[row][column]) {
                return sum - 2 * row;
            }
        }

        return Integer.MAX_VALUE;
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
                        || (row >= array[column] && data[row - array[column]][column - 1]);
            }
        }

        return data;
    }
}
