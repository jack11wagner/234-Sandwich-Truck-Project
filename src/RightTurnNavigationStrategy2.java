import java.util.LinkedList;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Author: Michael Shimer
 * This class implements the NavigationStrategy for right turn only navigation
 *
 * Edits:
 */


public class RightTurnNavigationStrategy2 implements NavigationStrategy {


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


    public RightTurnNavigationStrategy2() {
        this.direction = 0;
        navigationInstructions = new LinkedList<>();
    }


    public RightTurnNavigationStrategy2(double initialDirection) {
        this.direction = initialDirection;
        navigationInstructions = new LinkedList<>();
    }


    @Override
    public Collection<int[]> calculateNavInstructions(int begDirection, int[] begCoords, Collection<int[]> destCoords) {

        this.begX = begCoords[0];
        this.begY = begCoords[1];
        this.currentX = begX;
        this.currentY = begY;

        for (int[] destination : destCoords) {

            this.destX = destination[0];
            this.destY = destination[1];

            goToFirstIntersection();

            //while ((currentX != destX) || (currentY != destY)) {
                    // destination to the left of truck
                    if (currentX > destX) {
                        // facing wrong direction
                        if (isDirectionRight()) {
                            // truck already on correct number street
                            if (currentY == destY) {
                                turnAround();
                                // go to the intersection just past the destination and turn right
                                while (currentX > destX) {
                                    goToNextIntersection();
                                }
                                turnRight();
                                // go to the intersection where destination on street and turn right
                                goToNextIntersection();
                                turnRight();
                                // go to destination
                                addXInstruction(destX - currentX);
                            }

                            // destination above current location
                            else if (currentY > destY) {
                                turnAround();
                                // go to the intersection just past the destination and turn right
                                while (currentX > destX) {
                                    goToNextIntersection();
                                }
                                turnRight();
                                // go to the intersection where destination on street and turn right
                                addYInstruction(destY - currentY);
                                // if destination on number street turn right and go to destination
                                if (currentX < destX) {
                                    turnRight();
                                    // go to destination
                                    addXInstruction(destX - currentX);
                                }
                            }

                            // destination below current location
                            else if (currentY < destY) {
                                turnRight();
                                // go to intersection where destination is and turn right
                                while (currentY < destY) {
                                    goToNextIntersection();
                                }
                                turnRight();
                                // go to destination
                                addXInstruction(destX - currentX);
                                // if destination on letter street turn right and go to destination
                                if (currentY > destY) {
                                    turnRight();
                                    // go to destination
                                    addYInstruction(destY - currentY);
                                }
                            }
                        }

                        // facing correct direction
                        else if (isDirectionLeft()) {
                            // already on correct number street
                            if (currentY == destY) {
                                addXInstruction(destX - currentX);
                            }
                            // destination above current location
                            else if (currentY > destY) {
                                // go to the intersection just past the destination and turn right
                                while (currentX > destX) {
                                    goToNextIntersection();
                                }
                                turnRight();
                                // go to the intersection of the street of the destination and turn right
                                addYInstruction(destY - currentY);
                                // if destination on number street turn right and go to destination
                                if (currentX < destX) {
                                    turnRight();
                                    // go to destination
                                    addXInstruction(destX - currentX);
                                }

                            }
                            // destination below current location
                            else if (currentY < destY) {
                                // correctly orient truck using only right turns
                                turnAround();
                                goToNextIntersection();
                                turnRight();
                                // go to the intersection of the street of the destination and turn right
                                while (currentY < destY) {
                                    goToNextIntersection();
                                    turnRight();
                                }
                                // go to destination
                                addXInstruction(destX - currentX);
                                // if destination on letter street turn right and go to destination
                                if (currentY > destY) {
                                    turnRight();
                                    // go to destination
                                    addYInstruction(destY - currentY);
                                }
                            }
                        }
                    }

                    // destination to the right of truck
                    else if (currentX < destX) {
                        // facing correct direction
                        if (isDirectionRight()) {
                            // already on correct number street
                            if (currentY == destY) {
                                // go straight to destination
                                addXInstruction(destX - currentX);
                            }
                            // destination above current location
                            else if (currentY > destY) {
                                // orient truck to travel up
                                turnAround();
                                goToNextIntersection();
                                turnRight();
                                // go up to intersection of street of destination and turn right
                                while (currentY > destY) {
                                    goToNextIntersection();
                                }
                                turnRight();
                                // go straight to destination
                                addXInstruction(destX - currentX);
                                // if destination on letter street turn right and go to destination
                                if (currentY < destY) {
                                    turnRight();
                                    // go to destination
                                    addYInstruction(destY - currentY);
                                }
                            }
                            // destination below current location
                            else if (currentY < destY) {
                                // go to intersection just past the destination and turn right
                                while (currentX < destX) {
                                    goToNextIntersection();
                                }
                                turnRight();
                                // go down to intersection of street with destination and turn right
                                addYInstruction(destY - currentY);
                                // if destination on number street turn right and go to destination
                                if (currentX > destX) {
                                    turnRight();
                                    // go straight to destination
                                    addXInstruction(destX - currentX);
                                }
                            }
                        }

                        // facing wrong direction
                        else if (isDirectionLeft()) {
                            // already on correct number street
                            if (currentY == destY) {
                                turnAround();
                                // go to intersection just past the destination and turn right
                                while (currentX < destX) {
                                    goToNextIntersection();
                                }
                                turnRight();
                                // go to intersection of street with destination and turn right
                                goToNextIntersection();
                                turnRight();
                                // go straight to destination
                                addXInstruction(destX - currentX);
                            }
                            // destination above current location
                            else if (currentY > destY) {
                                turnRight();
                                // go up to intersection of street with destination and turn right
                                while (currentY > destY) {
                                    goToNextIntersection();
                                }
                                turnRight();
                                // go straight to destination
                                addXInstruction(destX - currentX);
                                // if destination on letter street turn right and go to destination
                                if (currentY < destY) {
                                    turnRight();
                                    addYInstruction(destY - currentY);
                                }
                            }
                            // destination below current location
                            else if (currentY < destY) {
                                turnAround();
                                // go to intersection just past destination and turn right
                                while (currentX < destX) {
                                    goToNextIntersection();
                                }
                                turnRight();
                                // go down to intersection of street with destination and turn right
                                addYInstruction(destY - currentY);
                                // if destination on number street turn right and go to destination
                                if (currentX > destX) {
                                    turnRight();
                                    // go straight to destination
                                    addXInstruction(destX - currentX);
                                }
                            }
                        }
                    }

                    // destination above the current location
                    else if (currentY > destY) {
                        // facing correct direction
                        if (isDirectionUp()) {
                            // already on correct letter street
                            if (currentX == destX) {
                                addYInstruction(destY - currentY);
                            }
                            // destination to the left of location
                            else if (currentX > destX) {
                                // orient truck to move up to destination
                                turnAround();
                                goToNextIntersection();
                                turnRight();
                                // go to intersection just past the destination and turn right
                                while (currentX > destX) {
                                    goToNextIntersection();
                                }
                                turnRight();
                                // go up to intersection of street with destination and turn right
                                addYInstruction(destY - currentY);
                                // if destination on number street turn right and go to destination
                                if (currentX < destX) {
                                    turnRight();
                                    // go straight to destination
                                    addXInstruction(destX - currentX);
                                }
                            }
                            // destination to the right of location
                            else if (currentX < destX) {
                                // go up to intersection of street with destination and turn right
                                while (currentY > destY) {
                                    goToNextIntersection();
                                }
                                turnRight();
                                // go straight to destination
                                addXInstruction(destX - currentX);
                                // if destination on letter street turn right and go to destination
                                if (currentY < destY) {
                                    turnRight();
                                    // go to destination
                                    addYInstruction(destY - currentY);
                                }
                            }
                        }

                        // facing wrong direction
                        else if (isDirectionDown()) {
                            // on correct letter street
                            if (currentX == destX) {
                                turnAround();
                                // go up to intersection of street with destination and turn right
                                while (currentY > destY) {
                                    goToNextIntersection();
                                }
                                turnRight();
                                // go straight to destination
                                addXInstruction(destX - currentX);
                                // if destination on letter street turn right and go to destination
                                if (currentY < destY) {
                                    turnRight();
                                    // go to destination
                                    addYInstruction(destY - currentY);
                                }
                            }
                            // destination to the left of location
                            else if (currentX > destX) {
                                turnRight();
                                // go to intersection just past the destination and turn right
                                while (currentX > destX) {
                                    goToNextIntersection();
                                }
                                turnRight();
                                // go up to intersection of street with destination and turn right
                                addYInstruction(destY - currentY);
                                // if destination on number street turn right and go to destination
                                if (currentX < destX) {
                                    turnRight();
                                    // go straight to destination
                                    addXInstruction(destX - currentX);
                                }
                            }
                            // destination to the right of location
                            else if (currentX < destX) {
                                turnAround();
                                // go up to intersection of street with destination and turn right
                                while (currentY > destY) {
                                    goToNextIntersection();
                                }
                                turnRight();
                                // go straight to destination
                                addYInstruction(destY - currentY);
                                // if destination on letter street turn right and go to destination
                                if (currentY < destY) {
                                    turnRight();
                                    // go to destination
                                    addYInstruction(destY - currentY);
                                }
                            }
                        }

                    }
                    // FIXME Up until here
                    // destination below current location
                    else if (currentY < destY) {
                        // facing wrong direction
                        if (isDirectionUp()) {
                            // on correct letter street
                            if (currentX == destX) {
                                turnAround();
                                // go down to intersection just past destination and turn right
                                while (currentY < destY) {
                                    goToNextIntersection();
                                }
                                turnRight();
                                // go to intersection of street with destination and turn right
                                addXInstruction(destX - currentX);
                                turnRight();
                                // go straight to destination
                                addYInstruction(destY - currentY);
                            }
                            // destination is to the left of location
                            else if (currentX > destX) {
                                turnAround();
                                // go down to intersection just past destination and turn right
                                while (currentY < destY) {
                                    goToNextIntersection();
                                }
                                turnRight();
                                // go to intersection of street with destination and turn right
                                addXInstruction(destX - currentX);
                                // if destination on letter street turn right and go to destination
                                if (currentY != destY) {
                                    turnRight();
                                    // go straight to destination
                                    addYInstruction(destY - currentY);
                                }
                            }
                            // destination is to the right of location
                            else if (currentX < destX) {
                                turnRight();
                                // go to intersection just past destination and turn right
                                while (currentX < destX) {
                                    goToNextIntersection();
                                }
                                // go down to intersection of street with destination and turn right
                                addYInstruction(destY - currentY);
                                // if destination on number street turn right and go to destination
                                if (currentX != destX) {
                                    turnRight();
                                    // go straight to destination
                                    addXInstruction(destX - currentX);
                                }
                            }
                        }

                        // facing correct direction
                        else if (isDirectionDown()) {
                            // on correct letter street
                            if (currentX == destX) {
                                addYInstruction(destY - currentY);
                            }
                            // destination is to the left of location
                            else if (currentX > destX) {
                                // go down to intersection of street with destination and turn right
                                while (currentY < destY) {
                                    goToNextIntersection();
                                }
                                turnRight();
                                // go straight to destination
                                addXInstruction(destX - currentX);
                                // if on letter street
                                if (currentY != destY) {
                                    turnRight();
                                    // go up to destination
                                    addYInstruction(destY - currentY);
                                }
                            }
                            // destination is to the right of location
                            else if (currentX < destX) {
                                // orient truck
                                turnAround();
                                goToNextIntersection();
                                turnRight();
                                // go to intersection just past destination and turn right
                                while (currentX < destX) {
                                    goToNextIntersection();
                                }
                                turnRight();
                                // go to intersection of street with destination and turn right
                                addYInstruction(destY - currentY);
                                //if on number street turn right and go to destination
                                if (currentX != destX) {
                                    turnRight();
                                    // go straight to destination
                                    addXInstruction(destX - currentX);
                                }
                            }
                        }
                    }
            //}

        }


        // testing functions
//        addXInstruction(100);
//        turnRight();
//        turnRight();
//        goToFirstIntersection();
//        turnAround();
//        goToNextIntersection();





        return navigationInstructions;
    }

