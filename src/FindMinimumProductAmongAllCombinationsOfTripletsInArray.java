import javafx.util.Pair;

import java.util.ArrayList;

// https://www.techiedelight.com/find-minimum-product-triplets-array/
public class FindMinimumProductAmongAllCombinationsOfTripletsInArray {
    public static void main(String[] args) {
        int[] array = StdIn.takeInputAndGetIntArray();
        System.out.println(minimumTripletProduct(array));
    }

    private static Pair<Long, Long> minimumTripletProduct(int[] array) {
        Triplet maximumTriplet = new Triplet(array[0], array[1], array[2]);
        Triplet minimumTriplet = new Triplet(array[0], array[1], array[2]);

        for (int index = 3 ; index < array.length ; index++) {
            if (isPositive(array[index])) {
                maximumTriplet = maximumTriplet.addToIncrease(array[index]);
                minimumTriplet = minimumTriplet.addToDecrease(array[index]);
            } else {
                maximumTriplet = max(maximumTriplet, minimumTriplet.addToIncrease(array[index]));
                minimumTriplet = min(minimumTriplet, maximumTriplet.addToDecrease(array[index]));
            }
        }

        return new Pair<>(maximumTriplet.product, minimumTriplet.product);
    }

    private static Triplet max(Triplet a, Triplet b) {
        return a.product > b.product ? a : b ;
    }

    private static Triplet min(Triplet a, Triplet b) {
        return a.product < b.product ? a : b ;
    }

    private static boolean isPositive(long number) {
        return number >= 0 ;
    }

    private static class Triplet {
        final long product;
        private ArrayList<Integer> positiveIntegers = new ArrayList<>();
        private ArrayList<Integer> negativeIntegers = new ArrayList<>();
        private final int minimumAbsPositiveInteger;
        private final int maximumAbsPositiveInteger;
        private final int minimumAbsNegativeInteger;
        private final int maximumAbsNegativeInteger;
        private final boolean isPositive;

        Triplet(int first, int second, int third) {
            addNumbersToRespectiveLists(first, second, third);
            minimumAbsPositiveInteger = positiveIntegers.stream().min(Integer::compareTo).orElse(Integer.MAX_VALUE);
            minimumAbsNegativeInteger = Math.abs(negativeIntegers.stream().max(Integer::compareTo).orElse(Integer.MAX_VALUE));
            maximumAbsNegativeInteger = Math.abs(negativeIntegers.stream().min(Integer::compareTo).orElse(Integer.MIN_VALUE));
            maximumAbsPositiveInteger = positiveIntegers.stream().max(Integer::compareTo).orElse(Integer.MIN_VALUE);
            product = first * second * third ;
            isPositive = isPositive(product);
        }

        private Triplet(ArrayList<Integer> positiveIntegers, ArrayList<Integer> negativeIntegers) {
            this.positiveIntegers = positiveIntegers;
            this.negativeIntegers = negativeIntegers;
            product = product(positiveIntegers) * product(negativeIntegers);
            minimumAbsPositiveInteger = positiveIntegers.stream().min(Integer::compareTo).orElse(Integer.MAX_VALUE);
            minimumAbsNegativeInteger = Math.abs(negativeIntegers.stream().max(Integer::compareTo).orElse(Integer.MAX_VALUE));
            maximumAbsNegativeInteger = Math.abs(negativeIntegers.stream().min(Integer::compareTo).orElse(Integer.MIN_VALUE));
            maximumAbsPositiveInteger = positiveIntegers.stream().max(Integer::compareTo).orElse(Integer.MIN_VALUE);
            isPositive = isPositive(product);
        }

        private long product(ArrayList<Integer> list) {
            long product = 1;
            for (int element : list) {
                product *= element;
            }
            return product;
        }

        private void addNumbersToRespectiveLists(int first, int second, int third) {
            addToRespectiveList(first);
            addToRespectiveList(second);
            addToRespectiveList(third);
        }

        private void addToRespectiveList(int number) {
            boolean ignored = isPositive(number) ? positiveIntegers.add(number) : negativeIntegers.add(number);
        }

        Triplet addToIncrease(int number) {
            if (isPositive) {
                return isPositive(number) ?
                        replaceWithMinimumInPositive(number) :
                        replaceWithMinimumInNegative(number) ;
            } else {
                return isPositive(number) ?
                        replaceWithMinimumInNegative(number) :
                        replaceWithMinimumInPositive(number);
            }
        }

        Triplet addToDecrease(int number) {
            if (isPositive) {
                return isPositive(number) ?
                        replaceWithMaximum(number) :
                        replaceWithMinimumInPositive(number);
            } else {
                return isPositive(number) ?
                        replaceWithMinimumInPositive(number) :
                        replaceWithMinimumInNegative(number);
            }
        }

        private Triplet replaceWithMinimumInNegative(int number) {
            ArrayList<Integer> newNegativeIntegers = (ArrayList) negativeIntegers.clone();
            if (number > minimumAbsNegativeInteger) {
                replaceIn(newNegativeIntegers, minimumAbsNegativeInteger, number);
            }
            return new Triplet((ArrayList) positiveIntegers.clone(), newNegativeIntegers);
        }

        private Triplet replaceWithMaximumInNegative(int number) {
            ArrayList<Integer> newNegativeIntegers = (ArrayList) negativeIntegers.clone();
            if (number < maximumAbsNegativeInteger) {
                replaceIn(newNegativeIntegers, maximumAbsNegativeInteger, number);
            }
            return new Triplet((ArrayList) positiveIntegers.clone(), newNegativeIntegers);
        }

        private Triplet replaceWithMinimumInPositive(int number) {
            ArrayList<Integer> newPositiveIntegers = (ArrayList) positiveIntegers.clone();
            if (number > minimumAbsPositiveInteger) {
                replaceIn(newPositiveIntegers, minimumAbsPositiveInteger, number);
            }
            return new Triplet(newPositiveIntegers, (ArrayList) negativeIntegers.clone());
        }

        private Triplet replaceWithMaximumInPositive(int number) {
            ArrayList<Integer> newPositiveIntegers = (ArrayList) positiveIntegers.clone();
            if (number < maximumAbsPositiveInteger) {
                replaceIn(newPositiveIntegers, maximumAbsPositiveInteger, number);
            }
            return new Triplet(newPositiveIntegers, (ArrayList) negativeIntegers.clone());
        }

        private Triplet replaceWithMinimum(int number) {
            return minimumAbsNegativeInteger < minimumAbsPositiveInteger ?
                replaceWithMinimumInNegative(number) :
                replaceWithMinimumInPositive(number) ;
        }

        private Triplet replaceWithMaximum(int number) {
            return minimumAbsNegativeInteger < minimumAbsPositiveInteger ?
                    replaceWithMaximumInNegative(number) :
                    replaceWithMaximumInPositive(number) ;
        }

        private void replaceIn(ArrayList<Integer> list, int replace, int with) {
            int index = list.indexOf(replace);
            list.remove(index);
            list.add(with);
        }
    }
}
