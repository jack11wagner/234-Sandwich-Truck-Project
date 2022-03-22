/**
Author: Nikolas Kovacs
This Class contains all the components to create and draw on the GUI

Edits by:
*/
import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    private TruckMap tMap = new TruckMap();
    private JButton start_button;

    Window() {
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
        tMap.addPinLocation(new int[]{x, y});
    }

    public void removePin() {
        tMap.removePin();
    }
}
