import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Solution {

    static int[] solution(String A, String B) {
        String[] firstStrings = A.split(",");
        String[] secondStrings = B.split(",");
        Map<Integer, Integer> valueFrequencies = getValueFrequencyMap(firstStrings);
        int[] result = new int[secondStrings.length];
        int index = 0;

        for (String string : secondStrings) {
            int value = getValue(string);
            int stringsSmallerThanValue = stringsSmallerThan(valueFrequencies, value);
            result[index++] = stringsSmallerThanValue;
        }

        return result;
    }

    private static Map<Integer, Integer> getValueFrequencyMap(String[] strings) {
        Map<Integer, Integer> result = new HashMap<>();
        for (String string : strings) {
            int value = getValue(string);
            result.put(value, result.getOrDefault(value, 0) + 1);
        }
        return result;
    }

    private static int stringsSmallerThan(Map<Integer, Integer> valueFrequencies, int value) {
        int sum = 0;
        for (int index = value - 1 ; index > 0 ; index--) {
            sum += valueFrequencies.getOrDefault(index, 0);
        }
        return sum;
    }

    private static int getValue(String string) {
        char minCharacter = getSmallestCharacter(string);
        Map<Character, Integer> characterFrequencies = getCharacterFrequencies(string);
        return characterFrequencies.get(minCharacter);
    }

    private static Map<Character, Integer> getCharacterFrequencies(String string) {
        Map<Character, Integer> characterFrequencies = new HashMap<>();
        for (int index = 0 ; index < string.length() ; index++) {
            Character character = string.charAt(index);
            characterFrequencies.put(character, characterFrequencies.getOrDefault(character, 0) + 1);
        }
        return characterFrequencies;
    }

    private static char getSmallestCharacter(String string) {
        char min = 'z';
        for (int index = 0 ; index < string.length() ; index++) {
            min = string.charAt(index) < min ? string.charAt(index) : min ;
        }
        return min;
    }

    public static void main(String[] args) {
        // Read from stdin, solve the problem, write answer to stdout.
        Scanner in = new Scanner(System.in);
        String A = in.next();
        String B = in.next();
        System.out.print(printIntArray(solution(A, B)));
    }

    private static String printIntArray(int[] input) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length; i++) {
            sb.append(input[i]);
            if (i < input.length - 1) {
                sb.append(',');
            }
        }
        return sb.toString();
    }
}