import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SandwichPrepInterface extends JFrame {
    /**
     * This Class Represents the Window that pops up when Sandwiches are being made
     *
     */
    private final Container sandwichPrepFrame;
    private final JLabel label;
    private final JTextArea sandwichComponentsTextArea;
    private final JProgressBar progBar;
    private String sandwich;
    private ArrayList<String> condiments = new ArrayList<>();


    SandwichPrepInterface(Order currOrder) throws InterruptedException {
        /**
         * Constructor for SandwichPrepInterface which handles all the components of the Frame
         * Adds a Text bar displaying the current time, a progress bar, and the sandwich order/condiments
         * This is triggered when the truck reaches an order destination
         */
        setTitle("Sandwich Prep");
        setBounds(1000, 0, 450, 170);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setResizable(false);
        sandwichPrepFrame = getContentPane();

        label = createJLabel(new JLabel("PREPARING SANDWICH"), 17, 250, 15, 135, 5);
        sandwichComponentsTextArea = getjTextArea();
        progBar = getjProgressBar();

        this.extractSandwichAndCondiments(currOrder.getSandwichOrder().split(" "));
        String sandwichPrepString =
                "Time : " + currOrder.getOrderTimestamp() + "\n" +
                "Making " + this.sandwich +
                "\nAdding condiments:  ";
        for(String condiment : condiments){
            sandwichPrepString+= condiment + " ";
        }
        sandwichPrepString += "\nCost : $" + String.format("%.2f", currOrder.getOrderCost());
        sandwichComponentsTextArea.setText(sandwichPrepString);

        sandwichPrepFrame.setLayout(new FlowLayout());
        this.setVisible(true);
        fill(progBar);
        Thread.sleep(1250);
        this.dispose();

    }

    private JProgressBar getjProgressBar() {
        /**
         * Creates a progress bar, this method is mainly used to reduce the confusion going on in the constructor
         */
        final JProgressBar progBar;
        progBar = new JProgressBar(0,100);
        progBar.setSize(500,500);
        progBar.setLocation(100, 100);
        progBar.setStringPainted(true);
        sandwichPrepFrame.add(progBar, FlowLayout.CENTER);
        return progBar;
    }

    private JTextArea getjTextArea() {
        /**
         * Creates a basic textArea on the Window Frame used to reduce amount of code in constructor
         */
        final JTextArea sandwichComponentsTextArea;
        sandwichComponentsTextArea = new JTextArea();
        sandwichComponentsTextArea.setFont(new Font("Arial", Font.PLAIN, 15));
        sandwichComponentsTextArea.setSize(400, 80);
        sandwichComponentsTextArea.setLocation(25, 25);
        sandwichComponentsTextArea.setLineWrap(true);
        sandwichComponentsTextArea.setEditable(false);
        sandwichPrepFrame.add(sandwichComponentsTextArea);
        return sandwichComponentsTextArea;
    }

    public void extractSandwichAndCondiments(String[] sandwichComponents)
    {
        /**
         * assigns the sandwich and condiments based on there location in the orderContents string
         */
        this.sandwich = sandwichComponents[0] + " " +sandwichComponents [1];
        if (sandwichComponents.length>3){
            for(int i = 3;i<sandwichComponents.length;i++) {
                this.condiments.add(sandwichComponents[i]);
            }
        }

    }
    private JLabel createJLabel(JLabel e, int fontSize, int sizeWidth, int sizeHeight, int locX, int locY) {
        /**
         * creates a basic JLabel you can specify the size and other things in the parameters
         */
        e.setFont(new Font("Arial", Font.BOLD, fontSize));
        e.setSize(sizeWidth, sizeHeight);
        e.setLocation(locX, locY);
        sandwichPrepFrame.add(e);
        return e;
    }


    public static void fill(JProgressBar progBar)
    {
        /**
         * This method fills up the progress bar by sleeping for 50 milliseconds before setting the value of the
         * progress bar
         */
        int i = 0;
        try {
            while (i <= 100) {
                // fill the menu bar
                progBar.setValue(i + 10);
                // delay the thread
                Thread.sleep(50);
                i += 1;
            }
        }
        catch (Exception e) {
        }
    }

}
