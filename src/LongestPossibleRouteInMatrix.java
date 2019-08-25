import javafx.util.Pair;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

// https://www.techiedelight.com/find-longest-possible-route-matrix/
public class LongestPossibleRouteInMatrix {
    public static void main(String[] args) {
        int[][] matrix = StdIn.getMatrixInput();
        Maze maze = new Maze(matrix);
        Pair<Integer, Integer> start = StdIn.getIntPair();
        Pair<Integer, Integer> end = StdIn.getIntPair();
        System.out.println(maze.longestPossiblePath(start, end));
    }

    private static class Maze {
        private final int rows;
        private final int columns;
        private final int[][] maze;
        private final Set<Coordinate> visitedPlaces = new HashSet<>();

        Maze(int[][] maze) {
            this.maze = maze.clone();
            this.rows = this.maze.length;
            this.columns = this.maze[0].length;
        }

        int longestPossiblePath(Pair<Integer, Integer> start, Pair<Integer, Integer> destination) {
            visitedPlaces.clear();
            return longestPossiblePath(
                    new Coordinate(start.getKey(), start.getValue()),
                    new Coordinate(destination.getKey(), destination.getValue())
            );
        }

        int longestPossiblePath(Coordinate start, Coordinate destination) {
            if (start.equals(destination)) {
                return 0;
            }

            placeAt(start);

            int answer = 0;
            if (canGoTo(left(start))) {
                answer = Math.max(answer, 1 + longestPossiblePath(left(start), destination));
            }

            if (canGoTo(top(start))) {
                answer = Math.max(answer, 1 + longestPossiblePath(top(start), destination));
            }

            if (canGoTo(right(start))) {
                answer = Math.max(answer, 1 + longestPossiblePath(right(start), destination));
            }

            if (canGoTo(bottom(start))) {
                answer = Math.max(answer, 1 + longestPossiblePath(bottom(start), destination));
            }

            removeFom(start);
            return answer;
        }

        private void placeAt(Coordinate coordinate) {
            visitedPlaces.add(coordinate);
        }

        private void removeFom(Coordinate coordinate) {
            visitedPlaces.remove(coordinate);
        }

        private boolean canGoTo(Coordinate coordinate) {
            return coordinate.row < rows
                   && coordinate.column < columns
                   && coordinate.row >= 0
                   && coordinate.column >= 0
                   && maze[coordinate.row][coordinate.column] == 1
                   && !visitedPlaces.contains(coordinate);
        }

        private Coordinate left(Coordinate coordinate) {
            return shift(coordinate, new Coordinate(0, -1));
        }

        private Coordinate top(Coordinate coordinate) {
            return shift(coordinate, new Coordinate(-1, 0));
        }

        private Coordinate right(Coordinate coordinate) {
            return shift(coordinate, new Coordinate(0, 1));
        }

        private Coordinate bottom(Coordinate coordinate) {
            return shift(coordinate, new Coordinate(1, 0));
        }

        private Coordinate shift(Coordinate origin, Coordinate shift) {
            return new Coordinate(origin.row + shift.row, origin.column + shift.column);
        }

        private static class Coordinate {
            private final int row;
            private final int column;

            Coordinate(int row, int column) {
                this.row = row;
                this.column = column;
            }

            @Override
            public boolean equals(Object obj) {
                if (obj.getClass() == Coordinate.class) {
                    Coordinate other = (Coordinate) obj;
                    return this.row == other.row && this.column == other.column;
                }
                return false;
            }

            @Override
            public int hashCode() {
                return Objects.hash(this.row, this.column);
            }

            @Override
            public String toString() {
                return "[" + row + "," + column + "]";
            }
        }
    }
}
