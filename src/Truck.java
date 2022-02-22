public class Truck {
    private int x;
    private int y;
    private Window window;
    private AddressConverter addConverter = new AddressConverter();
    private Strategy strat;

    Truck(Window window, Strategy strat) {
        this.window = window;
//        this.orderList = orderList;
        this.strat = strat;
    }

    private void move() {
        window.repaintTruck(x, y);
    }

    public int[] getTruckLocation()
    {
        return new int[]{x,y};
    }

}
