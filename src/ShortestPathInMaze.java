import javafx.util.Pair;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

// https://www.techiedelight.com/find-shortest-path-in-maze/
public class ShortestPathInMaze {
    public static void main(String[] args) {
        int maze[][] = StdIn.getMatrixInput();
        Maze game = new Maze(maze);
        Pair<Integer, Integer> start = StdIn.getIntPair();
        Pair<Integer, Integer> end = StdIn.getIntPair();
        System.out.println(game.shortestPath(start, end));
    }

    private static class Maze {
        private final int rows;
        private final int columns;
        private final int[][] maze;
        private final Set<Coordinate> visitedCoordinates = new HashSet<>();

        Maze(int[][] maze) {
            this.maze = maze.clone();
            this.rows = this.maze.length;
            this.columns = this.maze[0].length;
        }

        int shortestPath(Pair<Integer, Integer> start, Pair<Integer, Integer> destination) {
            visitedCoordinates.clear();
            return shortestPath(
                    new Coordinate(start.getKey(), start.getValue()),
                    new Coordinate(destination.getKey(), destination.getValue())
            );
        }

        int shortestPath(Coordinate start, Coordinate destination) {
            if (start.equals(destination)) {
                return 0;
            }

            placeAt(start);
            int answer = Integer.MAX_VALUE ;

            if (canGoTo(left(start))) {
                answer = Math.min(answer, 1 + shortestPath(new Coordinate(start.row, start.column - 1), destination));
            }

            if (canGoTo(top(start))) {
                answer = Math.min(answer, 1 + shortestPath(top(start), destination));
            }

            if (canGoTo(right(start))) {
                answer = Math.min(answer, 1 + shortestPath(right(start), destination));
            }

            if (canGoTo(bottom(start))) {
                answer = Math.min(answer, 1 + shortestPath(bottom(start), destination));
            }

            removeFrom(start);
            return answer;
        }

        private void placeAt(Coordinate coordinate) {
            visitedCoordinates.add(coordinate);
        }

        private void removeFrom(Coordinate coordinate) {
            visitedCoordinates.remove(coordinate);
        }

        private boolean canGoTo(Coordinate coordinate) {
            return coordinate.row < rows
                   && coordinate.column < columns
                   && coordinate.row >= 0
                   && coordinate.column >= 0
                   && !visitedCoordinates.contains(coordinate)
                   && maze[coordinate.row][coordinate.column] == 1;
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

        private Coordinate shift(Coordinate origin, Coordinate displacement) {
            return new Coordinate(origin.row + displacement.row, origin.column + displacement.column);
        }

        private class Coordinate {
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
