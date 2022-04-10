import java.io.IOException;

public class Main {

    public static void main(String[] args) throws FileFormatException, IOException {
        OrderList ol = new OrderList();

        ol.generateOrders(SimSettings.NUMBER_OF_ORDERS);
        SimSettings.orderList = ol;

        Window simWindow = new Window();
        // checks that start button and strategies are selected
        while (!simWindow.isReady() || !simWindow.isStrategySelected()) sleep();
        Truck truck = new Truck(simWindow, simWindow.getOrderStrategy(), simWindow.getNavigationStrategy());
        truck.start();
    }

    private static void sleep() {
        try
        {
            Thread.sleep(100);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }
}
