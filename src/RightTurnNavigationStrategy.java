import java.util.Collection;
import java.util.LinkedList;

/**
 * Author: Michael Shimer
 * This class implements the NavigationStrategy for right turn only navigation
 *
 * Edits:
 */
public class RightTurnNavigationStrategy implements NavigationStrategy{

    private final double spacing = SimSettings.ROAD_SPACING;
    private double direction;
    private int begX;
    private int begY;
    private int destX;
    private int destY;
    private int currentX;
    private int currentY;
    private int xDistance;
    private int yDistance;
    LinkedList<int[]> navigationInstructions;

    public RightTurnNavigationStrategy() {
        this.direction = 0;
        navigationInstructions = new LinkedList<>();
    }

    public RightTurnNavigationStrategy(double initialDirection) {
        this.direction = initialDirection;
        navigationInstructions = new LinkedList<>();
    }

    @Override
    public Collection<int[]> calculateNavInstructions(int begDirection, int[] begCoords, Collection<int[]> destCoords) {
        // example element (0,2) move right 2, (1,2) move down two, (0, -2) move left 2, (1, -2) move up 2

        this.begX = begCoords[0];
        this.begY = begCoords[1];
        this.currentX = begX;
        this.currentY = begY;

        for (int[] destination : destCoords) {

            this.destX = destination[0];
            this.destY = destination[1];

            goToFirstIntersection();

            // navigationInstructions.add(new int[]{1, yDistance});

            while ((currentX != destX) & (currentY != destY)) {

                // see if on correct letter street
                if (currentX != destX) {

                    if (currentX > destX) {

                        // truck facing correct direction
                        if (isDirectionLeft()){
                            xDistance = destX - currentX;
                            navigationInstructions.add(new int[]{0, xDistance});
                        }

                        // truck is facing wrong direction
                        if (isDirectionRight()){
                            turnAround();
                        }
                    }

                    if (currentX < destX) {

                        // truck is facing wrong direction
                        if (isDirectionLeft()){
                            turnAround();
                        }

                        // truck is facing correct direction
                        if (isDirectionRight()){
                            xDistance = destX - currentX;
                            navigationInstructions.add(new int[]{0, xDistance});
                        }
                    }
                }

                // see if on correct number street
                if (currentY != destY) {

                    if (currentY > destY) {

                        // truck is facing correct direction
                        if (isDirectionUp()){
                            yDistance =  destY - currentY;
                            navigationInstructions.add(new int[]{1, yDistance});

                        }

                        // truck is facing wrong direction
                        if (isDirectionDown()){
                            turnAround();

                        }
                    }

                    if (currentY < destY) {

                        // truck is facing wrong direction
                        if (isDirectionUp()){
                            turnAround();
                        }

                        // truck is facing correct direction
                        if (isDirectionDown()){
                            yDistance =  destY - currentY;
                            navigationInstructions.add(new int[]{1, yDistance});
                        }
                    }

                }

                turnRight();

            }


        }
        return navigationInstructions;
    }

    //TODO
    private void goToFirstIntersection() {

        int intersectionX;
        int intersectionY;
        int intSpace = (int)spacing;

        // if on letter road, move in y direction to next intersection
        if (isOnLetterRoad()) {
            // determine the coordinate of the next intersection (must be in the direction that the truck is facing)
            // subtract current location from destination so that the sign of the direction is correct
            if (isDirectionDown()) {
                // find next intersection with larger y coordinate
                intersectionY = (intSpace * (currentY / intSpace)) + intSpace;
                yDistance = intersectionY - currentY;
            }
            if (isDirectionUp()) {
                // find next intersection with smaller y coordinate
                intersectionY = (intSpace * (currentY / intSpace));
                yDistance = intersectionY - currentY;
            }
            navigationInstructions.add(new int[]{1, yDistance});
        }


        // if on number road, move in x direction to next intersection
        if (isOnNumberRoad()) {
            // determine the coordinate of the next intersection (must be in the direction that the truck is facing)
            // subtract destination from current location so that the sign of the direction is correct
            if (isDirectionLeft()) {
                // find next intersection with smaller x coordinate
                intersectionX = (intSpace * (currentX / intSpace));
                xDistance = intersectionX - currentX;
            }
            if (isDirectionRight()) {
                // find next intersection with larger x coordinate
                intersectionX = (intSpace * (currentX / intSpace)) + intSpace;
                xDistance = intersectionX - currentX;
            }
            navigationInstructions.add(new int[]{0, xDistance});
        }
    }

    // turns truck around by going to next intersection and making a right turn (2x)
    private void turnAround() {
        if (isOnLetterRoad()) {
            turnRight();
            goToNextIntersection();
            turnRight();

        }

        if (isOnNumberRoad()) {
            turnRight();
            goToNextIntersection();
            turnRight();

        }
    }

    // moves truck to the intersection it is facing
    private void goToNextIntersection() {
        int nextIntersection;
        int intSpace = (int)spacing;

        if (isOnNumberRoad()) {
            if (isDirectionLeft()) {
                // move left to next intersection
                nextIntersection = currentX - intSpace;
                xDistance = currentX - nextIntersection;
            }
            if (isDirectionRight()) {
                // move right to the next intersection
                nextIntersection = currentX + intSpace;
                xDistance = currentX - nextIntersection;
            }
            navigationInstructions.add(new int[]{0, xDistance});
        }

        if (isOnLetterRoad()) {
            if (isDirectionDown()) {
                // find next intersection with larger y coordinate
                nextIntersection = currentY + intSpace;
                yDistance = nextIntersection - currentY;
            }
            if (isDirectionUp()) {
                // find next intersection with smaller y coordinate
                nextIntersection = currentY - intSpace;
                yDistance = nextIntersection - currentY;
            }
            navigationInstructions.add(new int[]{1, yDistance});
        }
    }


    private void turnRight() {
        /**
         * helper method that causes the truck to turn right by adding half of pi to its current direction
         */
        this.direction += 3.14 / 2;
        if (direction == 6.28) {
            direction = 0;
        }
    }

    private boolean isOnNumberRoad() {
        /**
         * helper method that determines if the truck is currently on a number named road
         * @returns: a boolean value, true if the truck is on a number named road
         */
        return ((begY % spacing) == 0);
    }

    private boolean isOnLetterRoad() {
        /**
         * helper method that determines if the truck is currently on a letter named road
         * @returns: a boolean value, true if the truck is on a letter named road
         */
        return ((begX % spacing) == 0);
    }

    private boolean isDirectionRight() {
        /**
         * helper method that determines if the truck is facing right
         * @returns: a boolean value, true if the truck's direction is right
         */
        return (this.direction == 0);
    }

    private boolean isDirectionLeft() {
        /**
         * helper method that determines if the truck is facing left
         * @returns: a boolean value, true if the truck's direction is left
         */
        return (this.direction == 3.14);
    }

    private boolean isDirectionDown() {
        /**
         * helper method that determines if the truck is facing Down
         * @returns: a boolean value, true if the truck's direction is Down
         */
        return (this.direction == 3.14 / 2);
    }

    private boolean isDirectionUp() {
        /**
         * helper method that determines if the truck is facing up
         * @returns: a boolean value, true if the truck's direction is up
         */
        return (this.direction == 4.71);
    }




    //might not need these methods

    public void setDirection(double direction) {

        this.direction = direction;
    }

    public double getDirection() {
        /**
         * returns the direction of the truck
         * @returns: a double data type correlating to the radian angle in which the truck is facing
         */
        return this.direction;
    }
}

