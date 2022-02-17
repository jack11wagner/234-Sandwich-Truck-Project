public class Truck {
    private int x;
    private int y;
    private Window window;
    private OrderList orderList;

    Truck(Window window, OrderList orderList) {
        this.window = window;
        this.orderList = orderList;
    }

    private void move() {
        window.repaintTruck(x, y);
    }
}
