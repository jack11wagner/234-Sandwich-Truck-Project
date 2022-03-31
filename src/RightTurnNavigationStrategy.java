import java.util.LinkedList;
import java.util.Collection;


/**
 * Author: Michael Shimer
 * This class implements the NavigationStrategy for right turn only navigation
 *
 * Edits:
 */

public class RightTurnNavigationStrategy implements NavigationStrategy {

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



    /**
     * default constructor method for the right turn navigation strategy
     */
    public RightTurnNavigationStrategy() {
        this.direction = 0;
        navigationInstructions = new LinkedList<>();
    }


    /**
     * constructor method for the right turn navigation strategy that takes an initial direction
     * as a parameter
     */
    public RightTurnNavigationStrategy(double initialDirection) {
        this.direction = initialDirection;
        navigationInstructions = new LinkedList<>();
    }


    /**
     * main method that executes an algorithm to make the truck go from one destination
     * to the next while only making right turns. The truck executes a different set of instructions
     * depending on where the next destination is in relation to its current location
     * @params: and integer begDirection which correlates to the initial direction the truck
     * is facing at the start of the program, an integer array begCoords which hold the initial
     * coordinates of the truck on the map, and a collection of integer arrays destCoords which holds all
     * coordinates of each destination the truck needs to go to
     * @returns: returns a collection of integer arrays which hold the instructions to
     * correctly navigate the map
     */
    @Override
    public Collection<int[]> calculateNavInstructions(int begDirection, int[] begCoords, Collection<int[]> destCoords) {

        this.begX = begCoords[0];
        this.begY = begCoords[1];
        this.currentX = begX;
        this.currentY = begY;

        for (int[] destination : destCoords) {

            this.destX = destination[0];
            this.destY = destination[1];

            // if address is in front or behind truck
            // address is below truck
            if ((destX == currentX) && (destY > currentY)) {
                // in front of truck
                if (isDirectionDown()) {
                    addYInstruction(destY - currentY);
                }
                // behind truck
                if (isDirectionUp()) {
                    goToFirstIntersection();
                    turnAround();
                    // go to intersection just past the destination and turn around
                    while (destY > currentY) {
                        goToNextIntersection();
                    }
                    turnAround();
                    // go to destination
                    addYInstruction(destY - currentY);
                }
            }
            //address is above truck
            else if ((destX == currentX) && (destY < currentY)) {
                // in front of truck
                if (isDirectionUp()) {
                    addYInstruction(destY - currentY);
                }
                // behind truck
                if (isDirectionDown()) {
                    goToFirstIntersection();
                    turnAround();
                    // go to intersection just past the destination and turn around
                    while (destY < currentY){
                        goToNextIntersection();
                    }
                    turnAround();
                    // go to destination
                    addYInstruction(destY - currentY);
                }
            }
            //address is to the left of truck
            else if ((destY == currentY) && (destX < currentX)) {
                // in front of truck
                if (isDirectionLeft()) {
                    addXInstruction(destX - currentX);
                }
                // behind truck
                if (isDirectionRight()) {
                    goToFirstIntersection();
                    turnAround();
                    // go to intersection just past the destination and turn around
                    while (destX < currentX) {
                        goToNextIntersection();
                    }
                    turnAround();
                    // go to destination
                    addXInstruction(destX - currentX);
                }
            }
            // address is to the right of truck
            else if ((destY == currentY) && (destX > currentX)) {
                // in front of truck
                if (isDirectionRight()) {
                    addXInstruction(destX - currentX);
                }
                // behind truck
                if (isDirectionLeft()) {
                    goToFirstIntersection();
                    turnAround();
                    // go to intersection just past the destination and turn around
                    while (destX > currentX) {
                        goToNextIntersection();
                    }
                    turnAround();
                    // go to destination
                    addXInstruction(destX - currentX);
                }
            }
            // address if down and to the right of truck
            else if ((destY > currentY) && (destX > currentX)) {
                if (isDirectionRight()) {
                    goToFirstIntersection();
                    // go to intersection at or just past the destination and turn right
                    while (destX > currentX) {
                        goToNextIntersection();
                    }
                    turnRight();
                    // go down to destination or intersection of destination
                    addYInstruction(destY - currentY);
                    // if destination on number street turn right and go to destination
                    if (destX != currentX) {
                        turnRight();
                        addXInstruction(destX - currentX);
                    }
                }
                else if (isDirectionLeft()) {
                    goToFirstIntersection();
                    turnAround();
                    // go to intersection at or just past the destination and turn right
                    while (destX > currentX) {
                        goToNextIntersection();
                    }
                    turnRight();
                    // go down to destination or intersection of destination
                    addYInstruction(destY - currentY);
                    // if destination on number street turn right and go to destination
                    if (destX != currentX) {
                        turnRight();
                        addXInstruction(destX - currentX);
                    }
                }
                else if (isDirectionUp()) {
                    goToFirstIntersection();
                    turnRight();
                    // go to intersection at or just past the destination and turn right
                    while (destX > currentX) {
                        goToNextIntersection();
                    }
                    turnRight();
                    // go down to destination or intersection of destination
                    addYInstruction(destY - currentY);
                    // if destination on number street turn right and go to destination
                    if (destX != currentX) {
                        turnRight();
                        addXInstruction(destX - currentX);
                    }
                }
                else {
                    goToFirstIntersection();
                    turnAround();
                    goToNextIntersection();
                    turnRight();
                    // go to intersection at or just past the destination and turn right
                    while (destX > currentX) {
                        goToNextIntersection();
                    }
                    turnRight();
                    // go down to destination or intersection of destination
                    addYInstruction(destY - currentY);
                    // if destination on number street turn right and go to destination
                    if (destX != currentX) {
                        turnRight();
                        addXInstruction(destX - currentX);
                    }
                }
            }
            // address is up and to the right of truck
            else if ((destY < currentY) && (destX > currentX)) {
                if (isDirectionRight()) {
                    goToFirstIntersection();
                    turnAround();
                    goToNextIntersection();
                    turnRight();
                    // go up to intersection of destination or just past destination turn right
                    while (destY < currentY) {
                        goToNextIntersection();
                    }
                    turnRight();
                    // go to destination or intersection of destination
                    addXInstruction(destX - currentX);
                    // if address on letter street turn right and go to destination
                    if (destY != currentY) {
                        turnRight();
                        addYInstruction(destY - currentY);
                    }
                }
                else if (isDirectionLeft()) {
                    goToFirstIntersection();
                    turnRight();
                    // go up to intersection of destination or just past destination turn right
                    while (destY < currentY) {
                        goToNextIntersection();
                    }
                    turnRight();
                    // go to destination or intersection of destination
                    addXInstruction(destX - currentX);
                    // if address on letter street turn right and go to destination
                    if (destY != currentY) {
                        turnRight();
                        addYInstruction(destY - currentY);
                    }
                }
                else if (isDirectionUp()) {
                    goToFirstIntersection();
                    // go up to intersection of destination or just past destination turn right
                    while (destY < currentY) {
                        goToNextIntersection();
                    }
                    turnRight();
                    // go to destination or intersection of destination
                    addXInstruction(destX - currentX);
                    // if address on letter street turn right and go to destination
                    if (destY != currentY) {
                        turnRight();
                        addYInstruction(destY - currentY);
                    }
                }
                else {
                    goToFirstIntersection();
                    turnAround();
                    // go up to intersection of destination or just past destination turn right
                    while (destY < currentY) {
                        goToNextIntersection();
                    }
                    turnRight();
                    // go to destination or intersection of destination
                    addXInstruction(destX - currentX);
                    // if address on letter street turn right and go to destination
                    if (destY != currentY) {
                        turnRight();
                        addYInstruction(destY - currentY);
                    }
                }
            }
            // address is down and to the left of truck
            else if ((destY > currentY) && (destX < currentX)) {
                if (isDirectionRight()) {
                    goToFirstIntersection();
                    turnRight();
                    // go down to intersection of destination or just past intersection and turn right
                    while (destY > currentY) {
                        goToNextIntersection();
                    }
                    turnRight();
                    // go to destination or intersection of destination
                    addXInstruction(destX - currentX);
                    // if destination on letter street turn right and go to destination
                    if (destY != currentY) {
                        turnRight();
                        addYInstruction(destY - currentY);
                    }
                }
                else if (isDirectionLeft()) {
                    goToFirstIntersection();
                    turnRight();
                    goToNextIntersection();
                    turnAround();
                    // go down to intersection of destination or just past intersection and turn right
                    while (destY > currentY) {
                        goToNextIntersection();
                    }
                    turnRight();
                    // go to destination or intersection of destination
                    addXInstruction(destX - currentX);
                    // if destination on letter street turn right and go to destination
                    if (destY != currentY) {
                        turnRight();
                        addYInstruction(destY - currentY);
                    }
                }
                else if (isDirectionUp()) {
                    goToFirstIntersection();
                    turnAround();
                    // go down to intersection of destination or just past intersection and turn right
                    while (destY > currentY) {
                        goToNextIntersection();
                    }
                    turnRight();
                    // go to destination or intersection of destination
                    addXInstruction(destX - currentX);
                    // if destination on letter street turn right and go to destination
                    if (destY != currentY) {
                        turnRight();
                        addYInstruction(destY - currentY);
                    }
                }
                else {
                    goToFirstIntersection();
                    // go down to intersection of destination or just past intersection and turn right
                    while (destY > currentY) {
                        goToNextIntersection();
                    }
                    turnRight();
                    // go to destination or intersection of destination
                    addXInstruction(destX - currentX);
                    // if destination on letter street turn right and go to destination
                    if (destY != currentY) {
                        turnRight();
                        addYInstruction(destY - currentY);
                    }
                }
            }
            // address is up and to the left of truck
            else if ((destY < currentY) && (destX < currentX)) {
                if (isDirectionRight()) {
                    goToFirstIntersection();
                    turnAround();
                    // go to intersection of destination or just past destination and turn right
                    while (destX < currentX) {
                        goToNextIntersection();
                    }
                    turnRight();
                    // go to destination or intersection of destination
                    addYInstruction(destY - currentY);
                    // if destination on number street turn right and go to destination
                    if (destX != currentX) {
                        turnRight();
                        addXInstruction(destX - currentX);
                    }
                }
                else if (isDirectionLeft()) {
                    goToFirstIntersection();
                    // go to intersection of destination or just past destination and turn right
                    while (destX < currentX) {
                        goToNextIntersection();
                    }
                    turnRight();
                    // go to destination or intersection of destination
                    addYInstruction(destY - currentY);
                    // if destination on number street turn right and go to destination
                    if (destX != currentX) {
                        turnRight();
                        addXInstruction(destX - currentX);
                    }
                }
                else if (isDirectionUp()) {
                    goToFirstIntersection();
                    turnAround();
                    goToNextIntersection();
                    turnRight();
                    // go to intersection of destination or just past destination and turn right
                    while (destX < currentX) {
                        goToNextIntersection();
                    }
                    turnRight();
                    // go to destination or intersection of destination
                    addYInstruction(destY - currentY);
                    // if destination on number street turn right and go to destination
                    if (destX != currentX) {
                        turnRight();
                        addXInstruction(destX - currentX);
                    }
                }
                else {
                    goToFirstIntersection();
                    turnRight();
                    // go to intersection of destination or just past destination and turn right
                    while (destX < currentX) {
                        goToNextIntersection();
                    }
                    turnRight();
                    // go to destination or intersection of destination
                    addYInstruction(destY - currentY);
                    // if destination on number street turn right and go to destination
                    if (destX != currentX) {
                        turnRight();
                        addXInstruction(destX - currentX);
                    }
                }
            }
            addEndInstruction();
        }
        return navigationInstructions;
    }




