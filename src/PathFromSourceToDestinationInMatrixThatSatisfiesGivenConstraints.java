import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.Stack;

//  https://www.techiedelight.com/find-path-source-destination-matrix-satisfies-given-constraints/
public class PathFromSourceToDestinationInMatrixThatSatisfiesGivenConstraints {
    public static void main(String[] args) {
        int[][] matrix = StdIn.getMatrixInput();
        Maze maze = new Maze(matrix);
        maze.printAllPathsToDestination();
    }

    private static class Maze {
        private final int rows;
        private final int columns;
        private final int[][] maze;
        private final Set<Coordinate> visitedCoordinates = new HashSet<>();
        private final Stack<Coordinate> path = new Stack<>();
        private final Coordinate destination;

        Maze(int[][] maze) {
            this.maze = maze.clone();
            this.rows = this.maze.length;
            this.columns = this.maze[0].length;
            destination = new Coordinate(rows - 1, columns - 1);
        }

        void printAllPathsToDestination() {
            path.clear();
            visitedCoordinates.clear();
            printAllPathsToDestination(new Coordinate(0, 0));
        }

        void printAllPathsToDestination(Coordinate start) {
            if (start.equals(destination)) {
                addToPath(destination);
                System.out.println("Path: " + path);
                removeFromPah(destination);
                return;
            }

            addToPath(start);
            if (canGoTo(left(start))) {
                printAllPathsToDestination(left(start));
            }

            if (canGoTo(right(start))) {
                printAllPathsToDestination(right(start));
            }

            if (canGoTo(top(start))) {
                printAllPathsToDestination(top(start));
            }

            if (canGoTo(bottom(start))) {
                printAllPathsToDestination(bottom(start));
            }

            removeFromPah(start);
        }

        private void addToPath(Coordinate coordinate) {
            path.push(coordinate);
            visitedCoordinates.add(coordinate);
        }

        private void removeFromPah(Coordinate coordinate) {
            path.pop();
            visitedCoordinates.remove(coordinate);
        }

        private boolean canGoTo(Coordinate coordinate) {
            return coordinate.row < rows
                   && coordinate.column < columns
                   && coordinate.row >= 0
                   && coordinate.column >= 0
                   && !visitedCoordinates.contains(coordinate);
        }

        private Coordinate left(Coordinate coordinate) {
            return shift(coordinate, new Coordinate(0, -maze[coordinate.row][coordinate.column]));
        }

        private Coordinate right(Coordinate coordinate) {
            return shift(coordinate, new Coordinate(0, maze[coordinate.row][coordinate.column]));
        }

        private Coordinate top(Coordinate coordinate) {
            return shift(coordinate, new Coordinate(-maze[coordinate.row][coordinate.column], 0));
        }

        private Coordinate bottom(Coordinate coordinate) {
            return shift(coordinate, new Coordinate(maze[coordinate.row][coordinate.column], 0));
        }

        private Coordinate shift(Coordinate origin, Coordinate shift) {
            return new Coordinate(origin.row + shift.row, origin.column + shift.column);
        }

        private static final class Coordinate {
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
