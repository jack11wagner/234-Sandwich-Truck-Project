/*
Author: Jackson Wagner
This Class represents the strategy for creating the Order Queue based on
the earliest time of an Order

Edits by:
*/
import java.util.*;

public class TimeBasedStrategy implements OrderStrategy{
    /**
     * This class implements the Strategy Design Pattern
     */
    private PriorityQueue<Order> orderQueue;
    private ArrayList<Order> orderList;
    private OrderList orderListObj;

    public TimeBasedStrategy(OrderList orderList){
        /**
         * PRIMARY CONSTRUCTOR
         * Constructor for the TimeBasedStrategy
         * Strategies are used to determine the order the truck will use for delivery
         * The orderQueue is based on comparison of Time of Order placement
         */
        orderQueue = new PriorityQueue<>(new TimeComparator());
        this.orderList = orderList.getOrderList();
        orderListObj = orderList;
    }

    public TimeBasedStrategy() {
        /**
         * Constructor for the TimeBasedStrategy
         * Strategies are used to determine the order the truck will use for delivery
         * The orderQueue is based on comparison of Time of Order placement
         */
        orderQueue = new PriorityQueue<>(new TimeComparator());
    }

    public void setOrderList(OrderList orderList){
        /**
         * Used when using the alternate constructor
         */
        this.orderList = orderList.getOrderList();
        orderListObj = orderList;
    }

    public PriorityQueue<Order> getOrderQueue()
    {
        /**
         * Returns the orderQueue as a PriorityQueue
         */
        return orderQueue;
    }

    @Override
    public void createOrderQueue() {
        /**
         * Adds all orders to the orderqueue this method indirectly will use the TimeComparator Class since
         * the order Queue will need a way to sort a PriorityQueue.
         * This will have each order in the Queue Ascending from earliest to latest times
         * Ex: 1. 2022-01-01 11:12:43.0, 730 3 St., Italian Sandwich
         *     2. 2022-01-01 11:24:58.0, 1020 6 St., Italian Sandwich
         *     3. 2022-01-01 11:38:37.0, 360 3 St., Italian Sandwich

         */
        orderQueue.addAll(orderList);
        if (SimSettings.counter == 1) {
            SimSettings.staticTimeOrderQueue.addAll(orderQueue);
        }
        SimSettings.counter +=1;
    }

    @Override
    public Order getNextOrder() {
        /**
         * Returns and removes the next Order in the Queue
         */
        return orderQueue.poll();
    }

    @Override
    public int getNumberOfRemainingOrders()
    {
        /**
         * Returns the number of Orders left in the Queue
         */
        return this.orderQueue.size();
    }

    @Override
    public OrderList getOrderListCopy() {
        /**
         * Returns a copy of the OrderList
         */
        return new OrderList(orderListObj);
    }


    @Override
    public boolean queueIsEmpty() {
        /**
         * Makes sure the Queue is not empty
         */
        return getNumberOfRemainingOrders()==0;
    }

    public String toString(){
        return "TimeBasedStrategy";
    }

}
