/*
Author: Jackson Wagner
This Class stores represents the strategy for creating the Order Queue based on
the next closest order tot the truck

Edits by:
*/
import java.util.*;

public class DistanceBasedStrategy implements OrderStrategy{
    private Queue<Order> orderQueue;
    private ArrayList<Order> orderList;
    private int simTruckX;
    private int simTruckY;


    public DistanceBasedStrategy(OrderList orderList) {
        /**
         * Constructor for the DistanceBasedStrategy
         * Strategies are used to determine the order the truck will use for delivery
         * The orderQueue is based on the original truck location, and order is based on least distance to
         * the truck after it has moved
         */

        this.orderQueue = new LinkedList<>();
        this.orderList = orderList.getOrderList();
        simTruckX = SimSettings.INITIAL_TRUCK_X;
        simTruckY = SimSettings.INITIAL_TRUCK_Y;

    }

    private Order getClosestOrder()
    {
        /**
         * Calculates the closest Order address to the truck in the orderList
         * Makes sure the Order is not currently in the OrderQueue
         * Dummy TruckCoords are updated each time this method is called in order to keep track of where the
         * truck would be on the route and recalculate the next closest order
         *
         *
         */
        AddressConverter ac = new AddressConverter();
        int[] truck_coords = {simTruckX, simTruckY};

        double minDist = Integer.MAX_VALUE;
        Order minOrder =orderList.get(0);
        for(Order o: orderList){
            double dist = getDistance(truck_coords, ac.convert(o.getOrderAddress()));
            if (dist<minDist && !orderQueue.contains(o)) {
                minDist = dist;
                minOrder = o;
            }
        }
        simTruckX = ac.convert(minOrder.getOrderAddress())[0];
        simTruckY = ac.convert(minOrder.getOrderAddress())[1];
        return minOrder;

    }

    public Queue<Order> getOrderQueue()
    {
        /**
         * Method that returns the entire orderQueue
         * Mainly to help with ease of testing
         * @returns: Queue object which represents the entire Queue data structure
         */
        return orderQueue;
    }

    public void createOrderQueue()
    {
        /**
         * Generates the orderQueue which is done by going through all orders and continuously calling getClosestOrder
         *
         */
        for(int i=0 ;i< orderList.size();i++){
            Order closest = getClosestOrder();
            orderQueue.add(closest);
        }
    }

    public Order getNextOrder()
    {
        /**
         * Simply returns and removes the first Order in the orderQueue
         * @returns: Order object which represents the closest order to the truck at the moment
         */
        return orderQueue.poll();
    }

    public double getDistance(int[] location1, int[] location2) {
        /**
         * Returns the distance between two coordinates from an Order address
         */
        int x1, y1, x2, y2;
        x1 = location1[0];
        y1 = location1[1];
        x2 = location2[0];
        y2 = location2[1];

        return (Math.abs(x1 - x2) + Math.abs(y1 - y2));
    }

    @Override
    public int getNumberOfRemainingOrders()
    {
        /**
         * Gets the number of remaining Orders in the OrderQueue
         * If getNextOrder() has not been called this should be the size of the original orderlist
         */
        return this.orderQueue.size();
    }
    @Override
    public boolean queueIsEmpty() {
        /**
         * Function to get the status of the orderQueue
         */
        return getNumberOfRemainingOrders()==0;
    }
}