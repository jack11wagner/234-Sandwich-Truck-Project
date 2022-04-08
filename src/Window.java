/**
Author: Nikolas Kovacs
This Class contains all the components to create and draw on the GUI

Edits by:
*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Window extends JFrame implements ActionListener {
    private TruckMap tMap = new TruckMap();
    private JButton start_button;
    private JComboBox order_strategy_dropdown;
    private JComboBox navigation_strategy_dropdown;
    private JButton new_order_button;
    private boolean isReady = false;
    private boolean strategySelected = false;
    private OrderStrategy orderStrategy;
    private NavigationStrategy navigationStrategy;
    private String[] order_strategies = new String[] {"select strategy...", "Distance Based Strategy", "Time Based Strategy"};
    private String[] navigation_strategies = new String[] {"select strategy...", "Standard Navigation", "Right Turn Navigation"};
    OrderList ol = SimSettings.orderList;


    public OrderStrategy getOrderStrategy() {
        return orderStrategy;
    }

    public NavigationStrategy getNavigationStrategy() {
        return navigationStrategy;
    }

    Window() {
        /**
         * Handles the basic settings of the window including dimensions of the window, default closing,
         * This Constructor also adds the buttons, titles, and various panels seen on the Window GUI
         */
        super();
        this.setSize(SimSettings.DIMENSION, SimSettings.DIMENSION + 100);
        this.setTitle("Sandwich Truck Simulator");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        // Set up Map Panel (contains TruckMap)
        JPanel map_panel = new JPanel();
        map_panel.setPreferredSize(new Dimension(SimSettings.DIMENSION, SimSettings.DIMENSION));
        map_panel.add(tMap);
        this.add(map_panel);



        // Set up Control Panel (contains start button, Strategy selection dropdown button, create new order)
        JPanel control_panel = new JPanel();
        this.add(control_panel, BorderLayout.SOUTH);
        start_button = new JButton("Start");
        start_button.setBounds(0,SimSettings.DIMENSION - 10, 70, 80);
        control_panel.add(start_button, BorderLayout.WEST);
        start_button.addActionListener(this);

        order_strategy_dropdown = new JComboBox(order_strategies);
        order_strategy_dropdown.setBounds(0, SimSettings.DIMENSION + 10, 70, 80);
        control_panel.add(order_strategy_dropdown, BorderLayout.WEST);
        order_strategy_dropdown.addActionListener(this);

        navigation_strategy_dropdown = new JComboBox(navigation_strategies);
        navigation_strategy_dropdown.setBounds(0, SimSettings.DIMENSION+20, 70, 80);
        control_panel.add(navigation_strategy_dropdown, BorderLayout.WEST);
        navigation_strategy_dropdown.addActionListener(this);

        new_order_button = new JButton("Create New Order");
        new_order_button.setBounds(30, SimSettings.DIMENSION + 10, 100, 80);
        control_panel.add(new_order_button, BorderLayout.WEST);
        new_order_button.addActionListener(this);
        this.setVisible(true);
    }

    public void repaintTruck(int x, int y) {
        /**
         * This method is meant to be called from the Truck class so that it can update its location on the map
         * when it moves
         * :param1: int x, new x coordinate of truck
         * :param2: int y, new y coordinate of truck
         */
        tMap.setNewTruckCoords(new int[]{x, y});
        tMap.repaint();
    }

    public void addNewPinToMap(int x, int y) {
        /**
         * Adds a new pin to the Truck Map denoting an order's location
         *
         */
        tMap.addPinLocation(new int[]{x, y});
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /**
         * Listener method which awaits buttons on our GUI window to be pressed
         *
         */
        if (e.getSource() == new_order_button) {
            OrderInterface o = new OrderInterface();
        } else if (e.getSource() == order_strategy_dropdown) {
            switch (order_strategy_dropdown.getSelectedItem().toString()) {
                case "Distance Based Strategy" -> orderStrategy = new DistanceBasedStrategy(ol);
                case "Time Based Strategy" -> orderStrategy= new TimeBasedStrategy(ol);

            }
            System.out.println("Order Strategy Selected");
         }

        else if (e.getSource() == navigation_strategy_dropdown) {
            switch (navigation_strategy_dropdown.getSelectedItem().toString()) {
                case "Standard Navigation" -> navigationStrategy = new StandardNavigationStrategy();
                case "Right Turn Navigation" -> navigationStrategy = new RightTurnNavigationStrategy();
            }
            System.out.println("Navigation Strategy Selected");
        }
        if ((navigationStrategy!= null) && (orderStrategy != null)){
            strategySelected = true;
        }

        if (e.getSource() == start_button){
            isReady = true;
        }
    }

    public void removePin() {
        /**
         * Removes a pin if the truck has visited this location already
         *
         */
        tMap.removePin();
    }

    public boolean isReady() {
        /**
         * Helper function for main to denote that the start button has
         * been pressed and the simulation will now begin
         *
         */
        return isReady;
    }
    public void triggerSandwichModelingPanel(Order currOrder)
    {
        /**
         * Called whenever the truck reaches its destination this will create a new window that
         * displays the modeling of the sandwich making
         */
        //set up Sandwich Modeling Panel
        try {
            SandwichPrepInterface sandPrepInterface = new SandwichPrepInterface(currOrder);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    public boolean isStrategySelected(){
        /**
         * @returns the value of strategyselected
         */
        return strategySelected;
    }

    public void setDeliveryText(String text) {
        tMap.setDeliveryText(text);
    }
}
