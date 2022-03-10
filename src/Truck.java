/**
Author: Nikolas Kovacs
This class serves as the Truck in the simulator and contains the functionality of the sandwich truck as well
as access to the window to update its position

Edits by: Michael Shimer (added splitOrder method)
 */

public class Truck {
    private int x = SimSettings.INITIAL_TRUCK_X;
    private int y = SimSettings.INITIAL_TRUCK_Y;
    private final Window window;
    private final AddressConverter addConverter = new AddressConverter();
    private final OrderStrategy orderStrat;
    private final NavigationStrategy navStrat;

    Truck(Window window, OrderStrategy orderStrat, NavigationStrategy navStrat) {
        this.window = window;
        this.orderStrat = orderStrat;
        this.navStrat = navStrat;
    }

    public void start() {

    }

    private void move() {
        /**
         * Calls the repaintTruck method from the window class with the current or new
         * truck x, y coordinates to move the truck
         */
        window.repaintTruck(x, y);
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
