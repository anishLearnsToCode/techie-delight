import java.util.ArrayList;
import java.util.List;

// https://www.techiedelight.com/find-triplet-maximum-product-array/
public class FindTripletHavingMaximumProductInArray {
    public static void main(String[] args) {
        int[] array = StdIn.takeInputAndGetIntArray();
        System.out.println(maximumTripletProduct(array));
    }

    private static long maximumTripletProduct(int[] array) { ;
        long maximumProduct = array[0] * array[1] * array[2];
        Triplet maximumTriplet = new Triplet(array[0], array[1], array[2]);
        Triplet minimumTriplet = new Triplet(array[0], array[1], array[2]);

        for (int index = 3 ; index < array.length ; index++) {
            if (array[index] == 0) {
                minimumTriplet = minimumTriplet.addToDecrease(0);
                continue;
            }

            if (isPositive(array[index])) {
                maximumTriplet = maximumTriplet.addToIncrease(array[index]);
                minimumTriplet = minimumTriplet.addToDecrease(array[index]);
            } else {
                Triplet possibleMaxFromMinimum = minimumTriplet.addToIncrease(array[index]);
                Triplet possibleMinFromMaximum = maximumTriplet.addToDecrease(array[index]);

                if (possibleMaxFromMinimum.product > maximumTriplet.product) {
                    maximumTriplet = possibleMaxFromMinimum;
                }
                if (possibleMinFromMaximum.product < minimumTriplet.product) {
                    minimumTriplet = possibleMinFromMaximum;
                }
            }
        }

        return maximumTriplet.product;
    }

    private static boolean isPositive(int number) {
        return number >= 0 ;
    }

    private static class Triplet {
        private final List<Integer> positiveNumbers = new ArrayList<>();
        private final List<Integer> negativeNumbers = new ArrayList<>();
        final long product ;
        private final int minimumPositiveNumber;
        private final int minimumNegativeNumber;
        private final boolean isPositive;

        Triplet(int first, int second, int third) {
            addElementsToCorresponding(first, second, third);
            minimumPositiveNumber = minimumOf(positiveNumbers);
            minimumNegativeNumber = minimumOf(negativeNumbers);
            product = first * second * third ;
            isPositive = product >= 0 ;
        }

        private int minimumOf(List<Integer> list) {
            int min = Integer.MAX_VALUE;
            for (int element : list) {
                min = Math.min(min, Math.abs(element));
            }
            return min;
        }

        private void addElementsToCorresponding(int first, int second, int third) {
            addToCorresponding(first);
            addToCorresponding(second);
            addToCorresponding(third);
        }

        private void addToCorresponding(int number) {
            if (isPositive(number)) {
                positiveNumbers.add(number);
            } else {
                negativeNumbers.add(number);
            }
        }

        /***
         * Adds a number while removing the minimum absolute number present in the Triplet
         * @param number The number to add in the triplet
         * @return the smallest number that was removed to add this number. The returned value is a magnitude and
         * is hence all positive
         */
        Triplet addToIncrease(int number) {
            if (this.isPositive) {
                if (number > minimumPositiveNumber) {
                    positiveNumbers.remove(minimumPositiveNumber);
                    positiveNumbers.add(number);
                }
            }
            return new Triplet(0, 0, 0);
        }

        Triplet addToDecrease(int number) {
            return new Triplet(0, 0, 0);
        }
    }
}
