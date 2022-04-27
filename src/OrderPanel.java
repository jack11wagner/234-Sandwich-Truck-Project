import javax.swing.*;
import java.awt.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;

public class OrderPanel extends JFrame {
    /**
     * This Class Represents the Window that pops up when Sandwiches are being made
     *
     */
    private final Container orderFrame;
    private final JLabel headinglabel;
    private final JTextArea ordersTextArea;
    private String sandwich;
    private ArrayList<String> condiments = new ArrayList<>();
    private PriorityQueue<Order> orderQueue;


    OrderPanel() {
        /**
         * Constructor for SandwichPrepInterface which handles all the components of the Frame
         * Adds a Text bar displaying the current time, a progress bar, and the sandwich order/condiments
         * This is triggered when the truck reaches an order destination
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

    private String buildOrderString(Order order) {
        return order.getOrderTimestamp() + " " + order.getOrderAddress() + "\t\n" + order.getSandwichOrder() + "\n\n";
    }

    private void fillOrderQueue() {
        while(SimSettings.staticTimeOrderQueue.size() > 0) {
            SimSettings.timeOrderQueue.add(SimSettings.staticTimeOrderQueue.poll());
        }
    }


}
