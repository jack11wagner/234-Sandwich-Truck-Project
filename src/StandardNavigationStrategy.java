import java.util.Collection;
import java.util.LinkedList;

/**
 * Author: Nikolas Kovacs
 * This class implements the NavigationStrategy for Standard Navigation behavior
 */

public class StandardNavigationStrategy implements NavigationStrategy {
    private int currX;
    private int currY;
    private int destX;
    private int destY;
    private int direction;

    private Collection<int[]> navigationInstructions;

    public Collection<int[]> calculateNavInstructions(int begDirection, int[] begCoords, Collection<int[]> locations) {
        /**
         * This method is called by the truck, and it returns a collection of instructions
         * - format of instructions described below
         */
        navigationInstructions = new LinkedList<>();
        // example element (0,2) move right 2, (1,2) move down two, (2, 2) move left 2, (3, 2) move up 2
        // an instruction with the second element as a -1 signifies destination reached and flag is to be removed.

        direction = begDirection;

        currX = begCoords[0];
        currY = begCoords[1];

        boolean first = true;
        for (int[] location : locations) {
            destX = location[0];
            destY = location[1];


            if (first) { // the truck can turn either left or right out of the depot
                if (direction == -1) {
                    direction = 0;
                    if (!destAndTruckInSameDirection())
                        direction = 2;
                }
                first = false;
            }
            while (currX != destX || currY != destY) {
                // while truck is not at the destination, iterate over these steps until there.
                if (destAndTruckInSameDirection() && destIsOnSameRoad()) { // if dest straight ahead, go to it
                    moveToDestStraightAhead();
                } else { // else, move to intersection and evaluate next move
                    moveToIntersection();
                    if (destOnVerticalRoad()) {
                        if (!destIsOnSameRoad()) {
                            setXDir();
                            while (currX != destX)
                                moveToIntersection();
                        }
                        setYDir();
                        moveToDestStraightAhead();
                    } else if (destOnHorizontalRoad()) {
                        if (!destIsOnSameRoad()) {
                            setYDir();
                            while (currY != destY)
                                moveToIntersection();
                        }
                        setXDir();
                        moveToDestStraightAhead();
                    }
                }
            }

            navigationInstructions.add(new int[]{-1,-1}); // signifies that a destination has been reached
        }
        return navigationInstructions;
    }

    private void setYDir() {
        /**
         * This method sets the Y direction of the truck, taking into consideration where it has to go
         * and which ways it cannot go (no U-turns)
         */
        int forbiddenDir = forbiddenDirection();
        direction = getYDiff() > 0 ? 1 : 3;

        if (getYDiff() == 0 && direction == forbiddenDir) {
            if (currY < SimSettings.DIMENSION / 2) direction = 1;
            moveToIntersection();
            setYDir();
        }

        if (direction == forbiddenDir) {
            // travel X-wise and try again
            setXDir();
            moveToIntersection();
            direction = getYDiff() > 0 ? 1 : 3;
        }
    }

    private void setXDir() {
        /**
         * This method sets the X direction of the truck, taking into consideration where it has to go
         * and which ways it cannot go (no U-turns)
         */
        int forbiddenDir = forbiddenDirection();
        direction = getXDiff() > 0 ? 0 : 2;

        if (getXDiff() == 0 && direction == forbiddenDir) {
            if (currX < SimSettings.DIMENSION / 2) direction = 0;
            moveToIntersection();
            setXDir();
        }

        if (direction == forbiddenDir) {
            // travel Y-wise and try again
            setYDir();
            moveToIntersection();
            direction = getXDiff() > 0 ? 0 : 2;
        }
    }

    private int forbiddenDirection() {
        /**
         * Returns the "forbidden direction" of a truck, aka which way it cannot go (no U-turns)
         */
        if (facingUp())
            return 1;
        if (facingDown())
            return 3;
        if (facingLeft())
            return 0;
        return 2;
    }

