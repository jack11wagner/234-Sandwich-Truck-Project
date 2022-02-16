import java.util.ArrayList;
import java.util.Random;
import java.sql.Timestamp;

public class Order {
    /**
     * Stores information about an Order
     * Details such as timestamps, OrderContents, address are all randomly generated
     *
     */
    private Timestamp timestamp;
    private String date;
    private String address;
    private String orderContents;
    private String fullOrderDetails;

    public Order(String date){
        /**
         * Constructor for Order Class
         * Instantiates all instance variables to default values
         * @param : String date - The date all timestamps will have since all orders should be on a certain day
         */
        this.date = date;
        this.timestamp = Timestamp.valueOf("2000-01-01 0:0:0");
        this.orderContents = "";
        this.fullOrderDetails = "";
        this.address = "";
    }

    public void generateRandomFields()
    {
        /**
         * Calls methods to create random values for each of the instance variables
         */
        setRandomTimestamp();
        setRandomAddress();
        setRandomOrderContents();
        setRandomAddress();
        setFullOrderDetails();

    }

    private int getRandomNumber(int min , int max)
    {
        /**
         * Chooses a number between min(inclusive) and max(exclusive) at random
         * @param: int min - the minimum value the random choice could return
         * @param: int max - the max value the random choice could return - 1 (since r.nextInt() is exclusive of the max value)
         * @returns: a random number between the range min and max(exclusive)
         */
        Random r = new Random();
        return r.nextInt(max-min)+min;

    }

    private String getRandomStreetName(){
        /**
         * Private Helper Function for setRandomAddress()
         * Selects a random Street Name from a array of Possible Streets
         * @returns: a street name based on a random element in the streetList array
         */
        String[] streetList = {"1","2","3", "4","5","6", "7", "8", "9","10", "A","B", "C","D", "E", "F", "G","H","I", "J"};
        return streetList[new Random().nextInt(streetList.length)];

    }
    private String getRandomHouseNumber(){
        /**
         * Private Helper Function for setRandomAddress()
         * Selects a random house number from an ArrayList of possible house numbers
         * House numbers range from 100 to 1090 in increments of 10 so this Array List contains all the possible house numbers
         * @returns: a house number based on a random element in the houseNumbers ArrayList
         */
        ArrayList<String> houseNumbers = new ArrayList<>();
        for (int i = 100;i<=1090;i+=10)
            houseNumbers.add(String.valueOf(i));
        return houseNumbers.get(new Random().nextInt(houseNumbers.size()));
    }

    public void setRandomAddress() {
        /**
         * Calls private helper methods to generate random house number/ street name
         * This method just concatenates the two and assigns the result to the instance variable address
         */
        String houseNumber = this.getRandomHouseNumber();
        String street = this.getRandomStreetName();
        this.address = houseNumber + " " + street + " St.";
    }
    public void setRandomTimestamp()
    {
        /**
         * Generates Random Values for Hour, Min, and Seconds to generate a random timestamp
         * This method calls the getRandomNumber helper method in order to generate the random values for each time component
         * Sets the instance variable timestamp to a Random Timestamp object
         */
        int hour = getRandomNumber(11,18);
        int min = getRandomNumber(0,60);
        int sec = getRandomNumber(0,60);
        String fullTimestamp =date + " " + hour +":" + min + ":" + sec;
        this.timestamp = Timestamp.valueOf(fullTimestamp);
    }

    public void setRandomOrderContents()
    {
        /**
         * Selects random element from orderList and sets instance variable orderContents to it
         */
        String[] orderList = {"Italian Sandwich", "Ham and Cheese", "Bologna Sandwich", "Salami Sandwich"};
        orderContents = orderList[new Random().nextInt(orderList.length)];
    }

    public void setFullOrderDetails()
    {
        /**
         * Sets fullOrderDetails to the concatenated string of Timestamp, address and OrderContents
         */
        fullOrderDetails= this.getTimestamp() + " " + this.address +  " " + this.orderContents;
    }


    public String getOrderContents() {
        /**
         * @returns orderContents instance variable
         */
        return orderContents;
    }

    public String getFullOrderDetails() {
        /**
         * @returns fullOrderDetails instance variable
         */
        return fullOrderDetails;
    }

    public Timestamp getTimestamp() {
        /**
         * @returns timestamp instance variable
         */
        return timestamp;
    }

    public String getAddress() {
        /**
         * @returns address instance variable
         */
        return address;
    }

    public String getDate() {
        /**
         * @returns date instance variable
         */
        return date;
    }

    @Override
    public String toString() {
        /**
         * @returns fullOrderDetails which is a string representation of an order
         */
        return this.fullOrderDetails;
    }


}
