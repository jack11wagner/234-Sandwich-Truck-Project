import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;

public class OrderInterface extends JFrame implements ActionListener {

    // Components of the Form
    private Container c;
    private JLabel title;
    private JLabel nameLabel;
    private JTextField name;
    private JLabel orderAddressLabel;
    private JTextArea orderAddress;
    private JLabel sandwichLabel;
    private JComboBox sandwichOrder;
    private JLabel additionalNotesLabel;
    private JTextArea additionalNotes;
    private JButton submitButton;
    private JButton resetButton;
    private JTextArea summary;
    private JLabel res;
    private JTextArea resadd;

    private String[] sandwiches = {};

    // constructor, to initialize the components
    // with default values.
    public OrderInterface() {
        setTitle("Order Form");
        setBounds(300, 90, 900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        c = getContentPane();
        c.setLayout(null);

        title = new JLabel("Order Form");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setSize(300, 30);
        title.setLocation(300, 30);
        c.add(title);

        nameLabel = new JLabel("Name");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        nameLabel.setSize(100, 20);
        nameLabel.setLocation(100, 100);
        c.add(nameLabel);

        name = new JTextField();
        name.setFont(new Font("Arial", Font.PLAIN, 15));
        name.setSize(190, 20);
        name.setLocation(200, 100);
        c.add(name);

        orderAddressLabel = new JLabel("Address");
        orderAddressLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        orderAddressLabel.setSize(100, 20);
        orderAddressLabel.setLocation(100, 200);
        c.add(orderAddressLabel);

        orderAddress = new JTextArea();
        orderAddress.setFont(new Font("Arial", Font.PLAIN, 15));
        orderAddress.setSize(200, 75);
        orderAddress.setLocation(200, 200);
        orderAddress.setLineWrap(true);
        c.add(orderAddress);

        sandwichLabel = new JLabel("DOB");
        sandwichLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        sandwichLabel.setSize(100, 20);
        sandwichLabel.setLocation(100, 250);
        c.add(sandwichLabel);

        sandwichOrder = new JComboBox(sandwiches);
        sandwichOrder.setFont(new Font("Arial", Font.PLAIN, 15));
        sandwichOrder.setSize(100, 20);
        sandwichOrder.setLocation(200, 250);
        c.add(sandwichOrder);

        additionalNotesLabel = new JLabel("Additional Notes");
        additionalNotesLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        additionalNotesLabel.setSize(100, 20);
        additionalNotesLabel.setLocation(100, 300);
        c.add(additionalNotesLabel);

        additionalNotes = new JTextArea();
        additionalNotes.setFont(new Font("Arial", Font.PLAIN, 15));
        additionalNotes.setSize(200, 50);
        additionalNotes.setLocation(200, 300);
        additionalNotes.setLineWrap(true);
        c.add(additionalNotes);

        submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Arial", Font.PLAIN, 15));
        submitButton.setSize(100, 20);
        submitButton.setLocation(150, 450);
        submitButton.addActionListener(this);
        c.add(submitButton);

        resetButton = new JButton("Reset");
        resetButton.setFont(new Font("Arial", Font.PLAIN, 15));
        resetButton.setSize(100, 20);
        resetButton.setLocation(270, 450);
        resetButton.addActionListener(this);
        c.add(resetButton);

        summary = new JTextArea();
        summary.setFont(new Font("Arial", Font.PLAIN, 15));
        summary.setSize(300, 400);
        summary.setLocation(500, 100);
        summary.setLineWrap(true);
        summary.setEditable(false);
        c.add(summary);

        res = new JLabel("");
        res.setFont(new Font("Arial", Font.PLAIN, 20));
        res.setSize(500, 25);
        res.setLocation(100, 500);
        c.add(res);

        resadd = new JTextArea();
        resadd.setFont(new Font("Arial", Font.PLAIN, 15));
        resadd.setSize(200, 75);
        resadd.setLocation(580, 175);
        resadd.setLineWrap(true);
        c.add(resadd);

        setVisible(true);
    }

    // method actionPerformed()
        // to get the action performed
        // by the user and act accordingly
    @Override
    public void actionPerformed(ActionEvent e)
        {
            if (e.getSource() == submitButton) {
                    String data1;
                    String orderTime = Timestamp.valueOf(LocalDate.now().toString() + LocalTime.now().toString()).toString();
                    String data
                            = "Name : "
                            + name.getText() + "\n"
                            + "Address : "
                            + orderAddress.getText() + "\n"
                            + "Sandwich Order : "
                            + sandwichOrder.getSelectedItem() + "\n"
                            + "Order Time : "
                            + orderTime + "\n";

                    summary.setText(data);
                    summary.setEditable(false);
                    res.setText("Registration Successfully..");
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

