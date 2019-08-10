import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// https://www.techiedelight.com/print-triplets-forms-geometric-progression/
public class PrintAllTripletsThatFormGeometricProgression {
    public static void main(String[] args) {
        int[] array = StdIn.takeInputAndGetIntArray();
        System.out.println(allTripletsThatFormGeometricProgression(array));
    }

    private static List<List<Integer>> allTripletsThatFormGeometricProgression(int[] array) {
        Set<Integer> elements = elementsOf(array);
        List<List<Integer>> geometricProgressionTriplets = new ArrayList<>();

        for (int i = 0 ; i < array.length - 2 ; i++) {
            for (int j = i + 1 ; j < array.length - 1 ; j++) {
                if (isDivisible(array[j], array[i])) {
                    int ratio = array[j] / array[i];
                    int desired = array[j] * ratio;

                    if (elements.contains(desired)) {
                        List<Integer> triplet = new ArrayList<>();
                        triplet.add(array[i]);
                        triplet.add(array[j]);
                        triplet.add(desired);
                        geometricProgressionTriplets.add(triplet);
                    }
                }
            }
        }

        return geometricProgressionTriplets;
    }

    private static boolean isDivisible(int dividend, int divisor) {
        return dividend % divisor == 0;
    }

    private static Set<Integer> elementsOf(int[] array) {
        Set<Integer> elements = new HashSet<>();
        for (int element : array) {
            elements.add(element);
        }
        return elements;
    }
}
