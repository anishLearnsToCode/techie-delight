import javafx.util.Pair;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// https://www.techiedelight.com/find-symmetric-pairs-array-pairs/
public class FindAllSymmetricPairsInArrayOfPairs {
    public static void main(String[] args) {
        int[] array = StdIn.takeInputAndGetIntArray();
        System.out.println(allSymmetricPairs(array));
    }

    private static Set<Pair<Integer, Integer>> allSymmetricPairs(int[] array) {
        Set<Pair<Integer, Integer>> symmetricPairs = new HashSet<>();
        Map<Integer, Set<Integer>> pairs = new HashMap<>();

        for (int index = 0 ; index < array.length ; index += 2) {
            // check if symmetric pair is present - if yes add pair to symmetricPairs set
            if (pairs.containsKey(array[index + 1])) {
                if (pairs.get(array[index + 1]).contains(array[index])) {
                    symmetricPairs.add(new Pair<>(array[index], array[index + 1]));
                }
            }

            // add the current pair to pair map
            pairs.put(array[index], pairs.getOrDefault(array[index], new HashSet<>()));
            pairs.get(array[index]).add(array[index + 1]);
        }

//        System.out.println(pairs);
        return symmetricPairs;
    }
}
