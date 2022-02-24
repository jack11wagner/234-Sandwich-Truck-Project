/*
Made by: Nikolas Kovacs
Draws roads on a JPanel based on size of screen
Draw the truck at its current location
Draw a pin on the map for current order destination (soon)
Edits by:
*/
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TruckMap extends JPanel {
    private static BufferedImage truckImage;
    private int truckX;
    private int truckY;

    TruckMap() {
        this.setPreferredSize(new Dimension(SimSettings.DIMENSION, SimSettings.DIMENSION));

        // load the truck image
        try {
            truckImage = ImageIO.read(new File("images/truck.png"));
        } catch (IOException e) {
            truckImage = new BufferedImage(1, 1, 1);
            int rgb = new Color(255,0,255).getRGB();
            truckImage.setRGB(0, 0, rgb);
        }
        // Truck begins at the intersection of roads E5
        truckX = SimSettings.ROAD_SPACING * 4; // Road E
        truckY = SimSettings.ROAD_SPACING * 5; // Road 5
    }

    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        g2D.setStroke(new BasicStroke(SimSettings.ROAD_WIDTH));
        int halfRoadWidth = SimSettings.ROAD_WIDTH / 2;
        int verticalRoadName = SimSettings.NUM_ROADS;
        char horizontalRoadName = 'A';

        for (int i = 0; i < SimSettings.NUM_ROADS; i++) {
            // draw roads
            g2D.setPaint(Color.BLACK);
            g2D.drawLine(SimSettings.ROAD_SPACING * i + halfRoadWidth, 0, SimSettings.ROAD_SPACING * i + halfRoadWidth, SimSettings.DIMENSION);// vertical
            g2D.drawLine(0, SimSettings.ROAD_SPACING * i + halfRoadWidth, SimSettings.DIMENSION, SimSettings.ROAD_SPACING * i + halfRoadWidth);// horizontal

            // draw road names
            g2D.setPaint(Color.YELLOW);
            g2D.drawString(Character.toString(horizontalRoadName), SimSettings.ROAD_SPACING * i + halfRoadWidth / 2, SimSettings.DIMENSION - 25);
            horizontalRoadName++;
            g2D.drawString(Integer.toString(verticalRoadName), SimSettings.ROAD_SPACING / 2 - 15, SimSettings.ROAD_SPACING * i + halfRoadWidth + 5);
            verticalRoadName--;
        }
        // adjust the drawing coordinates of the truck, so it appears centered in the road
        int[] coords = adjustCoords(new int[]{truckX, truckY});
        truckX = coords[0];
        truckY = coords[1];
        // draw the truck image with the new, adjusted coordinates
        g2D.drawImage(truckImage, truckX, truckY, SimSettings.TRUCK_SIZE, SimSettings.TRUCK_SIZE, null);
    }


    // this method is meant to offset the coordinates so the truck appears centered with the road.
    // Otherwise, the top-left corner of the truck is centered in the road instead.
    private int[] adjustCoords(int[] coords) {
        int truckX = coords[0];
        int truckY = coords[1];
        int offset = SimSettings.TRUCK_SIZE / 2;
        truckX -= offset;
        truckY -= offset;
        truckX += SimSettings.ROAD_WIDTH / 2;
        truckY += SimSettings.ROAD_WIDTH / 2;

        return new int[]{truckX, truckY};
    }

    // Set new location of Truck
    public void setNewTruckCoords(int[] coords) {
        this.truckX = coords[0];
        this.truckY = coords[1];
    }
}

