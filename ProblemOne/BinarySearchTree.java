package ProblemOne;

import java.util.HashMap;

public class BinarySearchTree<AnyType extends Comparable<AnyType>> {

    private static class BinaryNode<AnyType> {

        HashMap<AnyType, Integer> map;
        AnyType element;
        BinaryNode<AnyType> left;
        BinaryNode<AnyType> right;

        BinaryNode(AnyType element) {
            HashMap<AnyType, Integer> map = new HashMap<>();
            map.put(element, 1);
            this.map = map;
            this.element = element;
            this.left = null;
            this.right = null;
        }

        BinaryNode(AnyType element, BinaryNode<AnyType> left, BinaryNode<AnyType> right) {
            this.map.put(element, 1);
            this.element = element;
            this.left = left;
            this.right = right;
        }

        public AnyType getElement() {
            return element;
        }

        public Integer getFreq() {
            return map.get(element);
        }

        public BinaryNode<AnyType> getLeft() {
            return left;
        }

        public BinaryNode<AnyType> getRight() {
            return right;
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

    public Integer count(AnyType x) {
        return count(x, root);
    }

    public boolean removeOne(AnyType x) {
        return removeOne(x, root);
    }

    public boolean removeAll(AnyType x) {
        if (removeAll(x, root) != null) {
            return true;
        } else {
            return false;
        }
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

        if (t == null) {
            return new BinaryNode<>(x);
        }

        int compareResult = x.compareTo(t.element);

        if (compareResult == 0) {
            Integer value = t.map.get(x);
            t.map.put(x, value += 1);
        } else if (compareResult < 0)
            t.left = insert(x, t.left);
        else if (compareResult > 0)
            t.right = insert(x, t.right);
        else
            ;

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
            return t.getFreq();
    }

    private boolean removeOne(AnyType x, BinaryNode<AnyType> t) {

        if (t == null)
            return false;

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0)
            return removeOne(x, t.left);
        else if (compareResult > 0)
            return removeOne(x, t.right);
        else {
            if (t.getFreq() > 1) {
                Integer value = t.map.get(x);
                t.map.put(x, value -= 1);
                return true;
            } else {
                return removeAll(x);
            }
        }
    }

    private BinaryNode<AnyType> removeAll(AnyType x, BinaryNode<AnyType> t) {
        if (t == null)
            return t;

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0)
            t.left = removeAll(x, t.left);
        else if (compareResult > 0)
            t.right = removeAll(x, t.right);

        else if (t.left != null && t.right != null) {
            t.element = findMin(t.right).element;
            t.right = removeAll(t.element, t.right);

        } else
            t = (t.left != null) ? t.left : t.right;

        return t;
    }

    public static <AnyType> void inOrder(BinaryNode<AnyType> treeNode) {

        if (treeNode.getLeft() != null) {
            inOrder(treeNode.getLeft());
        }

        System.out.println(treeNode.getElement() + " freq: " + treeNode.getFreq());

        if (treeNode.getRight() != null) {
            inOrder(treeNode.getRight());
        }
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        int[] array = { 5, 2, 6, 10, 3, 9, 3, 5, 5, 5 };

        for (int i = 0; i < array.length; i++) {
            bst.insert(array[i]);
        }

        System.out.println("Original Tree");
        System.out.println(bst.root.toString());
        // inOrder(bst.root);
        System.out.println("Key 5 has a frquency of: " + bst.count(5));
        bst.removeOne(5);
        System.out.println("After removeOne key 5 has a frquency of: " + bst.count(5));
        bst.removeOne(9);
        System.out.println("After removeOne key 9 has a frquency of: " + bst.count(9) + " *no longer in tree*");
        bst.removeAll(5);
        System.out.println("After removeAll key 5 has a frquency of: " + bst.count(5));
        System.out.println("Updated Tree");
        System.out.println(bst.root.toString());
        System.out.println("Does 10 exist in the tree? " + bst.contains(10));
        System.out.println("Does 9 exist in the tree? " + bst.contains(9));

    }
}
