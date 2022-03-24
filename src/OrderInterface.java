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
    private final JLabel title;
    private final JLabel nameLabel;
    private final JTextField name;
    private final JLabel orderAddressLabel;
    private final JTextArea orderAddress;
    private final JLabel sandwichLabel;
    private final JComboBox sandwichOrder;
    private final JLabel additionalNotesLabel;
    private final JTextArea additionalNotes;
    private final JButton submitButton;
    private final JButton resetButton;
    private final JTextArea summary;
    private final JLabel res;
    private final JTextArea resadd;
    private OrderList orderList;

    private final String[] sandwiches = {"Italian", "Bologna", "Roast Beef"};

    // constructor, to initialize the components
    // with default values.
    public OrderInterface() {

        setTitle("Order Form");
        setBounds(0, 0, 450, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        c = getContentPane();
        c.setLayout(null);

        title = new JLabel("Order Form");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setSize(250, 30);
        title.setLocation(125, 10);
        c.add(title);

        nameLabel = new JLabel("Name");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        nameLabel.setSize(80, 20);
        nameLabel.setLocation(75, 50);
        c.add(nameLabel);

        name = new JTextField();
        name.setFont(new Font("Arial", Font.PLAIN, 15));
        name.setSize(190, 20);
        name.setLocation(175, 50);
        c.add(name);

        orderAddressLabel = new JLabel("Address");
        orderAddressLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        orderAddressLabel.setSize(100, 20);
        orderAddressLabel.setLocation(55, 90);
        c.add(orderAddressLabel);

        orderAddress = new JTextArea();
        orderAddress.setFont(new Font("Arial", Font.PLAIN, 15));
        orderAddress.setSize(200, 75);
        orderAddress.setLocation(175, 90);
        orderAddress.setLineWrap(true);
        c.add(orderAddress);

        sandwichLabel = new JLabel("Sandwich");
        sandwichLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        sandwichLabel.setSize(100, 20);
        sandwichLabel.setLocation(55, 180);
        c.add(sandwichLabel);

        sandwichOrder = new JComboBox(sandwiches);
        sandwichOrder.setFont(new Font("Arial", Font.PLAIN, 15));
        sandwichOrder.setSize(175, 20);
        sandwichOrder.setLocation(175, 180);
        c.add(sandwichOrder);

        additionalNotesLabel = new JLabel("Notes");
        additionalNotesLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        additionalNotesLabel.setSize(100, 20);
        additionalNotesLabel.setLocation(55, 210);
        c.add(additionalNotesLabel);

        additionalNotes = new JTextArea();
        additionalNotes.setFont(new Font("Arial", Font.PLAIN, 15));
        additionalNotes.setSize(200, 50);
        additionalNotes.setLocation(175, 210);
        additionalNotes.setLineWrap(true);
        c.add(additionalNotes);

        submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Arial", Font.PLAIN, 15));
        submitButton.setSize(100, 20);
        submitButton.setLocation(125, 280);
        submitButton.addActionListener(this);
        c.add(submitButton);

        resetButton = new JButton("Reset");
        resetButton.setFont(new Font("Arial", Font.PLAIN, 15));
        resetButton.setSize(100, 20);
        resetButton.setLocation(245, 280);
        resetButton.addActionListener(this);
        c.add(resetButton);

        summary = new JTextArea();
        summary.setFont(new Font("Arial", Font.PLAIN, 15));
        summary.setSize(300, 170);
        summary.setLocation(75, 330);
        summary.setLineWrap(true);
        summary.setEditable(false);
        c.add(summary);

        res = new JLabel("");
        res.setFont(new Font("Arial", Font.PLAIN, 15));
        res.setSize(500, 25);
        res.setLocation(75, 300);
        c.add(res);

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
    }

    // method actionPerformed()
        // to get the action performed
        // by the user and act accordingly
    @Override
    public void actionPerformed(ActionEvent e)
        {
            if (e.getSource() == submitButton) {

                    String currTime = LocalDate.now() +" "+ LocalTime.now().toString();
                    String orderTime = Timestamp.valueOf(currTime.substring(0,currTime.length()-7)).toString();
                    String data
                            = "Name : "
                            + name.getText() + "\n"
                            + "Address : "
                            + orderAddress.getText().strip() + "\n"
                            + "Sandwich Order : "
                            + sandwichOrder.getSelectedItem() + "\n"
                            + "Notes : "
                            + additionalNotes.getText().strip() + "\n"
                            + "Order Time : "
                            + orderTime + "\n";

                    summary.setText(data);
                    summary.setEditable(false);
                    orderList.addOrder(new Order(orderTime.strip(), orderAddress.getText().strip(), sandwichOrder.getSelectedItem().toString()));
                try {
                    orderList.writeOrdersToFile();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                res.setText("Order Confirmed...");

                }

            else if (e.getSource() == resetButton) {
                String def = "";
                name.setText(def);
                orderAddress.setText(def);
                res.setText(def);
                summary.setText(def);
                resadd.setText(def);
            }
        }
}

