// https://www.techiedelight.com/trapping-rain-water-within-given-set-bars/
public class TrappingRainWaterWithinGivenSetOfBars {
    public static void main(String[] args) {
        int[] bars = StdIn.takeInputAndGetIntArray();
        System.out.println(maxTrappedWater(bars));
    }

    private static int maxTrappedWater(int[] array) {
        int water = 0;
        for (int index = array.length - 2, max = array[array.length - 1], proxy = 0 ; index >= 0 ; index--) {
            if (max > array[index]) {
                proxy += max - array[index];
            } else {
                water += proxy;
                proxy = 0;
                max = array[index];
            }
        }

        return water;
    }
}
