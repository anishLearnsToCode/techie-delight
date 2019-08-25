import javafx.util.Pair;

import java.util.*;

// https://www.techiedelight.com/chess-knight-problem-find-shortest-path-source-destination/
public class ChessKnightProblem__ShortestPathFromSourceToDestination {
    public static void main(String[] args) {
        int size = StdIn.getIntInput();
        Pair<Integer, Integer> startPosition = StdIn.getIntPair();
        Pair<Integer, Integer> destination = StdIn.getIntPair();
        KnightsProblem chessBoard = new KnightsProblem(size);
        System.out.println(chessBoard.shortestPath(startPosition, destination));
    }
}

class KnightsProblem {
    private final int size;
    private final Tuple<Coordinate> visitedSpaces= new Tuple<>();

    KnightsProblem(int size) {
        this.size = size;
    }

    int shortestPath(Pair<Integer, Integer> start, Pair<Integer, Integer> destination) {
        visitedSpaces.clear();
        return shortestPath(new Coordinate(start), new Coordinate(destination));
    }

    private int shortestPath(Coordinate start, Coordinate destination) {
        if (outOfChessBoard(start) || alreadyVisited(start)) {
            return Integer.MAX_VALUE - 1;
        }

        if (start.equals(destination)) {
            return 0;
        }

        placeAt(start);

        int answer = Integer.MAX_VALUE;
        answer = Math.min(answer, 1 + shortestPath(new Coordinate(start.row - 2, start.column - 1), destination));
//        removeFrom(start.row - 2, start.column - 1);

        answer = Math.min(answer, 1 + shortestPath(new Coordinate(start.row - 2, start.column + 1), destination));
//        removeFrom(start.row - 2, start.column + 1);

        answer = Math.min(answer, 1 + shortestPath(new Coordinate(start.row + 1, start.column + 2), destination));
//        removeFrom(start.row + 1, start.column + 1);

        answer = Math.min(answer, 1 + shortestPath(new Coordinate(start.row - 1, start.column + 2), destination));
//        removeFrom(start.row - 1, start.column + 2);

        answer = Math.min(answer, 1 + shortestPath(new Coordinate(start.row + 2, start.column - 1), destination));
//        removeFrom(start.row + 2, start.column - 1);

        answer = Math.min(answer, 1 + shortestPath(new Coordinate(start.row + 2, start.column + 1), destination));
//        removeFrom(start.row + 2, start.column + 1);

        answer = Math.min(answer, 1 + shortestPath(new Coordinate(start.row - 1, start.column - 2), destination));
//        removeFrom(start.row - 1, start.column - 2);

        answer = Math.min(answer, 1 + shortestPath(new Coordinate(start.row + 1, start.column - 2), destination));
//        removeFrom(start.row + 1, start.column - 2);

        removeFrom(start);
        return answer;
    }

    private boolean outOfChessBoard(Coordinate coordinate) {
        return coordinate.row >= size
               || coordinate.column >= size
               || coordinate.row < 0
               || coordinate.column < 0 ;
    }

    private boolean alreadyVisited(Coordinate coordinate) {
        return visitedSpaces.contains(coordinate);
    }

    private void placeAt(Coordinate coordinate) {
        visitedSpaces.add(coordinate);
    }

    private void removeFrom(Coordinate coordinate) {
        visitedSpaces.remove(coordinate);
    }

    private void removeFrom(int row, int column) {
        visitedSpaces.remove(new Coordinate(row, column));
    }

    private class Coordinate {
        private final int row;
        private final int column;

        Coordinate(Pair<Integer, Integer> pair) {
            this.row = pair.getKey();
            this.column = pair.getValue();
        }

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

    private class Tuple<T> {
        private final Map<T, Integer> frequencies;

        Tuple() {
            frequencies = new HashMap<>();
        }

        void add(T element) {
            frequencies.put(element, frequencies.getOrDefault(element, 0) + 1);
        }

        void remove(T element) {
            if (frequencies.containsKey(element) && frequencies.get(element) == 1) {
                frequencies.remove(element);
            } else if (frequencies.containsKey(element)) {
                frequencies.put(element, frequencies.get(element) - 1);
            }
        }

        boolean contains(T element) {
            return frequencies.containsKey(element) && frequencies.get(element) > 0 ;
        }

        void clear() {
            frequencies.clear();
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
            return frequencies.hashCode();
        }
    }
}
