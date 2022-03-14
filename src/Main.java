import java.io.IOException;

public class Main {

    public static void main(String[] args) throws FileFormatException, IOException {
        OrderList ol = new OrderList("orders.txt");
//        ol.generateOrders(100);

        Window simWindow = new Window();

        Truck truck = new Truck(simWindow, new DistanceBasedStrategy(ol), new StandardNavigationStrategy());

        truck.start();
    }
}
