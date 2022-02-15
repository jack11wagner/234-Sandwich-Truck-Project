/*
Made by: Nikolas Kovacs
Draws roads on a JPanel based on size of screen

Edits by:
*/
import javax.swing.JPanel;
import java.awt.*;

public class TruckMap extends JPanel{
    private static int roadWidth = SimSettings.DIMENSION / 35;

    TruckMap() {
        this.setPreferredSize(new Dimension(SimSettings.DIMENSION, SimSettings.DIMENSION));
    }

    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        g2D.setStroke(new BasicStroke(roadWidth));

        // draw roads
        float spacing = SimSettings.DIMENSION / (SimSettings.NUM_ROADS - 1);
        for (int i = 0; i < SimSettings.NUM_ROADS; i++) {
            g2D.drawLine((int) spacing*i, 0, (int) spacing*i, SimSettings.DIMENSION);// vertical
            g2D.drawLine(0, (int) spacing*i, SimSettings.DIMENSION, (int) spacing*i);// horizontal
        }

        // line to separate buttons from map
//        g2D.setStroke(new BasicStroke(1));
//        g2D.drawLine(0, SimSettings.DIMENSION, SimSettings.DIMENSION, SimSettings.DIMENSION);


    }
}

