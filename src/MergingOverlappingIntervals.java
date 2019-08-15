import java.util.ArrayList;
import java.util.List;

public class MergingOverlappingIntervals {
    public static void main(String[] args) {
        int[] array = StdIn.takeInputAndGetIntArray();
        System.out.println(numberOfOverlappingIntervals(array));
    }

    private static int numberOfOverlappingIntervals(int[] array) {
        List<Interval> intervals = getRanges(array);
        List<Interval> mergedIntervals = new ArrayList<>();
        mergedIntervals.add(intervals.get(0));
        Interval currentInterval = mergedIntervals.get(0);
        intervals.sort(Interval::compareTo);

        for (Interval interval : intervals) {
            if (interval.start <= currentInterval.end) {
                currentInterval.insert(interval);
            } else {
                mergedIntervals.add(interval);
                currentInterval = mergedIntervals.get(mergedIntervals.size() - 1);
            }
        }

        return mergedIntervals.size();
    }

    private static List<Interval> getRanges(int[] array) {
        List<Interval> result = new ArrayList<>();
        for (int index = 0 ; index < array.length ; index += 2) {
            result.add(new Interval(array[index], array[index + 1]));
        }
        return result;
    }

    private static class Interval implements Comparable<Interval> {
        int start;
        int end;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Interval other) {
            return Integer.compare(this.start, other.start);
        }

        void insert(Interval interval) {
            this.start = Math.min(this.start, interval.start);
            this.end = Math.max(this.end, interval.end);
        }
    }
}
