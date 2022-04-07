/**
 * Author: Jackson Wagner
 * Sandwiches should be instantiated before any Condiments are attached
 */

public class Italian extends Sandwich{
    public Italian(){
        /**
         * Generic Italian Sandwich Object
         */
        description = "Italian Sandwich";
    }

    public void prepare() {
        /**
         * Modeling of the Making of the Sandwich by printing the status of the sandwich
         */
        System.out.println("Making " + this.getDescription());
    }

    @Override
    public double cost() {
        /**
         * @returns the cost of the sandwich
         */
        return SimSettings.ITALIAN_SANDWICH_COST;
    }
}
