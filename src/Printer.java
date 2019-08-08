class Printer {
    static void print(int[] array) {
        for (int element : array) {
            System.out.print(element + " , ");
        }
        System.out.println();
    }

    static void print(long[] array) {
        for (long element : array) {
            System.out.print(element + " , ");
        }
        System.out.println();
    }

    static void print(boolean[] array) {
        for (boolean value :  array) {
            System.out.print(value + " , ");
        }
        System.out.println();
    }

    static void print(boolean[][] matrix) {
        for (boolean[] array : matrix) {
            print(array);
        }
    }
}
