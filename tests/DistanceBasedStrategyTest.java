import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class DistanceBasedStrategyTest {


    private OrderList getOl() throws IOException, FileFormatException {
        return new OrderList("orders.txt");
    }

    @Test
    public void testInstatiatingDistanceBasedCreatesOrderQueue() throws FileFormatException, IOException {
        OrderList ol = getOl();
        DistanceBasedStrategy dbS = new DistanceBasedStrategy(ol);
        dbS.createOrderQueue();
        assertEquals(dbS.getNumberOfRemainingOrders(), 100);
    }

    @Test
    public void testFirstOrderInOrderQueueIsCloserThanSecond() throws FileFormatException, IOException {
        OrderList ol = getOl();
        DistanceBasedStrategy dbS = new DistanceBasedStrategy(ol);
        AddressConverter ac = new AddressConverter();
        dbS.createOrderQueue();
        int [] truck_coords = {SimSettings.INITIAL_TRUCK_X, SimSettings.INITIAL_TRUCK_Y};
        int [] address1 = ac.convert(dbS.getNextOrder().getOrderAddress());
        int [] address2 = ac.convert(dbS.getNextOrder().getOrderAddress());
        double testdistance1 = dbS.getDistance(truck_coords, address1);
        double testdistance2 = dbS.getDistance(truck_coords, address2);
        System.out.println(testdistance1+" "+ testdistance2);

        assertTrue(testdistance1<testdistance2);

    }

    @Test
    public void test100CallsToGetNextOrderMeansOrderQueueIsEmpty() throws FileFormatException, IOException {
        OrderList ol = getOl();
        DistanceBasedStrategy dbS = new DistanceBasedStrategy(ol);
        AddressConverter ac = new AddressConverter();
        dbS.createOrderQueue();
        for(int i = 0; i<ol.getNumberOfOrders(); i++) dbS.getNextOrder();
        assertEquals(0,dbS.getNumberOfRemainingOrders());
        assertTrue(dbS.queueIsEmpty());
    }



}