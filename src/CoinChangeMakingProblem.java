import java.util.*;

// https://www.techiedelight.com/coin-change-making-problem-unlimited-supply-coins/
public class CoinChangeMakingProblem {
    public static void main(String[] args) {
        int[] array = StdIn.takeInputAndGetIntArray();
        int money = StdIn.getIntInput();
        System.out.println(minimumCoinsRequired(array, money));
    }

    private static int minimumCoinsRequired(int[] array, int money) {
        Set<Integer> coins = from(array);
        return minimumCoinsRequired(coins, money, new HashMap<>());
    }

    private static Set<Integer> from(int[] array) {
        Set<Integer> coins = new HashSet<>();
        for (int coin : array) {
            coins.add(coin);
        }
        return coins;
    }

    private static int minimumCoinsRequired(Set<Integer> coins, int money, Map<Integer, Integer> answerData) {
        if (answerData.containsKey(money)) {
            return answerData.get(money);
        }

        switch (money) {
            case 0 :
                answerData.put(0, 0);
                return 0;
            case 1 :
                answerData.put(1, coins.contains(1) ? 1 : Integer.MAX_VALUE);
                return answerData.get(1);
        }

        if (coins.contains(money)) {
            answerData.put(money, 1);
            return 1;
        }

        int answer = Integer.MAX_VALUE;
        for (int coin : coins) {
            if (money >= coin) {
                answer = Math.min(answer, 1 + minimumCoinsRequired(coins, money - coin, answerData));
            }
        }
        answerData.put(money, answer);

        return answer;
    }
}
