/**
Made by: Jackson Wagner
Abstract class which handles strategies that the truck should select when
making deliveries
Edits by:
*/
public interface OrderStrategy {
    public void createOrderQueue();
    public Order getNextOrder();
    public abstract boolean queueIsEmpty();
    public int getNumberOfRemainingOrders();
}
