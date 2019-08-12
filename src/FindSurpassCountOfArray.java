import java.util.Objects;

// https://www.techiedelight.com/surpasser-count-each-element-array/
public class FindSurpassCountOfArray {
    public static void main(String[] args) {
        int[] array = StdIn.takeInputAndGetIntArray();
        Printer.print(surpasserArray(array));
    }

    private static int[] surpasserArray(int[] array) {
        int[] result = new int[array.length];
        result[array.length - 1] = 0;
        BinarySearchTree binarySearchTree = new BinarySearchTree(array[array.length - 1]);

        for (int index = array.length - 2 ; index >= 0 ; index--) {
            result[index] = binarySearchTree.add(array[index]);
        }

        return result;
    }

    private static class BinarySearchTree {
        Node root;
        BinarySearchTree left;
        BinarySearchTree right;
        final BinarySearchTree parent;
        private int size = 0;
        private final int level;

        private BinarySearchTree(BinarySearchTree parent) {
            this.parent = parent;
            level = Objects.isNull(parent) ? 0 : this.parent.level + 1;
        }

        private BinarySearchTree(BinarySearchTree parent, int root) {
            this(parent);
            this.root = new Node(root);
            size = 1;
        }

        BinarySearchTree(int root) {
            this(null, root);
        }

        int add(int value) {
            this.size++;
            if (value < root.value) {
                if (Objects.isNull(this.left)) {
                    this.left = new BinarySearchTree(this, value);
                    return 1 + (Objects.isNull(this.right) ? 0 : this.right.size) ;
                } else {
                    return 1 + (Objects.isNull(this.right) ? 0 : this.right.size) + this.left.add(value);
                }
            } else {
                if (Objects.isNull(this.right)) {
                    this.right = new BinarySearchTree(this, value);
                    return 0;
                } else {
                    return this.right.add(value);
                }
            }
        }

        private int elementsGreaterThan(BinarySearchTree root) {
            if (Objects.isNull(root.parent)) {
                return 0;
            }

            return 1 + elementsGreaterThan(root.parent) + (Objects.isNull(root.right) ? 0 : root.right.size) ;
        }

        private static class Node {
            final int value;

            Node(int value) {
                this.value = value;
            }
        }
    }
}
