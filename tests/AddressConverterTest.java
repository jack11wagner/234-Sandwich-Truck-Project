import org.junit.jupiter.api.Test;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class AddressConverterTest {
    
    private String order;
    AddressConverter converter = new AddressConverter();

    @Test
    public void testLetterStreetAddress() {
        order = "date, 700 G St., food";
        System.out.println("L: " + converter.convert(order));
    }

    @Test
    public void testNumberStreetAddress() {
        order = "date, 500 5 St., food";
        System.out.println("N: " + converter.convert(order));
    }

    @Test
    public void testSplitOrder() {
        order = "date, 250 A st., food";
        converter.convert(order);
        assertEquals(" 250 A st.", converter.getAddress());
    }

    @Test
    public void testSplitAddress() {
        order = "date, 250 A st., food";
        converter.convert(order);
        assertEquals(250, converter.getAddrNum());
        assertEquals("A", converter.getStreet());
}
