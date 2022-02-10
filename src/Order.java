import java.util.Random;
import java.sql.Timestamp;

public class Order {
    private Timestamp timestamp;
    private String date;
    private String address;
    private int locationX;
    private int locationY;
    private String orderContents;
    private String fullOrderDetails;


    public Order(String date){
        this.date = date;
        this.timestamp = Timestamp.valueOf("2000-01-01 0:0:0");
        this.locationX = 0;
        this.locationY = 0;
        this.orderContents = "";
        this.fullOrderDetails = "";
        this.address = "";
        generateRandomFields();

    }

    public void generateRandomFields()
    {
        setRandomTimestamp();
        setRandomAddress();
        setOrderContents();
        setFullOrderDetails();
    }

    private void setRandomAddress() {
        int[] streetList = {1,2,3,4,5,6,7,8,9,10};

    }

    public int getRandomNumber(int min , int max)
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
        String[] orderList = {"Sandwich", "Soup", "French Fries", ""};
        orderContents = orderList[new Random().nextInt(orderList.length)];
    }

    public void setFullOrderDetails()
    {
        fullOrderDetails+= this.getTimestamp() + " " + this.orderContents + " " + this.address;
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

    public String getDate() {
        return date;
    }

    public int getLocationX() {
        return locationX;
    }

    public int getLocationY() {
        return locationY;
    }

    @Override
    public String toString() {
        return "Order{" +
                "timestamp='" + timestamp + '\'' +
                ", date='" + date + '\'' +
                ", streetName='" + address + '\'' +
                ", locationX=" + locationX +
                ", locationY=" + locationY +
                ", orderContents='" + orderContents + '\'' +
                ", fullOrderDetails='" + fullOrderDetails + '\'' +
                '}';
    }


}
