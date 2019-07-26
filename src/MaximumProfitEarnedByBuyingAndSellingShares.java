// https://www.techiedelight.com/maximum-profit-earned-buying-and-selling-shares/
public class MaximumProfitEarnedByBuyingAndSellingShares {
    public static void main(String[] args) {
        int[] array = StdIn.takeInputAndGetArray();
        System.out.println(maximumProfit(array));
    }

    private static int maximumProfit(int[] array) {
        int sum = 0, min = array[0], max = array[0];
        for (int element : array) {
            if (element < max) {
                sum += max - min;
                max = min = element;
            } else {
                max = element;
            }
        }

        return sum + max - min ;
    }
}
