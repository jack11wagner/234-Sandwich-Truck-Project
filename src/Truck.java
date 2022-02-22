public class Truck {
    private int x;
    private int y;
    private Window window;
    private OrderList orderList;
    private AddressConverter addConverter = new AddressConverter();

    Truck(Window window, OrderList orderList) {
        this.window = window;
        this.orderList = orderList;
    }

    private void move() {
        window.repaintTruck(x, y);
    }

    public int[] getTruckLocation()
    {
        return new int[]{x,y};
    }


    // splits original order string on the commas into three seperate strings: orderDate, address, and foodOrder
    /**
     * spilts the order into individual strings for the address and food order, given in the whole order string,
     * so that the address and food order can be used seperatley
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
