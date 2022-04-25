package ProblemTwo;

import ProblemTwo.Items;

public class Box {
    private double capacity;
    private double currentWeight = 0;
    private Items[] contained = new Items[10];
    private int lastItem = 0;

    public Box() {
        this.capacity = 1;
    }

    public Box(double capacity) {
        this.capacity = capacity;
    }

    public double getCapacity() {
        return capacity;
    }

    public double getCurrentWeight() {
        return currentWeight;
    }

    // public void setCurrentWeight(int newWeight) {
    // currentWeight = currentWeight + newWeight;
    // }

    public boolean addItem(Items item) {
        double tmp = currentWeight + item.getWeight();

        if (tmp > 1) {
            return false;
        }

        currentWeight = tmp;
        contained[lastItem] = item;
        lastItem++;
        capacity = capacity - currentWeight;
        return true;
    }
}
