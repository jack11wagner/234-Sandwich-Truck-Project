/**
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
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

public class TruckMap extends JPanel {
    private static BufferedImage truckImage;
    private static BufferedImage pinImage;
    private static BufferedImage nextPinImage;
    private int truckX;
    private int truckY;
    private LinkedList<int[]> pinLocations;
    private String deliveryText = "";


    TruckMap() {
        /**
         * Constructor for the TruckMap mainly just reading in our image files and setting Map settings
         */
        this.setPreferredSize(new Dimension(SimSettings.DIMENSION, SimSettings.DIMENSION));

        pinLocations = new LinkedList<>();

        // load the truck image
        try {
            truckImage = ImageIO.read(new File("images/truck.png"));
        } catch (IOException e) {
            setBackupImage(truckImage);
        }

        // load pin_for_location.png
        try {
            pinImage = ImageIO.read(new File("images/pin_for_location.png"));
        } catch (IOException e) {
            setBackupImage(pinImage);
        }

        // load next_destination_pin.png
        try {
            nextPinImage = ImageIO.read(new File("images/next_destination_pin.png"));
        } catch (IOException e) {
            setBackupImage(pinImage);
        }

        // Truck begins at the intersection of roads E5
        truckX = SimSettings.INITIAL_TRUCK_X;
        truckY = SimSettings.INITIAL_TRUCK_Y;
    }


    private void setBackupImage(BufferedImage image) {
        /**
         * This method sets a colored square as the backup image if the file cannot be found
         */
        image = new BufferedImage(1, 1, 1);
        int rgb = new Color(255, 0, 255).getRGB();
        image.setRGB(0, 0, rgb);
    }

    public void paint(Graphics g) {
        /**
         * This method paints everything on the gui, including the roads, road names, truck, houses and location pins.
         */
        Graphics2D g2D = (Graphics2D) g;
        g2D.setStroke(new BasicStroke(SimSettings.ROAD_WIDTH));
        int halfRoadWidth = SimSettings.ROAD_WIDTH / 2;
        int verticalRoadName = SimSettings.NUM_ROADS;
        char horizontalRoadName = 'A';

        // paint the background light gray
        g2D.setColor(new Color(200, 200, 200));
        g2D.fillRect(0, 0, SimSettings.DIMENSION, SimSettings.DIMENSION);

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
        int[] coords = adjustTruckCoords(new int[]{truckX, truckY});
        truckX = coords[0];
        truckY = coords[1];
        // draw the truck image with the new, adjusted coordinates
        g2D.drawImage(truckImage, truckX, truckY, SimSettings.TRUCK_SIZE, SimSettings.TRUCK_SIZE, null);


        boolean firstPin = true;
        for (int i =0; i<pinLocations.size();i++) {
            int [] pinCoords = adjustPinCoords(pinLocations.get(i));
            int pinX = pinCoords[0];
            int pinY = pinCoords[1];

            if (firstPin) {
                g2D.drawImage(nextPinImage, pinX, pinY, SimSettings.PIN_SIZE, SimSettings.PIN_SIZE, null);
                firstPin = false;
            } else {
                g2D.drawImage(pinImage, pinX, pinY, SimSettings.PIN_SIZE, SimSettings.PIN_SIZE, null);
            }
        }
        g2D.setPaint(Color.GREEN);
        if (truckX <= 10)
            g2D.drawString(deliveryText, truckX + 5, truckY - 3);
        else if (truckX >= SimSettings.DIMENSION - 10)
            g2D.drawString(deliveryText, truckX - 15, truckY - 3);
        else if (truckY <= 10)
            g2D.drawString(deliveryText, truckX, truckY + 5);
        else
            g2D.drawString(deliveryText, truckX, truckY + 5);
    }


    // this method is meant to offset the coordinates so the truck appears centered with the road.
    // Otherwise, the top-left corner of the truck is centered in the road instead.
    private int[] adjustTruckCoords(int[] coords) {
        /**
         * This method offsets the coordinates of the truck before drawing it to the screen to ensure
         * that it is centered on the road
         * :param: array of coordinates to be adjusted
         * :return: array of adjusted coordinates
         */
        truckX = coords[0];
        truckY = coords[1];
        int offset = SimSettings.TRUCK_SIZE / 2;
        truckX -= offset;
        truckY -= offset;
        truckX += SimSettings.ROAD_WIDTH / 2;
        truckY += SimSettings.ROAD_WIDTH / 2;

        return new int[]{truckX, truckY};
    }

    private int[] adjustPinCoords(int[] coords) {
        /**
         * This method offsets the coordinates of the pin before drawing it to the screen to ensure
         * that it is centered on the road
         * :param: array of coordinates to be adjusted
         * :return: array of adjusted coordinates
         */
        int X = coords[0];
        int Y = coords[1];
        int offset = SimSettings.PIN_SIZE / 2;
        X -= offset;
        Y -= offset;
        X += SimSettings.ROAD_WIDTH / 2;
        Y += SimSettings.ROAD_WIDTH / 2;

        return new int[]{X, Y};
    }

    // Set new location of Truck
    public void setNewTruckCoords(int[] coords) {
        /**
         * This method is intended to be called from outside of the TruckMap class in order to update the location
         * of the truck on the screen
         * :param: array of coordinates
         */
        this.truckX = coords[0];
        this.truckY = coords[1];
    }

    public void addPinLocation(int[] coords) {
        pinLocations.add(coords);
    }

    public void removePin() {
        pinLocations.remove(0);
    }

    public void setDeliveryText(String text){
        deliveryText = text;
    }
}

