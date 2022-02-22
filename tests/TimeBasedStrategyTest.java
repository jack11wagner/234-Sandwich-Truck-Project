import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

class TimeBasedStrategyTest {

    @Test
    public void testCreatingOrderQueueIsInAscendingOrder() throws IOException {
        OrderList ol = new OrderList("orders.txt");
        TimeBasedStrategy tbs = new TimeBasedStrategy(ol);
        tbs.createOrderQueue();
        Timestamp testOrder1Timestamp = tbs.getNextOrder().getOrderTimestamp();
        Timestamp testOrder2Timestamp = tbs.getNextOrder().getOrderTimestamp();
        assertTrue(testOrder1Timestamp.compareTo(testOrder2Timestamp)<0);

    }
    @Test
    public void testWhenGettingOrderNextOrderFromOrderQueueOrderQueueSizeIsDecreased() throws IOException {
        OrderList ol = new OrderList("orders.txt");
        TimeBasedStrategy tbs = new TimeBasedStrategy(ol);
        tbs.createOrderQueue();
        tbs.getNextOrder();
        assertEquals(99, tbs.getOrderQueue().size());

    }



}