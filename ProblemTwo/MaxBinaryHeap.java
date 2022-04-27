package ProblemTwo;

import ProblemTwo.Items;

public class MaxBinaryHeap<AnyType>{
    
    private static final int DEFAULT_CAPACITY = 10;
    private int currentSize;
    private Items [] array;

    public MaxBinaryHeap() {
        this(DEFAULT_CAPACITY);
    }

    public MaxBinaryHeap(int capacity) {
        currentSize = 0;
        array = new Items[capacity + 1];
    }

    public MaxBinaryHeap(Items[] Itemses) {
        currentSize = Itemses.length;
        array = new Items[currentSize + 1];
        int i = 1;
        for (Items Items : Itemses) {
            array[i++] = Items;
        }

        buildHeap();
    }

    public int getCurrentSize() {
        return currentSize;
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public void makeEmpty() {
        currentSize = 0;
    }

    public void insert(Items x) {

        if (currentSize == array.length - 1)
            enlargeArray(array.length * 2 + 1);

        // percolate up
        int hole = ++currentSize;
        for (array[0] = x; x.getWeight() > array[hole / 2].getWeight(); hole /= 2) {
            array[hole] = array[hole / 2];
        }
        array[hole] = x;
    }

    private void enlargeArray(int newSize) {
        Items[] old = array;
        array = new Items[newSize + 1];
        for (int i = 0; i < old.length; i++) {
            array[i] = old[i];
        }
    }

    public Items findMin() {
        if(isEmpty()) {
            return null;
        }

        return array[1];
    }

    public Items deleteMax() {

        Items minItem = findMin();

        if(minItem == null){
            return null;
        }

        array[1] = array[currentSize--];
        percolateDown(1);

        return minItem;
    }

    public void percolateDown(int hole) {
        int child = 0;
        Items tmp = array[hole];

        for(; hole * 2 <= currentSize; hole = child){
            child = hole * 2;

            // if child + 1 is smaller than child then update child to child + 1
            if(child != currentSize && array[child + 1].getWeight() > array[child].getWeight()){
                child++;
            }if(array[child].getWeight() > tmp.getWeight()){
                array[hole] = array[child];
            }else{
                break;
            }            
        }
        array[hole] = tmp;
    }

    public void buildHeap() {
        for(int i = (currentSize / 2); i > 0; i--){
            percolateDown(i);
        }
    }

    public void showHeap() {
        for(int i = 1; i <= currentSize; i++){
            System.out.print(array[i].getWeight() + ", ");
        }
        System.out.println();
    }

    // public static void main(String[] args) {

    //     Items i1 = new Items(.1, 1);
    //     Items i2 = new Items(.2, 2);
    //     Items i3 = new Items(.3, 3);
    //     Items i4 = new Items(.4, 4);

    //     Items[] items = {i1, i2, i3, i4};

    //     MaxBinaryHeap mbh = new MaxBinaryHeap(items);

    //     mbh.showHeap();

    //     mbh.deleteMax();

    //     mbh.showHeap();

    // }
}