    private void moveToIntersection() {
        /**
         * This method moves the truck to the next intersection in its current direction
         */
        int intersection;
        int diff = 0;
        if (truckAtIntersection()) {
            navigationInstructions.add(new int[]{direction, SimSettings.ROAD_SPACING});
            adjustCurrentCoordinates(SimSettings.ROAD_SPACING);
        } else {
            if (facingLeft()) {
                intersection = currX / SimSettings.ROAD_SPACING;
                diff = Math.abs(currX - intersection * SimSettings.ROAD_SPACING);
            } else if (facingRight()) {
                intersection = (currX + SimSettings.ROAD_SPACING) / SimSettings.ROAD_SPACING;
                diff = Math.abs(intersection * SimSettings.ROAD_SPACING - currX);
            } else if (facingUp()) {
                intersection = currY / SimSettings.ROAD_SPACING;
                diff = Math.abs(intersection * SimSettings.ROAD_SPACING - currY);
            } else if (facingDown()) {
                intersection = (currY + SimSettings.ROAD_SPACING) / SimSettings.ROAD_SPACING;
                diff = Math.abs(intersection * SimSettings.ROAD_SPACING - currY);
            }
            navigationInstructions.add(new int[]{direction, diff});
            adjustCurrentCoordinates(diff);
        }
    }

    private void moveToDestStraightAhead() {
        /**
         * This method is called when the destination is immediately ahead of the truck
         */
        int diff;
        if (facingLeft() || facingRight()) {
            diff = getXDiff();
        } else {
            diff = getYDiff();
        }
        navigationInstructions.add(new int[]{direction, Math.abs(diff)});
        adjustCurrentCoordinates(Math.abs(diff));
    }

    private void adjustCurrentCoordinates(int diff) {
        /**
         * After adding instructions to the navigationInstructions, call this method to
         * reflect the proposed movement of the truck
         */
        if (facingLeft())
            currX -= diff;
        else if (facingRight())
            currX += diff;
        else if (facingUp())
            currY -= diff;
        else if (facingDown())
            currY += diff;
    }

    private boolean destIsOnSameRoad() {
        /**
         * returns True if the destination is on the same road as the truck, false otherwise
         */
        if (truckOnHorizontalRoad() && currY == destY)
            return true;
        else if (truckOnVerticalRoad() && currX == destX)
            return true;
        else
            return false;
    }

    private boolean truckOnHorizontalRoad() {
        /**
         * Returns true if the truck is on a horizontal road, false otherwise
         */
        float yQuotient = currY / (float)SimSettings.ROAD_SPACING;
        float yDifference = yQuotient - (int) yQuotient;
        return yDifference == 0;
    }

    private boolean truckOnVerticalRoad() {
        /**
         * Returns true if the truck is on a vertical road, false otherwise
         */
        float xQuotient = currX / (float)SimSettings.ROAD_SPACING;
        float xDifference = xQuotient - (int) xQuotient;
        return xDifference == 0;
    }

    private boolean destOnHorizontalRoad() {
        /**
         * Returns true if the destination is on a horizontal road, false otherwise
         */
        float yQuotient = destY / (float)SimSettings.ROAD_SPACING;
        float yDifference = yQuotient - (int) yQuotient;
        return yDifference == 0;
    }

    private boolean destOnVerticalRoad() {
        /**
         * Returns true if destination is on a vertical road, false otherwise
         */
        float xQuotient = destX / (float)SimSettings.ROAD_SPACING;
        float xDifference = xQuotient - (int) xQuotient;
        return xDifference == 0;
    }


    private boolean destAndTruckInSameDirection() {
        /**
         * Returns true if the destination is in the same direction that the truck is facing, false otherwise
         */
        if (facingRight() && destX > currX)
            return true;
        else if (facingLeft() && destX < currX)
            return true;
        else if (facingDown() && destY > currY)
            return true;
        else return facingUp() && destY < currY;
    }

    private boolean truckAtIntersection() {
        /**
         * Returns true if the truck is in an intersection, false otherwise
         */
        return truckOnVerticalRoad() && truckOnHorizontalRoad();
    }

    private boolean facingRight(){
        /**
         * Returns true if the truck is facing right, false otherwise
         */
        return direction == 0;
    }

    private boolean facingLeft() {
        /**
         * Returns true if the truck is facing left, false otherwise
         */
        return direction == 2;
    }

    private boolean facingDown() {
        /**
         * Returns true if the truck is facing down, false otherwise
         */
        return direction == 1;
    }

    private boolean facingUp() {
        /**
         * Returns true if the truck is facing up, false otherwise
         */
        return direction == 3;
    }

    private int getXDiff() {
        /**
         * Returns the difference in the destX and the currX
         */
        return destX - currX;
    }

    private int getYDiff() {
        /**
         * Returns the difference in the destY and the currY
         */
        return destY - currY;
    }
}