// https://www.techiedelight.com/sort-array-containing-0s-1s-2s-dutch-national-flag-problem/

public class DutchFlagProblem {
    public static void main(String[] args) {
        int length = StdIn.getIntInput();
        int[] array = new int[length];
        StdIn.getIntInput(array);
        int[] result = getDutchSortedArray(array);
        Printer.print(result);
    }

    private static int[] getDutchSortedArray(int[] array) {
        int numberOf0 = 0, numberOf1 = 0, numberOf2 = 0;
        for (int element : array) {
            switch (element) {
                case 0: numberOf0++;
                case 1: numberOf1++;
                case 2: numberOf2++;
            }
        }

        int[] result = new int[array.length];
        for (int index = numberOf0 ; index < numberOf0 + numberOf1 ; index++) {
            result[index] = 1;
        }

        for (int index = numberOf1 ; index < result.length ; index++) {
            result[index] = 2;
        }

        return result;
    }
}
