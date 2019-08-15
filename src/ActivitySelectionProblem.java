import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// https://www.techiedelight.com/activity-selection-problem/
public class ActivitySelectionProblem {
    public static void main(String[] args) {
        int[] array = StdIn.takeInputAndGetIntArray();
        System.out.println(maximumNumberOfActivities(array));
    }

    private static int maximumNumberOfActivities(int[] array) {
        List<Interval> intervals = getIntervals(array);
        intervals.sort(Interval::compareTo);
        Activities activities = new Activities();

        for (Interval interval : intervals) {
            activities.add(interval);
        }

        return activities.size();
    }

    private static List<Interval> getIntervals(int[] array) {
        List<Interval> intervals = new ArrayList<>();
        for (int index =0 ; index < array.length ; index += 2) {
            intervals.add(new Interval(array[index], array[index + 1]));
        }
        return intervals;
    }

    private static class Interval implements Comparable<Interval> {
        final int startTime;
        final int endTime;

        Interval(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj.getClass() == Interval.class) {
                Interval other = (Interval) obj;
                return this.endTime == other.endTime
                       && this.startTime == other.startTime ;
            }

            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(this.startTime) +
                   31 * Objects.hashCode(this.endTime);
        }

        @Override
        public int compareTo(Interval other) {
            if (this.endTime == other.endTime) {
                return Integer.compare(other.startTime, this.startTime);
            }

            return Integer.compare(this.endTime, other.endTime);
        }

        @Override
        public String toString() {
            return "(" + startTime + " , " + endTime + ")";
        }
    }

    private static class Activities {
        int numberOfActivities = 0;
        int endTime = -1;

        void add(Interval interval) {
            if (interval.startTime > this.endTime) {
                numberOfActivities++;
                this.endTime = interval.endTime;
            }
        }

        int size() {
            return numberOfActivities;
        }
    }
}
