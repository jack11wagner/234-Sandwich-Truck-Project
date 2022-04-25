import java.util.HashMap;

public class Customer implements Observer {

    private String currentTruckAddress;
    private String customerName;
    private int xTruckCoord;
    private int yTruckCoord;
    private AddressConverter converter;
    public HashMap<int[], String> letterAddresses;
    public HashMap<int[], String> numberAddresses;
    private Order order;


    /**
     * constructor for the customer class which assigns the customer name, and instantiates
     * the necessary variables and objects for the customer object
     * @param: a string noting the customers name
     */
    public Customer(String name) {
        this.customerName = name;
        this.converter = new AddressConverter();
        this.letterAddresses = new HashMap<>();
        this.numberAddresses = new HashMap<>();
        fillLetterAddresses();
        fillNumberAddresses();
        this.order = null;
    }

    /**
     * method which takes assigns an order to a customer
     * @param: an order object which corresponds to the order of the customer
     */
    public void registerOrder(Order order)
    {
        this.order = order;
    }


    public String getCurrentTruckAddress() {
        return currentTruckAddress;

    }

    /**
     * method updates the customer everytime the truck moves with the new coordinates of the truck
     * @param: an array of two integers, the first being the trucks x coordinate and the second the y
     * coordinate
     */
    @Override
    public void update(int[] truckCoordinates) {
        xTruckCoord = truckCoordinates[0];
        yTruckCoord = truckCoordinates[1];
        currentTruckAddress = findCurrentTruckAddress();

    }


    /**
     * helper method which uses the x and y coordinates of the truck and determines the
     * address the truck is at as it moves about the map
     * @returns: a string which corresponds to the current address of the truck
     */
    private String findCurrentTruckAddress() {

        for (int[] coordinate : letterAddresses.keySet()) {
            if (isNearAddress(coordinate)) {
                return letterAddresses.get(coordinate);
            }
        }
        for (int[] coordinate : numberAddresses.keySet()) {
            if (isNearAddress(coordinate)) {
                return numberAddresses.get(coordinate);
            }
        }
            return currentTruckAddress;
    }


    /**
     * helper method which fills a hash map of all the coordinates of letter street addresses
     * this is used to determine if the truck is near one of the addresses to update its
     * address location to the customer
     */
    private void fillLetterAddresses() {
        char street = 'A';
        int streetNum = 0;
        String address;
        int[] coords;
        while (street <= 'J') {
            for (streetNum = 100; streetNum <= 990; streetNum+=10) {
                address = streetNum + " " + street + " St.";
                coords = converter.convert(address);
                letterAddresses.put(coords, address);
            }
            street += 1;
        }
    }

    /**
     * helper method which fills a hash map of all the coordinates of letter street addresses
     * this is used to determine if the truck is near one of the addresses to update its
     * address location to the customer
     */
    private void fillNumberAddresses() {
        int street = 1;
        int streetNum = 0;
        String address;
        int[] coords;
        while (street <= 10) {
            for (streetNum = 100; streetNum <= 990; streetNum+=10) {
                address = streetNum + " " + street + " St.";
                coords = converter.convert(address);
                numberAddresses.put(coords, address);
            }
            street += 1;
        }
    }

    /**
     * helper method which determines is the truck is close to one of the addresses on the truck map
     * @param: a collection of integers referring to the coordinates of a street address
     * @returns: a boolean value which corresponds to whether or not the truck is near an address
     */
    private boolean isNearAddress(int[] coordinate) {
        int range = 5;
        if ( ((xTruckCoord >= coordinate[0] - range) & (xTruckCoord <= coordinate[0] + range))
                & ((yTruckCoord >= coordinate[1] - range) & (yTruckCoord <= coordinate[1] + range))) {
            return true;
        }
        else {
            return false;
        }
    }
}
