import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AddressConverterTest {
    
    private String address;
    private final AddressConverter converter = new AddressConverter();

    @Test
    public void testLetterStreetAddress() {
        address = "700 G St.";
        System.out.println("L: " + converter.convert(address));
    }

    @Test
    public void testNumberStreetAddress() {
        address = "500 5 St.";
        System.out.println("N: " + converter.convert(address));
    }

    @Test
    public void testSplitOrder() {
        address = "250 A st.";
        converter.convert(address);
        assertEquals("250 A st.", converter.getAddress());
    }

    @Test
    public void testSplitAddress() {
        address = "250 A st.";
        converter.convert(address);
        assertEquals(250, converter.getAddrNum());
        assertEquals("A", converter.getStreet());
    }
}
