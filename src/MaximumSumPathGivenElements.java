// https://www.techiedelight.com/find-maximum-sum-path-involving-elements-given-arrays/
public class MaximumSumPathGivenElements {
    public static void main(String[] args) {
        int[] first = StdIn.takeInputAndGetIntArray();
        int[] second = StdIn.takeInputAndGetIntArray();
        System.out.println(maximumCommonSumPath(first, second));
    }

    private static int maximumCommonSumPath(int[] first, int[] second) {
        int sum = 0;
        for (int firstIndex = 0, secondIndex = 0, currentSumFirst = 0, currentSumSecond = 0 ;
             firstIndex <= first.length && secondIndex <= second.length ; ) {

            if (firstIndex == first.length) {
                while (secondIndex < second.length) {
                    currentSumSecond += second[secondIndex++];
                }
                sum += Math.max(currentSumFirst, currentSumSecond);
                break;
            }

            if (secondIndex == second.length) {
                while (firstIndex < first.length) {
                    currentSumFirst += first[firstIndex++];
                }
                sum += Math.max(currentSumFirst, currentSumSecond);
                break;
            }


            if (first[firstIndex] < second[secondIndex]) {
                currentSumFirst += first[firstIndex++];
            } else if (first[firstIndex] > second[secondIndex]){
                currentSumSecond += second[secondIndex++];
            } else {
                sum += Math.max(currentSumFirst, currentSumSecond) + first[firstIndex];
                currentSumFirst = 0;
                currentSumSecond = 0;
                firstIndex++;
                secondIndex++;
            }
        }

        return sum;
    }
}
