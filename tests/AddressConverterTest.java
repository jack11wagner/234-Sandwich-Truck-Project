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
        order = "date, 250 E St., food";
        System.out.print(converter.convert(order));
    }

    @Test
    public void testSplitOrder() {
        order = "date, address, food";
        //assertEquals();
    }



}
