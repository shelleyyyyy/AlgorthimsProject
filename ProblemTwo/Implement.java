package ProblemTwo;

import ProblemTwo.Items;
import ProblemTwo.Box;
import ProblemTwo.BinaryHeap;

public class Implement {

    public void inOrder(double[] weights, BinaryHeap queue) {
        Items[] array = new Items[weights.length];
        for (int i = 0; i < array.length; i++) {
            array[i] = new Items(weights[i], i);
        }

        for (int i = 0; i < 2; i++) {
            Box min = queue.deleteMin();
            System.out.println("before add " + min.getCapacity());
            System.out.println("before add array[i] " + array[i].getWeight());

            min.addItem(array[i]);
            System.out.println("after add " + min.getCapacity());
            queue.insert(min);
            queue.printHeap();
        }

    }

    public void print(BinaryHeap queue) {
        queue.printHeap();
    }

    public static void main(String[] args) {

        Box b1 = new Box();
        Box b2 = new Box();
        Box b3 = new Box(.6);
        Box b4 = new Box(.6);
        Box b5 = new Box(.2);

        Box[] boxes = { b1, b2 };
        double[] weights = { .4, .4, .6, .6 };

        BinaryHeap boxHeapPriorityQueue = new BinaryHeap<>(boxes);
        Implement test = new Implement();
        test.inOrder(weights, boxHeapPriorityQueue);
        // test.print(boxHeapPriorityQueue);

    }
}
