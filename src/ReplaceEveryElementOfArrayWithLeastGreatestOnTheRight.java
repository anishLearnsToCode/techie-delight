import java.util.Objects;


// https://www.techiedelight.com/replace-every-element-array-least-greater-element-right/
public class ReplaceEveryElementOfArrayWithLeastGreatestOnTheRight {
  public static void main(String[] args) {
    int[] array = StdIn.takeInputAndGetIntArray();
    Printer.print(leastGreatestOnRight(array));
  }

  private static int[] leastGreatestOnRight(int[] array) {
    int[] result = new int[array.length];
    result[result.length - 1] = -1;
    BinarySearchTree tree = new BinarySearchTree(array[array.length - 1]);

    for (int index = array.length - 2 ; index >= 0 ; index--) {
      result[index] = tree.add(array[index]);
    }

    return result;
  }

  private static class BinarySearchTree {
    private static Node<Integer> root;

    BinarySearchTree(int value) {
      root = new Node<>(value);
    }

    int add(int value) {
      return add(value, root, Integer.MIN_VALUE);
    }

    int add(int value, Node<Integer> node, int nextGreatestSoFar) {
      if (Objects.isNull(node)) {
        return nextGreatestSoFar;
      }

      if (value > node.value) {
        if (Objects.isNull(node.right)) {
          node.right = new Node<>(value);
          return nextGreatestSoFar == Integer.MIN_VALUE ? -1 : nextGreatestSoFar;
        }
        return add(value, node.right, nextGreatestSoFar);
      } else if(value < node.value) {
        if (Objects.isNull(node.left)) {
          node.left = new Node<>(value);
          return node.value;
        }
        return add(value, node.left, node.value);
      }

      return Objects.isNull(node.right) ? -1 : smallest(node.right);
    }

    private int smallest(Node<Integer> node) {
      if (Objects.isNull(node.left)) {
        return node.value;
      }
      return smallest(node.left);
    }

    private class Node<T> {
      final T value;
      Node<T> left;
      Node<T> right;

      Node(T value) {
        this.value = value;
      }
    }
  }
}
