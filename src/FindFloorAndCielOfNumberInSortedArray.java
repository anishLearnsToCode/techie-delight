import javafx.util.Pair;

// https://www.techiedelight.com/find-floor-ceil-number-sorted-array/
public class FindFloorAndCielOfNumberInSortedArray {
    public static void main(String[] args) {
        int[] array = StdIn.takeInputAndGetIntArray();
//        int[] array = new int[] {1, 4, 6, 8, 9};
        for (int index = 0 ; index <= 10 ; index++) {
            System.out.println(floorAndCiel(array, index));
        }
    }

    private static Pair<Integer, Integer> floorAndCiel(int[] array, int number) {
        return new Pair<>(floor(array, number), ciel(array, number));
    }

    private static Integer floor(int[] array, int number) {
        for (int start = 0, end = array.length - 1, middle = end / 2 ; start <= end ; middle = (start + end) / 2) {
            if (array[middle] == number) {
                if (middle - 1 >= 0 && array[middle - 1] == number) {
                    end = middle - 1;
                    continue;
                }

                return middle;
            }

            if (array[middle] < number) {
                if (middle == array.length - 1 || array[middle + 1] > number) {
                    return middle;
                }

                start = middle + 1;
            } else {
                if (middle == 0) {
                    return null;
                }
                if (array[middle - 1] < number) {
                    return middle - 1;
                }

                end = middle - 1;
            }
        }

        return null;
    }

    private static Integer ciel(int[] array, int number) {
        for (int start = 0, end = array.length - 1, middle = end / 2 ; start <= end ; middle = (start + end) / 2) {
            if (array[middle] == number) {
                if (middle == array.length - 1) {
                    return middle;
                } else if (array[middle + 1] == number) {
                    start = middle + 1;
                    continue;
                }

                return middle;
            }

            if (array[middle] < number) {
                if (middle == array.length - 1) {
                    return null;
                }
                if (array[middle + 1] > number) {
                    return middle + 1;
                }

                start = middle + 1;
            } else {
                if (middle == 0) {
                    return 0;
                }
                if (array[middle - 1] < number) {
                    return middle;
                }

                end = middle - 1;
            }
        }

        return null;
    }
}
