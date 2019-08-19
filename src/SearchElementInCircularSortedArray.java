// https://www.techiedelight.com/search-element-circular-sorted-array/
public class SearchElementInCircularSortedArray {
    public static void main(String[] args) {
        int[] array = StdIn.takeInputAndGetIntArray();
        int element = StdIn.getIntInput();
        System.out.println(indexOf(array, element));
    }

    private static int indexOf(int[] array, int element) {
        // search space is array[left..right]

        // iterate till search space contains at-least one element
        for (int left = 0, right = array.length - 1, mid = right / 2 ; left <= right ; mid = (left + right) / 2) {
            // if key is found, return its index
            if (element == array[mid]) {
                return mid;
            }

            // if right half (array[mid..right]) is sorted and mid is not
            // the key element
            if (array[mid] <= array[right]) {
                // compare key with array[mid] and array[right] to know
                // if it lies in array[mid..right] or not
                if (element > array[mid] && element <= array[right]) {
                    // go searching in right sorted half
                    left = mid + 1;
                } else {
                    right = mid - 1;	// go searching left
                }
            }

            // if left half (array[left..mid]) is sorted and mid is not
            // the key element
            else {
                // compare key with array[left] and array[mid] to know
                // if it lies in array[left..mid] or not
                if (element >= array[left] && element < array[mid]) {
                    // go searching in left sorted half
                    right = mid - 1;
                }
                else {
                    left = mid + 1;	// go searching right
                }
            }
        }

        // key not found or invalid input
        return -1;
    }
}
