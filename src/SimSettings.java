import java.sql.Timestamp;
import java.util.*;
import java.util.ArrayList;
import java.util.Queue;


/**
Author: Nikolas Kovacs
Any project-wide variables are stored here
 */
public class SimSettings {
    public static int counter = 0;
    public static final int DIMENSION = 700;
    public static final int NUM_ROADS = 10;
    public static final int ROAD_WIDTH = DIMENSION / 35;
    public static final int ROAD_SPACING = DIMENSION/(NUM_ROADS - 1) - ROAD_WIDTH / (NUM_ROADS + 1);
    public static final int TRUCK_SIZE = DIMENSION / 17;
    public static final int TRUCK_SPEED = 1; // unit = pixels per cycle (defined below)
    public static final int SIM_SPEED = 10; // unit = milliseconds: how often the cycle repeats (defined below)
    public static final int PIN_SIZE = DIMENSION / 25;
    public static final int NUMBER_OF_ORDERS = 5;
    public static final int NUM_HOUSES_PER_BLOCK = 10;
    public static final int DELIVERY_TIME = 2000;
    public static final String SERVICE_CENTER = "550 5 St.";
    public static final ArrayList<Order> newOrders = new ArrayList<>();

    private static final int[] initial_truck_xy = new AddressConverter().convert(SERVICE_CENTER);
    public static final int INITIAL_TRUCK_X = initial_truck_xy[0];
    public static final int INITIAL_TRUCK_Y = initial_truck_xy[1];
    //public static Customer[] customerList;
    public static final OrderStrategy[] allOrderStrategies = new OrderStrategy[]{new TimeBasedStrategy(), new DistanceBasedStrategy()};
    public static final NavigationStrategy[] allNavStrategies = new NavigationStrategy[]{new StandardNavigationStrategy(), new RightTurnNavigationStrategy()};


    // list of customers that are observing the truck and waiting for an order to be delivered
    public static OrderList orderList;
    public static ArrayList<Order> timeOrderQueue = new ArrayList<>();
    public static PriorityQueue<Order> staticTimeOrderQueue = new PriorityQueue<>(new TimeComparator());
    public static Queue<Order> distanceOrderQueue = new LinkedList<>();
    public static ArrayList<Customer> customerList = new ArrayList<>();

    // Pricing for Menu
    public static final double HAM_SANDWICH_COST = 4.50;
    public static final double ITALIAN_SANDWICH_COST = 5.00;
    public static final double PASTRAMI_SANDWICH_COST = 4.75;
    public static final double MAYO_CONDIMENT_COST = .25;
    public static final double MUSTARD_CONDIMENT_COST = .35;
    public static final double LETTUCE_CONDIMENT_COST = .50;
    public static final double BACON_CONDIMENT_COST = .95;
    public static final double TOMATO_CONDIMENT_COST = .55;
    public static final double WHITE_BREAD_CONDIMENT_COST = 1.00;
    public static final double WHOLE_WHEAT_BREAD_CONDIMENT_COST = 1.00;


    public static void cycle() {
        try
        {
            Thread.sleep(SIM_SPEED);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }

    public static void pauseAtDestination() {
        try
        {
            Thread.sleep(DELIVERY_TIME);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }

    public static void removeDuplicates(PriorityQueue<Order> queue) {
        HashSet<Timestamp> orders = new HashSet<>();
        for (Order order : queue) {
            if (orders.contains(order.getOrderTimestamp())) {
                queue.remove(order);
            }
            else {
                orders.add(order.getOrderTimestamp());
            }
        }
    }

}
