import java.util.*;

// https://www.techiedelight.com/k-partition-problem-print-all-subsets/
public class KPartitionProblem__PrintingAllPartitions {
    public static void main(String[] args) {
        int[] array = StdIn.takeInputAndGetIntArray();
        int k = StdIn.getIntInput();
        printAllPartitions(array, k);
    }

    private static void printAllPartitions(int[] array, int k) {
        int sum = Arrays.stream(array).sum();
        if (k == 0 || sum % k != 0) {
            return;
        }
        Set<List<Partition>> partitions = new HashSet<>();
        printAllPartitions(array, sum / k, getPartitions(k), 0, partitions);
        System.out.println(partitions);
    }

    private static List<Partition> getPartitions(int length) {
        List<Partition> partitions = new ArrayList<>();
        while (length-- > 0) {
            partitions.add(new Partition());
        }
        return partitions;
    }

    private static void printAllPartitions(int[] array, int sum, List<Partition> partitions, int index,
                                           Set<List<Partition>> answerData) {
        if (index == array.length) {
            if (allPartitionsHaveBeenResolved(partitions, sum)) {
                System.out.println(partitions);
            }
            return;
        }

        if (sumExceedsMaxSum(partitions, sum)) {
            return;
        }

        for (Partition partition : partitions) {
            partition.add(array[index]);
            printAllPartitions(array, sum, partitions, index + 1, answerData);
            partition.remove(array[index]);
        }
    }

    private static boolean allPartitionsHaveBeenResolved(List<Partition> partitions, int sum) {
        for (Partition partition : partitions) {
            if (partition.sum != sum) {
                return false;
            }
        }
        return true;
    }

    private static boolean sumExceedsMaxSum(List<Partition> partitions, int sum) {
        for (Partition partition : partitions) {
            if (partition.sum > sum) {
                return true;
            }
        }
        return false;
    }

    private static class Partition {
        private final Map<Integer, Integer> frequencies = new HashMap<>();
        private int sum = 0;

        void add(int element) {
            frequencies.put(element, frequencies.getOrDefault(element, 0) + 1);
            sum += element;
        }

        void remove(int element) {
            if (frequencies.containsKey(element) && frequencies.get(element) == 1) {
                frequencies.remove(element);
                sum -= element;
            } else if (frequencies.containsKey(element)) {
                frequencies.put(element, frequencies.get(element) - 1);
                sum -= element;
            }
        }

        @Override
        public boolean equals(Object obj) {
            if (obj.getClass() == Partition.class) {
                Partition other = (Partition) obj;
                return this.frequencies.equals(other.frequencies) && this.sum == other.sum;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(frequencies, sum);
        }

        @Override
        public String toString() {
            return frequencies.toString() + ":" + sum ;
        }
    }
}
