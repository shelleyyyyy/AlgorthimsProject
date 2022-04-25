package ProblemOne;

import java.util.HashMap;

public class BinarySearchTree<AnyType extends Comparable<AnyType>> {

    private static class BinaryNode<AnyType> {

        HashMap<AnyType, Integer> map;
        AnyType element;
        BinaryNode<AnyType> left;
        BinaryNode<AnyType> right;

        // BinaryNode(AnyType element) {
        //     HashMap<AnyType, Integer> map = new HashMap<>();
        //     this.map = map.put(element, 0);
        //     this.element = element;
        //     this.left = null;
        //     this.right = null;
        // }

        BinaryNode(AnyType element, BinaryNode<AnyType> left, BinaryNode<AnyType> right) {
            this.map.put(element, 0);
            this.element = element;
            this.left = left;
            this.right = right;
        }

        public StringBuilder toString(StringBuilder prefix, boolean isTail, StringBuilder sb) {

            if (right != null)
                right.toString(new StringBuilder().append(prefix).append(isTail ? "│   " : "    "), false, sb);

            sb.append(prefix).append(isTail ? "└── " : "┌── ").append(element.toString()).append("\n");

            if (left != null)
                left.toString(new StringBuilder().append(prefix).append(isTail ? "    " : "│   "), true, sb);

            return sb;
        }

        @Override
        public String toString() {

            return this.toString(new StringBuilder(), true, new StringBuilder()).toString();
        }
    }

    private BinaryNode<AnyType> root;

    public BinarySearchTree() {
        root = null;
    }

    public void makeEmpty() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void insert(AnyType x) {
        root = insert(x, root);
    }

    public AnyType findMin() {
        return findMin(root).element;
    }

    public AnyType findMax() {
        return findMax(root).element;
    }

    public boolean contains(AnyType x) {
        return contains(x, root);
    }

    private BinaryNode<AnyType> findMin(BinaryNode<AnyType> root) {

        if (root == null)
            return null;

        if (root.left == null)
            return root;

        return findMin(root.left);
    }

    private BinaryNode<AnyType> findMax(BinaryNode<AnyType> root) {

        if (root != null) {
            while (root.right != null)
                root = root.right;
        }

        return root;
    }

    private BinaryNode<AnyType> insert(AnyType x, BinaryNode<AnyType> t) {

        if (t == null)
            return new BinaryNode<>(x);

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0)
            t.left = insert(x, t.left);
        else if (compareResult > 0)
            t.right = insert(x, t.right);
        else if (compareResult == 0) {
            Integer value = t.map.get(x);
            t.map.put(x, value += 1);
        }

        return t;
    }

    private boolean contains(AnyType x, BinaryNode<AnyType> t) {

        if (t == null)
            return false;

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0)
            return contains(x, t.left);
        else if (compareResult > 0)
            return contains(x, t.right);
        else
            return true;
    }

    private Integer count(AnyType x, BinaryNode<AnyType> t) {
        if (t == null)
            return 0;

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0)
            return count(x, t.left);
        else if (compareResult > 0)
            return count(x, t.right);
        else
            return t.map.get(x);
    }

    /*
     * private boolean removeOne(AnyType x, BinaryNode<AnyType> t) {
     * if (t == null)
     * return t;
     * 
     * int compareResult = x.compareTo(t.element);
     * 
     * if (compareResult < 0)
     * t.left = remove(x, t.left);
     * else if (compareResult > 0)
     * t.right = remove(x, t.right);
     * 
     * if (t.map.get(x) == 1) {
     * if (t.left != null && t.right != null) {
     * t.element = findMin(t.right).element;
     * t.right = remove(t.element, t.right);
     * 
     * } else
     * t = (t.left != null) ? t.left : t.right;
     * 
     * return t;
     * } else {
     * value = t.get(x);
     * t.map.put(x, value -= 1);
     * }
     * 
     * return t;
     * }
     * 
     * private BinaryNode<AnyType> removeAll(AnyType x, BinaryNode<AnyType> t) {
     * if (t == null)
     * return t;
     * 
     * int compareResult = x.compareTo(t.element);
     * 
     * if (compareResult < 0)
     * t.left = remove(x, t.left);
     * else if (compareResult > 0)
     * t.right = remove(x, t.right);
     * 
     * else if (t.left != null && t.right != null) {
     * t.element = findMin(t.right).element;
     * t.right = remove(t.element, t.right);
     * 
     * } else
     * t = (t.left != null) ? t.left : t.right;
     * 
     * return t;
     * }
     */

    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        for (int i = 1; i <= 20; i++) {
            int rand = 1 + (int) (Math.random() * 5);

            bst.insert(rand, bst.root);
        }

        /*
         * System.out.println("Original Tree");
         * System.out.println(bst.root.toString());
         * 
         * int key = 1 + (int) (Math.random() * 10);
         * 
         * System.out.println(key + " is in tree?" + bst.contains(key));
         * System.out.println("Min element in the tree is " + bst.findMin());
         * System.out.println("Max element in the tree is " + bst.findMax());
         * 
         * 
         * bst.remove(key);
         * 
         * System.out.println("Mofified Tree remove " + key);
         * System.out.println(bst.root.toString());
         */
    }
}
