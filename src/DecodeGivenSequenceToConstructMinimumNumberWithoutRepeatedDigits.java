import java.util.Scanner;

// https://www.techiedelight.com/decode-the-given-sequence-construct-minimum-number-without-repeated-digits/
public class DecodeGivenSequenceToConstructMinimumNumberWithoutRepeatedDigits {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        String string = SCANNER.nextLine();
        System.out.println(decodedMinimumNumber(string));
//        System.out.println(getRange(1, 2));
    }

    private static String decodedMinimumNumber(String string) {
        StringBuilder result = new StringBuilder("");
        for (int index = 0, current = 1, lastD = 0 ; index <= string.length() ; index++) {
            if (index == string.length()) {
                result.append(getRange(current + 1, index - lastD));
                result.append(current);
                break;
            }

            if (string.charAt(index) == 'I') {
                result.append(getRange(current + 1, index - lastD));
                result.append(current);
                current += index - lastD + 1;
                lastD = index + 1;
            }
        }

        return result.toString();
    }

    private static StringBuilder getRange(int start, int length) {
        StringBuilder result = new StringBuilder();
        while (length-- > 0) {
            result.append(start + length);
        }
        return result;
    }
}
