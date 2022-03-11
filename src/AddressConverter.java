/**
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
    private int[] coordinates;
    private final HashMap<String, Integer> letterStreets;
    private final HashMap<String, Integer> numberStreets;
    private final double spacing = SimSettings.ROAD_SPACING;
    private int xCoordinate;
    private int yCoordinate;

    /**
     * Constructor for the AddressConverter Class
     * Instantiates the data structures necessary to hold the output coordinates and maps
     * to hold values corresponding to street addresses to help calculate the output coordinates
     */
    public AddressConverter() {
        coordinates = new int[2];
        letterStreets = new HashMap<>();
        numberStreets = new HashMap<>();
        fillAddressMaps();
    }


    /**
     * helper method for convert method which splits the address, given to the convert method,
     * into the address number and the street name and assigns them to the respective variables
     */
    private void splitAddress() {
        String[] splitAddressArray = address.trim().split(" ");
        addrNum = Integer.parseInt(splitAddressArray[0]);
        street = splitAddressArray[1];
    }

    /**
     * decides if the address is on a street named after a number or letter and calls the appropriate helper method
     * (convertNumberAddress or convertLetterAddress) to calculate the x and y pixel coordinates that correspond to
     * a specific address location on the truck map
     * @param: String address - the address for the delivery of a reepective order
     * @returns: an array of two integers corresponding to the x pixel coordinate and the y pixel coordinate
     */
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
    /**
     * helper method for the convert method that calculates the x and y pixel coordinates corresponding to
     * a specific address location on the truck map for streets named after numbers, and assigns the correct x
     *      * and y values to the xCoordinate and yCoordinate instance variables
     */
    private void convertNumberAddress() {
        
        int horizontalBlockNumber = addrNum / 100;
        int horizontalStreetNumber = (addrNum % 100) / 10;
        
        xCoordinate = (int)(spacing * (horizontalBlockNumber- 1) + (spacing / 11) * horizontalStreetNumber);
        yCoordinate = (int)(spacing * (10 - numberStreets.get(street) - 1));
    }

    // FIXME
    /**
     * helper method for the convert method that calculates the x and y pixel coordinates corresponding to
     * a specific address location on the truck map for streets named after letters, and assigns the correct x
     * and y values to the xCoordinate and yCoordinate instance variables
     */
    private void convertLetterAddress() {
        int verticalBlockNumber = addrNum / 100;
        int verticalStreetNumber = (addrNum % 100) / 10;

        xCoordinate = (int)(spacing * (letterStreets.get(street)));
        yCoordinate = (int)(spacing * (10 - verticalBlockNumber - 1) + (spacing / 11) * (10 - verticalStreetNumber + 1));
    }


    /**
     * a helper method that populates the letterStreets and numberStreets hashMaps with numbers that
     * correlate to specific street numbers or letter to aid in the conversion of addresses to x and y
     * pixel coordinates
     * Edits: Nikolas Kovacs - make method dynamic to SimSettings.NUM_ROADS
     */
    private void fillAddressMaps() {
        for (char i = 'A'; i < (char)SimSettings.NUM_ROADS + 65; i++) {
            letterStreets.put(Character.toString(i), (int)i-65);
        }

        for (int i = 0; i < SimSettings.NUM_ROADS; i++) {
            numberStreets.put(Integer.toString(i + 1), i);
        }
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
