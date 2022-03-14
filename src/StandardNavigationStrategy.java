import java.util.Collection;
import java.util.LinkedList;

/**
 * Author: Nikolas Kovacs
 * This class implements the NavigationStrategy for Standard Navigation behavior
 */
public class StandardNavigationStrategy extends NavigationStrategy{

    public Collection<int[]> calculateNavInstructions(int[] begCoords, Collection<int[]> locations) {
        LinkedList<int[]> navigationInstructions = new LinkedList<>();
        // example element (0,2) move right 2, (1,2) move down two, (0, -2) move left 2, (1, -2) move up 2

        int begX = begCoords[0];
        int begY = begCoords[1];

        int currX = begX;
        int currY = begY;

        for (int[] location : locations) {
            int destX = location[0];
            int destY = location[1];

            int xDistance = destX - begX;
            int yDistance = destY - begY;

            if (begX / SimSettings.ROAD_SPACING == 0) {// X direction
                navigationInstructions.add(new int[]{0, xDistance});
                navigationInstructions.add(new int[]{1, yDistance});

            } else {
                navigationInstructions.add(new int[]{1, yDistance});
                navigationInstructions.add(new int[]{0, xDistance});
            }

            // set old destination coordinates to new beginning coordinates
            begX = destX;
            begY = destY;
        }


        return navigationInstructions;
    }
}
