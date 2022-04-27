package ProblemTwo;

import ProblemTwo.Box;

public class BinaryHeap<AnyType> {

    private static final int DEFAULT_CAPACITY = 10;
    private int currentSize;
    private Box[] array;

    public BinaryHeap() {
        this(DEFAULT_CAPACITY);
    }

    public BinaryHeap(int capacity) {
        currentSize = 0;
        array = new Box[capacity + 1];
    }

    public BinaryHeap(Box[] boxes) {
        currentSize = boxes.length;
        array = new Box[currentSize + 1];
        int i = 1;
        for (Box box : boxes) {
            array[i++] = box;
        }

        buildHeap();
    }

    public int getCurrentSize() {
        return currentSize;
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public Box[] getArray() {
        return array;
    }

    public void makeEmpty() {
        currentSize = 0;
    }

    public void insert(Box x) {

        if (currentSize == array.length - 1)
            enlargeArray(array.length * 2 + 1);

        // percolate up
        int hole = ++currentSize;
        for (array[0] = x; x.getCapacity() < array[hole / 2].getCapacity(); hole /= 2) {
            array[hole] = array[hole / 2];
        }
        array[hole] = x;
    }

    private void enlargeArray(int newSize) {

        Box[] old = array;
        array = new Box[newSize + 1];
        for (int i = 0; i < old.length; i++) {
            array[i] = old[i];
        }
    }

    public Box findMin() {

        if (isEmpty())
            return null;

        return array[1];
    }

    public Box deleteMin() {

        Box minItem = findMin();
        if (minItem == null)
            return null;

        array[1] = array[currentSize--];

        percolateDown(1);

        return minItem;
    }

    public void percolateDown(int hole) {

        int child = 0;
        Box tmp = array[hole];

        for (; hole * 2 <= currentSize; hole = child) {
            child = hole * 2;
            if (child != currentSize && array[child + 1].getCapacity() < array[child].getCapacity())
                child++;
            if (array[child].getCapacity() < tmp.getCapacity())
                array[hole] = array[child];
            else
                break;
        }
        array[hole] = tmp;
    }

    public void buildHeap() {

        for (int i = currentSize / 2; i > 0; i--) {
            percolateDown(i);
        }
    }

    public void printHeap() {
        for (int i = 1; i < array.length; i++) {
            if (array[i] != null)
                System.out.print(array[i].getCapacity() + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {

    }
}
