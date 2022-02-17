/*
Made by: Nikolas Kovacs
Draws roads on a JPanel based on size of screen

Edits by:
*/
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TruckMap extends JPanel {
    private static int roadWidth = SimSettings.DIMENSION / 35;
    private static BufferedImage truckImage;
    private int truckX;
    private int truckY;

    TruckMap() {
        this.setPreferredSize(new Dimension(SimSettings.DIMENSION, SimSettings.DIMENSION));
        try {
            truckImage = ImageIO.read(new File("ikmages/truck.png"));
        } catch (IOException e) {
            truckImage = new BufferedImage(1, 1, 1);
            int rgb = new Color(255,0,255).getRGB();
            for (int i = 0; i < truckImage.getWidth(); i++) {
                for (int j = 0; j < truckImage.getHeight(); j++) {
                    truckImage.setRGB(i, j, rgb);
                }
            }
        }
        // Truck begins at the intersection of roads E5
        truckX = SimSettings.ROAD_SPACING * 4; // Road E
        truckY = SimSettings.ROAD_SPACING * 4; // Road 5
    }

    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        g2D.setStroke(new BasicStroke(roadWidth));

        // draw roads
        for (int i = 0; i < SimSettings.NUM_ROADS; i++) {
            g2D.drawLine(SimSettings.ROAD_SPACING * i, 0, SimSettings.ROAD_SPACING * i, SimSettings.DIMENSION);// vertical
            g2D.drawLine(0, SimSettings.ROAD_SPACING * i, SimSettings.DIMENSION, SimSettings.ROAD_SPACING * i);// horizontal
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

        return new int[]{truckX, truckY};
    }

    // Set new location of Truck
    public void setNewTruckCoords(int[] coords) {
        this.truckX = coords[0];
        this.truckY = coords[1];
    }
}

