package Odoo;

public class Question_1 {
    public static void main(String[] args) {
        for (int number = 1 ; number <=99 ; number++) {
            if (isMultipleOf3(number) && !isMultipleOf7(number)) {
                System.out.println("Open");
            } else if (!isMultipleOf3(number) && isMultipleOf7(number)) {
                System.out.println("Source");
            } else {
                System.out.println(number);
            }
        }
    }

    private static boolean isMultipleOf3(int number) {
        return isMultipleOf(number, 3);
    }

    private static boolean isMultipleOf7(int number) {
        return isMultipleOf(number, 7);
    }

    private static boolean isMultipleOf(int number, int divisor) {
        return number % divisor == 0 ;
    }
}
