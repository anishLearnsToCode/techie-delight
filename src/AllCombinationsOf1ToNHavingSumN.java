import java.util.*;

// https://www.techiedelight.com/print-all-combination-numbers-from-1-to-n/
public class AllCombinationsOf1ToNHavingSumN {
    public static void main(String[] args) {
        int sum = StdIn.getIntInput();
        System.out.println(allCombinationsHavingSum(sum));
        printAllPossibleCombinations(sum);
    }

    private static void printAllPossibleCombinations(int sum) {
        printAllPossibleCombinations(sum, new ArrayList<>());
    }

    private static void printAllPossibleCombinations(int sum, List<Integer> combination) {
        if (sum == 0) {
            System.out.println(combination);
            return;
        }

        for (int initialValue = combination.isEmpty() ? 1 : combination.get(combination.size() - 1), index = initialValue
             ; index <= sum ; index++) {

            List<Integer> copyList = new ArrayList<>(combination);
            copyList.add(index);
            printAllPossibleCombinations(sum - index, copyList);
        }
    }

    private static Set<Tuple<Integer>> allCombinationsHavingSum(int sum) {
        Map<Integer, Set<Tuple<Integer>>> sumCombinations = new HashMap<>();
        sumCombinations.put(0, new HashSet<>());
        sumCombinations.put(1, allCombinationsHavingSumOne());
        return allCombinationsHavingSum(sum, sumCombinations);
    }

    private static Set<Tuple<Integer>> allCombinationsHavingSumOne() {
        Set<Tuple<Integer>> allCombinations = new HashSet<>();
        allCombinations.add(new Tuple<>(1));
        return allCombinations;
    }

    private static Set<Tuple<Integer>> allCombinationsHavingSum(int sum, Map<Integer, Set<Tuple<Integer>>> sumCombinations) {
        if (sumCombinations.containsKey(sum)) {
            return sumCombinations.get(sum);
        }

        Set<Tuple<Integer>> allCombinations = new HashSet<>();
        allCombinations.add(new Tuple<>(sum));
        for (int index = sum - 1 ; index >= 1 ; index--) {
            Set<Tuple<Integer>> combined = combine(new Tuple<>(sum - index), allCombinationsHavingSum(index));
            allCombinations.addAll(combined);
        }

        return allCombinations;
    }

    private static Set<Tuple<Integer>> combine(Tuple<Integer> tuple, Set<Tuple<Integer>> tuples) {
        Set<Tuple<Integer>> combinations = new HashSet<>();
        for (Tuple<Integer> otherTuple : tuples) {
            combinations.add(combine(tuple, otherTuple));
        }
        return combinations;
    }

    private static Tuple<Integer> combine(Tuple<Integer> tuple, Tuple<Integer> otherTuple) {
        return new Tuple<>(tuple, otherTuple);
    }

    private static class Tuple<T> {
        Map<T, Integer> elements = new HashMap<>();

        Tuple() { }

        @SafeVarargs
        Tuple(Tuple<T>... tuples) {
            for (Tuple<T> tuple : tuples) {
                add(tuple);
            }
        }

        @SafeVarargs
        Tuple(T... elements) {
            for (T element : elements) {
                this.elements.put(element, this.elements.getOrDefault(element, 0) + 1);
            }
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Tuple) {
               Tuple other = (Tuple) obj;
               if (this.elements.size() != other.elements.size()) {
                   return false;
               }

               for (Map.Entry<T, Integer> entry : this.elements.entrySet()) {
                   T element = entry.getKey();
                   int frequency = entry.getValue();

                   if (!other.elements.containsKey(element)) {
                       return false;
                   }

                   int otherFrequency = (Integer) other.elements.get(element);
                   if (otherFrequency != frequency) {
                       return false;
                   }
               }

               return true;
            }

            return false;
        }

        /**
         * depends on individual elements and frequencies, not on object
         * reference or order of elements inserted in the tuple
         */
        @Override
        public int hashCode() {
           int hashcode = 0 ;
           for (Map.Entry<T, Integer> entry : elements.entrySet()) {
               T element = entry.getKey();
               int frequency = entry.getValue();
               hashcode += element.hashCode() * frequency ;
           }
           return hashcode;
        }

        @Override
        public String toString() {
            StringBuilder tuple = new StringBuilder("( ");
            for (Map.Entry<T, Integer> entry : elements.entrySet()) {
                int frequency = entry.getValue();
                T element = entry.getKey();
                while (frequency-- > 0) {
                    tuple.append(element);
                    tuple.append(" , ");
                }
            }
            return tuple.append(" )").toString();
        }

        void add(Tuple<? extends T> tuple) {
            for (Map.Entry<? extends T, Integer> entry : tuple.elements.entrySet()) {
                T element = entry.getKey();
                int frequency = entry.getValue();
                this.elements.put(element, this.elements.getOrDefault(element, 0) + frequency);
            }
        }
    }
}