    /**
     * helper method that executes instructions such that the truck turns around by
     * turning right, going to the next block, and turning right again
     */
    private void turnAround() {
        turnRight();
        goToNextIntersection();
        turnRight();

    }

    /**
     * helper method that adds an instruction to make the truck go from one intersection to the next
     * in the direction it is facing
     */
    private void goToNextIntersection() {
        int nextIntersection;
        int intSpace = (int)spacing;

            if (isDirectionLeft()) {
                // move left to next intersection
                nextIntersection = currentX - intSpace;
                xDistance = nextIntersection - currentX;
                addXInstruction(xDistance);
            }
            if (isDirectionRight()) {
                // move right to the next intersection
                nextIntersection = currentX + intSpace;
                xDistance = nextIntersection - currentX;
                addXInstruction(xDistance);
            }

            if (isDirectionDown()) {
                // find next intersection with larger y coordinate
                nextIntersection = currentY + intSpace;
                yDistance = nextIntersection - currentY;
                addYInstruction(yDistance);
            }
            if (isDirectionUp()) {
                // find next intersection with smaller y coordinate
                nextIntersection = currentY - intSpace;
                yDistance = nextIntersection - currentY;
                addYInstruction(yDistance);
            }
    }


    /**
     * helper method that adds the proper instructions to make the truck go to the intersection it is facing
     */
    private void goToFirstIntersection() {
        int intersectionX;
        int intersectionY;
        int intSpace = (int) spacing;

            // determine the coordinate of the next intersection (must be in the direction that the truck is facing)
            // subtract current location from destination so that the sign of the direction is correct
            if (isDirectionDown()) {
                // find next intersection with larger y coordinate
                intersectionY = (intSpace * (currentY / intSpace)) + intSpace;
                yDistance = intersectionY - currentY;
                addYInstruction(yDistance);

            }
            else if (isDirectionUp()) {
                // find next intersection with smaller y coordinate
                intersectionY = (intSpace * (currentY / intSpace));
                yDistance = intersectionY - currentY;
                addYInstruction(yDistance);
            }

            // determine the coordinate of the next intersection (must be in the direction that the truck is facing)
            // subtract destination from current location so that the sign of the direction is correct
            if (isDirectionLeft()) {
                // find next intersection with smaller x coordinate
                intersectionX = (intSpace * (currentX / intSpace));
                xDistance = intersectionX - currentX;
                addXInstruction(xDistance);
            }
            if (isDirectionRight()) {
                // find next intersection with larger x coordinate
                intersectionX = (intSpace * (currentX / intSpace)) + intSpace;
                xDistance = intersectionX - currentX;
                addXInstruction(xDistance);

            }
    }


