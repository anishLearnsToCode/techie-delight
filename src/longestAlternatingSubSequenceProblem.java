import javafx.util.Pair;

// https://www.techiedelight.com/longest-alternating-subsequence/
public class longestAlternatingSubSequenceProblem {
    public static void main(String[] args) {
        int[] array = StdIn.takeInputAndGetIntArray();
        System.out.println(lengthOfLongestAlternatingSubSequence(array));
    }

    private static int lengthOfLongestAlternatingSubSequence(int[] array) {
        Pair<Sequence, Sequence> descendingAndAscendingSequences = ascendingAndDescendingSequences(array, 0);
        return Math.max(descendingAndAscendingSequences.getKey().length, descendingAndAscendingSequences.getValue().length);
    }

    private static Pair<Sequence, Sequence> ascendingAndDescendingSequences(int[] array, int index) {
        if (index == array.length - 1) {
            return new Pair<>(new Sequence(array[index], 1), new Sequence(array[index], 1));
        }

        Pair<Sequence, Sequence> sequences = ascendingAndDescendingSequences(array, index + 1);
        Sequence ascending = sequences.getKey(), newAscending = new Sequence(ascending);
        Sequence descending = sequences.getValue(), newDescending = new Sequence(descending);
        if (array[index] < descending.value) {
            newAscending.value = array[index];
            newAscending.length = descending.length + 1;
        } else if (array[index] > descending.value) {
            newDescending.value = array[index];
        }

        if (array[index] > ascending.value) {
            if (ascending.length + 1 > newDescending.length) {
                newDescending = new Sequence(array[index], ascending.length + 1);
            }
        } else if (array[index] < ascending.value) {
            if (newAscending.length <= ascending.length) {
                newAscending = new Sequence(array[index], ascending.length);
            }
        }

        return new Pair<>(newAscending, newDescending);
    }

    private static class Sequence {
        int length;
        int value;

        Sequence() { }

        Sequence(int value, int length) {
            this.value = value;
            this.length = length;
        }

        Sequence(Sequence sequence) {
            this(sequence.value, sequence.length);
        }
    }
}