    // turns truck around by going to next intersection and making a right turn (2x)
    private void turnAround() {
        if (isOnLetterRoad()) {
            turnRight();
            goToNextIntersection();
            turnRight();

        }

        if (isOnNumberRoad()) {
            //turnRight();
            //goToNextIntersection();
            //turnRight();

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
                xDistance = nextIntersection - currentX;
                addXInstruction(xDistance);
            }
            if (isDirectionRight()) {
                // move right to the next intersection
                nextIntersection = currentX + intSpace;
                xDistance = nextIntersection - currentX;
                addXInstruction(xDistance);
            }
        }

        if (isOnLetterRoad()) {
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
    }


    private void goToFirstIntersection() {

        int intersectionX;
        int intersectionY;
        int intSpace = (int) spacing;

        // if on letter road, move in y direction to next intersection
        if (isOnLetterRoad()) {
            // determine the coordinate of the next intersection (must be in the direction that the truck is facing)
            // subtract current location from destination so that the sign of the direction is correct
            if (isDirectionDown()) {
                // find next intersection with larger y coordinate
                intersectionY = (intSpace * (currentY / intSpace)) + intSpace;
                yDistance = intersectionY - currentY;
                addYInstruction(yDistance);

            }
            if (isDirectionUp()) {
                // find next intersection with smaller y coordinate
                intersectionY = (intSpace * (currentY / intSpace));
                yDistance = intersectionY - currentY;
                addYInstruction(yDistance);
            }
        }

        // if on number road, move in x direction to next intersection
        if (isOnNumberRoad()) {
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
    }




    /**
     * helper method that adds an instruction in the x direction and updates the current x coordinate
     */
    private void addXInstruction(int distance) {
        navigationInstructions.add(new int[]{0, distance});
        currentX += distance;
    }

    /**
     * helper method that adds an instruction in the y direction and updates the current x coordinate
     */
    private void addYInstruction(int distance) {
        navigationInstructions.add(new int[]{1, distance});
        currentY += distance;
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
        return ((currentY % spacing) == 0);
    }

    private boolean isOnLetterRoad() {
        /**
         * helper method that determines if the truck is currently on a letter named road
         * @returns: a boolean value, true if the truck is on a letter named road
         */
        return ((currentX % spacing) == 0);
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


}
