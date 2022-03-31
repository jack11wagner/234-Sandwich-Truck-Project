/**
Author: Nikolas Kovacs
This Class contains all the components to create and draw on the GUI

Edits by:
*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends JFrame implements ActionListener {
    private TruckMap tMap = new TruckMap();
    private JButton start_button;
    private JButton new_order_button;
    private boolean isReady = false;

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

        // Set up Control Panel (contains start button)
        JPanel control_panel = new JPanel();
        this.add(control_panel, BorderLayout.SOUTH);
        start_button = new JButton("Start");
        start_button.setBounds(10, SimSettings.DIMENSION + 10, 100, 80);
        control_panel.add(start_button, BorderLayout.WEST);
        start_button.addActionListener(this);


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

    public void removePin() {
        /**
         * Removes a pin if the truck has visited this location already
         *
         */
        tMap.removePin();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /**
         * Listener method which awaits buttons on our GUI window to be pressed
         *
         */
//        if (e.getSource() == new_order_button){
//            OrderInterface myFrame = new OrderInterface();
//        }
        if (e.getSource() == start_button){
            isReady = true;
        }

    }
    public boolean isReady() {
        /**
         * Helper function for main to denote that the start button has
         * been pressed and the simulation will now begin
         *
         */
        return isReady;
    }

    public void setDeliveryText(String text) {
        tMap.setDeliveryText(text);
    }
}
