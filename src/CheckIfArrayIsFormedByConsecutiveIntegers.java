import java.util.Arrays;

// https://www.techiedelight.com/check-array-formed-consecutive-integers/
public class CheckIfArrayIsFormedByConsecutiveIntegers {
    public static void main(String[] args) {
        int[] array = StdIn.takeInputAndGetIntArray();
        System.out.println(formedByConsecutiveIntegers(array));
    }

    private static boolean formedByConsecutiveIntegers(int[] array) {
        int sum = Arrays.stream(array).sum();
        int min = Arrays.stream(array).min().getAsInt();
        int max = Arrays.stream(array).max().getAsInt();
        int idealElements = max - min + 1;
        int idealSum = summation(max) - summation(min) + min ;

        return idealElements == array.length && sum == idealSum ;
    }

    private static int summation(int number) {
        return (number * (number + 1)) / 2 ;
    }
}
