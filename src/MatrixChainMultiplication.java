import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

// https://www.techiedelight.com/matrix-chain-multiplication/
public class MatrixChainMultiplication {
    public static void main(String[] args) {
        int[] array = StdIn.takeInputAndGetIntArray();
        Path result = minimumNumberOfOperations(array);
        System.out.println(result);
        System.out.println(result.operations);
    }

    private static Path minimumNumberOfOperations(int[] array) {
        List<Matrix> matrices = matricesFrom(array);
        System.out.println(matrices);
        return minimumNumberOfOperations(new Vector(matrices));
    }

    private static List<Matrix> matricesFrom(int[] array) {
        List<Matrix> matrices = new ArrayList<>();
        for (int index = 0 ; index < array.length ; index += 2) {
            matrices.add(Matrix.from(array[index], array[index + 1]));
        }
        return matrices;
    }

    private static Path minimumNumberOfOperations(Vector matrices) {
        switch (matrices.size()) {
            case 1 : return new Path(matrices.get(0));
            case 2 : return new Path(matrices.get(0), matrices.get(1));
        }

        Path answer = new Path();
        for (int i = 1 ; i < matrices.size() ; i++) {
            Path left = minimumNumberOfOperations(matrices.subMatrices(0, i));
            Path right = minimumNumberOfOperations(matrices.subMatrices(i));
            long operations = operations(left.matrix, right.matrix);
            if (operations < answer.operations) {
                answer = new Path(left, right);
            }
        }

        return answer;
    }

    private static long operations(Matrix first, Matrix second) {
        return first.rows * first.columns * second.columns ;
    }

    private static class Vector {
        final List<Matrix> matrices;

        Vector(List<Matrix> matrices) {
            this.matrices = matrices;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj.getClass() == Vector.class) {
                Vector other = (Vector) obj;
                if (other.size() != this.size()) {
                    return false;
                }

                for (int index = 0 ; index < size() ; index++) {
                    if (!this.matrices.get(index).equals(other.matrices.get(index))) {
                        return false;
                    }
                }
                return true;
            }

            return false;
        }

        @Override
        public int hashCode() {
            int hashcode = 0;
            for (Matrix matrix : matrices) {
                hashcode += matrix.hashCode();
            }
            return hashcode;
        }

        int size() {
            return this.matrices.size();
        }

        Matrix get(int index) {
            return matrices.get(index);
        }

        Vector subMatrices(int startIndex) {
            return subMatrices(startIndex, size());
        }

        Vector subMatrices(int startIndex, int endIndex) {
            return new Vector(matrices.subList(startIndex, endIndex));
        }
    }

    private static class Matrix {
        private static int COUNTER = 0;
        final int id;
        final int rows;
        final int columns;

        Matrix(int id, int rows, int columns) {
            this.id = id;
            this.rows = rows;
            this.columns = columns;
        }

        static Matrix from(int rows, int columns) {
            return new Matrix(COUNTER++, rows, columns);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj.getClass() == Matrix.class) {
                Matrix other = (Matrix) obj;
                return this.rows == other.rows && this.columns == other.columns;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(rows, columns);
        }

        @Override
        public String toString() {
            return "[" + rows + "*" + columns + "]";
        }
    }

    private static class Path {
        private final Node root;
        private final Path left;
        private final Path right;
        final Matrix matrix;
        private final long operations;

        Path() {
            this.root = null;
            this.left = null;
            this.right = null;
            this.matrix = null;
            this.operations = Long.MAX_VALUE;
        }

        private Path(Path left, Path right) {
            this.root = new PathNode();
            this.left = left;
            this.right = right;
            this.matrix = Matrix.from(left.matrix.rows, right.matrix.columns);
            this.operations = this.left.operations + this.right.operations + operations(this.left.matrix, this.right.matrix);
        }

        private Path(Matrix expression) {
            this.root = new ValueNode(expression.id);
            this.left = null;
            this.right = null;
            this.matrix = Matrix.from(expression.rows, expression.columns);
            this.operations = 0;
        }

        private Path(Matrix left, Matrix right) {
            this.root = new PathNode();
            this.left = new Path(left);
            this.right = new Path(right);
            this.matrix = Matrix.from(left.rows, right.columns);
            this.operations = left.rows * left.columns * right.columns ;
        }

        @Override
        public String toString() {
            return (Objects.nonNull(this.left) ? "(" + left.toString() + ")" : "")
                   + (this.root.getClass() == ValueNode.class ? root.toString() : "")
                   + (Objects.nonNull(this.right) ? "(" + right.toString() + ")" : "") ;
        }

        private static abstract class Node { }
        private static class PathNode extends Node { }
        private static class ValueNode extends Node {
            final int id;

            ValueNode(int id) {
                this.id = id;
            }

            @Override
            public String toString() {
                return id + "";
            }
        }
    }
}
