import java.util.*;
import java.util.stream.Collectors;

// https://www.techiedelight.com/find-largest-number-possible-set-given-numbers/
public class LargestNumberPossibleFromSetOfGivenNumbers {
    public static void main(String[] args) {
        int[] array = StdIn.takeInputAndGetArray();
        System.out.println(getLargestNumber(array));
    }

    private static String getLargestNumber(int[] array) {
        return Arrays
                .stream(array)
                .mapToObj(element -> element + "")
                .sorted(new NumberComparator())
                .collect(Collectors.joining());
    }

    private static class NumberComparator implements Comparator<String> {

        @Override
        public int compare(final String first, final String second) {
            if (first.isEmpty() && second.isEmpty()) {
                return 0;
            } else if (first.isEmpty()) {
                return -1;
            } else if (second.isEmpty()) {
                return 1;
            }

            if (first.charAt(0) == second.charAt(0)) {
                return compare(first.substring(1), second.substring(1));
            }

            return first.charAt(0) > second.charAt(0) ? -1 : 1 ;
        }

        @Override
        public boolean equals(Object other) {
            return super.equals(other);
        }
    }
}
