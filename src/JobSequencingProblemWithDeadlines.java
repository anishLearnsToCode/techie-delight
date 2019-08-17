import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// https://www.techiedelight.com/job-sequencing-problem-deadlines/
public class JobSequencingProblemWithDeadlines {
    public static void main(String[] args) {
        int[] array = StdIn.takeInputAndGetIntArray();
        System.out.println(maximumProfit(array));
    }

    private static int maximumProfit(int[] array) {
        List<Task> tasks = tasksFrom(array);
        return maximumProfit(tasks);
    }

    private static List<Task> tasksFrom(int[] array) {
        List<Task> result = new ArrayList<>();
        for (int index = 0 ; index < array.length ; index += 2) {
            result.add(new Task(array[index], array[index + 1]));
        }
        return result;
    }

    private static int maximumProfit(List<Task> tasks) {
        tasks.sort(Task::compareTo);
        JobSequence jobSequence = new JobSequence(tasks);
        return jobSequence.totalProfit();
    }

    private static class Task implements Comparable<Task> {
        int deadline;
        int profit;

        Task(int deadline, int profit) {
            this.profit = profit;
            this.deadline = deadline;
        }

        @Override
        public int compareTo(Task other) {
            if (this.profit == other.profit) {
                return Integer.compare(this.deadline, other.deadline);
            }

            return Integer.compare(other.profit, this.profit);
        }

        @Override
        public String toString() {
            return "(deadline: " + deadline + " ,profit: " + profit + " )";
        }
    }

    private static class JobSequence {
        private int profit = 0;
        private final Set<Integer> deadlines = new HashSet<>();

        JobSequence(List<Task> tasks) {
            for (Task task : tasks) {
                add(task);
            }
        }

        void add(Task task) {
            if (add(task.deadline)) {
                profit += task.profit;
            }
        }

        private boolean add(int deadline) {
            if (deadline == 0) {
                return false;
            }

            if (deadlines.contains(deadline)) {
                return add(deadline - 1);
            }

            deadlines.add(deadline);
            return true;
        }

        int totalProfit() {
            return profit;
        }
    }
}
