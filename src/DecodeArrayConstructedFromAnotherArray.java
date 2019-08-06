public class DecodeArrayConstructedFromAnotherArray {
    public static void main(String[] args) {
        int[] array = StdIn.takeInputAndGetIntArray();
        Printer.print(decodedArray(array));
    }

    private static int[] decodedArray(int[] array) {
        if (array.length == 1) {
            return new int[] {0, array[0]} ;
        }

        int decodedArrayLength = getDecodedArrayLength(array.length);
        final int[] decodedArray = new int[decodedArrayLength];

        decodedArray[0] = (array[0] + array[1] - array[decodedArrayLength - 1]) / 2 ;
        for (int index = 1 ; index < decodedArrayLength ; index++) {
            decodedArray[index] = array[index - 1] - decodedArray[0];
        }

        return decodedArray;
    }

    private static int getDecodedArrayLength(int length) {
        return (int)(1 + Math.sqrt(1 + 8 * length)) / 2;
    }
}
