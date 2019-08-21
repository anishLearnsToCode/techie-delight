import java.util.HashMap;
import java.util.Map;

// https://www.techiedelight.com/rot-cutting/
public class RodCuttingProblem {
    public static void main(String[] args) {
        int[] lengths = StdIn.takeInputAndGetIntArray();
        int[] prices = StdIn.takeInputAndGetIntArray();
        int length = StdIn.getIntInput();
        System.out.println(maximumProfitInRodCutting(lengths, prices, length));
    }

    private static int maximumProfitInRodCutting(int[] lengths, int[] prices, int length) {
        Map<Integer, Integer> pricesForLengths = pricesForLengths(prices, lengths);
        return maximumProfitInRodCutting(pricesForLengths, length, new HashMap<>());
    }

    private static Map<Integer, Integer> pricesForLengths(int[] prices, int[] lengths) {
        Map<Integer, Integer> result = new HashMap<>();
        for (int index = 0 ; index < prices.length ; index++) {
            result.put(lengths[index], prices[index]);
        }
        return result;
    }

    private static int maximumProfitInRodCutting(Map<Integer, Integer> pricesForLengths, int length, Map<Integer, Integer> answerData) {
        if (answerData.containsKey(length)) {
            return answerData.get(length);
        }

        if (length == 1) {
            answerData.put(1, pricesForLengths.getOrDefault(1, Integer.MIN_VALUE));
            return answerData.get(1);
        }

        int answer = Integer.MIN_VALUE;
        for (int partitionLength = 1 ; partitionLength <= length / 2 ; partitionLength++) {
            answer = Math.max(answer,
                    maximumProfitInRodCutting(pricesForLengths, partitionLength, answerData) +
                        maximumProfitInRodCutting(pricesForLengths, length - partitionLength, answerData)
            );
        }
        answer = Math.max(answer, pricesForLengths.getOrDefault(length, Integer.MIN_VALUE));
        answerData.put(length, answer);

        return answer;
    }
}
