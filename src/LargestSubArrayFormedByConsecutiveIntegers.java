import java.util.HashSet;
import java.util.Set;

// https://www.techiedelight.com/find-largest-sub-array-formed-by-consecutive-integers/
public class LargestSubArrayFormedByConsecutiveIntegers {
    public static void main(String[] args) {
        int elements = StdIn.getIntInput();
        int[] array = new int[elements];
        StdIn.getIntInput(array);

        Range result = getLargestConsecutiveSubArrayRange(array);
        System.out.println(result);
    }

    private static Range getLargestConsecutiveSubArrayRange(int[] array) {
        int[] indexRanges = new int[array.length];
        initializeIncremental(indexRanges);
        int startIndex = 0, endIndex = 0;

        for (int index = 0 ; index < array.length ; ) {
            Set<Integer> elements = new HashSet<>();
            elements.add(array[index]);
            int min = array[index], max = array[index];

            for (int currentIndex = index + 1 ; currentIndex < array.length ; currentIndex++) {
                if (elements.contains(array[currentIndex])) {
                    break;
                }

                min = Math.min(min, array[currentIndex]);
                max = Math.max(max, array[currentIndex]);
                elements.add(array[currentIndex]);
                if (max - min == currentIndex - index) {
                    indexRanges[index] = currentIndex;
                    if (currentIndex - index > endIndex - startIndex) {
                        startIndex = index;
                        endIndex = currentIndex;
                    }
                }
            }

            index = indexRanges[index] + 1;
        }

        return new Range(startIndex, endIndex);
    }

    private static void initializeIncremental(int[] array) {
        for (int index = 0 ; index < array.length ; index++) {
            array[index] = index;
        }
    }

    private static class Range {
        final int startIndex;
        final int endIndex;

        Range(int startIndex, int endIndex) {
            this.startIndex = startIndex;
            this.endIndex = endIndex;
        }

        @Override
        public String toString() {
            return "(" + startIndex + " , " + endIndex + ")";
        }
    }
}
