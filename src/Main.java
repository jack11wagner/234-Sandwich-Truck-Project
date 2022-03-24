import java.io.IOException;

public class Main {

    public static void main(String[] args) throws FileFormatException, IOException {
        OrderList ol = new OrderList("orders.txt");
//        ol.generateOrders(SimSettings.NUMBER_OF_ORDERS);

        Window simWindow = new Window();

        Truck truck = new Truck(simWindow, new DistanceBasedStrategy(ol), new StandardNavigationStrategy());

        truck.start();

    }
}
