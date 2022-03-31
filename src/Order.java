/*
Author: Jackson Wagner
This Class stores information about an Order and generates
random information for each order component including a ordertime, address and ordercontents

Edits by:
*/
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.sql.Timestamp;

public class Order {
    /**
     * Stores information about an Order
     * Details such as timestamps, OrderContents, address are all randomly generated
     */
    private Timestamp orderTimestamp;
    private String orderDate;
    private String orderAddress;
    private String sandwichOrder;
    private double orderCost;
    private String fullOrderDetails;
    private HashSet<String> addressesUsed = new HashSet<>();
    // orders have a customer

    public Order(String date) {
        /**
         * Constructor for Order Class
         * Instantiates all instance variables to default values
         * @param : String date - The date all timestamps will have since all orders should be on a certain day
         */
        this.orderDate = date;
        this.orderTimestamp = Timestamp.valueOf("2000-01-01 0:0:0");
        this.sandwichOrder = "";
        this.fullOrderDetails = "";
        this.orderAddress = "";
    }

    public Order(String timestamp, String address, Sandwich sandwich){
        /**
         * Constructor for Order Class
         * Instantiates all instance variables to default values
         * @param : String timestamp - The timestamp of an order
         * @param : String address the address of the customer
         * @param : Sandwich sandwich the sandwich object to set the description to
         *
         */
        this.orderDate = "";
        this.orderTimestamp = Timestamp.valueOf(timestamp);
        this.sandwichOrder = sandwich.getDescription();
        this.orderCost = sandwich.cost();
        this.fullOrderDetails = timestamp + "," + address + "," + sandwichOrder + ',' + String.format("%.2f",orderCost);
        this.orderAddress = address;
    }


    public Order(String timestamp, String address, String orderContents, double cost) {
        /**
         * Constructor for Order Class
         * Instantiates all instance variables to default values
         * @param : String timestamp - The timestamp of an order
         * @param : String address the address of the customer
         *
         */
        this.orderDate = "";
        this.orderTimestamp = Timestamp.valueOf(timestamp);
        this.sandwichOrder = orderContents;
        this.orderCost = cost;
        this.fullOrderDetails = timestamp + "," + address + "," + orderContents + "," + cost;
        this.orderAddress = address;
    }

    public void generateRandomFields(HashSet<String> addressesUsed) {
        /**
         * Calls methods to create random values for each of the instance variables
         */
        setRandomTimestamp();
        setRandomAddress(addressesUsed);
        setRandomSandwichOrder();
        setFullOrderDetails();
    }

    // TODO
    public Sandwich getRandomSandwichChoice(){
         /**
         * Selects a random Sandwich using the Decorator Pattern
         * Still looking for better randomization ways but with the Decorator pattern it is tough to randomize
         */
       int randomSandwichChoice = new Random().nextInt(4)+1;

       // 4 random Sandwich Choice options
       switch (randomSandwichChoice) {
           case 1: return new Mayo(new Lettuce(new Mustard(new Pastrami())));
           case 2: return new Mustard(new Bacon(new Tomato(new Ham())));
           case 3: return new Tomato(new WhiteBread(new Lettuce(new Italian())));
           case 4: return new WholeWheatBread(new Tomato(new Mustard(new Pastrami())));
           default: return new Italian();
       }
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
         * House numbers range from 100 to 1000 in increments of 10 so this Array List contains all the possible house numbers
         * @returns: a house number based on a random element in the houseNumbers ArrayList
         */
        ArrayList<String> houseNumbers = new ArrayList<>();
        for (int i = 100;i<=1000;i+=10)
            houseNumbers.add(String.valueOf(i));
        return houseNumbers.get(new Random().nextInt(houseNumbers.size()));
    }

    public void setRandomAddress(HashSet<String> addressesUsed) {
        /**
         * Calls private helper methods to generate random house number/ street name
         * This method just concatenates the two and assigns the result to the instance variable address
         */
        String orderAddress =  this.getRandomHouseNumber() + " " + this.getRandomStreetName() + " St.";
        while (addressesUsed.contains(orderAddress)) {
            orderAddress = this.getRandomHouseNumber() + " " + this.getRandomStreetName() + " St.";
        }
        this.orderAddress = orderAddress;

    }
    public void setRandomTimestamp()
    {
        /**
         * Generates Random Values for Hour, Min, and Seconds to generate a random timestamp
         * This method calls the getRandomNumber helper method in order to generate the random values for each time component
         * Sets the instance variable orderTimestamp to a Random Timestamp object
         */
        int hour = getRandomNumber(11,18);
        int min = getRandomNumber(0,60);
        int sec = getRandomNumber(0,60);
        String fullTimestamp = orderDate + " " + hour +":" + min + ":" + sec;
        this.orderTimestamp = Timestamp.valueOf(fullTimestamp);
    }

    public void setRandomSandwichOrder()
    {
        /**
         * Selects random element from orderList and sets instance variable orderContents to it
         */
        Sandwich RandomSandwich = getRandomSandwichChoice();
        sandwichOrder = RandomSandwich.getDescription();
        orderCost = RandomSandwich.cost();
    }

    public void setFullOrderDetails()
    {
        /**
         * Sets fullOrderDetails to the concatenated string of orderTimestamp, orderAddress and OrderContents
         */
        fullOrderDetails= this.getOrderTimestamp() + "," + this.orderAddress +  "," + this.sandwichOrder +","+ String.format("%.2f",this.orderCost);
    }

    public void setSandwichOrder(Sandwich sandwich) {
        this.sandwichOrder = sandwich.getDescription();
    }

    public String getSandwichOrder() {
        /**
         * @returns orderContents instance variable
         */
        return sandwichOrder;
    }

    public String getFullOrderDetails() {
        /**
         * @returns fullOrderDetails instance variable
         */
        return fullOrderDetails;
    }

    public Timestamp getOrderTimestamp() {
        /**
         * @returns timestamp instance variable
         */
        return orderTimestamp;
    }

    public String getOrderAddress() {
        /**
         * @returns address instance variable
         */
        return orderAddress;
    }

    public String getOrderDate() {
        /**
         * @returns date instance variable
         */
        return orderDate;
    }

    @Override
    public String toString() {
        /**
         * @returns fullOrderDetails which is a string representation of an order
         */
        return this.fullOrderDetails;
    }


}
