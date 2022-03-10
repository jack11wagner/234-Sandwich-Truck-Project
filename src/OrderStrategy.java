/*
Made by: Jackson Wagner
Abstract class which handles strategies that the a truck should select when
making deliveries
Edits by:
*/
public abstract class OrderStrategy {
    public abstract double getDistance(int[] location1, int[] location2);
    public abstract void createOrderQueue();
    public abstract Order getNextOrder();
}
