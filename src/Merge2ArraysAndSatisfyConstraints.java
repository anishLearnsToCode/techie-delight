import java.util.Arrays;

// https://www.techiedelight.com/merge-two-arrays-satisfying-given-constraints/
public class Merge2ArraysAndSatisfyConstraints {
    public static void main(String[] args) {
        int length1 = StdIn.getIntInput();
        int[] X = new int[length1];
        StdIn.getIntInput(X);

        int length2 = StdIn.getIntInput();
        int[] array2 = new int[length2];
        StdIn.getIntInput(array2);

        int[] result = mergeWithConstraint(X, array2);
        Printer.print(result);
    }

    private static int[] mergeWithConstraint(int[] first, int[] second) {
        first = Arrays.stream(first).filter(element -> element != 0).toArray();
        return merge(first, second);
    }

    private static int[] merge(int[] first, int[] second) {
        int[] result = new int[first.length + second.length];

        for (int firstIndex = 0, secondIndex = 0, index = 0 ; firstIndex <= first.length && secondIndex <= second.length ; index++) {
            if (firstIndex == first.length) {
                while (secondIndex < second.length) {
                    result[index] = second[secondIndex++];
                }
                break;
            }

            if (secondIndex == second.length) {
                while (firstIndex < first.length) {
                    result[index] = first[firstIndex++];
                }
                break;
            }

            if (first[firstIndex] <= second[secondIndex]) {
                result[index] = first[firstIndex++];
            } else {
                result[index] = second[secondIndex++];
            }
        }

        return result;
    }
}
