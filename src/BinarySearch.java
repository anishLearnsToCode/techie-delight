import java.util.ArrayList;
import java.util.List;

public class BinarySearch {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(0);
        list.add(0);
        list.add(0);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(19);
        list.add(19);
        list.add(19);
        list.add(23); // 11
        list.add(23);
        list.add(23);
        list.add(23);
        list.add(23);
        list.add(23);
        list.add(45);
        System.out.println(anyExactMatch(list, 23));
        System.out.println(exactFirstIndex(list, 23));
        System.out.println(getFirstNextLargestElementIndex(list, 0));
        System.out.println(exactLastIndex(list, 19));
    }

    private static int getFirstNextLargestElementIndex(List<Integer> list, int element) {
        int middle = list.size() / 2 ;
        for (int startIndex = 0, endIndex = list.size() ; ; middle = (startIndex + endIndex) / 2) {
            if (list.get(middle) <= element) {
                if (list.get(middle + 1) > element) {
                    return middle + 1;
                }
                startIndex = middle;
            } else {
                if (middle == 0) {
                    return middle;
                }
                if (list.get(middle - 1) < element) {
                    return middle;
                }
                endIndex = middle;
            }
        }
    }

    /***
     * Considers that the element is actually present
     * @param list
     * @param element
     * @return
     */
    private static int anyExactMatch(List<Integer> list, int element) {
        for (int start = 0, end = list.size(), middle = end / 2 ; ; middle = (start + end) / 2) {
            if (list.get(middle) == element) {
                return middle;
            } else if (list.get(middle) < element) {
                start = middle;
            } else {
                end = middle;
            }
        }
    }

    private static int exactFirstIndex(List<Integer> list, int element) {
        for (int start = 0, end = list.size(), middle = end / 2 ; ; middle = (start + end) / 2) {
            if (list.get(middle) == element) {
                if (middle != 0 && list.get(middle - 1) == element) {
                    end = middle;
                    continue;
                }
                return middle;
            } else if (list.get(middle) < element) {
                start = middle;
            } else {
                end = middle;
            }
        }
    }

    private static int exactLastIndex(List<Integer> list, int element) {
        for (int start = 0, end = list.size(), middle = end / 2 ; ; middle = (start + end) / 2) {
            if (list.get(middle) == element) {
                if (middle != list.size() - 1 && list.get(middle + 1) == element) {
                    start = middle;
                    continue;
                }
                return middle;
            } else if (list.get(middle) < element) {
                start = middle;
            } else {
                end = middle;
            }
        }
    }
}
