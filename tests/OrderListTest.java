/*
Made by: Jackson Wagner
Testing for OrderList Class
Edits by:
*/

import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class OrderListTest {
    @Test
    public void testOrderListIsFilledWhenReadingFromValidFile() throws IOException, FileFormatException {
        OrderList o = new OrderList();
        o.generateOrders(100);
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

    @Test
    public void testGenerating100OrdersAllOrderAddressesAreUnique()
    {
        OrderList ol = new OrderList();
        HashSet<String> addressesUsed = new HashSet<>();
        ol.generateOrders(100);
        for (Order o: ol.getOrderList()){
            addressesUsed.add(o.getOrderAddress());
        }
        assertEquals(100, addressesUsed.size());

    }

    @Test
    public void testCreatingSandwichObjectAddsToOrdersFile() throws IOException {
        OrderList ol = new OrderList();
        Sandwich italian = new Mustard(new Mayo(new Lettuce(new Italian())));
        Order testOrder = new Order("2020-01-01 12:01:00", "110 A St.", italian);
        ol.addOrder(testOrder);
        ol.writeOrdersToFile();

    }





}