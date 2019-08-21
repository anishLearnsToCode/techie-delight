import java.util.Arrays;

// https://www.techiedelight.com/partition-problem/
public class PartitionProblem {
    public static void main(String[] args) {
        int[] array = StdIn.takeInputAndGetIntArray();
        System.out.println(canBePartitioned(array));
    }

    private static boolean canBePartitioned(int[] array) {
        int sum = Arrays.stream(array).sum();
        if (isOdd(sum)) {
            return false;
        }

        boolean[][] subsetSumData = subsetSumData(array, sum / 2);
        return subsetSumData[subsetSumData.length - 1][subsetSumData[0].length - 1];
    }

    private static boolean isOdd(int number) {
        return number % 2 == 1;
    }

    private static boolean[][] subsetSumData(int[] array, int sum) {
        int rows = sum + 1, columns = array.length;
        boolean[][] data = new boolean[rows][columns];

        // first row
        for (int row = 0, column = 0 ; column < columns ; column++) {
            data[row][column] = true;
        }

        // first column
        for (int row = 1, column = 0 ; row < rows ; row++) {
            data[row][column] = row == array[column];
        }

        // rest of the table
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
