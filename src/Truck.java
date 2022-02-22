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
