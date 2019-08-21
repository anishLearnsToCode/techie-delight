public class Zero_One_KnapsackProblem {
    public static void main(String[] args) {
        int[] values = StdIn.takeInputAndGetIntArray();
        int[] weights = StdIn.takeInputAndGetIntArray();
        int allowedWeight = StdIn.getIntInput();
        System.out.println(maximumKnapsack(values, weights, allowedWeight));
    }

    private static int maximumKnapsack(int[] values, int[] weights, int allowedWeight) {
        int rows = allowedWeight + 1, columns = values.length;
        int[][] data = new int[rows][columns];

        // first cell
        data[0][0] = weights[0] <= 0 ? values[0] : 0;

        // first row
        for (int row = 0, column = 1 ; column < columns ; column++) {
            data[row][column] = data[row][column - 1] + weights[column] <= 0 ? values[column] : 0;
        }

        // first column
        for (int row = 1, column = 0 ; row < rows ; row++) {
            data[row][column] = Math.max(
                    data[row-1][column],
                    row >= weights[column] ? values[column] : 0
            );
        }

        // rest of the table
        for (int row = 1 ; row < rows ; row++) {
            for (int column = 1 ; column < columns ; column++) {
                data[row][column] = Math.max(
                        Math.max(data[row - 1][column], data[row][column - 1]),
                        row >= weights[column] ? values[column] + data[row - weights[column]][column - 1] : 0
                );
            }
        }

        return data[rows - 1][columns - 1];
    }
}
