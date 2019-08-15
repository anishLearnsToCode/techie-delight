package Odoo;

public class Question__2_GuessTheNumber {
    public static void main(String[] args) {
        System.out.println(playGame());
    }

    private static int playGame() {
        int start = 0, end = 1000000, guess;
        Game game = new Game();

        while (start <= end) {
            guess = (start + end) / 2;
            int result = game.verify(guess);

            switch (result) {
                case 0 : return guess;
                case 1 : start = guess; break;
                case -1 : end = guess; break;
            }
        }

        return -1;
    }

    private static class Game {
        private int counter = 0;
        private final int MAX_TRIES = 50;
        private final int RANGE_END = 1000000;
        private final int secret = (int) (Math.random() * RANGE_END);

        int verify(int guess) {
            if (counter++ == MAX_TRIES) {
                throw new GameLostException(MAX_TRIES);
            }

            return -Integer.compare(guess, secret);
        }

        private class GameLostException extends RuntimeException {
            GameLostException(int attempt) {
                System.out.println("Game lost after the : " + attempt + "th attempt");
            }
        }
    }
}
