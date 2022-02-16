import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

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
    public void testRandomTimestamp()
    {
        Order o = new Order("2022-01-01");

    }

    @Test
    public void testRandomOrder()
    {
        ArrayList<String> orderList = makeOrderList();
        Order o = new Order("2022-01-01");

        o.setRandomOrderContents();
        assertTrue(o.getDate().equals("2022-01-01"));
        assertTrue(orderList.contains(o.getOrderContents()));

    }


    @Test
    public void testAddressIsValid()
    {
        String[] streetNamesTestList = {"1","2","3", "4","5","6", "7", "8", "9","10", "A","B", "C","D", "E", "F", "G","H","I", "J"};
        ArrayList<String> houseNumberTestList = createHouseNumberTestList();
        Order o = new Order("2022-01-01");
        o.setRandomAddress();
        String address = o.getAddress();

        String[] address_components = address.split(" ");

        assertTrue(houseNumberTestList.contains(address_components[0]));
        assertTrue(Arrays.asList(streetNamesTestList).contains(address_components[1]));

    }


}