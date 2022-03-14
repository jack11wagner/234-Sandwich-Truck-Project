/**
Made by: Jackson Wagner
Abstract class which handles strategies that the truck should select when
making deliveries
Edits by:
*/
public abstract class OrderStrategy {
    public abstract void createOrderQueue();
    public abstract Order getNextOrder();
    public abstract boolean queueIsEmpty();
}
