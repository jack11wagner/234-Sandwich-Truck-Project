public class Truck {
    private int x;
    private int y;
    private Window window;

    Truck(Window window) {
        this.window = window;
    }

    private void move() {
        window.repaintTruck(x, y);
    }
}
