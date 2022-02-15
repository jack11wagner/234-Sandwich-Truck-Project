import java.util.ArrayList;
import java.util.Random;
import java.sql.Timestamp;

public class Order {
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
    }

    public void generateRandomFields()
    {
        setRandomTimestamp();
        setRandomAddress();
        setRandomOrderContents();
        setRandomAddress();
        setFullOrderDetails();

    }

    private int getRandomNumber(int min , int max)
    {
        Random r = new Random();
        return r.nextInt(max-min)+min;

    }

    private String getRandomStreetName(){
        String[] streetList = {"1","2","3", "4","5","6", "7", "8", "9","10", "A","B", "C","D", "E", "F", "G","H","I", "J"};
        return streetList[new Random().nextInt(streetList.length)];

    }
    private String getRandomHouseNumber(){
        ArrayList<String> houseNumbers = new ArrayList<>();
        for (int i = 100;i<=1090;i+=10)
            houseNumbers.add(String.valueOf(i));
        return houseNumbers.get(new Random().nextInt(houseNumbers.size()));
    }

    public void setRandomAddress() {
        String houseNumber = this.getRandomHouseNumber();
        String street = this.getRandomStreetName();
        this.address = houseNumber + " " + street + " St.";
    }
    public void setRandomTimestamp()
    {
        int hour = getRandomNumber(11,18);
        int min = getRandomNumber(0,60);
        int sec = getRandomNumber(0,60);
        String fullTimestamp =date + " " + hour +":" + min + ":" + sec;
        this.timestamp = Timestamp.valueOf(fullTimestamp);
    }

    public void setRandomOrderContents()
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
        return this.fullOrderDetails;
    }


}
