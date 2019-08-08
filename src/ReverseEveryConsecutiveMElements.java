// https://www.techiedelight.com/reverse-every-consecutive-m-elements-given-subarray/
public class ReverseEveryConsecutiveMElements {
    public static void main(String[] args) {
        int[] array = StdIn.takeInputAndGetIntArray();
        int m = StdIn.getIntInput();
        int startIndex = StdIn.getIntInput();
        int endIndex = StdIn.getIntInput();
        reverseConsecutiveMElements(array, m, startIndex, endIndex);
        Printer.print(array);
    }

    private static void reverseConsecutiveMElements(int[] array, int m, int startIndex, int endIndex) {
        for (int index = startIndex ; index + m - 1 <= endIndex ; index += m) {
            reverse(array, index, index + m - 1);
        }
    }

    private static void reverse(int[] array, int startIndex, int endIndex) {
        int[] reverse = new int[endIndex - startIndex + 1];
        for (int index = endIndex ; index >= startIndex ; index--) {
            reverse[endIndex - index] = array[index];
        }
        System.arraycopy(reverse, 0, array, startIndex, endIndex + 1 - startIndex);
    }
}
