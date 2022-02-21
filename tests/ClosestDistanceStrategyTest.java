import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ClosestDistanceStrategyTest {

    public static final int[] TRUCK_ORIGIN_COORDS = new int[]{5,5};

    private Order makeOrder(String s) {
        return new Order("2022-01-01 15:50:08.0", s, "Sandwich");
    }

    private double getDistance(AddressConverter ac, ClosestDistanceStrategy cds, Order testOrder1, int[] truckOriginCoords) {
        return cds.getDistance(truckOriginCoords, ac.convert(testOrder1.getOrderAddress()));
    }

    private ClosestDistanceStrategy getCds(OrderList ol) {
        return new ClosestDistanceStrategy(new Truck(new Window(), ol), ol);
    }

    @Test
    public void testGetDistance()
    {
        OrderList ol = new OrderList();
        ClosestDistanceStrategy cds = getCds(ol);
        assertEquals(2, cds.getDistance(new int[] {5,5}, new int[] {6,6}));

    }

    @Test
    public void testAddingOneOrderToQueueUpdatesOrderQueue()
    {
        OrderList ol = new OrderList();
        AddressConverter ac = new AddressConverter();
        Order testOrder = makeOrder("110 9 St.");
        ol.addOrder(testOrder);
        ClosestDistanceStrategy cds = getCds(ol);
        cds.createOrderQueue();
        assertEquals(cds.getNextOrder(), testOrder);

    }


    @Test
    public void testAddingTwoOrdersToQueueResultsInProperQueueOrder()
    {
        OrderList ol = new OrderList();
        AddressConverter ac = new AddressConverter();
        ClosestDistanceStrategy cds = getCds(ol);
        Order testOrder1 = makeOrder("150 A St.");
        Order testOrder2 = makeOrder("280 B St.");
        ol.addOrder(testOrder1);
        ol.addOrder(testOrder2);
        double truckToTest1Distance = getDistance(ac, cds, testOrder1, TRUCK_ORIGIN_COORDS);
        double truckToTest2Distance = getDistance(ac, cds, testOrder2, TRUCK_ORIGIN_COORDS);
        assertTrue(truckToTest2Distance<truckToTest1Distance);
        cds.createOrderQueue();
        assertEquals(cds.getNextOrder(), testOrder2);
        assertEquals(cds.getNextOrder(), testOrder1);

    }

    @Test
    public void testGetNextOrderRemovesOrderFromQueue()
    {
        OrderList ol = new OrderList();
        AddressConverter ac = new AddressConverter();
        ClosestDistanceStrategy cds = getCds(ol);
        Order testOrder = makeOrder("120 J St.");
        ol.addOrder(testOrder);
        cds.createOrderQueue();
        assertEquals(cds.getNextOrder(),testOrder);
        assertEquals(0, cds.getQueueSize());
        assertNull(cds.getNextOrder()); // There should be nothing left in the Queue since we removed the only order in it
    }

    @Test
    public void testGettingOrderQueueFromListOfOrders() throws IOException {
        OrderList ol = new OrderList("orders.txt");
        ClosestDistanceStrategy cds = getCds(ol);
        cds.createOrderQueue();
        assertEquals(cds.getQueueSize(), 100);
    }




}