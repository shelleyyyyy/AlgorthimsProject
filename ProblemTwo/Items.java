package ProblemTwo;

public class Items {

    private double weight;
    private int id;

    public Items() {

    }

    public Items(double weight, int id) {
        this.weight = weight;
        this.id = id;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double newWeight) {
        weight = newWeight;
    }

    public int getID() {
        return id;
    }

    public void setID(int newID) {
        id = newID;
    }
}
