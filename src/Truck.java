import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/**
Author: Nikolas Kovacs
This class serves as the Truck in the simulator and contains the functionality of the sandwich truck as well
as access to the window to update its position

Edits by: Michael Shimer (added splitOrder method)
 */

public class Truck implements Subject{
    private int x = SimSettings.INITIAL_TRUCK_X;
    private int y = SimSettings.INITIAL_TRUCK_Y;
    private int direction = -1;
    private final Window window;
    private final AddressConverter addConverter = new AddressConverter();
    private final OrderStrategy orderStrat;
    private NavigationStrategy navStrat;
    private List orderQueueCopy;

    public Truck(Window window, OrderStrategy orderStrat, NavigationStrategy navStrat) {
        /**
         * Truck constructor which initializes the instance variables based on the Main instantiation
         * Also sets the navigation strategy as well
         */
        this.window = window;
        this.orderStrat = orderStrat;
        this.orderStrat.createOrderQueue();
        orderQueueCopy =  orderStrat.getOrderQueue().stream().toList();

        this.navStrat = navStrat;
    }

    public Truck(OrderStrategy orderStrat, NavigationStrategy navStrat) {
        /**
         * This constructor should be used CAREFULLY. It is used strictly for calculating distances
         * NOT meant for the actual simulation
         */
        this.window = null;
        this.orderStrat = orderStrat;
        this.orderStrat.createOrderQueue();
        this.navStrat = navStrat;
    }

    private void setupQuickestTime() {
        /**
         * This method sets the quickest time for the truck to complete the order
         */
        DistanceComparer distanceComparer = new DistanceComparer(orderStrat.getOrderListCopy());
        AbstractMap<String, Integer> times = distanceComparer.getStrategyTimes();
        // iterate over the times and find the quickest time
        int quickestTime = Integer.MAX_VALUE;
        String quickestKey = "";
        for (String key : times.keySet()) {
            int time = times.get(key);
            if (time < quickestTime) {
                quickestTime = time;
                quickestKey = key;
            }
        }
        String[] indexes = quickestKey.split(" ");
        int navKey = Integer.parseInt(indexes[1]);
        int orderKey = Integer.parseInt(indexes[0]);

        window.setShortestDistanceText("Quickest - " + SimSettings.allOrderStrategies[orderKey].toString() + ", " +
                SimSettings.allNavStrategies[navKey].toString() + ": " + quickestTime + " miles");
    }

    public void start() {
        /**
         * This method is to be called when the start button is pressed in the window
         * This method goes through the general process of a Truck and the steps it must complete to move to each order
         * This method gets the nextOrder, adds all the Order destinations to the map, and creates the navigation instructions
         * for the Truck to move around
         */
        setupQuickestTime();
        ArrayList<int[]> orderDestinationsInOrder = new ArrayList<>();
        Order nextOrder;
        String address;


        // populate orderDestinationsInOrder list according to the OrderStrategy
        while ((nextOrder = orderStrat.getNextOrder()) != null) {// fetch next order, null means empty
            String[] splittedOrder = splitOrder(nextOrder.toString()); // split the order into the address and food order
            address = splittedOrder[0];

            orderDestinationsInOrder.add(addConverter.convert(address)); // convert the address into a pair of coordinates and append
        }
        addPinsToMap(orderDestinationsInOrder);
        Collection<int[]> navInstructions = navStrat.calculateNavInstructions(direction, getTruckLocation(), orderDestinationsInOrder);
        window.repaintTruck(x,y);
        try
        {
            Thread.sleep(750);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
        move(navInstructions);
    }

    private void addPinsToMap(ArrayList<int[]> orderDestinationsInOrder) {
        /**
         * Helper function for adding all the pins to the Truck Map
         */

        for (int[] orderCoords : orderDestinationsInOrder) {

            int newX = orderCoords[0];
            int newY = orderCoords[1];
            window.addNewPinToMap(newX, newY);
        }
    }

    private void move(Collection<int[]> navInstructions) {
        /**
         * Calls the repaintTruck method repreatedly from the window class with the current or new
         * truck x, y coordinates to move the truck
         */
        int currOrderIndex = 0;
        Order currOrder;
        for (int[] instruction : navInstructions) {
            notifyCustomers();
            if (instruction[1] == -1) {
                // modeling goes on here
                currOrder = (Order) orderQueueCopy.get(currOrderIndex);
                System.out.print(currOrder.getOrderTimestamp() + " : ");
                currOrder.getSandwichObject().prepare();
                currOrderIndex++;
                window.triggerSandwichModelingPanel(currOrder);

                window.setDeliveryText("Delivering Order");
                System.out.println("Delivered Order " + currOrderIndex + "/" +orderQueueCopy.size());
                if (currOrderIndex == orderQueueCopy.size()){
                    System.out.println("No more orders...");
                }
                window.repaintTruck(x, y);
                SimSettings.pauseAtDestination();
                window.removePin();
                continue;
            }

            window.setDeliveryText("");
            direction = instruction[0]; // 0 - right, 1 - down, 2 - left, 3 - up
            int distance = instruction[1];

            // how many steps the truck has to take to reach dest (or partial dest),
            int distance_steps = distance / SimSettings.TRUCK_SPEED;

            for (int i = 0; i < Math.abs(distance_steps); i++) {
                if (direction == 0) { // right
                    x += SimSettings.TRUCK_SPEED;
                } else if (direction == 2) { // left
                    x -= SimSettings.TRUCK_SPEED;
                } else if (direction == 1) { // down
                    y += SimSettings.TRUCK_SPEED;
                } else { // up
                    y -= SimSettings.TRUCK_SPEED;
                }
                window.repaintTruck(x, y);
                SimSettings.cycle();
            }
        }
    }

    private int[] getTruckLocation() {
        /**
         * Gets the current location coordinates of the truck
         * @return: array of coordinates
         */
        return new int[]{x,y};
    }

    private String[] splitOrder(String order) {
        /**
         * spilts the order into individual strings for the address and food order, given in the whole order string,
         * so that the address and food order can be used separately
         * @param: String order - the whole string order that contains the time, address, and food order
         * @return: an array of two strings corresponding to the address string and the food order string
         */
        String[] splitOrderArray;
        String address;
        String foodOrder;
        splitOrderArray = order.split(",");
        address =  splitOrderArray[1];
        foodOrder = splitOrderArray[2];
        return new String[]{address, foodOrder};
    }


    @Override
    public void notifyCustomers() {
        for (Customer customer : SimSettings.customerList) {
            customer.update(new int[] {x, y});
        }
    }

    @Override
    public void removeCustomer(Customer customer) {
        SimSettings.customerList.remove(customer);
    }

    @Override
    public void registerCustomer(Customer customer) {
        SimSettings.customerList.add(customer);
    }
}
