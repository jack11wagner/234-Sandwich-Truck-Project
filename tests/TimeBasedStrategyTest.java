import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

class TimeBasedStrategyTest {

    @Test
    public void testCreatingOrderQueueIsInAscendingOrder() throws IOException, FileFormatException {
        OrderList ol = new OrderList("orders.txt");
        TimeBasedStrategy tbs = new TimeBasedStrategy(ol);
        tbs.createOrderQueue();
        Timestamp testOrder1Timestamp = tbs.getNextOrder().getOrderTimestamp();
        for(Order orderToCompare: tbs.getOrderQueue())
            assertFalse(testOrder1Timestamp.compareTo(orderToCompare.getOrderTimestamp()) >= 0);

    }
    @Test
    public void testWhenGettingNextOrderFromOrderQueueOrderQueueSizeIsDecreased() throws IOException, FileFormatException {
        OrderList ol = new OrderList("orders.txt");
        TimeBasedStrategy tbs = new TimeBasedStrategy(ol);
        tbs.createOrderQueue();
        tbs.getNextOrder();
        assertEquals(99, tbs.getOrderQueue().size());

    }



}