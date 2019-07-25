import java.util.Arrays;


// https://www.techiedelight.com/find-maximum-product-two-integers-array/
public class MaximumProductInArray {

  public static void main(String[] args) {
    int length = StdIn.getIntInput();
    int[] array = new int[length];
    StdIn.getIntInput(array);
    System.out.println(maximumProduct(array));
  }

  private static int maximumProduct(int[] array) {
    Pair negativeMaximums = getFirstAndSecondMax(Arrays.stream(array).map(element -> -element).toArray());
    Pair positiveMaximums = getFirstAndSecondMax(array);

    System.out.println(negativeMaximums);
    System.out.println(positiveMaximums);

    return Math.max(negativeMaximums.first * negativeMaximums.second, positiveMaximums.first * positiveMaximums.second);
  }

  private static Pair getFirstAndSecondMax(int[] array) {
    int first = Integer.MIN_VALUE, second = Integer.MIN_VALUE;

    for (int element : array) {
      if (element > first) {
        second = first;
        first = element;
      } else if (element > second){
        second = element;
      }
    }

    return new Pair(first, second);
  }
}

class Pair {
  final int first;
  final int second;

  Pair(int first, int second) {
    this.first = first;
    this.second = second;
  }

  @Override
  public String toString() {
    return "(" + first + " , " + second + ")";
  }
}
