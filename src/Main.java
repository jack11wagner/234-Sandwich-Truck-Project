
public class Main {

    public static void main(String[] args) {
        OrderList ol = new OrderList();
        ol.generateOrders(100);

        Window simWindow = new Window();

        Truck truck = new Truck(simWindow, new TimeBasedStrategy(ol), new StandardNavigationStrategy());

        truck.start();
    }
}
