import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

// https://www.techiedelight.com/print-possible-solutions-n-queens-problem/
public class NQueensProblem {
    public static void main(String[] args) {
        int size = StdIn.getIntInput();
        NQueens chessBoard = new NQueens(size);
        chessBoard.printAllPossiblePositions();
    }

    private static class NQueens {
        private final Tuple rows = new Tuple();
        private final Tuple columns = new Tuple();
        private final Tuple leftDiagonals = new Tuple();
        private final Tuple rightDiagonals = new Tuple();
        private final int size;
        private final int columnCount;
        private final int rowCount;
        private int queensPlaced = 0;

        NQueens(int size) {
            this.size = size;
            rowCount = size;
            columnCount = size;
        }

        void printAllPossiblePositions() {
            queensPlaced = 0;
            printAllPossiblePositions(0);
        }

        private void printAllPossiblePositions(int row) {
            if (row == rowCount) {
                if (queensPlaced == size) {
                    print();
                }
                return;
            }

            for (int column = 0 ; column < columnCount ; column++) {
                if (canPlaceAt(row, column)) {
                    placeQueenAt(row, column);
                    printAllPossiblePositions(row + 1);
                    removeQueenFrom(row, column);
                }
            }
        }

        private boolean canPlaceAt(int row, int column) {
            return !rows.contains(row) &&
                   !columns.contains(column) &&
                   !leftDiagonals.contains(leftDiagonalNumber(row, column))&&
                   !rightDiagonals.contains(rightDiagonalNumber(row, column));
        }

        private void placeQueenAt(int row, int column) {
            rows.add(row);
            columns.add(column);
            leftDiagonals.add(leftDiagonalNumber(row, column));
            rightDiagonals.add(rightDiagonalNumber(row, column));
            queensPlaced++;
        }

        private void removeQueenFrom(int row, int column) {
            rows.remove(row);
            columns.remove(column);
            leftDiagonals.remove(leftDiagonalNumber(row, column));
            rightDiagonals.remove(rightDiagonalNumber(row, column));
            queensPlaced--;
        }

        private int leftDiagonalNumber(int row, int column) {
            return column - row + size - 1;
        }

        private int rightDiagonalNumber(int row, int column) {
            return column + row;
        }

        private void print() {
            System.out.println("Configuration");
            System.out.println(rows);
            System.out.println(columns);
            System.out.println(leftDiagonals);
            System.out.println(rightDiagonals);
        }
    }

    private static class Tuple {
        private final Map<Integer, Integer> frequencies;

        Tuple() {
            frequencies = new HashMap<>();
        }

        void add(int element) {
            frequencies.put(element, frequencies.getOrDefault(element, 0) + 1);
        }

        void remove(int element) {
            if (frequencies.containsKey(element) && frequencies.get(element) == 1) {
                frequencies.remove(element);
            } else if (frequencies.containsKey(element)) {
                frequencies.put(element, frequencies.get(element) - 1);
            }
        }

        boolean contains(int element) {
            return frequencies.containsKey(element);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj.getClass() == Tuple.class) {
                Tuple other = (Tuple) obj;
                return this.frequencies.equals(other.frequencies);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(frequencies);
        }

        @Override
        public String toString() {
            return frequencies.toString();
        }
    }
}
