import org.junit.jupiter.api.Test;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class AddressConverterTest {
    
    private String order;
    AddressConverter converter;

    @Test
    public void testLetterStreetAddress() {
        converter = new AddressConverter();
        order = "date, 250 E St., food";
        System.out.print(converter.convert(order));
    }



}
