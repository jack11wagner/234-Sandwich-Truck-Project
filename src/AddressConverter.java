/*
Made by: Michael Shimer
Allows orders to be split into sections: orderDate, address, and foodOrder and
uses the address string to convert the address into x and y coordinates used to
display locations on the truck map

Edits by: Jackson Wagner
*/

import java.util.HashMap;

public class AddressConverter {

    private int addrNum;
    private String street;
    private String address;
    private String[] splitAddressArray;
    private int[] coordinates;
    private HashMap<String, Integer> letterStreets;
    private HashMap<String, Integer> numberStreets;
    private double spacing = SimSettings.DIMENSION / (SimSettings.NUM_ROADS - 1);
    private int xCoordinate;
    private int yCoordinate;

    public AddressConverter() {
        coordinates = new int[2];
        letterStreets = new HashMap<>();
        numberStreets = new HashMap<>();
        fillAddressMaps();
    }

//    // splits original order string on the commas into three seperate strings: orderDate, address, and foodOrder
//    private void splitOrder(String order) {
//        splitOrderArray = order.split(",");
//        address =  splitOrderArray[1];
//    }

    // splits address string into an address number (addrNum) and a street (street)
    private void splitAddress() {
        splitAddressArray = address.trim().split(" ");
        addrNum = Integer.parseInt(splitAddressArray[0]);
        street = splitAddressArray[1];
    }

    // decides which conversion to do based on the street type (letter or number) and then returns the 
    // x and y coordinates for the address that correspond to the location on the truck map
    public int[] convert(String address) {
        this.address = address;
        splitAddress();
        if (numberStreets.containsKey(street))
            convertNumberAddress();
        else if (letterStreets.containsKey(street))
            convertLetterAddress();
        else
            //throw new Exception("Invalid Street Label");
            System.out.println("invalid street label");
        coordinates = new int[]{xCoordinate, yCoordinate};
        return coordinates;
    }

    // FIXME
    // if the address is on a number named street, calculates the x and y coordinates for the truck map
    private void convertNumberAddress() {
        
        int horizontalBlockNumber = addrNum / 100;
        int horizontalStreetNumber = (addrNum % 100) / 10;
        
        xCoordinate = (int)(spacing * (horizontalBlockNumber- 1) + (spacing / 11) * horizontalStreetNumber);
        yCoordinate = (int)(spacing * (10 - numberStreets.get(street) - 1));
    }

    // FIXME
    // if the address is on a letter named street, calculates the x and y coordinates for the truck map
    private void convertLetterAddress() {
        int verticalBlockNumber = addrNum / 100;
        int verticalStreetNumber = (addrNum % 100) / 10;

        xCoordinate = (int)(spacing * (letterStreets.get(street)));
        yCoordinate = (int)(spacing * (10 - verticalBlockNumber - 1) + (spacing / 11) * (10 - verticalStreetNumber + 1));
    }


    private void fillAddressMaps() {
        letterStreets.put("A", 0);
        letterStreets.put("B", 1);
        letterStreets.put("C", 2);
        letterStreets.put("D", 3);
        letterStreets.put("E", 4);
        letterStreets.put("F", 5);
        letterStreets.put("G", 6);
        letterStreets.put("H", 7);
        letterStreets.put("I", 8);
        letterStreets.put("J", 9);

        numberStreets.put("1", 0);
        numberStreets.put("2", 1);
        numberStreets.put("3", 2);
        numberStreets.put("4", 3);
        numberStreets.put("5", 4);
        numberStreets.put("6", 5);
        numberStreets.put("7", 6);
        numberStreets.put("8", 7);
        numberStreets.put("9", 8);
        numberStreets.put("10", 9);
    }


    public int getAddrNum() {
        return addrNum;
    }

    public String getStreet() {
        return street;
    }

    public String getAddress() {
        return address;
    }
}
