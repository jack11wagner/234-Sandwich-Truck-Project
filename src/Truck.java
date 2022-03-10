/**
Author: Nikolas Kovacs
This class serves as the Truck in the simulator and contains the functionality of the sandwich truck as well
as access to the window to update its position

Edits by: Michael Shimer (added splitOrder method)
 */

public class Truck {
    private int x;
    private int y;
    private final Window window;
    private final AddressConverter addConverter = new AddressConverter();
    private final OrderStrategy orderStrat;
    private final NavigationStrategy navStrat;

    Truck(Window window, OrderStrategy orderStrat, NavigationStrategy navStrat) {
        this.window = window;
        this.orderStrat = orderStrat;
        this.navStrat = navStrat;
    }

    private void move() {
        window.repaintTruck(x, y);
    }

    private int[] getTruckLocation()
    {
        return new int[]{x,y};
    }


    /**
     * spilts the order into individual strings for the address and food order, given in the whole order string,
     * so that the address and food order can be used separately
     * @param: String order - the whole string order that contains the time, address, and food order
     * @returns: an array of two strings corresponding to the address string and the food order string
     */
    private String[] splitOrder(String order) {
        String[] splitOrderArray;
        String address;
        String foodOrder;
        splitOrderArray = order.split(",");
        address =  splitOrderArray[1];
        foodOrder = splitOrderArray[2];
        return new String[]{address, foodOrder};
    }

}
