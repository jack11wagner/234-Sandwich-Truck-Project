/*
Made by: Jackson Wagner
Class which handles storage of all orders as well as
creating an array of orders from a filename
Edits by:
*/
import java.io.BufferedReader;
import java.io.FileReader;
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
         * Instantiates the orderList to a new ArrayList
         */
        orderList = new ArrayList<>();
    }
    public OrderList(String filename) throws IOException, FileFormatException {
        /**
         * Reads an order file and adds each order to the order list from a file
         * @throws: IOException if the file cannot be found/read
         */
        orderList = new ArrayList<>();
        BufferedReader in = new BufferedReader(new FileReader(filename));
        String line;
        while ( (line = in.readLine())!=null){
            line = line.trim();
            String[] orderLine = line.split(",");
            if (orderLine.length!=3) throw new FileFormatException("Invalid file format");
            this.addOrder(new Order(orderLine[0], orderLine[1], orderLine[2]));
        }
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
            writer.write(o.getOrderTimestamp()+","+ o.getOrderAddress() + "," + o.getOrderContents() + System.lineSeparator());
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

    public ArrayList<Order> getOrderList() {
        /**
         * Method which gets the OrderList ArrayList
         * @returns: orderList
         */
        return orderList;
    }
    public int getNumberOfOrders()
    {
        /**
         * Method which returns the number of orders
         * @returns: size of orderList
         */
        return this.orderList.size();
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
