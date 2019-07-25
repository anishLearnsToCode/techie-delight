import java.util.ArrayList;
import java.util.List;

// https://www.techiedelight.com/maximum-sum-circular-subarray/
public class MaximumSumCircularSubArray {
    public static void main(String[] args) {
        int[] array = StdIn.takenputAndGetArray();
        System.out.println(maximumSumCircularSubArrayRanges(array));
    }

    private static List<Range> maximumSumCircularSubArrayRanges(int[] array) {
        List<Range> ranges = new ArrayList<>();

        for (int index = 0, sum = 0, startIndex = 0, max = 0, loopIteration = 0 ; ; index++) {
            if (index >= array.length) {
                if (loopIteration == 1) {
                    break;
                }
                loopIteration++;
                index = index % array.length ;
            }

            sum += array[index];

            if (sum <= 0) {
                startIndex = (index + 1) % array.length ;
                sum = 0;
                continue;
            }

            if (sum > max) {
                max = sum;
                ranges = new ArrayList<>();
                ranges.add(new Range(startIndex, index));
            } else if (sum == max) {
                ranges.add(new Range(startIndex, index));
            }
        }

        return ranges;
    }
}
