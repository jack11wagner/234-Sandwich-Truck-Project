

import javax.swing.*;
import java.awt.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;

public class OrderPanel extends JFrame {
    /**
     * This Class Represents the Window that pops up to display the upcoming orders and the
     * current location of the truck
     */
    private final Container orderFrame;
    private final JLabel headinglabel;
    private final JTextArea ordersTextArea;
    private String sandwich;
    private ArrayList<String> condiments = new ArrayList<>();
    private PriorityQueue<Order> orderQueue;


    OrderPanel() {
        /**
         * Constructor for OrderPanel which handles all the components of the Frame
         * Adds a Text bar displaying the upcoming orders and location of the truck
         */
        setTitle("Orders");
        setBounds(10, 0, 350, 700);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setResizable(false);
        orderFrame = getContentPane();

        headinglabel = createJLabel(new JLabel("UPCOMING ORDERS"), 17, 250, 15, 135, 5);
        ordersTextArea = getjTextArea();
        //JScrollPane scroll = new JScrollPane (ordersTextArea);
        //scroll.setSize(325, 600);

        ordersTextArea.setText("Truck Location: \n\nOrders: \n\n");

        orderFrame.setLayout(new FlowLayout());
        //this.add(scroll);
        this.setVisible(true);

        orderQueue = new PriorityQueue<>();
    }


    private JTextArea getjTextArea() {
        /**
         * Creates a basic textArea on the Window Frame used to reduce amount of code in constructor
         */
        final JTextArea sandwichComponentsTextArea;
        sandwichComponentsTextArea = new JTextArea();
        sandwichComponentsTextArea.setFont(new Font("Arial", Font.PLAIN, 12));
        sandwichComponentsTextArea.setSize(340, 600);
        sandwichComponentsTextArea.setLocation(25, 25);
        sandwichComponentsTextArea.setLineWrap(true);
        sandwichComponentsTextArea.setEditable(false);
        orderFrame.add(sandwichComponentsTextArea);
        return sandwichComponentsTextArea;
    }


    private JLabel createJLabel(JLabel e, int fontSize, int sizeWidth, int sizeHeight, int locX, int locY) {
        /**
         * creates a basic JLabel you can specify the size and other things in the parameters
         */
        e.setFont(new Font("Arial", Font.BOLD, fontSize));
        e.setSize(sizeWidth, sizeHeight);
        e.setLocation(locX, locY);
        orderFrame.add(e);
        return e;
    }


    /**
     * A method that updates the data output to the text area as the truck moves
     * and delivers orders
     * @param: this method takes an order strategy as a parameter to determine the
     * order in which the customers orders are displayed
     */
    public void update(OrderStrategy orderStrategy) {
        String orders = "";
        String truckLocation = SimSettings.customerList.get(99).getCurrentTruckAddress();
        if (orderStrategy instanceof TimeBasedStrategy) {
            fillOrderQueue();
            for (Order order : SimSettings.timeOrderQueue) {
                orders += buildOrderString(order);
            }
        }
        if (orderStrategy instanceof DistanceBasedStrategy) {
            for (Order order : SimSettings.distanceOrderQueue) {
                orders += buildOrderString(order);
            }
        }
        ordersTextArea.setText("Truck Location: " + truckLocation + " \n\nNext Orders: \n" + orders);
    }


    /**
     * A helper method that builds the string (gets order information in a nice format)
     * to output to the text area for each order
     * @param: this method takes an order as a parameter to pull order information
     * from and format it for the display
     * @returns: this method returns a string of nicely formatted order data
     */
    private String buildOrderString(Order order) {
        return order.getOrderTimestamp() + " " + order.getOrderAddress() + "\t\n" + order.getSandwichOrder() + "\n\n";
    }


    /**
     * A helper method that gets the customer orders in the correct order for
     * the time based delivery strategy
     */
    private void fillOrderQueue() {
        while(SimSettings.staticTimeOrderQueue.size() > 0) {
            SimSettings.timeOrderQueue.add(SimSettings.staticTimeOrderQueue.poll());
        }
    }

}
