package Odoo;

import java.util.Scanner;
import java.util.regex.Pattern;

public class MatchString {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String string = scanner.nextLine();
        System.out.println(containsOdooOrRules(string));
    }

    private static boolean containsOdooOrRules(String string) {
        Pattern pattern = Pattern.compile("(\\w)\\1");
        return pattern.matcher(string).matches();
    }
}
