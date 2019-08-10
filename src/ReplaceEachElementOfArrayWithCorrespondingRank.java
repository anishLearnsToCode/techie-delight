import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

// https://www.techiedelight.com/replace-each-element-corresponding-rank-array/
public class ReplaceEachElementOfArrayWithCorrespondingRank {
    public static void main(String[] args) {
        int[] array = StdIn.takeInputAndGetIntArray();
        Printer.print(replacedArray(array));
    }

    private static int[] replacedArray(int[] array) {
        Map<Integer, Integer> elementIndices = getElementIndices(array);
        Arrays.sort(array);
        return indicesFrom(array, elementIndices);
    }

    private static Map<Integer, Integer> getElementIndices(int[] array) {
        Map<Integer, Integer> indices = new HashMap<>();
        for (int index = 0 ; index < array.length ; index++) {
            indices.put(array[index], index);
        }
        return indices;
    }

    private static int[] indicesFrom(int[] array, Map<Integer, Integer> elementIndices) {
        int[] indices = new int[array.length];
        for (int index = 0 ; index < array.length ; index++) {
            indices[elementIndices.get(array[index])] = index + 1 ;
        }
        return indices;
    }
}
