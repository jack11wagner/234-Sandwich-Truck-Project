import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class OrderList {
    private ArrayList<Order> orderList;
    public OrderList(){
        orderList = new ArrayList<>();
    }

    public void addOrder(Order e)
    {
        orderList.add(e);
    }

    private void writeOrdersToFile() throws IOException {
        FileWriter writer = new FileWriter("orders.txt");
        for(Order o : orderList) {
            writer.write(o.getTimestamp()+","+ o.getAddress() + "," + o.getOrderContents() + System.lineSeparator());
        }
        writer.close();
    }
    public void generateOrders(int numOrders)
    {
        for(int i =0;i<numOrders;i++){
            Order o = new Order("2022-01-01");
            o.generateRandomFields();
            this.orderList.add(o);
        }
        try {
            this.writeOrdersToFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public String toString() {
        String result ="(";
        for(Order e:orderList){
            result+= e.getFullOrderDetails()+", ";
        }
        result+=")";
        return result;
    }
}
