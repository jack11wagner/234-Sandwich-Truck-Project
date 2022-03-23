import java.util.ArrayList;
import java.util.Collection;

/**
Author: Nikolas Kovacs
This class serves as the Truck in the simulator and contains the functionality of the sandwich truck as well
as access to the window to update its position

Edits by: Michael Shimer (added splitOrder method)
 */

public class Truck {
    private int x = SimSettings.INITIAL_TRUCK_X;
    private int y = SimSettings.INITIAL_TRUCK_Y;
    private int direction = -1;
    private final Window window;
    private final AddressConverter addConverter = new AddressConverter();
    private final OrderStrategy orderStrat;
    private final NavigationStrategy navStrat;

    Truck(Window window, OrderStrategy orderStrat, NavigationStrategy navStrat) {
        this.window = window;
        this.orderStrat = orderStrat;
        this.orderStrat.createOrderQueue();

        this.navStrat = navStrat;
    }

    public void start() {
        /**
         * This method is to be called when the start button is pressed in the window
         * This method goes through the general process of a Truck and the steps it must complete to move to each order
         * This method gets the nextOrder, adds all the Order destinations to the map, and creates the navigation instructions
         * for the Truck to move around
         */
        ArrayList<int[]> orderDestinationsInOrder = new ArrayList<>();
        Order nextOrder;
        String address;
        //String foodOrder; // food order commented out until future

        // populate orderDestinationsInOrder list according to the OrderStrategy
        while ((nextOrder = orderStrat.getNextOrder()) != null) { // fetch next order, null means empty
            String[] splittedOrder = splitOrder(nextOrder.toString()); // split the order into the address and food order
            address = splittedOrder[0];
            //foodOrder = splittedOrder[1]; // food order commented out until future
            orderDestinationsInOrder.add(addConverter.convert(address)); // convert the address into a pair of coordinates and append
        }
        addPinsToMap(orderDestinationsInOrder);
        Collection<int[]> navInstructions = navStrat.calculateNavInstructions(direction, getTruckLocation(), orderDestinationsInOrder);
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
        for (int[] instruction : navInstructions) {
            if (instruction[1] == -1) {
                window.removePin();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                continue;
            }
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
         * :return: array of coordinates
         */
        return new int[]{x,y};
    }

    private String[] splitOrder(String order) {
        /**
         * spilts the order into individual strings for the address and food order, given in the whole order string,
         * so that the address and food order can be used separately
         * @param: String order - the whole string order that contains the time, address, and food order
         * @returns: an array of two strings corresponding to the address string and the food order string
         */
        String[] splitOrderArray;
        String address;
        String foodOrder;
        splitOrderArray = order.split(",");
        address =  splitOrderArray[1];
        foodOrder = splitOrderArray[2];
        return new String[]{address, foodOrder};
    }


}
