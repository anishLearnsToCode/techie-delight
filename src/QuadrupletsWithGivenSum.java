import java.util.*;

// https://www.techiedelight.com/4-sum-problem/
public class QuadrupletsWithGivenSum {
    public static void main(String[] args) {
        int[] array = StdIn.takeInputAndGetIntArray();
        int sum = StdIn.getIntInput();
        System.out.println(quadrupletWithSumExists(array, sum));
    }

    private static boolean quadrupletWithSumExists(int[] array, int sum) {
        System.out.println(quadrupletWithSumExistsInArray(array, sum));
        return sumExistsInArraySubSequence(array, sum, 0, 4);
    }

    // Recursive solution: O(2^n)
    private static boolean sumExistsInArraySubSequence(int[] array, int sum, int index, int subArrayLength) {
        if (subArrayLength == 0) {
            return sum == 0;
        }

        if (index == array.length) {
            return false;
        }

        return sumExistsInArraySubSequence(array, sum, index + 1, subArrayLength) ||
                sumExistsInArraySubSequence(array, sum - array[index], index + 1, subArrayLength - 1);
    }

    // Hashing solution: O(n^3)
    private static boolean quadrupletWithSumExistsInArray(int[] array, int sum) {
        Set<Quadruplet> quadruplets = allQuadrupletsWithSum(array, sum);
        System.out.println(quadruplets);
        return !quadruplets.isEmpty();
    }

    private static Set<Quadruplet> allQuadrupletsWithSum(int[] array, int sum) {
        Map<Integer, List<Pair>> sumPairs = new HashMap<>();
        Set<Quadruplet> quadruplets = new HashSet<>();

        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                int currentPairSum = array[i] + array[j];
                Pair currentPair = new Pair(i, j);
                int requiredSum = sum - currentPairSum;

                if (sumPairs.containsKey(requiredSum)) {
                    for (Pair pair : sumPairs.get(requiredSum)) {
                        if (currentPair.doesNotOverlap(pair)) {
                            quadruplets.add(new Quadruplet(currentPair, pair));
                        }
                    }
                }

                sumPairs.putIfAbsent(currentPairSum, new ArrayList<>());
                sumPairs.get(currentPairSum).add(currentPair);
            }
        }

        return quadruplets;
    }

    private static class Quadruplet {
        private Set<Integer> tuple = new HashSet<>();

        Quadruplet(Pair first, Pair second) {
            tuple.add(first.first);
            tuple.add(first.second);
            tuple.add(second.first);
            tuple.add(second.second);
        }

        @Override
        public boolean equals(Object other) {
            if (other instanceof Quadruplet) {
                Quadruplet otherQuadruplet = (Quadruplet) other;
                return this.tuple.size() == otherQuadruplet.tuple.size()
                       && hasSameElements(tuple,  otherQuadruplet.tuple);
            }

            return false;
        }

        private boolean hasSameElements(Set<Integer> first, Set<Integer> second) {
            for (int element : first) {
                if (!second.contains(element)) {
                    return false;
                }
            }

            return true;
        }

        @Override
        public int hashCode() {
            int hashcode = 0, factor = 1;
            for (Integer element : tuple) {
                hashcode += element.hashCode();
            }
            return hashcode;
        }

        @Override
        public String toString() {
            StringBuilder result = new StringBuilder("( ");
            for (int element : tuple) {
                result.append(element).append(" , ");
            }
            return result + " )";
        }
    }

    // solving using dp: first creating a can solve for sum table and then computing all paths
    // and checking for path lengths with 4 O((sum * n)*(n))
    private static boolean hasQuadrupletWithSumUsingDp(int[] array, int sum) {
        boolean[][] sumData = getSumDataFor(array, sum);
        Set<Tuple<Integer>> tuples = allPossibleTuplesForSum(sumData, sum);
        System.out.println(tuples);
        return containsTupleWithLength(tuples, 4);
    }

    private static boolean[][] getSumDataFor(int[] array, int sum) {
        int rowCount = sum + 1, columnCount = array.length;
        boolean[][] result = new boolean[rowCount][columnCount];

        // First Column
        for (int row = 1, column = 0 ; row < rowCount ; row++) {
            result[row][column] = row == array[0];
        }

        // First Row
        for (int row = 0, column = 0 ; column < columnCount ; column++) {
            result[row][column] = true;
        }

        // Rest of the table
        for (int row = 1 ; row < rowCount ; row++) {
            for (int column = 1 ; column < columnCount ; column++) {
                result[row][column] = result[row][column - 1]
                        || row == array[column]
                        || (row >= array[column] && result[row - array[column]][column - 1]) ;
            }
        }

        return result;
    }

    private static Set<Tuple<Integer>> allPossibleTuplesForSum(boolean[][] sumData, int[] array, int sum) {
        return allPossibleTuplesForSum(sumData, sum, array, sumData[0].length - 1);
    }

    private static Set<Tuple<Integer>> allPossibleTuplesForSum(boolean[][] sumData, int sum, int[] array, int endIndex) {
        Set<Tuple<Integer>> possiblePathsToSum = new HashSet<>();
        for (int index = endIndex ; index >= 0 ; index--) {
            if (sumData[sum + 1][index]) {
                Tuple<Integer> tuple = new Tuple<>(array[index]);
                Set<Tuple<Integer>> allPaths = allPossibleTuplesForSum(sumData, sum - array[index], array, index - 1);
                for (Tuple<Integer> path : allPaths) {
                    tuple.add(path);
                }
                possiblePathsToSum.add(tuple);
            }
        }
        return possiblePathsToSum;
    }

    private static boolean containsTupleWithLength(Set<Tuple<Integer>> tuples, int length) {
        for (Tuple tuple : tuples) {
            if (tuple.size() == length) {
                return true;
            }
        }
        return false;
    }

    private static class Tuple<T> {
        private Set<T> set = new HashSet<>();

        Tuple(Set<T> set) {
            this.set = set;
        }

        @SafeVarargs
        Tuple(T... values) {
            set.addAll(Arrays.asList(values));
        }

        @Override
        public boolean equals(Object other) {
            if (other instanceof Tuple) {
                Tuple otherTuple = (Tuple) other ;
                return otherTuple.size() == this.size() &&
                       hasSameElements(this.set, otherTuple.set);
            }

            return false;
        }

        private boolean hasSameElements(Set<T> first, Set second) {
            for (T element : first) {
                if (!second.contains(element)) {
                    return false;
                }
            }

            return true;
        }

        @Override
        public int hashCode() {
            int hashCode = 0;
            for (T element : set) {
                hashCode += element.hashCode();
            }
            return hashCode;
        }

        @Override
        public String toString() {
            StringBuilder result = new StringBuilder("( ");
            for (T element : set) {
                result.append(element).append(" , ");
            }
            return result + " )";
        }

        int size() {
            return this.set.size();
        }

        void add(Tuple<T> tuple) {
            this.set.addAll(tuple.set);
        }
    }
}
