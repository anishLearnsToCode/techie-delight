import javafx.util.Pair;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

// https://www.techiedelight.com/find-total-number-unique-paths-maze-source-destination/
public class TotalNumberOfUniquePathsFromSourceToDestinationInMaze {
    public static void main(String[] args) {
        int[][] matrix = StdIn.getMatrixInput();
        Maze maze = new Maze(matrix);
        Pair<Integer, Integer> start = StdIn.getIntPair();
        Pair<Integer, Integer> end = StdIn.getIntPair();
        System.out.println(maze.uniqueWaysToReachDestination(start, end));
    }

    private static class Maze {
        private final int rows;
        private final int columns;
        private final int[][] maze;
        private final Set<Coordinate> visitedCoordinates = new HashSet<>();

        Maze(int[][] maze) {
            this.maze = maze.clone();
            this.rows = maze.length;
            this.columns = maze[0].length;
        }

        int uniqueWaysToReachDestination(Pair<Integer, Integer> start, Pair<Integer, Integer> end) {
            visitedCoordinates.clear();
            return uniqueWaysToReachDestination(
                    new Coordinate(start.getKey(), start.getValue()),
                    new Coordinate(end.getKey(), end.getValue())
            );
        }

        int uniqueWaysToReachDestination(Coordinate start, Coordinate destination) {
            if (start.equals(destination)) {
                return 1;
            }

            addToPath(start);

            int answer = 0;
            if (canGoTo(left(start))) {
                answer += uniqueWaysToReachDestination(left(start), destination);
            }

            if (canGoTo(right(start))) {
                answer += uniqueWaysToReachDestination(right(start), destination);
            }

            if (canGoTo(top(start))) {
                answer += uniqueWaysToReachDestination(top(start), destination);
            }

            if (canGoTo(bottom(start))) {
                answer += uniqueWaysToReachDestination(bottom(start), destination);
            }

            removeFromPath(start);
            return answer;
        }

        private boolean canGoTo(Coordinate coordinate) {
            return coordinate.row < rows
                   && coordinate.column < columns
                   && coordinate.row >= 0
                   && coordinate.column >= 0
                   && maze[coordinate.row][coordinate.column] == 1
                   && !visitedCoordinates.contains(coordinate);
        }

        private Coordinate left(Coordinate coordinate) {
            return shift(coordinate, new Coordinate(0, -1));
        }

        private Coordinate right(Coordinate coordinate) {
            return shift(coordinate, new Coordinate(0, 1));
        }

        private Coordinate top(Coordinate coordinate) {
            return shift(coordinate, new Coordinate(-1, 0));
        }

        private Coordinate bottom(Coordinate coordinate) {
            return shift(coordinate, new Coordinate(1, 0));
        }

        private Coordinate shift(Coordinate origin, Coordinate shift) {
            return new Coordinate(origin.row + shift.row, origin.column + shift.column);
        }

        private void addToPath(Coordinate coordinate) {
            visitedCoordinates.add(coordinate);
        }

        private void removeFromPath(Coordinate coordinate) {
            visitedCoordinates.remove(coordinate);
        }
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
    }
}
