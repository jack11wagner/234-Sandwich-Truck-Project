/*
Made by: Jackson Wagner
Testing for Order Class
Edits by:
*/
import org.junit.jupiter.api.Test;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {
    private ArrayList<String> makeOrderList() {
        ArrayList<String> orderList = new ArrayList<>();
        orderList.add("Italian Sandwich");
        orderList.add("Ham and Cheese");
        orderList.add("Bologna Sandwich");
        orderList.add("Salami Sandwich");
        return orderList;
    }

    private ArrayList<String> createHouseNumberTestList() {
        ArrayList<String> houseNumberTestList = new ArrayList<>();
        for (int i = 100;i<=1090;i+=10)
            houseNumberTestList.add(String.valueOf(i));
        return houseNumberTestList;
    }

    @Test
    public void testRandomTimestampReturnsValidTimestamp()
    {
        String date = "2022-01-01";
        Order o = new Order("2022-01-01");
        o.setRandomTimestamp();
        Timestamp testTimestamp = o.getOrderTimestamp();
        assertEquals(Timestamp.class, testTimestamp.getClass());
        assertTrue(testTimestamp.toString().startsWith(date));
    }

    @Test
    public void testInvalidDateFormatThrowsIllegalArgumentException()
    {
        Order o = new Order("abcdef");
        assertThrows(IllegalArgumentException.class, () -> o.setRandomTimestamp());
        Order o2 = new Order("2021:01:01");
        assertThrows(IllegalArgumentException.class, () -> o2.setRandomTimestamp());

    }

    @Test
    public void testRandomOrder()
    {
        ArrayList<String> orderList = makeOrderList();
        Order o = new Order("2022-01-01");

        o.setRandomOrderContents();
        assertTrue(o.getOrderDate().equals("2022-01-01"));
        assertTrue(orderList.contains(o.getOrderContents()));

    }


    @Test
    public void testAddressIsValid()
    {
        String[] streetNamesTestList = {"1","2","3", "4","5","6", "7", "8", "9","10", "A","B", "C","D", "E", "F", "G","H","I", "J"};
        ArrayList<String> houseNumberTestList = createHouseNumberTestList();
        Order o = new Order("2022-01-01");
        o.setRandomAddress(new HashSet<>());
        String address = o.getOrderAddress();

        String[] address_components = address.split(" ");

        assertTrue(houseNumberTestList.contains(address_components[0]));
        assertTrue(Arrays.asList(streetNamesTestList).contains(address_components[1]));

    }


}