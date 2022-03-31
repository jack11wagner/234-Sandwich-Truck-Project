/*
Made by: Michael Shimer
Testing class for Customer class to test correct functionality
of the class as a whole, as well as the methods within it

Edits by:
*/

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest {

    Customer customer = new Customer("test");

    @Test
    public void testFillLetterAddresses() {
        System.out.println("Letters:");
        System.out.println(customer.letterAddresses.size());
    }

    @Test
    public void testFillNumberAddresses() {
        System.out.println("Numbers:");
        System.out.println(customer.numberAddresses.size());
    }




}
