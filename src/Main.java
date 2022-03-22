import java.io.IOException;

public class Main {

    public static void main(String[] args) throws FileFormatException, IOException {
        OrderList ol = new OrderList();
        ol.generateOrders(SimSettings.NUMBER_OF_ORDERS);

        Window simWindow = new Window();

        //Truck truck = new Truck(simWindow, new DistanceBasedStrategy(ol), new StandardNavigationStrategy());
        Truck truck = new Truck(simWindow, new DistanceBasedStrategy(ol), new RightTurnNavigationStrategy2());

        truck.start();

    }
}
