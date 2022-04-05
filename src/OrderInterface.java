import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;

public class OrderInterface extends JFrame implements ActionListener {

    // Components of the Form
    private final Container c;
    private final JLabel title, nameLabel,orderAddressLabel, sandwichLabel, extrasLabel, confirmationLabel;
    private final JTextField name, orderAddress;
    private final JComboBox sandwichOrder;
    private final JButton submitButton, resetButton;
    private final JTextArea summary;
    private final JTextArea resadd;
    private final JRadioButton Lettuce, Mustard, Mayo, Tomato, WholeWheatBread, WhiteBread, Bacon;
    private final JRadioButton[] extrasList;
    private OrderList orderList;
    private final String[] sandwiches = {"choose...", "Italian", "Ham", "Pastrami"};

    // constructor, to initialize the components
    // with default values.
    public OrderInterface() {

        setTitle("Order Form");
        setBounds(0, 0, 450, 550);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setResizable(false);
        c = getContentPane();
        c.setLayout(null);
//        c.setBackground(Color.getHSBColor(49, 33, 92));

        createJLabel(title = new JLabel("Enter your order below"),22, 400,30, 100,5);

        // creates Name Label and TextField for Name
        createJLabel(nameLabel = new JLabel("Name"), 20, 80, 20, 75, 50);
        createJTextField(name = new JTextField(),160,20,175,50 );

        // creates Address Label and TextField for Address
        createJLabel(orderAddressLabel = new JLabel("Address"), 20, 100, 20, 55, 90);
        createJTextField(orderAddress = new JTextField(), 160, 20, 175, 90);

        // creates Sandwich Label and Dropdown for Sandwich Choice
        createJLabel(sandwichLabel = new JLabel("Sandwich"), 20, 100, 20, 55, 140);
        sandwichOrder = new JComboBox(sandwiches);
        sandwichOrder.setFont(new Font("Arial", Font.PLAIN, 15));
        sandwichOrder.setSize(175, 20);
        sandwichOrder.setLocation(175, 140);
        c.add(sandwichOrder);

        // creates Extras Label and RadioButtons for the various Extras
        createJLabel(extrasLabel = new JLabel("Extras"), 20, 100, 20, 55, 180);
        createJRadioButton(Lettuce = new JRadioButton("Lettuce"), 0, 0);
        createJRadioButton(Mustard = new JRadioButton("Mustard"), 95, 0);
        createJRadioButton(Mayo = new JRadioButton("Mayo"), 195, 0);
        createJRadioButton(Tomato = new JRadioButton("Tomato"), 0, 30);
        createJRadioButton(WholeWheatBread = new JRadioButton("Whole Wheat"), 95, 30);
        createJRadioButton(WhiteBread = new JRadioButton("White Bread"), 195, 30);
        createJRadioButton(Bacon = new JRadioButton("Bacon"), 0 , 60);



        // creates Submit and Reset Button
        createJButton(submitButton = new JButton("Submit"), 100,20, 125, 275);
        createJButton(resetButton = new JButton("Reset"), 100, 20, 245, 275);

        summary = new JTextArea();
        summary.setFont(new Font("Arial", Font.PLAIN, 15));
        summary.setSize(300, 170);
        summary.setLocation(75, 330);
        summary.setLineWrap(true);
        summary.setEditable(false);
        c.add(summary);

        createJLabel(confirmationLabel = new JLabel(""), 15, 500, 25, 150, 300);

        resadd = new JTextArea();
        resadd.setFont(new Font("Arial", Font.PLAIN, 15));
        resadd.setSize(200, 75);
        resadd.setLocation(545, 300);
        resadd.setLineWrap(true);
        c.add(resadd);

        try {
            orderList = new OrderList("orders.txt");
        } catch (IOException | FileFormatException e) {
            e.printStackTrace();
        }
        setVisible(true);
        extrasList = new JRadioButton[]{Lettuce, Mayo, Tomato, Mustard, Bacon, WholeWheatBread, WhiteBread};
    }

