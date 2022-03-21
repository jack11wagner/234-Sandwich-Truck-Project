import java.util.Collection;
import java.util.LinkedList;

/**
 * Author: Nikolas Kovacs
 * This class implements the NavigationStrategy for Standard Navigation behavior
 */
public class StandardNavigationStrategy extends NavigationStrategy {
    private int currX;
    private int currY;
    private int destX;
    private int destY;
    private int direction;

    private Collection<int[]> navigationInstructions;

    public Collection<int[]> calculateNavInstructions(int begDirection, int[] begCoords, Collection<int[]> locations) {
        navigationInstructions = new LinkedList<>();
        // example element (0,2) move right 2, (1,2) move down two, (2, 2) move left 2, (3, 2) move up 2

        direction = begDirection;
        currX = begCoords[0];
        currY = begCoords[1];

        for (int[] location : locations) {


        }
    }
}