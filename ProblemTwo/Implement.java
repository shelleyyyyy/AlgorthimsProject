package ProblemTwo;

// import ProblemTwo.Items;
// import ProblemTwo.Box;
// import ProblemTwo.BinaryHeap;
// import ProblemTwo.MaxBinaryHeap;

public class Implement<AnyType> {

    public static void inOrder(Items[] items, BinaryHeap bh) {

        if (bh.getCurrentSize() <= 0) {
            insertNewBox(1, bh);
        }

        for (int i = 0; i < items.length; i++) {
            Box[] removed = new Box[bh.getCurrentSize() + 1];
            int run = bh.getCurrentSize();
            for (int j = 0; j <= run; j++) {

                Box box = bh.deleteMin();

                Boolean bool = box.addItem(items[i]);
                removed[j] = box;

                if (bool) {
                    break;
                }

                if (bh.getCurrentSize() <= 0) {
                    insertNewBox(1, bh);
                    run++;
                }
            }

            for (int j = 0; j < removed.length; j++) {
                if (removed[j] == null) {
                    break;
                }
                bh.insert(removed[j]);
            }
        }
    }

    public static void inOrderWorstFit(Items[] items, MaxBinaryHeap bh) {

        if (bh.getCurrentSize() <= 0) {
            insertNewBoxMax(1, bh);
        }

        for (int i = 0; i < items.length; i++) {
            Box box = bh.deleteMax();

            Boolean bool = box.addItem(items[i]);

            bh.insert(box);

            if (bool) {
                continue;
            }

            insertNewBoxMax(1, bh);
            box = bh.deleteMax();
            box.addItem(items[i]);
            bh.insert(box);
        }
    }

    public static void orderItems(Items[] items) {

    }

    public void print(BinaryHeap queue) {
        queue.printHeap();
    }

    public static void insertNewBox(double capacity, BinaryHeap bh) {
        Box newBox = new Box(capacity);
        bh.insert(newBox);
    }

    public static void insertNewBoxMax(double capacity, MaxBinaryHeap bh) {
        Box newBox = new Box(capacity);
        bh.insert(newBox);
    }

    public static void main(String[] args) {

        Items i1 = new Items(.4, 1);
        Items i2 = new Items(.4, 1);
        Items i3 = new Items(.6, 1);
        Items i4 = new Items(.6, 1);
        // Items i5 = new Items(.2, 1);
        // Items i6 = new Items(.5, 1);
        // Items i7 = new Items(.9, 1);

        Items[] items = { i1, i2, i3, i4 };

        // Part 1 - Scan items in order given
        System.out.println("*********** Part 1 *************");
        BinaryHeap boxHeapPriorityQueue = new BinaryHeap<>();
        inOrder(items, boxHeapPriorityQueue);
        System.out.println("Stored items with " + boxHeapPriorityQueue.getCurrentSize() + " boxes");
        System.out.println("*********** End Part 1 *************");
        System.out.println();

        // make sorted items
        ItemsBinaryHeap mbh = new ItemsBinaryHeap<>(items);
        Items[] orderd = new Items[mbh.getCurrentSize()];

        for (int i = 0; i < orderd.length; i++) {
            orderd[i] = mbh.deleteMax();
        }

        // Part 2 - Use same strategy after sort
        System.out.println("*********** Part 2 *************");
        BinaryHeap boxHeapPriorityQueue2 = new BinaryHeap<>();
        inOrder(orderd, boxHeapPriorityQueue2);
        System.out.println("Stored items with " + boxHeapPriorityQueue2.getCurrentSize() + " boxes");
        System.out.println("*********** End Part 2 *************");
        System.out.println();

        // Part 3 - Customized: Max Binary Heap with sorted items
        System.out.println("*********** Part 3 *************");
        MaxBinaryHeap maxHeap = new MaxBinaryHeap<>();
        inOrderWorstFit(orderd, maxHeap);
        System.out.println("Stored items with " + maxHeap.getCurrentSize() + " boxes");
        System.out.println("*********** End Part 3 *************");

    }
}
