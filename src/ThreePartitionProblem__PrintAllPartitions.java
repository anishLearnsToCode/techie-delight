import java.util.*;

// https://www.techiedelight.com/3-partition-problem-extended-print-all-partitions/
public class ThreePartitionProblem__PrintAllPartitions {
    public static void main(String[] args) {
        int[] array = StdIn.takeInputAndGetIntArray();
        printAllPossiblePartitions(array);
    }

    private static void printAllPossiblePartitions(int[] array) {
        int sum = Arrays.stream(array).sum();
        if (sum % 3 == 0) {
            List<Partition> partitions = getPartitions(3);
            printAllPossiblePartitions(array, partitions, 0, new HashMap<>(), sum / 3);
        }
    }

    private static boolean printAllPossiblePartitions(int[] array, List<Partition> partitions, int index,
                                                   Map<StringBuilder, Boolean> memory, int sum) {
        StringBuilder key = getUniqueKey(partitions, index);
        if (memory.containsKey(key)) {
            return memory.get(key);
        }

        if (index == array.length) {
            if (allPartitionsAreResolved(partitions, sum)) {
                memory.put(key, true);
                System.out.println(partitions);
                return true;
            }
        }

        if (partitionSumHasExceededSum(partitions, sum)) {
            memory.put(key, false);
            return false;
        }

        boolean answer = false;
        for (int i = 0 ; i < partitions.size() ; i++) {
            partitions.set(i, partitions.get(i).add(array[index]));
            answer = printAllPossiblePartitions(array, partitions, index + 1, memory, sum);
            partitions.set(i, partitions.get(i).remove(array[index]));

            if (answer) {
                break;
            }
        }

        memory.put(key, answer);
        return answer;
    }

    private static List<Partition> getPartitions(int length) {
        List<Partition> partitions = new ArrayList<>();
        while (length-- > 0) {
            partitions.add(new Partition());
        }
        return partitions;
    }

    private static boolean partitionSumHasExceededSum(List<Partition> partitions, int sum) {
        for (Partition partition : partitions) {
            if (partition.sum > sum) {
                return true;
            }
        }
        return false;
    }

    private static StringBuilder getUniqueKey(List<Partition> partitions, int index) {
        StringBuilder key = new StringBuilder();
        for (Partition partition : partitions) {
            key.append(partition.sum).append("|");
        }
        return key.append(index);
    }


    private static boolean allPartitionsAreResolved(List<Partition> partitions, int sum) {
        for (Partition partition : partitions) {
            if (!partition.isResolved(sum)) {
                return false;
            }
        }
        return true;
    }

    private static class Partition {
        private final Map<Integer, Integer> frequencyMap;
        private final int sum;

        Partition() {
            this(0);
        }

        private Partition(int sum) {
            this.frequencyMap = new HashMap<>();
            this.sum = sum;
        }

        private Partition(Map<Integer, Integer> frequencyMap, int sum) {
            this.sum = sum;
            this.frequencyMap = new HashMap<>(frequencyMap);
        }

        Partition add(int element) {
            Partition partition = new Partition(frequencyMap, sum + element);
            partition.frequencyMap.put(element, partition.frequencyMap.getOrDefault(element, 0) + 1);
            return partition;
        }

        Partition remove(int element) {
            Partition partition = new Partition(frequencyMap, sum - element);
            if (partition.frequencyMap.containsKey(element) && partition.frequencyMap.get(element) == 1) {
                partition.frequencyMap.remove(element);
            } else if (frequencyMap.containsKey(element)){
                partition.frequencyMap.put(element, partition.frequencyMap.get(element) - 1);
            }
            return partition;
        }

        boolean isResolved(int sum) {
            return this.sum == sum;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj.getClass() == Partition.class) {
                Partition other = (Partition) obj;
                return this.sum == other.sum && this.frequencyMap.equals(other.frequencyMap);
            }
            return true;
        }

        @Override
        public int hashCode() {
            return Objects.hash(frequencyMap, sum);
        }

        @Override
        public String toString() {
            return frequencyMap.toString();
        }
    }
}