    private JButton createJButton(JButton e, int sizeWidth, int sizeHeight, int locX, int locY) {
        e.setFont(new Font("Arial", Font.PLAIN, 15));
        e.setSize(sizeWidth, sizeHeight);
        e.setLocation(locX, locY);
        e.addActionListener(this);
        c.add(e);
        return e;
    }

    private JTextField createJTextField(JTextField e, int sizeWidth, int sizeHeight, int locX, int locY) {
        e.setFont(new Font("Arial", Font.PLAIN, 15));
        e.setSize(sizeWidth, sizeHeight);
        e.setLocation(locX, locY);
        c.add(e);
        return e;
    }


    private JLabel createJLabel(JLabel e, int fontSize, int sizeWidth, int sizeHeight, int locX, int locY) {
        e.setFont(new Font("Arial", Font.PLAIN, fontSize));
        e.setSize(sizeWidth, sizeHeight);
        e.setLocation(locX, locY);
        c.add(e);
        return e;
    }

    private JRadioButton createJRadioButton(JRadioButton e, int xoffset, int yoffset) {
        e.setFont(new Font("Arial", Font.PLAIN, 12));
        e.setSelected(false);
        e.setSize(115, 20);
        e.setLocation(125+xoffset, 180+yoffset);
        c.add(e);
        return e;
    }

    private Sandwich generateSandwichObject(String sandwich){
        return switch (sandwich){
            case "Italian" -> new Italian();
            case "Pastrami" -> new Pastrami();
            case "Ham" -> new Ham();
            default -> null;
        };
    }

    public Sandwich addCondiment(Sandwich sandwich, String condiment)
    {
        return switch (condiment){
            case "Tomato" -> new Tomato(sandwich);
            case "Lettuce" -> new Lettuce(sandwich);
            case "Bacon" -> new Bacon(sandwich);
            case "Mayo" -> new Mayo(sandwich);
            case "Mustard" -> new Mustard(sandwich);
            case "Whole Wheat" -> new WholeWheatBread(sandwich);
            case "White Bread" -> new WhiteBread(sandwich);
            default -> null;
        };
    }


        // to get the action performed
        // by the user and act accordingly
    @Override
    public void actionPerformed(ActionEvent e)
        {
            if (e.getSource() == submitButton) {
                    String currTime = LocalDate.now() +" "+ LocalTime.now().toString();
                    String orderTime = Timestamp.valueOf(currTime.substring(0,currTime.length()-7)).toString();
                String extrasString ="";
                Sandwich sandwich = generateSandwichObject(sandwichOrder.getSelectedItem().toString().strip());
                for (JRadioButton jRadioButton : extrasList) {
                    if (jRadioButton.isSelected()) {
                        extrasString+= jRadioButton.getText() + " ";
                        sandwich = addCondiment(sandwich, jRadioButton.getText());
                    }
                }
                    String data
                            = "Name : "
                            + name.getText() + "\n"
                            + "Address : "
                            + orderAddress.getText().strip() + "\n"
                            + "Sandwich: "
                            + sandwichOrder.getSelectedItem() + "\n"
                            + "Extras : "
                            + extrasString.strip() + "\n"
                            + "Order Time : "
                            + orderTime + "\n";

                    summary.setText(data);
                    summary.setEditable(false);

                    Customer customer = new Customer(name.getText());
                    Order order = new Order(orderTime, orderAddress.getText().strip(), sandwich, customer);
                    customer.registerOrder(order);
                    orderList.addOrder(order);

                    try {
                        orderList.writeOrdersToFile();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                try {
                    orderList.writeOrdersToFile();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                confirmationLabel.setText("Order Confirmed...");

                }

            else if (e.getSource() == resetButton) {
                String def = "";
                name.setText(def);
                orderAddress.setText(def);
                confirmationLabel.setText(def);
                summary.setText(def);
                resadd.setText(def);
                for (JRadioButton jRadioButton : extrasList) {
                    jRadioButton.setSelected(false);
                }

            }
        }
}

