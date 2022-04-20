package ProblemTwo;

public class Box {
    private int capacity = 1;
    private int currentWeight = 0;
    private int [] contained = new int[10];
    private int lastItem = 0;

    public Box() {
        
    }
    

    public int getCapacity() {
        return capacity;
    }

    public int getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(int newWeight) {
        currentWeight = currentWeight + newWeight;
    }

    public boolean addItem(int item) {
        int tmp = currentWeight + item;

        if(tmp > 1){
            return false;
        }

        currentWeight = tmp;
        contained[lastItem] = item;
        return true;
    }


}
