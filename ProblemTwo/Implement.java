package ProblemTwo;

// import ProblemTwo.Items;
// import ProblemTwo.Box;
// import ProblemTwo.BinaryHeap;
// import ProblemTwo.MaxBinaryHeap;

public class Implement {

    // public void inOrder(double[] weights, BinaryHeap queue) {
    //     Items[] array = new Items[weights.length];
    //     for (int i = 0; i < array.length; i++) {
    //         array[i] = new Items(weights[i], i);
    //     }

    //     for (int i = 0; i < 2; i++) {
    //         Box min = queue.deleteMin();
    //         System.out.println("before add " + min.getCapacity());
    //         System.out.println("before add array[i] " + array[i].getWeight());

    //         min.addItem(array[i]);
    //         System.out.println("after add " + min.getCapacity());
    //         queue.insert(min);
    //         queue.printHeap();
    //     }
    // }

    public static void inOrder(Items[] items, BinaryHeap bh){

        if(bh.getCurrentSize() <= 0){
            insertNewBox(1, bh);
        }

        for(int i = 0; i < items.length; i++) {
            Box[] removed = new Box[bh.getCurrentSize() + 1];
            int run = bh.getCurrentSize();
            for(int j = 0; j <= run; j++){

                Box box = bh.deleteMin();

                System.out.println("removed box: " + j);
                System.out.println(items[i].getWeight());
                System.out.println(box.getCapacity());
                Boolean bool = box.addItem(items[i]);
                removed[j] = box;

                if(bool){
                    System.out.println("fits");
                    break;
                }

                System.out.println("does not fit");

                if(bh.getCurrentSize() <= 0){
                    insertNewBox(1, bh);
                    run++;
                }
            }

            for(int j = 0; j < removed.length; j++){
                if(removed[j] == null){
                    break;
                }
                bh.insert(removed[j]);
            }
        }

        System.out.println("************FINAL**************");
        bh.printHeap();
    }

    public static void orderItems(Items[] items){

    }

    public void print(BinaryHeap queue) {
        queue.printHeap();
    }

    public static void insertNewBox(double capacity, BinaryHeap bh){
        Box newBox = new Box(capacity);
        bh.insert(newBox);
    }

    public static void main(String[] args) {

        Items i1 = new Items(.4, 1);
        Items i2 = new Items(.4, 1);
        Items i3 = new Items(.6, 1);
        Items i4 = new Items(.6, 1);
        
        Items[] items = {i1, i2, i3, i4};

        BinaryHeap boxHeapPriorityQueue = new BinaryHeap<>();
        MaxBinaryHeap mbh = new MaxBinaryHeap<>(items);

        // inOrder(items, boxHeapPriorityQueue);
    
        // make sorted items
        Items[] orderd = new Items[mbh.getCurrentSize()];

        for(int i = 0; i < orderd.length; i++){
            orderd[i] = mbh.deleteMax();
        }

        inOrder(orderd, boxHeapPriorityQueue);
        boxHeapPriorityQueue.printHeap();
        System.out.println("Stored items with " + boxHeapPriorityQueue.getCurrentSize() + " boxes");

    }
}
