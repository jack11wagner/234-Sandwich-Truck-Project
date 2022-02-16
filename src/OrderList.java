import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class OrderList {
    /**
     * Class which holds Orders in a Data structure
     */
    private ArrayList<Order> orderList;
    public OrderList(){
        /**
         * @returns orderContents instance variable
         */
        orderList = new ArrayList<>();
    }

    public void addOrder(Order e)
    {
        /**
         * Method which adds an order to the OrderList instance variable data structure
         * @params: Order e - an instance of an Order Class
         */
        orderList.add(e);
    }

    private void writeOrdersToFile() throws IOException {
        /**
         * Private helper method for generateOrders
         * Writes the contents of an OrderList to a file named orders.txt
         * @throws: IOException in case the file is not found to be written to
         */
        FileWriter writer = new FileWriter("orders.txt");
        for(Order o : orderList) {
            writer.write(o.getTimestamp()+","+ o.getAddress() + "," + o.getOrderContents() + System.lineSeparator());
        }
        writer.close();
    }
    public void generateOrders(int numOrders)
    {
        /**
         * Method which generates a defined number of random orders and appends them to the orderList data structure
         * Method also calls writeOrdersToFile to generate a txt file of the orderList
         * @param: int numOrders - the number of orders to make
         */
        for(int i =0;i<numOrders;i++){
            Order o = new Order("2022-01-01");
            o.generateRandomFields();
            this.addOrder(o);
        }
        try {
            this.writeOrdersToFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public String toString() {
        /**
         * @returns string representation of the orderList data structure
         */
        String result ="(";
        for(Order e:orderList){
            result+= e.getFullOrderDetails()+", ";
        }
        result+=")";
        return result;
    }
}
