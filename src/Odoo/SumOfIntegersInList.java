package Odoo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SumOfIntegersInList {
    public static void main(String[] args) {
        String[] strings = new String[] { "anish", "sachdeva", "100", "10", "odoo", "95", "job", "awesome", "-5" };
        System.out.println(sumOfIntegersInList(new ArrayList<>(Arrays.asList(strings))));
        System.out.println(sumOfIntegersIn(new ArrayList<>(Arrays.asList(strings))));
    }

    private static int sumOfIntegersInList(List<String> strings) {
        int sum = 0;
        for (String string : strings) {
            try {
                int number = Integer.parseInt(string);
                sum += number;
            } catch (Exception ignored) { }
        }
        return sum;
    }

    private static int sumOfIntegersIn(List<String> strings) {
        return sumOfIntegersIn(strings, 0);
    }

    private static int sumOfIntegersIn(List<String> strings, int index) {
        if (index == strings.size()) {
            return 0;
        }

        return getNumericalValueOf(strings.get(index)) + sumOfIntegersIn(strings, index + 1);
    }

    private static int getNumericalValueOf(String string) {
        try {
            return Integer.parseInt(string);
        } catch (Exception ignored) {
            return 0;
        }
    }
}
