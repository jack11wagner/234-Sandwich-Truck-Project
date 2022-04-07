import java.util.ArrayList;

/**
Author: Nikolas Kovacs
Any project-wide variables are stored here
 */
public class SimSettings {
    public static final int DIMENSION = 700;
    public static final int NUM_ROADS = 10;
    public static final int ROAD_WIDTH = DIMENSION / 35;
    public static final int ROAD_SPACING = DIMENSION/(NUM_ROADS - 1) - ROAD_WIDTH / (NUM_ROADS + 1);
    public static final int TRUCK_SIZE = DIMENSION / 17;
    public static final int TRUCK_SPEED = 1; // unit = pixels per cycle (defined below)
    public static final int SIM_SPEED = 10; // unit = milliseconds: how often the cycle repeats (defined below)
    public static final int PIN_SIZE = DIMENSION / 25;
    public static final int NUMBER_OF_ORDERS = 100;
    public static final int NUM_HOUSES_PER_BLOCK = 10;
    public static final int DELIVERY_TIME = 2000;
    public static final String SERVICE_CENTER = "550 5 St.";

    private static final int[] initial_truck_xy = new AddressConverter().convert(SERVICE_CENTER);
    public static final int INITIAL_TRUCK_X = initial_truck_xy[0];
    public static final int INITIAL_TRUCK_Y = initial_truck_xy[1];
    //public static Customer[] customerList;

    // list of customers that are observing the truck and waiting for an order to be delivered
    public static OrderList orderList;
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
}
