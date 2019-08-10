import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// https://www.techiedelight.com/print-triplets-forms-arithmetic-progression/
public class PrintAllTripletsThatFormArithmeticProgression {
    public static void main(String[] args) {
        int[] array = StdIn.takeInputAndGetIntArray();
        System.out.println(allApTriplets(array));
    }

    private static List<List<Integer>> allApTriplets(int[] array) {
        Set<Integer> elements = elementsOf(array);
        List<List<Integer>> arithmeticProgressionTriplets = new ArrayList<>();

        for (int index = 0 ; index < array.length - 2 ; index++) {
            for (int j = index + 1 ; j < array.length - 1 ; j++) {
                int difference = array[j] - array[index];
                if (elements.contains(array[j] + difference)) {
                    List<Integer> triplet = new ArrayList<>();
                    triplet.add(array[index]);
                    triplet.add(array[index] + difference);
                    triplet.add(array[index] + 2 * difference);
                    arithmeticProgressionTriplets.add(triplet);
                }
            }
        }

        return arithmeticProgressionTriplets;
    }

    private static Set<Integer> elementsOf(int[] array) {
        Set<Integer> elements = new HashSet<>();
        for (int element : array) {
            elements.add(element);
        }
        return elements;
    }
}
