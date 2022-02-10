import java.util.Random;

public class Order {
    private String timestamp;
    private String date;
    private String streetName;
    private int locationX;
    private int locationY;
    private String orderContents;
    private String fullOrderDetails;


    public Order(String date){
        this.date = date;
        this.timestamp = "";
        this.locationX = 0;
        this.locationY = 0;
        this.orderContents = "";
        this.fullOrderDetails = "";
        this.streetName = "";
        generateFields();

    }

    public void generateFields()
    {
        generateRandomTimestamp();
        setOrderContents();
        setFullOrderDetails();
    }

    public int getRandomNumber(int min , int max)
    {
        Random r = new Random();
        return r.nextInt(max-min)+min;

    }

    public String generateRandomTimestamp()
    {
        int hour = getRandomNumber(11,18);
        int min = getRandomNumber(0,60);
        int sec = getRandomNumber(0,60);
        timestamp+=date + " " + hour +":" + min + ":" + sec;
        return timestamp;
    }

    public void setOrderContents()
    {
        String[] orderList = {"Sandwich", "Soup", "French Fries"};
        orderContents = orderList[new Random().nextInt(orderList.length)];
    }

    public void setFullOrderDetails()
    {
        fullOrderDetails+= timestamp + " "+ orderContents + " " + streetName;
    }


    public String getOrderContents() {
        return orderContents;
    }


    public String getTimestamp() {
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
