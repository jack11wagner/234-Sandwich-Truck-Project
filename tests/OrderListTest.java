import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class OrderListTest {
    @Test
    public void testOrderListIsFilledWhenReadingFromValidFile() throws IOException {
        OrderList ol = new OrderList("orders.txt");
        assertEquals(100, ol.getNumberOfOrders());
    }

    @Test
    public void testOrderListConstructorThrowsFileNotFoundForInvalidFile()
    {
        assertThrows(FileNotFoundException.class, ()-> new OrderList("no-file.txt"));
    }



}