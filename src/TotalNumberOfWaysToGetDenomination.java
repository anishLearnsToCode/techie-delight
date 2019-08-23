import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

// https://www.techiedelight.com/coin-change-problem-find-total-number-ways-get-denomination-coins/
public class TotalNumberOfWaysToGetDenomination {
    public static void main(String[] args) {
        int[] coins = StdIn.takeInputAndGetIntArray();
        int denomination = StdIn.getIntInput();
        System.out.println(numberOfWaysToGetDenomination(coins, denomination));
    }

    private static int numberOfWaysToGetDenomination(int[] array, int denomination) {
        Set<Integer> coins = from(array);
        return numberOfWaysToGetDenomination(coins, denomination);
    }

    private static int numberOfWaysToGetDenomination(Set<Integer> coins, int denomination) {
        Tuple<Tuple<Integer>> answer = waysToGetDenomination(coins, denomination, new HashMap<>());
        System.out.println(answer);
        return answer.size();
    }

    private static Set<Integer> from(int[] array) {
        Set<Integer> coins = new HashSet<>();
        for (int coin : array) {
            coins.add(coin);
        }
        return coins;
    }

    private static Tuple<Tuple<Integer>> waysToGetDenomination(Set<Integer> coins, int denomination, Map<Integer, Tuple<Tuple<Integer>>> answerData) {
        if (answerData.containsKey(denomination)) {
            return answerData.get(denomination);
        }

        switch (denomination) {
            case 0 :
                answerData.put(0, new Tuple<>(new Tuple<>()));
                return answerData.get(0);
            case 1 :
                if (coins.contains(1)) {
                    Tuple<Integer> tuple = new Tuple<>(1);
                    answerData.put(1, new Tuple<>(tuple));
                } else {
                    answerData.put(1, new Tuple<>());
                }
                return answerData.get(1);
        }

        Tuple<Tuple<Integer>> allPaths = new Tuple<>();
        for (int coin : coins) {
            if (denomination >= coin) {
                Tuple<Tuple<Integer>> paths = waysToGetDenomination(coins, denomination - coin, answerData);
                addToAll(paths, coin);
                allPaths.addAll(paths);
            }
        }
        answerData.put(denomination, allPaths);
        return allPaths;
    }

    private static void addToAll(Tuple<Tuple<Integer>> paths, int coin) {
        for (Tuple<Integer> path : paths) {
            path.add(coin);
        }
    }

    private static class Tuple<T> implements Iterable<T>, Cloneable {
        private final Map<T, Integer> frequencyMap;
        private int hashcode = 0;

        Tuple() {
            this.frequencyMap = new HashMap<>();
        }

        Tuple(T element) {
            this();
            add(element);
        }

        private Tuple(Map<T, Integer> frequencyMap) {
            this.frequencyMap = frequencyMap;
        }

        private Tuple(Map<T, Integer> frequencyMap, int hashcode) {
            this(frequencyMap);
            this.hashcode = hashcode;
        }

        void add(T element) {
            hashcode += element.hashCode();
            frequencyMap.put(element, frequencyMap.getOrDefault(element, 0) + 1);
        }

        void addAll(Tuple<T> tuple) {
            for (T element : tuple) {
                add(element);
            }
        }

        int size() {
            return frequencyMap.size();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj.getClass() == Tuple.class) {
                Tuple<T> other = (Tuple<T>) obj;
                return this.size() == other.size() && equals(other.frequencyMap);
            }

            return false;
        }

        private boolean equals(Map<T, Integer> otherMap) {
            for (Map.Entry<T, Integer> entry : this.frequencyMap.entrySet()) {
                if (!otherMap.get(entry.getKey()).equals(entry.getValue())) {
                    return false;
                }
            }
            return true;
        }

        @Override
        public int hashCode() {
            return hashcode;
        }

        @Override
        public String toString() {
            return frequencyMap.toString();
        }

        @Override
        public Iterator<T> iterator() {
            return frequencyMap.keySet().iterator();
        }
    }
}
