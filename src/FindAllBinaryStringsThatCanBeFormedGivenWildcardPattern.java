import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

// https://www.techiedelight.com/find-binary-strings-can-formed-given-wildcard-pattern/
public class FindAllBinaryStringsThatCanBeFormedGivenWildcardPattern {
    public static void main(String[] args) {
        StringBuilder pattern = StdIn.getStringBuilder();
        System.out.println(allBinaryStrings(pattern));
    }

    private static Set<String> allBinaryStrings(StringBuilder pattern) {
        return allBinaryStrings(pattern, 0);
    }

    private static Set<String> allBinaryStrings(StringBuilder pattern, int index) {
        if (index == pattern.length()) {
            return Collections.singleton("");
        }

        for (int i = index ; i < pattern.length() ; i++) {
            if (pattern.charAt(i) == '?') {
                Set<String> subStringPatterns = allBinaryStrings(pattern, i + 1);
                return combine(subStringPatterns, pattern.substring(index, i));
            }
        }

        return Collections.singleton(pattern.substring(index));
    }

    private static Set<String> combine(Set<String> binaryStrings, String string) {
        Set<String> result = new HashSet<>();
        for (String binaryString : binaryStrings) {
            result.add(string + '0' + binaryString);
        }

        for (String binaryString : binaryStrings) {
            result.add(string + '1' + binaryString);
        }

        return result;
    }
}
