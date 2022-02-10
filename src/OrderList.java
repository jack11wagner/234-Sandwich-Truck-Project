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

    public void writeOrdersToFile() throws IOException {
        FileWriter writer = new FileWriter("orders.txt");
        for(Order o : orderList) {
            writer.write(o.getTimestamp()+","+ o.getOrderContents()+ System.lineSeparator());
        }
        writer.close();
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
