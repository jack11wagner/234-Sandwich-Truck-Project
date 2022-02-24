/*
Made by: Jackson Wagner
Testing for OrderList Class
Edits by:
*/

import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class OrderListTest {
    @Test
    public void testOrderListIsFilledWhenReadingFromValidFile() throws IOException, FileFormatException {
        OrderList ol = new OrderList("orders.txt");
        assertEquals(100, ol.getNumberOfOrders());
    }

    @Test
    public void testOrderListConstructorThrowsFileNotFoundForInvalidFile()
    {
        assertThrows(FileNotFoundException.class, ()-> new OrderList("no-file.txt"));
    }

    @Test
    public void testOrderListConstructorThrowsInvalidFileFormatExceptionWhenReadingBadFile()
    {
        // test file only has two elements in first line therefore it is an invalid format for an order
        assertThrows(FileFormatException.class, ()-> new OrderList("tests/badFileFormat.txt"));

    }



}