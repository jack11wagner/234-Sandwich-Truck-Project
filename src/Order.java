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

        this.date = date;
        this.timestamp = Timestamp.valueOf("2000-01-01 0:0:0");
        this.orderContents = "";
        this.fullOrderDetails = "";
        this.address = "";
        generateRandomFields();

    }

    public void generateRandomFields()
    {
        /**
         * Calls methods to create random values for each of the instance variables
         */
        setRandomTimestamp();
        setRandomAddress();
        setOrderContents();
        setRandomAddress();
        setFullOrderDetails();

    }

    public void setRandomAddress() {
        /**
         *
         */
        String[] streetList = {"1","2","3", "4","5","6", "7", "8", "9","10", "A","B", "C","D", "E", "F", "G","H","I", "J"};
        ArrayList<String> houseNumbers = new ArrayList<>();
        for (int i = 100;i<=1090;i+=10){
            houseNumbers.add(String.valueOf(i));
        }
        String street = streetList[new Random().nextInt(streetList.length)];
        String houseNumber = houseNumbers.get(new Random().nextInt(houseNumbers.size()));

        this.address = houseNumber + " " + street + " St.";

    }

    private int getRandomNumber(int min , int max)
    {
        Random r = new Random();
        return r.nextInt(max-min)+min;

    }

    public void setRandomTimestamp()
    {
        int hour = getRandomNumber(11,18);
        int min = getRandomNumber(0,60);
        int sec = getRandomNumber(0,60);
        String fullTimestamp =date + " " + hour +":" + min + ":" + sec;
        this.timestamp = Timestamp.valueOf(fullTimestamp);
    }

    public void setOrderContents()
    {
        String[] orderList = {"Italian Sandwich", "Ham and Cheese", "Bologna Sandwich", "Salami Sandwich"};
        orderContents = orderList[new Random().nextInt(orderList.length)];
    }

    public void setFullOrderDetails()
    {
        fullOrderDetails= this.getTimestamp() + " " + this.address +  " " + this.orderContents;
    }


    public String getOrderContents() {
        return orderContents;
    }

    public String getFullOrderDetails() {
        return fullOrderDetails;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public String getAddress() {
        return address;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Order{" +
                "timestamp='" + timestamp + '\'' +
                ", date='" + date + '\'' +
                ", streetName='" + address + '\'' +
                ", orderContents='" + orderContents + '\'' +
                ", fullOrderDetails='" + fullOrderDetails + '\'' +
                '}';
    }


}
