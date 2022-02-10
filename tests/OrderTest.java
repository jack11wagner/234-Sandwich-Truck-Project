import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {
    private ArrayList<String> makeOrderList() {
        ArrayList<String> orderList = new ArrayList<>();
        orderList.add("Sandwich");
        orderList.add("French Fries");
        orderList.add("Soup");
        return orderList;
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
        o.setOrderContents();
        assertTrue(orderList.contains(o.getOrderContents()));
    }


}