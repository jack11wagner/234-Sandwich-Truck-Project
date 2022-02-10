import java.util.Random;
import java.sql.Timestamp;

public class Order {
    private Timestamp timestamp;
    private String date;
    private String streetName;
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
        this.streetName = "";
        generateFields();

    }

    public void generateFields()
    {
        setRandomTimestamp();
        setOrderContents();
        setFullOrderDetails();
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
        String fullTimestamp;
        fullTimestamp =date + " " + hour +":" + min + ":" + sec;
        this.timestamp = Timestamp.valueOf(fullTimestamp);
    }

    public void setOrderContents()
    {
        String[] orderList = {"Sandwich", "Soup", "French Fries"};
        orderContents = orderList[new Random().nextInt(orderList.length)];
    }

    public void setFullOrderDetails()
    {
        fullOrderDetails+= this.getTimestamp() + " " + this.orderContents + " " + streetName;
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
                ", streetName='" + streetName + '\'' +
                ", locationX=" + locationX +
                ", locationY=" + locationY +
                ", orderContents='" + orderContents + '\'' +
                ", fullOrderDetails='" + fullOrderDetails + '\'' +
                '}';
    }


}
