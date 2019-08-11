import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://www.techiedelight.com/find-two-non-overlapping-pairs-sum-array/
public class Find2NonOverlappingPairsHavingSameSumInArray {
    public static void main(String[] args) {
        int[] array = StdIn.takeInputAndGetIntArray();
        System.out.println(nonOverlappingPairsExists(array));
    }

    private static boolean nonOverlappingPairsExists(int[] array) {
        Map<Integer, List<Pair<Integer, Integer>>> sumPairIndices = new HashMap<>();

        for (int i = 0 ; i < array.length - 1 ; i++) {
            for (int j = i + 1 ; j < array.length ; j++) {
                int sum = array[i] + array[j] ;
                if (sumPairIndices.containsKey(sum)) {
                    return true;
                }

                List<Pair<Integer, Integer>> pairs = sumPairIndices.getOrDefault(sum, new ArrayList<>());
                pairs.add(new Pair<>(i, j));
                sumPairIndices.put(sum, pairs);
            }
        }

        return false;
    }
}
