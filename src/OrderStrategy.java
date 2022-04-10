import java.util.PriorityQueue;
import java.util.Queue;


/**
Made by: Jackson Wagner
Abstract class which handles strategies that the truck should select when
making deliveries
Edits by:
*/
public interface OrderStrategy {
    public void createOrderQueue();
    public Queue getOrderQueue();
    public Order getNextOrder();
    public boolean queueIsEmpty();
    public int getNumberOfRemainingOrders();
    public OrderList getOrderListCopy();
    public void setOrderList(OrderList orderList);
}
