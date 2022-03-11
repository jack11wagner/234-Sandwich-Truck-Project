import java.util.Collection;
import java.util.LinkedList;

/**
 * Author: Nikolas Kovacs
 * This class implements the NavigationStrategy for Standard Navigation behavior
 */
public class StandardNavigationStrategy extends NavigationStrategy{

    public Collection<int[]> calculateNavInstructions(int[] begCoords, int[] destCoords) {
        LinkedList<int[]> navigationInstructions = new LinkedList<>();
        // example element (0,2) move right 2, (1,2) move down two, (0, -2) move left 2, (1, -2) move up 2

        int begX = begCoords[0];
        int begY = begCoords[1];
        int destX = destCoords[0];
        int destY = destCoords[1];

        int xDistance = destX - begX;
        int yDistance = destY - begY;

        // randomly choose whether to move in the X or Y direction first
        if ((int) (Math.random() * 1) == 0) {// X direction first
            navigationInstructions.add(new int[]{0, xDistance});
            navigationInstructions.add(new int[]{1, yDistance});
        } else {
            navigationInstructions.add(new int[]{1, yDistance});
            navigationInstructions.add(new int[]{0, xDistance});
        }


        return navigationInstructions;
    }
}