    /**
     * helper method that adds an instruction to signify that the destination has been reached
     */
    private void addEndInstruction() {
        navigationInstructions.add(new int[]{0, -1});
    }


    /**
     * helper method that adds an instruction in the x direction and updates the current x coordinate
     * @param: an integer value defining the distance that should be travelled
     */
    private void addXInstruction(int distance) {
        if (distance > 0) {
            addRightInstruction(Math.abs(distance));
        }
        else {
            addLeftInstruction(Math.abs(distance));
        }
        currentX += distance;
    }


    /**
     * helper method that adds an instruction in the y direction and updates the current x coordinate
     * @param: an integer value defining the distance that should be travelled
     */
    private void addYInstruction(int distance) {
        if (distance > 0) {
            addDownInstruction(Math.abs(distance));
        }
        else {
            addUpInstruction(Math.abs(distance));
        }
        currentY += distance;
    }


    /**
     * helper method that adds an instruction to make truck travel down
     * @param: an integer value defining the distance that should be travelled
     */
    private void addDownInstruction(int distance) {
        navigationInstructions.add(new int[]{1, distance});
    }


    /**
     * helper method that adds an instruction to make truck travel up
     * @param: an integer value defining the distance that should be travelled
     */
    private void addUpInstruction(int distance) {
        navigationInstructions.add(new int[]{3, distance});
    }


