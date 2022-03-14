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
        assertEquals(dbS.getQueueSize(), 100);
    }

    @Test
    public void testFirstOrderInOrderQueueIsCloserThanSecond() throws FileFormatException, IOException {
        OrderList ol = getOl();
        DistanceBasedStrategy dbS = new DistanceBasedStrategy(ol);
        AddressConverter ac = new AddressConverter();
        dbS.createOrderQueue();
        int [] truck_coords = {5,5};
        int [] address1 = ac.convert(dbS.getOrderQueue().poll().getOrderAddress());
        int [] address2 = ac.convert(dbS.getOrderQueue().poll().getOrderAddress());
        double testdistance1 = dbS.getDistance(truck_coords, address1);
        double testdistance2 = dbS.getDistance(truck_coords, address2);

        assertTrue(testdistance1<testdistance2);

    }


}