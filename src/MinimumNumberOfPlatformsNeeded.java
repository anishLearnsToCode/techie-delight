import java.util.Arrays;

public class MinimumNumberOfPlatformsNeeded {
    public static void main(String[] args) {
        double[] arrivalTimes = StdIn.takeInputAndGetDoubleArray();
        double[] departureTimes = StdIn.takeInputAndGetDoubleArray();
        System.out.println(minimumPlatformsNeeded(arrivalTimes, departureTimes));
    }

    private static int minimumPlatformsNeeded(double[] arrivalTimes, double[] departureTimes) {
        final double[] sortedArrivalTimes = Arrays.stream(arrivalTimes).sorted().toArray();
        final double[] sortedDepartureTimes = Arrays.stream(departureTimes).sorted().toArray();

        int max = Integer.MIN_VALUE ;
        for (int arrivalTrainIndex = 0, departureTrainIndex = 0, platforms = 0 ;
             arrivalTrainIndex <= arrivalTimes.length && departureTrainIndex <= departureTimes.length ; ) {

            if (arrivalTrainIndex == sortedArrivalTimes.length) {
                break;
            }

            if (departureTrainIndex == sortedDepartureTimes.length) {
                max = max + sortedArrivalTimes.length - arrivalTrainIndex - 1;
                break;
            }

            if (sortedArrivalTimes[arrivalTrainIndex] < sortedDepartureTimes[departureTrainIndex]) {
                max = Math.max(max, ++platforms);
                arrivalTrainIndex++;
            } else if (sortedArrivalTimes[arrivalTrainIndex] > sortedDepartureTimes[departureTrainIndex]){
                max = Math.max(max, --platforms);
                departureTrainIndex++;
            } else {
                arrivalTrainIndex++;
                departureTrainIndex++;
            }
        }

        return max;
    }
}