    /**
     * helper method that adds an instruction to make truck travel to the right
     * @param: an integer value defining the distance that should be travelled
     */
    private void addRightInstruction(int distance) {
        navigationInstructions.add(new int[]{0, distance});
    }


    /**
     * helper method that adds an instruction to make truck travel to the left
     * @param: an integer value defining the distance that should be travelled
     */
    private void addLeftInstruction(int distance) {
        navigationInstructions.add(new int[]{2, distance});
    }


    /**
     * helper method that causes the truck to turn right by adding half of pi to its current direction
     */
    private void turnRight() {
        direction += 3.14 / 2;
        if (direction == 6.28) {
            direction = 0;
        }
    }

    /**
     * helper method that determines if the truck is currently on a number named road
     * @returns: a boolean value, true if the truck is on a number named road
     */
    private boolean isOnNumberRoad() {
        return ((currentY % spacing) == 0);
    }

    /**
     * helper method that determines if the truck is currently on a letter named road
     * @returns: a boolean value, true if the truck is on a letter named road
     */
    private boolean isOnLetterRoad() {
        return ((currentX % spacing) == 0);
    }


    /**
     * helper method that determines if the truck is facing right
     * @returns: a boolean value, true if the truck's direction is right
     */
    private boolean isDirectionRight() {
        return (this.direction == 0);
    }


    /**
     * helper method that determines if the truck is facing left
     * @returns: a boolean value, true if the truck's direction is left
     */
    private boolean isDirectionLeft() {
        return (this.direction == 3.14);
    }


    /**
     * helper method that determines if the truck is facing Down
     * @returns: a boolean value, true if the truck's direction is Down
     */
    private boolean isDirectionDown() {
        return (this.direction == 3.14 / 2);
    }


    /**
     * helper method that determines if the truck is facing up
     * @returns: a boolean value, true if the truck's direction is up
     */
    private boolean isDirectionUp() {
        return (this.direction == 4.71);
    }


}


